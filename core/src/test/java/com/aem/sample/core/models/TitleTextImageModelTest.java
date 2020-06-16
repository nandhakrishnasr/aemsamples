package com.aem.sample.core.models;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import io.wcm.testing.mock.aem.junit5.AemContext;


public class TitleTextImageModelTest {
	
	@Rule
	public final AemContext ctx = new AemContext();
	
	@InjectMocks
	TitleTextImageModel titletextimagemodel;
	
	private String alt;

	@Before
	public void setUp() throws Exception {
		ctx.addModelsForClasses(TitleTextImageModel.class);
        
	    ctx.load().json("/SampleProjectPerf/core/src/main/ttac.json", "/content");
	}

		@Test
		public void TestText() throws Exception {
		    final String expected = "MAKE THE ULTIMATE POWER MOVE.";
		 
		    ctx.currentResource("/content");
		    TitleTextImageModel titleTextImageModel = ctx.request().adaptTo(TitleTextImageModel.class);
		     
		    String actual = titleTextImageModel.getTitle();
		    assertEquals(expected, actual);
		}
		@Test
		public void TestFileRefference() throws Exception {
		    //final String expected = "MAKE THE ULTIMATE POWER MOVE.";
		 
		    ctx.currentResource("/content");
		    TitleTextImageModel titleTextImageModel = ctx.request().adaptTo(TitleTextImageModel.class);
		     
		    String actual = titleTextImageModel.getFileReference();
		    //assertEquals(expected, actual);
		}
		@Test
		public void TestCTALinks() throws Exception {
		    ctx.currentResource("/content");
		    TitleTextImageModel titleTextImageModel = ctx.request().adaptTo(TitleTextImageModel.class);
		     
		    List<CTALinks> actual = titleTextImageModel.getCtaLinks();
		    
		}
		@Test
		public void TestAlt() throws Exception {
		    //final String expected = "MAKE THE ULTIMATE POWER MOVE.";
		 
		    ctx.currentResource("/content");
		    TitleTextImageModel titleTextImageModel = ctx.request().adaptTo(TitleTextImageModel.class);
		     
		    String actual = titleTextImageModel.getAlt();
		    //assertEquals(expected, actual);
		}
		@Test
		public void TestLinks() throws Exception {
		    //final String expected = "MAKE THE ULTIMATE POWER MOVE.";
		 
		    ctx.currentResource("/content");
		    TitleTextImageModel titleTextImageModel = ctx.request().adaptTo(TitleTextImageModel.class);
		     
		    String actual = titleTextImageModel.getLink();
		    //assertEquals(expected, actual);
		}
	}


