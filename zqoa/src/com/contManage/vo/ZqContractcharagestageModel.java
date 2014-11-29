package com.contManage.vo;

/**
 * ZqContractcharagestage entity. @author MyEclipse Persistence Tools
 */

public class ZqContractcharagestageModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String chargeTime;
	private Integer chargeMoney;
	private String realChargeTime;
	private Integer realChargeMoney;
	private String ispaid;
	private Integer contractId;

	// Constructors

	/** default constructor */
	public ZqContractcharagestageModel() {
	}

	/** full constructor */
	public ZqContractcharagestageModel(String chargeTime, Integer chargeMoney,
			String realChargeTime, Integer realChargeMoney, String ispaid,
			Integer contractId) {
		this.chargeTime = chargeTime;
		this.chargeMoney = chargeMoney;
		this.realChargeTime = realChargeTime;
		this.realChargeMoney = realChargeMoney;
		this.ispaid = ispaid;
		this.contractId = contractId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChargeTime() {
		return this.chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public Integer getChargeMoney() {
		return this.chargeMoney;
	}

	public void setChargeMoney(Integer chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	public String getRealChargeTime() {
		return this.realChargeTime;
	}

	public void setRealChargeTime(String realChargeTime) {
		this.realChargeTime = realChargeTime;
	}

	public Integer getRealChargeMoney() {
		return this.realChargeMoney;
	}

	public void setRealChargeMoney(Integer realChargeMoney) {
		this.realChargeMoney = realChargeMoney;
	}

	public String getIspaid() {
		return this.ispaid;
	}

	public void setIspaid(String ispaid) {
		this.ispaid = ispaid;
	}

	public Integer getContractId() {
		return this.contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

}