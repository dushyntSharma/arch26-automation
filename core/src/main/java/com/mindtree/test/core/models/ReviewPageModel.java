package com.mindtree.test.core.models;

public class ReviewPageModel {
	private String name;
	private String templatePath;
	private String title;
	private String description;
	private String pageParent;

	public ReviewPageModel(String name, String templatePath, String title, String description, String pageParent) {
		super();
		this.name = name;
		this.templatePath = templatePath;
		this.title = title;
		this.description = description;
		this.pageParent = pageParent;
	}

	public ReviewPageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPageParent() {
		return pageParent;
	}

	public void setPageParent(String pageParent) {
		this.pageParent = pageParent;
	}

}
