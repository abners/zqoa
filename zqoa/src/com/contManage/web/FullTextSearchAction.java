package com.contManage.web;


import com.contManage.business.ebi.ContracterManageEbi;
import com.executiveManage.vo.IndexModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FullTextSearchAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3796337323520431512L;
	private String searchContent;
	private ContracterManageEbi contracterManageEbi;
	public String execute(){
		IndexModel indexModels = contracterManageEbi.fullSearchCaseAndContract(searchContent);
		ActionContext.getContext().getValueStack().set("index", indexModels);
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
