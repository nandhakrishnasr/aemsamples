package com.aem.sample.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.wcm.api.Page;

public class ServiceUtils {
	 /**
     * Append the html extension to a {@code url}.
     *
     * @param resolver ResourceResolver
     * @param url URL to evaluate
     * @return URL with extension (if needed)
     */
    public static String appendLinkExtension(ResourceResolver resolver, String url) {
        if (url == null) return null;

        Resource resource = resolver.getResource(url);
        
        String result = url;
        if (resource != null) {
            Page page = resource.adaptTo(Page.class);
            if (page != null) {
                result = url + ".html";
            }
        }
        return result;
    }
}
