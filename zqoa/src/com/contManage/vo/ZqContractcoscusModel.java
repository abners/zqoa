package com.contManage.vo;

/**
 * ZqContractcoscus entity. @author MyEclipse Persistence Tools
 */

public class ZqContractcoscusModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer lawyerId;
	private String lawyerName;
	/**
	 * //合同id
	 */
	private Integer contractId;
	/**
	 * 案件id
	 */
	private Integer caseId;

	// Constructors

	/** default constructor */
	public ZqContractcoscusModel() {
	}

	/** full constructor */
	public ZqContractcoscusModel(Integer id,Integer lawyerId, String lawyerName,
			Integer contractId,Integer caseId) {
		this.id = id;
		this.lawyerId = lawyerId;
		this.lawyerName = lawyerName;
		this.contractId = contractId;
		this.caseId = caseId;
	}

	// Property accessors
	
	public Integer getId() {
		return this.id;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLawyerId() {
		return this.lawyerId;
	}

	public void setLawyerId(Integer lawyerId) {
		this.lawyerId = lawyerId;
	}

	public String getLawyerName() {
		return this.lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public Integer getContractId() {
		return this.contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

}