package com.aem.sample.core.models;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.resourceresolver.MockResourceResolverFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;


public class ServiceUtilsTest {
	
	//@Rule
	//public SlingContext context = new SlingContext(ResourceResolverType.JCR_OAK);
	/*
	 * @Rule public final AemContext context = new AemContext();
	 */
	@InjectMocks
	ServiceUtils serviceUtils;
	
	
	//private MockResourceResolverFactory factory;
	
	private ResourceResolver resolver;
    private Resource resource;
    private Page page;
	
	//private final AemContext context = new AemContext();
	
	//private final SlingContext context = new SlingContext();

	
	@Before
	public void setUp() throws Exception {
		
		
		//Resource resource = context.resourceResolver().getResource("/content/sample/en");
		
		//factory = new MockResourceResolverFactory();
		
	}


	/*
	 * public static String appendLinkExtension(ResourceResolver resolver, String
	 * url) { if (url == null) return null;
	 * 
	 * Resource resource = resolver.getResource(url); String result = url; if
	 * (resource != null) { Page page = resource.adaptTo(Page.class); if (page !=
	 * null) { result = url + ".html"; } } return result; }
	 */

	@Test
    public void testAppendLinkExtension() throws Exception{
        String url = "/content/sample/en.html";
        resolver = Mockito.mock(ResourceResolver.class);
        resource = Mockito.mock(Resource.class);
        page = Mockito.mock(Page.class);
        //ResourceResolver resolver = Mockito.mock(ResourceResolver.class);
        //ResourceResolver resolver = factory.getResourceResolver(null);
        //Mockito.when(resource.getResourceResolver()).thenReturn(resolver);
        //Page page = resource.adaptTo(Page.class);
        Mockito.when(resolver.getResource(url)).thenReturn(resource);
        Mockito.when(resource.getResourceResolver()).thenReturn(resolver);
        Mockito.when(resource.adaptTo(Page.class)).thenReturn(page);
        String actual = ServiceUtils.appendLinkExtension(resolver, url);
        //assertEquals("/content/sample/en.html",actual);
		
	}
	}

