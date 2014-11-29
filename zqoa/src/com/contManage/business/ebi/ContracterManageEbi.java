package com.contManage.business.ebi;

import java.util.List;
import java.util.Map;

import com.FileManage.vo.ZqBusFileModel;
import com.FileManage.vo.ZqFileModel;
import com.contManage.vo.ZqContractAffairModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcharagestageModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.contManage.vo.ZqContracttypeModel;
import com.customerManage.vo.ZqCustomerModel;
import com.login.vo.ZqUserModel;
import com.util.PageBean;

/**
 * 合同管理业务处接口
 * @author peng
 * @since 2013-9-15下午07:59:46
 */
public interface ContracterManageEbi {
	/**
	 * 分页获取合同分类信息
	 * @param conMap
	 * @return
	 */
	public PageBean getCotracterTypeList(Map conMap);

	/**
	 * 添加或修改合同类型
	 * @param zqContracttypeModel
	 * @return 0添加失败 1添加成功 2重复添加
	 */
	public String addContractType(ZqContracttypeModel zqContracttypeModel);

	/**
	 * 删除合同类型
	 * @param zqContracttypeModel
	 * @return
	 */
	public String deltContractType(ZqContracttypeModel zqContracttypeModel);

	/**
	 * 获取所有的合同类型
	 * @return
	 */
	public List<ZqContracttypeModel> getAllCotracterType();

	/**
	 * 获取所有当前在职的用户
	 * @return
	 */
	public List<ZqUserModel> getAllUser();

	/**
	 * 获取所有的当前客户
	 * @return
	 */
	public List<ZqCustomerModel> getAllNowCust();

	/**
	 * 保存要添加的合同信息
	 * @param zqContractModel 合同信息实例
	 * @param lawyerId 协办律师id集合
	 * @param lawyerName 协办律师名称集合
	 * @param pay 应付款项集合
	 * @param payTime 应付款时间集合
	 * @return 0失败 1成功 2:合同编号已经存在
	 */
	public String addContract(ZqContractModel zqContractModel,
			Integer[] lawyerId, String[] lawyerName, Integer[] pay,
			String[] payTime);

	/**
	 * 分页获取合同信息
	 * @param conditionMap
	 * @return
	 */
	public PageBean getCotracterList(Map conditionMap);

	/**
	 * 根据合同id获取合同详情
	 * @param contId
	 * @return
	 */
	public ZqContractModel getContractById(Integer contId);

	/**
	 * 获取合同的所有收费信息
	 * @param contId
	 * @return
	 */
	public List<ZqContractcharagestageModel> getCharageStageByContid(
			Integer contId);

	/**
	 * 获取合同的协办律师信息
	 * @param contId
	 * @return
	 */
	public List<ZqContractcoscusModel> getCoscusByContid(Integer contId);

	/**
	 * 根据协办律师记录id删除协办律师
	 * @param coscusid
	 * @return
	 */
	public String deltCoscusById(Integer coscusid);

	/**
	 * 更新合同信息
	 * @param chargeId 已有付费阶段集合
	 * @param payTime 应付费时间
	 * @param pay 应付费金额
	 * @param lawyerName 协办律师名称
	 * @param lawyerId 协办律师id
	 * @param zqContractModel 合同信息
	 * @return 0：失败 1：成功
	 */
	public String updateContract(Integer[] chargeId, ZqContractModel zqContractModel, Integer[] lawyerId, String[] lawyerName, Integer[] pay, String[] payTime);

	/**
	 * 删除合同信息
	 * @param contId
	 * @return 0：删除失败 1：删除成功 2:已有附属案件
	 */
	public String deltContractById(Integer contId);

	/**
	 * 根据合同类型id获取合同
	 * @param contTypeId
	 * @return
	 */
	public List<Map> getContractByTypeId(Integer contTypeId);
	/**
	 * 添加合同事务
	 * @param zqContractAffairModel
	 * @return 1添加成功，0添加失败
	 */
	public String addContAffair(ZqContractAffairModel zqContractAffairModel);

	/**
	 * 获取合同下的所有事务记录
	 * @param contId
	 * @return
	 */
	public List<ZqContractAffairModel> viewAffairByContId(Integer contId);

	/**
	 * 归档合同
	 * @param contId
	 * @param archived 0:出档  1：归档
	 * @return 0：归档失败 1：归档成功
	 */
	public String arachiveCont(Integer contId, String archived);

	/**
	 * 添加合同附件
	 * @param fileids 附件id集合
	 * @param zqBusFileModel 业务Model
	 * @return 0:操作成功 1:操作失败
	 */
	public String addContFile(String fileids, ZqBusFileModel zqBusFileModel);

	/**
	 * 删除合同附件的相关信息
	 * @param fileId
	 * @return
	 */
	public String deltContFileById(Integer fileId);

	/**
	 * 更新合同付费阶段情况
	 * @param contId 所属合同id
	 * @param chargeId 付费阶段id集合
	 * @param payMoney 实际付费金额
	 * @param payTime 实际付费时间
	 * @param chargeMoney 应付费金额
	 * @param chargeTime 应付费时间
	 * @return
	 */
	public String updateRegFees(Integer contId, Integer[] chargeId, Integer[] payMoney,
			String[] payTime, String[] chargeTime, Integer[] chargeMoney);

	/**
	 * 添加档案
	 * @param zqContractModel
	 * @param lawyerId
	 * @param lawyerName
	 * @param pay
	 * @param payTime
	 * @return
	 */
	public String addArchive(ZqContractModel zqContractModel,
			Integer[] lawyerId, String[] lawyerName, Integer[] pay,
			String[] payTime);


	
	
	
	
}
