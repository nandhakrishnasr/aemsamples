package com.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Model(adaptables=Resource.class)
public class FooterModel {
	@Inject
	@Optional
	private String copyrighttext;
	@Inject
	@Optional
	private String link;
	@Inject
	@Optional
	private String icon;
	@Inject
	@Optional
	private String contactustext;
	@Inject
	@Optional
	private String accessibilityLabel;
	@Inject
	@Optional
	private String target;
	
	@Inject
	public Resource quicklinksfield;
	
	@Inject
    public Resource socialicons;
	
	
	public String getTarget() {
		return target;
	}
	public String getAccessibilityLabel() {
		return accessibilityLabel;
	}
	public String getCopyrighttext() {
		return copyrighttext;
	}
	public String getLink() {
		return link;
	}
	public String getIcon() {
		return icon;
	}
	public String getContactustext() {
		return contactustext;
	}
	public Resource getSocialicons() {
		return socialicons;
	}
	public Resource getQuicklinksfield() {
		return quicklinksfield;
	}
	
	
}
