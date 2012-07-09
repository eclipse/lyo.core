/*******************************************************************************
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
 *    Steve Pitschke - initial API and implementation
 *******************************************************************************/
package org.eclipse.lyo.core.query.test;

import java.util.Map;

import org.eclipse.lyo.core.query.OrderByClause;
import org.eclipse.lyo.core.query.ParseException;
import org.eclipse.lyo.core.query.QueryUtils;

/**
 * Basic tests of oslc.orderBy clause parsing
 */
public class BasicOrderByTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String prefixes = "qm=<http://qm.example.com/ns>," +
            "olsc=<http://open-services.net/ns/core#>";
       
        String[] expressions = {
                "+gm:priority",
                "+gm:priority,-oscl:name",
                "gm:tested_by{+oslc:description}",
                "?qm:blah"
            };
        
        for (String expression : expressions) {
        
            try {
                
                Map<String, String> prefixMap =
                    QueryUtils.parsePrefixes(prefixes);
                OrderByClause orderByClause =
                    QueryUtils.parseOrderBy(expression, prefixMap);
                
                System.out.println(orderByClause);
                
            } catch (ParseException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
