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

using Org.Eclipse.Lyo.Core.Attribute;

namespace Org.Eclipse.Lyo.Core.Model
{
    [OslcNamespace(OslcConstants.OSLC_CORE_NAMESPACE)]
    [OslcResourceShape(title = "OSLC Dialog Resource Shape", describes = new string[] { OslcConstants.TYPE_DIALOG })]
    public class Dialog : AbstractResource 
    {
        private SortedSet<Uri> resourceTypes = new SortedSet<Uri>();
        private SortedSet<Uri> usages = new SortedSet<Uri>();

        private Uri dialog;
	    private String hintHeight;
	    private String hintWidth;
	    private String label;
	    private String title;

	    public Dialog() : base()
        {
	    }

	    public Dialog(String title, Uri dialog) : this()
        {
		    this.title = title;
		    this.dialog = dialog;
	    }

        public void AddResourceType(Uri resourceType) {
            this.resourceTypes.Add(resourceType);
        }

        public void AddUsage(Uri usage) {
            this.usages.Add(usage);
        }

	    [OslcDescription("The Uri of the dialog")]
	    [OslcOccurs(Occurs.ExactlyOne)]
	    [OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "dialog")]
	    [OslcReadOnly]
        [OslcTitle("Dialog")]
	    public Uri GetDialog() {
	        return dialog;
	    }

	    [OslcDescription("Values MUST be expressed in relative length units as defined in the W3C Cascading Style Sheets Specification (CSS 2.1) Em and ex units are interpreted relative to the default system font (at 100% size)")]
	    [OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "hintHeight")]
	    [OslcReadOnly]
        [OslcTitle("Hint Height")]
	    public String GetHintHeight() {
		    return hintHeight;
	    }

	    [OslcDescription("Values MUST be expressed in relative length units as defined in the W3C Cascading Style Sheets Specification (CSS 2.1) Em and ex units are interpreted relative to the default system font (at 100% size)")]
	    [OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "hintWidth")]
	    [OslcReadOnly]
        [OslcTitle("Hint Width")]
	    public String GetHintWidth() {
		    return hintWidth;
	    }

	    [OslcDescription("Very short label for use in menu items")]
	    [OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "label")]
	    [OslcReadOnly]
        [OslcTitle("Label")]
	    public String GetLabel() {
		    return label;
	    }

	    [OslcDescription("The expected resource type Uri for the resources that will be returned when using this dialog. These would be the Uris found in the result resource's rdf:type property")]
	    [OslcName("resourceType")]
	    [OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "resourceType")]
	    [OslcReadOnly]
        [OslcTitle("Resource Types")]
	    public Uri[] GetResourceTypes() {
	        return resourceTypes.ToArray();
	    }

	    [OslcDescription("Title string that could be used for display")]
	    [OslcOccurs(Occurs.ExactlyOne)]
	    [OslcPropertyDefinition(OslcConstants.DCTERMS_NAMESPACE + "title")]
	    [OslcReadOnly]
        [OslcTitle("Title")]
        [OslcValueType(ValueType.XMLLiteral)]
	    public String GetTitle() {
		    return title;
	    }

	    [OslcDescription("An identifier Uri for the domain specified usage of this dialog. If a service provides multiple selection or creation dialogs, it may designate the primary or default one that should be used with a property value of http://open-services/ns/core#default")]
	    [OslcName("usage")]
	    [OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "usage")]
	    [OslcReadOnly]
        [OslcTitle("Usages")]
	    public Uri[] GetUsages() {
	        return usages.ToArray();
	    }

	    public void SetDialog(Uri dialog) {
	        this.dialog = dialog;
	    }

	    public void SetHintHeight(String hintHeight) {
		    this.hintHeight = hintHeight;
	    }

	    public void SetHintWidth(String hintWidth) {
		    this.hintWidth = hintWidth;
	    }

	    public void SetLabel(String label) {
		    this.label = label;
	    }

	    public void SetResourceTypes(Uri[] resourceTypes) {
	        this.resourceTypes.Clear();
	        if (resourceTypes != null) {
                this.resourceTypes.AddAll(resourceTypes);
            }
	    }

	    public void SetTitle(String title) {
		    this.title = title;
	    }

	    public void SetUsages(Uri[] usages) {
	        this.usages.Clear();
	        if (usages != null) {
                this.usages.AddAll(usages);
            }
	    }
    }
}
