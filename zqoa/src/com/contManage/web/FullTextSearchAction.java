package com.contManage.web;


import com.contManage.business.ebi.ContracterManageEbi;
import com.executiveManage.vo.IndexModel;
import com.opensymphony.xwork2.ActionSupport;

public class FullTextSearchAction extends ActionSupport {
	private String searchContent;
	private ContracterManageEbi contracterManageEbi;
	public String execute(){
		IndexModel indexModel = contracterManageEbi.fullSearchCaseAndContract(searchContent);
		return SUCCESS;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	public ContracterManageEbi getContracterManageEbi() {
		return contracterManageEbi;
	}
	public void setContracterManageEbi(ContracterManageEbi contracterManageEbi) {
		this.contracterManageEbi = contracterManageEbi;
	}
	
	
	
}
