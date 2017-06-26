/*******************************************************************************
 * Copyright (c) 2013 KTH Royal Institute of Technology.
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
 *	  Andrew Berezovskyi - Initial implementation
 *******************************************************************************/

package org.eclipse.lyo.core.trs;

import com.hp.hpl.jena.rdf.model.Model;
import java.net.URI;
import org.eclipse.lyo.oslc4j.provider.jena.JenaModelHelper;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * TRS resource unit tests with JenaModelHelper.
 * @author Andrew Berezovskyi (andriib@kth.se)
 * @version $version-stub$
 * @since 2.2.0
 */
public class TrackedResourceSetTest {
    @Test
    public void emptyTrsIsUnmarshalled() throws Exception {
        final AbstractChangeLog changeLog = new ChangeLog();
        changeLog.setAbout(URI.create("http://example.com/dummy/changelog"));

        final TrackedResourceSet trsExpected = new TrackedResourceSet();
        trsExpected.setBase(URI.create("http://example.com/dummy"));
        trsExpected.setChangeLog(changeLog);

        final Model model = JenaModelHelper.createJenaModel(new Object[]{trsExpected});
        final Object[] objects = JenaModelHelper.fromJenaModel(model, TrackedResourceSet.class);
        final TrackedResourceSet trsJena = (TrackedResourceSet) objects[0];

        assertEquals("TRS Base URI is preserved", trsExpected.getBase(), trsJena.getBase());
        assertEquals("TRS Changelog About URI is preserved", trsExpected.getChangeLog().getAbout(),
                trsJena.getChangeLog().getAbout());
    }
}
