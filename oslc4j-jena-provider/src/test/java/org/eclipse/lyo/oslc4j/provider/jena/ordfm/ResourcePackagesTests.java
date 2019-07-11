package org.eclipse.lyo.oslc4j.provider.jena.ordfm;

import java.util.Optional;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.eclipse.lyo.oslc4j.provider.jena.resources.Cat;
import org.eclipse.lyo.oslc4j.provider.jena.resources.Pet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link ResourcePackages}
 * @author rherrera
 */
public class ResourcePackagesTests {

    private Resource resource;

    @Before
    public void init() {
        Model model = ModelFactory.createDefaultModel();
        resource = model.createResource("http://example.com/1");
    }

    @Test
    public void testMapPackage() {
        ResourcePackages.mapPackage(Pet.class.getPackage());
        Assert.assertEquals(1, ResourcePackages.SCANNED_PACKAGES.size());
        Assert.assertEquals(6, ResourcePackages.TYPES_MAPPINGS.keySet().size());
    }

    @Test
    public void testGetClassOf_noMapping() {
        Assert.assertEquals(false, ResourcePackages.getClassOf(resource).isPresent());
    }

    @Test
    public void testGetClassOf_noResourceType() {
        ResourcePackages.mapPackage(Pet.class.getPackage());
        Assert.assertEquals(false, ResourcePackages.getClassOf(resource).isPresent());
    }

    @Test
    public void testGetClassOf_simpleResourceType() {
        ResourcePackages.mapPackage(Pet.class.getPackage());
        resource.addProperty(RDF.type, ResourceFactory.createResource("http://locahost:7001/vocabulary/Cat"));
        Optional<Class<?>> mappedClass = ResourcePackages.getClassOf(resource);
        Assert.assertEquals(true, mappedClass.isPresent());
        Assert.assertEquals(Cat.class, mappedClass.get());
    }

    @Test(expected = IllegalStateException.class)
    public void testGetClassOf_multipleResourceTypes() {
        ResourcePackages.mapPackage(Pet.class.getPackage());
        resource.addProperty(RDF.type, ResourceFactory.createResource("http://locahost:7001/vocabulary/Cat"));
        resource.addProperty(RDF.type, ResourceFactory.createResource("http://locahost:7001/vocabulary/Dog"));
        ResourcePackages.getClassOf(resource);
    }

    @Test
    public void testGetClassOf_mostConcreteResourceType() {
        ResourcePackages.mapPackage(Pet.class.getPackage());
        resource.addProperty(RDF.type, ResourceFactory.createResource("http://locahost:7001/vocabulary/Cat"));
        resource.addProperty(RDF.type, ResourceFactory.createResource("http://locahost:7001/vocabulary/Animal"));
        Optional<Class<?>> mappedClass = ResourcePackages.getClassOf(resource);
        Assert.assertEquals(true, mappedClass.isPresent());
        Assert.assertEquals(Cat.class, mappedClass.get());
    }

}
