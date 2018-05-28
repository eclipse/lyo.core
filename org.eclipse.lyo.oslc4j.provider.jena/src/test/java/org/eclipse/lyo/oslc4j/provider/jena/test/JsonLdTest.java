package org.eclipse.lyo.oslc4j.provider.jena.test;

import com.github.jsonldjava.utils.Obj;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.jena.ext.com.google.common.collect.ImmutableList;
import org.apache.wink.common.internal.MultivaluedMapImpl;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.provider.jena.OslcJsonLdArrayProvider;
import org.eclipse.lyo.oslc4j.provider.jena.OslcJsonLdCollectionProvider;
import org.eclipse.lyo.oslc4j.provider.jena.OslcJsonLdProvider;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 2018-03-03
 *
 * @author Andrew Berezovskyi (andriib@kth.se)
 * @version $version-stub$
 * @since 0.0.1
 */
public class JsonLdTest {
    @Test
    @SuppressWarnings({
                              "unchecked",
                              "rawtypes"
                      })
    public void testContentTypeTurtleUTF8() throws Exception {
        OslcJsonLdProvider provider = new OslcJsonLdProvider();

        InputStream is = ServiceProviderTest.class.getResourceAsStream("/provider.jsonld");
        assertNotNull("Could not read file: provider.jsonld", is);

        ServiceProvider p = (ServiceProvider) provider.readFrom((Class) ServiceProvider.class,
                                                                null,
                                                                ServiceProvider.class.getAnnotations(),
                                                                OslcMediaType.APPLICATION_JSON_LD_TYPE,
                                                                null,
                                                                is);
        assertNotNull("Provider was not read", p);
    }

    @Test
    public void testWrite() throws Exception {
        ServiceProvider sp = new ServiceProvider();
        sp.setDescription("Hello world");
        OslcJsonLdProvider provider = new OslcJsonLdProvider();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        provider.writeTo(sp, ServiceProvider.class, ServiceProvider.class, ServiceProvider.class
                .getAnnotations(), OslcMediaType.APPLICATION_JSON_LD_TYPE, new
                MultivaluedMapImpl<>(), outputStream);

        final String jsonLD = outputStream.toString("UTF-8");

        assertTrue("Provider was not read", jsonLD.contains("Hello world"));

    }

    @Test
    public void testWriteArray() throws Exception {
        final OslcJsonLdArrayProvider provider = new OslcJsonLdArrayProvider();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ServiceProvider sp = new ServiceProvider();
        sp.setDescription("Hello world");
        final Object[] objects = {sp};
        provider.writeTo(objects,
                         objects.getClass(),
                         ServiceProvider.class,
                         ServiceProvider.class.getAnnotations(),
                         OslcMediaType.APPLICATION_JSON_LD_TYPE,
                         new MultivaluedMapImpl<>(),
                         outputStream);

        final String jsonLD = outputStream.toString("UTF-8");

        assertTrue("Provider was not read", jsonLD.contains("Hello world"));
    }

    @Test
    @Ignore("TypeVariableImpl cannot be cast to java.lang.Class")
    public void testWriteCollection() throws Exception {
        final OslcJsonLdCollectionProvider provider = new OslcJsonLdCollectionProvider();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ServiceProvider sp = new ServiceProvider();
        sp.setDescription("Hello world");
        final Collection<ServiceProvider> objects = ImmutableList.of(sp);
        provider.writeTo(
                new ArrayList<Object>(objects),
                objects.getClass(),
                objects.getClass().getGenericSuperclass(),
                ServiceProvider.class.getAnnotations(),
                OslcMediaType.APPLICATION_JSON_LD_TYPE,
                new MultivaluedMapImpl<>(),
                outputStream);

        final String jsonLD = outputStream.toString("UTF-8");

        assertTrue("Provider was not read", jsonLD.contains("Hello world"));
    }


}
