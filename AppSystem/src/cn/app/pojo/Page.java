package cn.app.pojo;

import java.util.List;

public class Page {
	//当前页
	public int currentPageNo;
	//页面大小
	public int pageSize;
	//总页数
	public int pageCountNo;
	//总记录数
	public int all;
	//每页显示的内容，需要当前页和页面大小
	public List<?> infos;
	
	//当前页
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo<=0){
			currentPageNo=1;
		}else if(currentPageNo>=this.pageCountNo){
			currentPageNo=this.pageCountNo;
		}
		this.currentPageNo = currentPageNo;
	}
	//页面大小
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<=0){
			pageSize=1;
		}
		this.pageSize = pageSize;
	}
	//总页数
	public int getPageCountNo() {
		return pageCountNo;
	}
	public void setPageCountNo(int pageCountNo) {
		this.pageCountNo = pageCountNo;
	}
	//总记录数
	public int getAll() {
		return all;
	}
	public void setAll(int all) {
		this.all = all;
		if(all!=0){
			this.pageCountNo=all%this.pageSize==0?all/this.pageSize:all/this.pageSize+1;
		}
	}
	public List getInfos() {
		return infos;
	}
	public void setInfos(List infos) {
		this.infos = infos;
	}

}
