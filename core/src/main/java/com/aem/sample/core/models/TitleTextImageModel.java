package com.aem.sample.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import com.aem.sample.core.models.ComponentSlingModel;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class TitleTextImageModel extends ComponentSlingModel{
	@Inject
	private String title;
	
	@Inject
	private String fileReference;
	
	@Inject
	private String alt;
	
	@Inject
	private String link;
	
	@Inject
	public Resource cta;
	
	@Inject
	public Resource subtitle;

	
	@ChildResource(name = "cta")
	private List<CTALinks> ctaLinks;
	
	
	public List<CTALinks> getCtaLinks() {
		return new ArrayList<>(ctaLinks);
	}
	public String getTitle() {
		return title;
	}
	public String getFileReference() {
		return fileReference;
	}
	public String getAlt() {
		return alt;
	}
	public String getLink() {
		return link;
	}
}