package com.aem.sample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables=Resource.class, defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class FooterLinksModel {
	
	@Inject
	public Resource quicklinksfield;

	public Resource getQuicklinksfield() {
		return quicklinksfield;
	}

}
