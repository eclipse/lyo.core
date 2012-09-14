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
using System.Reflection;
using System.Text;

using Org.Eclipse.Lyo.Core.Attribute;

namespace Org.Eclipse.Lyo.Core.Exceptions
{
    public class OslcCoreInvalidPropertyTypeException : OslcCoreApplicationException
    {
        public OslcCoreInvalidPropertyTypeException(Type resourceType, MethodInfo method, Type returnType) :
            base(MESSAGE_KEY, new Object[] { resourceType.Name, method.Name, returnType.Name })
        {
            this.method = method;
            this.resourceType = resourceType;
            this.returnType = returnType;
        }

        public MethodInfo GetMethod()
        {
            return method;
        }

        public Type GetResourceClass()
        {
            return resourceType;
        }

        private static readonly String MESSAGE_KEY = "InvalidPropertyTypeException";

        private MethodInfo method;
        private Type resourceType;
        private Type returnType;
    }
}
