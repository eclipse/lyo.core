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
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Formatting;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

using Org.Eclipse.Lyo.Core.Model;

namespace Org.Eclipse.Lyo.Client
{
    public sealed class OslcRestClient
    {
        public const int DEFAULT_READ_TIMEOUT = 60000;

        private readonly ISet<MediaTypeFormatter>   formatters;
        private readonly String                     uri;
        private readonly HttpClient                 client;
        private readonly String                     mediaType;
        private readonly int                        readTimeout;

	    public OslcRestClient(ISet<MediaTypeFormatter>  formatters,
	                          String                    uri,
	                          String                    mediaType,
	                          int                       readTimeout)
	    {
	        this.formatters  = formatters;
		    this.uri         = uri;
		    this.mediaType   = mediaType;
		    this.readTimeout = readTimeout;

		    this.client = new HttpClient();

            this.client.Timeout = new TimeSpan(TimeSpan.TicksPerMillisecond * readTimeout);
	    }

	    public OslcRestClient(ISet<MediaTypeFormatter>  formatters,
                              String                    uri,
                              String                    mediaType) :
            this(formatters, uri, mediaType, DEFAULT_READ_TIMEOUT)
        {
        }

        public OslcRestClient(ISet<MediaTypeFormatter>  formatters,
                              String                    uri,
                              int                       timeout) :
            this(formatters, uri, OslcMediaType.APPLICATION_RDF_XML, timeout)
        {
        }

        public OslcRestClient(ISet<MediaTypeFormatter>  formatters,
                              String                    uri) :
            this(formatters, uri, OslcMediaType.APPLICATION_RDF_XML, DEFAULT_READ_TIMEOUT)
        {
        }

        public OslcRestClient(ISet<MediaTypeFormatter>  formatters,
                              Uri                       uri,
                              String                    mediaType,
                              int                       readTimeout) :
            this(formatters, uri.ToString(), mediaType, readTimeout)
        {
        }

	    public OslcRestClient(ISet<MediaTypeFormatter>  formatters,
                              Uri                       uri,
                              String                    mediaType) :
            this(formatters, uri.ToString(), mediaType, DEFAULT_READ_TIMEOUT)
        {
        }

        public OslcRestClient(ISet<MediaTypeFormatter>  formatters,
                              Uri                       uri,
                              int                       timeout) :
            this(formatters, uri.ToString(), OslcMediaType.APPLICATION_RDF_XML, timeout)
        {
        }

	    public OslcRestClient(ISet<MediaTypeFormatter>  formatters,
                              Uri                       uri) :
            this(formatters, uri.ToString(), OslcMediaType.APPLICATION_RDF_XML, DEFAULT_READ_TIMEOUT)
        {
        }

	    public ISet<MediaTypeFormatter> GetFormatters()
	    {
	        return formatters;
	    }

	    public String GetMediaType()
	    {
	        return mediaType;
	    }

	    public int GetReadTimeout()
	    {
	        return readTimeout;
	    }

        public String GetUri()
        {
            return uri;
        }

	    public HttpClient GetClient()
	    {
		    return client;
	    }

	    public T GetOslcResource<T>() where T : class
        {
            this.client.DefaultRequestHeaders.Clear();
            this.client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue(mediaType));

    	    HttpResponseMessage response = client.GetAsync(uri).Result;
            HttpStatusCode statusCode = response.StatusCode;

            switch (statusCode)
            {
                case HttpStatusCode.OK:
                    return response.Content.ReadAsAsync<T>(formatters).Result;
                case HttpStatusCode.NoContent:
                case HttpStatusCode.NotFound:
                case HttpStatusCode.Gone:
                    return null;
                default:
                    throw new HttpRequestException(response.ReasonPhrase);
            }
        }

	    public T[] GetOslcResources<T>()
	    {
            this.client.DefaultRequestHeaders.Clear();
            this.client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue(mediaType));

    	    HttpResponseMessage response = client.GetAsync(uri).Result;
            HttpStatusCode statusCode = response.StatusCode;

            switch (statusCode)
            {
                case HttpStatusCode.OK:
                    T[] dummy = new T[0];
                    return (T[])response.Content.ReadAsAsync(dummy.GetType(), formatters).Result;
                case HttpStatusCode.NoContent:
                case HttpStatusCode.NotFound:
                case HttpStatusCode.Gone:
                    return null;
                default:
                    throw new HttpRequestException(response.ReasonPhrase);
            }
	    }

        public T AddOslcResource<T>(T oslcResource)
	    {
            this.client.DefaultRequestHeaders.Clear();
            this.client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("*/*"));

            MediaTypeHeaderValue mediaTypeValue = new MediaTypeHeaderValue(mediaType);
            MediaTypeFormatter formatter =
                new MediaTypeFormatterCollection(formatters).FindWriter(oslcResource.GetType(), mediaTypeValue);
            ObjectContent<T> content = new ObjectContent<T>(oslcResource, formatter);

            content.Headers.ContentType = mediaTypeValue;

            return client.PostAsync(uri, content).ContinueWith(response =>
                {
                    HttpStatusCode status = response.Result.StatusCode;

                    if (status != HttpStatusCode.Created && status != HttpStatusCode.OK)
                    {
                        throw new HttpRequestException(response.Result.ReasonPhrase);
                    }

                    return response;
                }).Result.Result.Content.ReadAsAsync<T>(formatters).Result;
            }

	    public HttpResponseMessage AddOslcResourceReturnClientResponse(Object oslcResource)
	    {
            this.client.DefaultRequestHeaders.Clear();
            this.client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("*/*"));

            MediaTypeHeaderValue mediaTypeValue = new MediaTypeHeaderValue(mediaType);
            MediaTypeFormatter formatter =
                new MediaTypeFormatterCollection(formatters).FindWriter(oslcResource.GetType(), mediaTypeValue);
            ObjectContent content = new ObjectContent(oslcResource.GetType(), oslcResource, formatter);

            content.Headers.ContentType = mediaTypeValue;

            HttpResponseMessage response = client.PostAsync(uri, content).Result;
            HttpStatusCode statusCode = response.StatusCode;

            switch (statusCode)
            {
                case HttpStatusCode.OK:
                case HttpStatusCode.Created:
                    return response;
                default:
                    throw new HttpRequestException(response.ReasonPhrase);
            }
	    }

        public HttpResponseMessage UpdateOslcResourceReturnClientResponse(Object oslcResource)
        {
            this.client.DefaultRequestHeaders.Clear();
            this.client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("*/*"));

            MediaTypeFormatter formatter =
                new MediaTypeFormatterCollection(formatters).FindWriter(oslcResource.GetType(), new MediaTypeHeaderValue(mediaType));
            ObjectContent content = new ObjectContent(oslcResource.GetType(), oslcResource, formatter);

            content.Headers.ContentType = new MediaTypeHeaderValue(mediaType);

            HttpResponseMessage response = client.PutAsync(uri, content).Result;

            return response;
        }

        public HttpResponseMessage RemoveOslcResourceReturnClientResponse()
        {
            this.client.DefaultRequestHeaders.Clear();
            this.client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("*/*"));

            HttpResponseMessage response = client.DeleteAsync(uri).Result;

            return response;
        }
    }
}
