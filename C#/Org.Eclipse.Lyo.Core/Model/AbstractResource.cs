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
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Org.Eclipse.Lyo.Core.Model
{
    public abstract class AbstractResource : IExtendedResource
    {
        private Uri about;
        private ICollection<Uri> types = (ICollection<Uri>)new List<Uri>();
        private IDictionary<QName, Object> extendedProperties = new Dictionary<QName, Object>();

        protected AbstractResource(Uri about)
        {
            this.about = about;
        }

        protected AbstractResource()
        {
        }

        public Uri GetAbout()
        {
		    return about;
	    }

        public void SetAbout(Uri about)
        {
		    this.about = about;
	    }
    
	    public void SetExtendedProperties(IDictionary<QName, Object> properties)
	    {
		    this.extendedProperties = properties;
	    }

	    public IDictionary<QName, Object> GetExtendedProperties()
	    {
		    return extendedProperties;
	    }

        public ICollection<Uri> GetTypes()
        {
    	    return types;
        }

        public void SetTypes(ICollection<Uri> types)
        {
    	    this.types = types;
        }

        public void AddType(Uri type)
        {
    	    this.types.Add(type);
        }
    }
}
