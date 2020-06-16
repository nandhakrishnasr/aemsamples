package com.aem.sample.core.models;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class TitleTextImageModelTest2 {
	
	private final AemContext ctx = new AemContext();
	
	private TitleTextImageModel titleTextImageModel;
	
	private CTALinks ctaLinks;
	
	@BeforeEach
	void setUp() throws Exception {
		ctx.addModelsForClasses(TitleTextImageModel.class);
        
	    ctx.load().json("/ttac.json", "/content");
	}

	@Test
	void TestText() throws Exception {
		final String expected = "Testing";
	    ctx.currentResource("/content/title_text_asset");
	    titleTextImageModel = ctx.currentResource().adaptTo(TitleTextImageModel.class);
	    String actual = titleTextImageModel.getTitle();
	    assertEquals(expected, actual);
	}
	
	@Test
	void getFileReferenceTest() throws Exception {
		final String expected = "/content/dam/core-components-examples/library/sample-assets/mountain-range.jpg";
	    ctx.currentResource("/content/title_text_asset/dynamic-image");
	    titleTextImageModel = ctx.currentResource().adaptTo(TitleTextImageModel.class);
	    String actual = titleTextImageModel.getFileReference();
	    assertEquals(expected, actual);
		
	}
	
	@Test
	void getAltTest() throws Exception {
		
		final String expected = "test";
	    ctx.currentResource("/content/title_text_asset");
	    titleTextImageModel = ctx.currentResource().adaptTo(TitleTextImageModel.class);
	    String actual = titleTextImageModel.getAlt();
	    assertEquals(expected, actual);
		
	}
	
	@Test
	void getLinkTest() throws Exception {
		
		final String expected = "/content/atvi/support/web/en/homepage/support/onlineservices";
	    //final String expected1 = "button";
		ctx.currentResource("/content/title_text_asset");
	    titleTextImageModel = ctx.currentResource().adaptTo(TitleTextImageModel.class);
	    String actual = titleTextImageModel.getLink();
	    //String actual1 = titleTextImageModel.get
	    assertEquals(expected, actual);
	}
	
	@Test
	void getCTATest() throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("target", "true");
        map.put("hexaCode", "#00000");
        map.put("path","/content/wknd/en/salesforcedemo");
        map.put("displayFormat", "button");
        ctx.build().resource("/content/title_text_asset").siblingsMode().resource("cta",map);
        titleTextImageModel = ctx.resourceResolver().getResource("/content/title_text_asset").adaptTo(TitleTextImageModel.class);
        titleTextImageModel.getCtaLinks().get(0);
        System.out.println(titleTextImageModel.getCtaLinks().get(0).getCtaText());
        assertEquals(map.get("target"), titleTextImageModel.getCtaLinks().get(0).getTarget());
        assertEquals(map.get("hexaCode"), titleTextImageModel.getCtaLinks().get(0).getHexaCode());
        assertEquals(map.get("path"), titleTextImageModel.getCtaLinks().get(0).getPath());
        
	}
	
	@Test
	void getCTADisplayFormatCheck() throws Exception {
		final String expected = "button";
		ctx.currentResource("/content/title_text_asset");
		ctaLinks = ctx.currentResource().adaptTo(CTALinks.class);
		String actual = ctaLinks.getDisplayFormat();
		assertEquals(expected,actual);
	}
}
