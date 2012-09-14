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
 *
 *     Steve Pitschke  - initial API and implementation
 *******************************************************************************/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Org.Eclipse.Lyo.Core.Attribute
{
    [System.AttributeUsage(System.AttributeTargets.Method)
    ]
    public class OslcValueShape : System.Attribute
    {
        /**
         * If the value-type is a resource type, then Property MAY provide a shape value
         * to indicate the Resource Shape that applies to the resource.
         */
        public readonly string value;

        public OslcValueShape(string value)
        {
            this.value = value;
        }
    }
}
