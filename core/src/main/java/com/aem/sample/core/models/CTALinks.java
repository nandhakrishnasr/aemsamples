package com.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.aem.sample.core.models.ServiceUtils;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CTALinks extends ComponentSlingModel{
	
	@Inject
	@ValueMapValue
	private String displayFormat;
	
	@Inject
	@ValueMapValue
	private String ctaText;
	
	@Inject
	@ValueMapValue
	private String hexaCode;

	@Inject
	@ValueMapValue
	private String path;
	
	@Inject
	@ValueMapValue
	private String target;

	
	public String getTarget() {
		return target;
	}

	public String getPath() {
		path = ServiceUtils.appendLinkExtension(getResourceResolver(), getProperties().get("path", String.class));
		return path;
	}

	public String getCtaText() {
		return ctaText;
	}
	
	public String getHexaCode() {
		return hexaCode;
	}

	public String getDisplayFormat() {
		return displayFormat;
	}
}
