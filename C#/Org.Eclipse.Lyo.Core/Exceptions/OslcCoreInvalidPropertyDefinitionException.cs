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
    public class OslcCoreInvalidPropertyDefinitionException : OslcCoreApplicationException
    {
        public OslcCoreInvalidPropertyDefinitionException(Type resourceType, MethodInfo method, OslcPropertyDefinition oslcPropertyDefinition) :
            base(MESSAGE_KEY, new object[] { resourceType.Name, method.Name, oslcPropertyDefinition.value })
        {
            this.method = method;
            this.oslcPropertyDefinition = oslcPropertyDefinition;
            this.resourceType = resourceType;
        }

        private static readonly string MESSAGE_KEY = "InvalidPropertyDefinitionException";

        private MethodInfo              method;
        private OslcPropertyDefinition  oslcPropertyDefinition;
        private Type                    resourceType;
    }
}
