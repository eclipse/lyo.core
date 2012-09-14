﻿/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *  
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Steve Pitschke  - initial API and implementation
 *******************************************************************************/

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Net.Http.Headers;
using System.Numerics;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

using Org.Eclipse.Lyo.Core.Attribute;
using Org.Eclipse.Lyo.Core.Model;

using log4net;

using VDS.RDF;
using VDS.RDF.Parsing;
using VDS.RDF.Writing;

namespace Org.Eclipse.Lyo.Core.DotNetRdfProvider
{
    public class RdfXmlMediaTypeFormatter : MediaTypeFormatter
    {
        public RdfXmlMediaTypeFormatter()
        {
            SupportedMediaTypes.Add(new MediaTypeHeaderValue(RDF_XML));
        }

        public override bool CanWriteType(Type type)
        {
            if (IsSinglton(type))
            {
                return true;
            }
            
            return GetMemberType(type) != null;
        }

        public override Task  WriteToStreamAsync(
            Type type,
            object value,
            Stream writeStream,
            HttpContent content,
            TransportContext transportContext
        )
        {
            return Task.Factory.StartNew(() =>
                {
                    IGraph graph;

                    if (type.GetCustomAttributes(typeof(OslcResourceShape), false).Length > 0)
                    {
                        graph = DotNetRdfHelper.CreateDotNetRdfGraph(new object[] { value });
                    }
                    else
                    {
                        graph = DotNetRdfHelper.CreateDotNetRdfGraph(new EnumerableWrapper(value));
                    }

                    RdfXmlWriter rdfXmlWriter = new RdfXmlWriter();

                    rdfXmlWriter.UseDtd = false;
                    rdfXmlWriter.PrettyPrintMode = false;
                    rdfXmlWriter.CompressionLevel = 20;

                    StreamWriter streamWriter = new StreamWriter(writeStream);

                    rdfXmlWriter.Save(graph, streamWriter);
                });
        }

        public override bool CanReadType(Type type)
        {
            if (IsSinglton(type))
            {
                return true;
            }

            return GetMemberType(type) != null;
        }

        public override Task<object> ReadFromStreamAsync(
            Type type,
            Stream readStream,
            HttpContent content,
            IFormatterLogger formatterLogger
        )
        {
            var tcs = new TaskCompletionSource<object>();

            if (content != null && content.Headers != null && content.Headers.ContentLength == 0) return null;

            try
            {
                RdfXmlParser rdfXmlParser = new RdfXmlParser();
                IGraph graph = new Graph();
                StreamReader streamReader = new StreamReader(readStream);

                using (streamReader)
                {
                    rdfXmlParser.Load(graph, streamReader);

                    bool isSingleton = IsSinglton(type);
                    object output = DotNetRdfHelper.FromDotNetRdfGraph(graph, isSingleton ? type : GetMemberType(type));

                    if (isSingleton)
                    {
                        bool haveOne = (int)output.GetType().GetProperty("Count").GetValue(output, null) > 0;

                        tcs.SetResult(haveOne ? output.GetType().GetProperty("Item").GetValue(output, new object[] { 0 }): null);
                    }
                    else if (type.IsArray)
                    {
                        tcs.SetResult(output.GetType().GetMethod("ToArray", Type.EmptyTypes).Invoke(output, null));
                    }
                    else
                    {
                        tcs.SetResult(output);
                    }
                }
            }
            catch (Exception e)
            {
                if (formatterLogger == null) throw;

                formatterLogger.LogError(String.Empty, e.Message);

                tcs.SetResult(GetDefaultValueForType(type));
            }

            return tcs.Task;
        }

        private bool IsSinglton(Type type)
        {
            return type.GetCustomAttributes(typeof(OslcResourceShape), false).Length > 0;
        }

        private Type GetMemberType(Type type)
        {
            if (InheritedGenericInterfacesHelper.ImplementsGenericInterface(typeof(IEnumerable<>), type))
            {
                Type[] interfaces = type.GetInterfaces();

                foreach (Type interfac in interfaces)
                {
                    if (interfac.GetGenericTypeDefinition() == typeof(IEnumerable<object>).GetGenericTypeDefinition())
                    {
                        Type memberType = interfac.GetGenericArguments()[0];

                        if (memberType.GetCustomAttributes(typeof(OslcResourceShape), false).Length > 0)
                        {
                            return memberType;
                        }

                        return null;
                    }
                }
            }

            return null;
        }

        private readonly string RDF_XML = "application/rdf+xml";
    }
}
