package com.executiveManage.vo;

import java.util.List;

import com.caseManage.vo.ZqCaseModel;
import com.contManage.vo.ZqContractModel;

/**
 * 首页数据集合
 * @author peng
 * @since 2013-11-10下午03:04:33
 */
public class IndexModel {
	//未结案件
	private List<ZqCaseModel> zqNoOverCaseModels;
	//已结案件
	private List<ZqCaseModel> zqOverCaseModels;
	//未结合同
	private List<ZqContractModel> zqNoOverContractModels;
	//已结合同
	private List<ZqContractModel> zqOverContractModels;
	public List<ZqCaseModel> getZqNoOverCaseModels() {
		return zqNoOverCaseModels;
	}
	public void setZqNoOverCaseModels(List<ZqCaseModel> zqNoOverCaseModels) {
		this.zqNoOverCaseModels = zqNoOverCaseModels;
	}
	public List<ZqCaseModel> getZqOverCaseModels() {
		return zqOverCaseModels;
	}
	public void setZqOverCaseModels(List<ZqCaseModel> zqOverCaseModels) {
		this.zqOverCaseModels = zqOverCaseModels;
	}
	public List<ZqContractModel> getZqNoOverContractModels() {
		return zqNoOverContractModels;
	}
	public void setZqNoOverContractModels(
			List<ZqContractModel> zqNoOverContractModels) {
		this.zqNoOverContractModels = zqNoOverContractModels;
	}
	public List<ZqContractModel> getZqOverContractModels() {
		return zqOverContractModels;
	}
	public void setZqOverContractModels(List<ZqContractModel> zqOverContractModels) {
		this.zqOverContractModels = zqOverContractModels;
	}
	
	
}
