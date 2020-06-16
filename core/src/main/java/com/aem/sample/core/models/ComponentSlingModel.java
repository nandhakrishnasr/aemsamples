package com.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

/**
 * Sling Model based implementation prerequisites.
 *
 */
public abstract class ComponentSlingModel {


    @Inject
    @Required
    @SlingObject
    private Resource resource;

    @Inject
    @Required
    @SlingObject
    private ResourceResolver resourceResolver;

    @Inject
    @Required
    @OSGiService
    private SlingSettingsService slingSettingsService;

    @Inject
    @Optional
    @SlingObject
    private SlingHttpServletRequest request;

    @Inject
    @Optional
    private Page currentPage;

    /** lazily loaded field */
    private Logger logger;

    /**
     * Initialization method necessary for Sling Sling models.
     */
    protected ComponentSlingModel() {
        // All fields will be set dynamically via Sling Models.
    }

    
    public Resource getResource() {
        return resource;
    }

   
    public ValueMap getProperties() {
        return getResource().getValueMap();
    }

  
    public ResourceResolver getResourceResolver() {
        return resourceResolver;
    }

   
    public Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger(getClass());
        }
        return logger;
    }

    
    public SlingSettingsService getSlingSettingsService() {
        return slingSettingsService;
    }

    
    public SlingHttpServletRequest getRequest() {
        return request;
    }

    /**
     * When this Sling Model is instantiated from a {@link Resource} (typically via {@code resource.adaptTo(...)}) it
     * will not have a {@link SlingHttpServletRequest object set. <p> This method is provided so that you can manually
     * set the request object prior to invoking methods that may require the request object.
     *
     * @param request {@link SlingHttpServletRequest} object.
     */
    public void setRequest(SlingHttpServletRequest request) {
        this.request = request;
    }

    
    public Page getCurrentPage() {
        if (currentPage == null) {
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            if (pageManager != null) {
                currentPage = pageManager.getContainingPage(getResource());
            }
        }
        return currentPage;
    }

}