package com.twsm.dto;

public class MobappNews extends Basic{
	private static final long serialVersionUID = 1L;
	//	private Integer id;					//
	private String newsId;					//新闻网络标识
	private String title;					//新闻标题
	private String newsText;					//新闻简介
	private String pubTime;					//新闻发布时间
	private String surl;					//新闻链接
	private String source;					//新闻源发布人
	private String appName;					//应用名称
	private String columnName;					//新闻栏目
	private String addTime;					//添加时间
	private String imgsrc;                 //图片url	

	public void setNewsId(String newsId){
		this.newsId = newsId;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getNewsId(){
		return this.newsId;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setNewsText(String newsText){
		this.newsText = newsText;
	}
	public String getNewsText(){
		return this.newsText;
	}
	public void setPubTime(String pubTime){
		this.pubTime = pubTime;
	}
	public String getPubTime(){
		return this.pubTime;
	}
	public void setSurl(String surl){
		this.surl = surl;
	}
	public String getSurl(){
		return this.surl;
	}
	public void setSource(String source){
		this.source = source;
	}
	public String getSource(){
		return this.source;
	}
	public void setAppName(String appName){
		this.appName = appName;
	}
	public String getAppName(){
		return this.appName;
	}
	public void setColumnName(String column){
		this.columnName = column;
	}
	public String getColumnName(){
		return this.columnName;
	}
	public void setAddTime(String addTime){
		this.addTime = addTime;
	}
	public String getAddTime(){
		return this.addTime;
	}
}