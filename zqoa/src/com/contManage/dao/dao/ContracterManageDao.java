package com.contManage.dao.dao;

import java.util.List;
import java.util.Map;

import com.FileManage.vo.ZqBusFileModel;
import com.FileManage.vo.ZqFileModel;
import com.caseManage.vo.ZqCaseModel;
import com.contManage.vo.ZqContractAffairModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcharagestageModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.contManage.vo.ZqContracttypeModel;
import com.customerManage.vo.ZqCustomerModel;
import com.login.vo.ZqUserModel;
import com.util.PageBean;

/**
 * 合同管理持久层接口
 * @author peng
 * @since 2013-9-15下午08:05:07
 */
public interface ContracterManageDao {
	/**
	 * 获取合同类型记录
	 */
	public static final String SELECT_CONTRACTERTYPE = " from ZqContracttypeModel ";
	/**
	 * 统计合同类型记录数目
	 */
	public static final String SELECT_CONTRACTERTYPE_COUNT = " select count(*) from ZqContracttypeModel ";
	/**
	 * 按条件获取合同类型记录
	 * 
	 */
	public static final String SELECT_CONTRACTERTYPE_BYNAME = " from ZqContracttypeModel where contractTypeName=? ";
	public static final String SELECT_CONTRACTERTYPE_BYNAMEID = " from ZqContracttypeModel where contractTypeName=? and contractTypeId!=?";
	
	/**
	 * 根据合同类型id获取合同信息
	 */
	public static final String SELECT_CONTRACT_BYTYPENAME = " select id from ZqContractModel where typeId=? ";
	/**
	 * 查询所有在职的当前的用户
	 */
	public static final String SELECT_USER = " from ZqUserModel where isCurrent='1' ";
	/**
	 * 查询所有当前客户
	 */
	public static final String SELEC_CUST = " from ZqCustomerModel where isnow='1'";
	/**
	 * 统计合同数目
	 */
	public static final String SELECT_CONTRACT_COUNT = " select count(*) from ZqContractModel where archived=?";
	/**
	 * 查询合同信息
	 */
	public static final String SELECT_CONTRACT = " from ZqContractModel where archived=? ";
	/**
	 * 查询合同的收费信息
	 */
	public static final String SELECT_CHARGESTAGE = " from ZqContractcharagestageModel where contractId=? ";
	/**
	 * 查询合同协办律师信息
	 */
	public static final String SELECT_COSCUS = " from ZqContractcoscusModel where contractId=? ";
	/**
	 * 合同信息delete
	 */
	public static final String deltContSql = " delete from ZqContractModel where id=:contId ";
	/**
	 * 合同协办律师delete
	 */
	public static final String deltCoscus = " delete from ZqContractcoscusModel where contractId=:contId ";
	/**
	 * 删除合同付费阶段信息
	 */
	public static final String deltChargestage = " delete from ZqContractcharagestageModel where contractId=:contId ";
	/**
	 * 删除合同事务
	 */
	public static final String deltContAffair = " delete from ZqContractAffairModel where contractId=:contId";
	/**
	 * 根据合同类型id获取合同
	 */
	public static final String SELECT_CONTBYTYPEID = " select id,contName from ZqContractModel where typeId=? ";
	/**
	 * 查询合同下的所有事务
	 */
	public static final String SELECT_CONTAFFAIR_BY_CONTID = " select a,b.contName,c.name from ZqContractAffairModel a,ZqContractModel b,ZqUserModel c" +
			                                                 "       where a.contractId=b.id and a.operator=c.id and a.contractId=? ";
	/**
	 * 分页查询合同分类信息
	 * @param conditionMap 条件信息
	 * @return
	 */
	public PageBean queryContTypeList(Map conditionMap);
	
	/**
	 * 根据合同类型名称获取合同类型
	 * @param contractTypeName
	 * @return
	 */
	public List<ZqContracttypeModel> queryContracttypeModelsByName(String contractTypeName,Integer contractTypeId);
	
	/**
	 * 添加合同类型
	 * @param zqContracttypeModel
	 */
	public void addContractType(ZqContracttypeModel zqContracttypeModel);

	/**
	 * 根据合同类型id获取合同信息
	 * @param contractTypeId
	 * @return
	 */
	public List<ZqContractModel> queryContractByType(Integer contractTypeId);

	/**
	 * 删除合同类型信息
	 * @param zqContracttypeModel
	 */
	public void deltContractType(ZqContracttypeModel zqContracttypeModel);

	/**
	 * 查询所有的合同类型记录
	 * @return
	 */
	public List<ZqContracttypeModel> queryAllCotracterType();

	/**
	 * 获取所有用户
	 * @return
	 */
	public List<ZqUserModel> queryAllUsers();

	/**
	 * 查询所有当前客户
	 * @return
	 */
	public List<ZqCustomerModel> queryAllNowCust();

	/**
	 * 保存添加的合同信息
	 * @param zqContractModel 合同信息
	 * @param zqContractcoscusModels 协办律师信息
	 * @param zqContractcharagestageModels 付款阶段信息
	 * @throws Exception 
	 */
	public void saveContract(ZqContractModel zqContractModel,
			ZqContractcoscusModel[] zqContractcoscusModels,
			List<ZqContractcharagestageModel> zqContractcharagestageModels);

	/**
	 * 分页查合同或档案信息
	 * @param conditionMap
	 * @return
	 */
	public PageBean queryContractList(Map conditionMap);

	/**
	 * 查询合同详情
	 * @param contId
	 * @return
	 */
	public ZqContractModel queryContractById(Integer contId);

	/**
	 * 查询特定合同的所有收费信息
	 * @param contId
	 * @return
	 */
	public List<ZqContractcharagestageModel> queryCharageStageByContid(
			Integer contId);

	/**
	 * 查询合同协办律师信息
	 * @param contId
	 * @return
	 */
	public List<ZqContractcoscusModel> queryCoscusByContid(Integer contId);

	public void deltCoscusByid(ZqContractcoscusModel zqContractcoscusModel);

	/**
	 * 更新合同信息
	 * @param chargeId 已有付费id集合
	 * @param zqContractModel 合同信息
	 * @param zqContractcoscusModels 协办律师集合
	 * @param zqContractcharagestageModels 修改后的付费阶段信息集合
	 */
	public void updateContract(Integer[] chargeId,
			ZqContractModel zqContractModel,
			ZqContractcoscusModel[] zqContractcoscusModels,
			List<ZqContractcharagestageModel> zqContractcharagestageModels);

	/**
	 * 删除合同及其相关信息
	 * @param contId
	 */
	public void deletContById(Integer contId);

	/**
	 * 根据合同类型id获取合同
	 * @param contTypeId
	 * @return
	 */
	public List<Map> queryContractByTypeId(Integer contTypeId);

	/**
	 * 添加合同事务
	 * @param zqContractAffairModel
	 */
	public void addContAffair(ZqContractAffairModel zqContractAffairModel);

	/**
	 * 根据合同id查询合同下的所有事务
	 * @param contId
	 * @return
	 */
	public List queryContAffairByContId(Integer contId);

	/**
	 * 查询合同的附属案件
	 * @param contId 合同id
	 * @return
	 */
	public List<ZqCaseModel> queryContTypeList(Integer contId);

	/**
	 * 更新合同的归档状态
	 * @param contId
	 * @param archived 状态值
	 */
	public void updateContractArchived(Integer contId, String archived);

	/**
	 * 保存业务附件
	 * @param fileid
	 * @param zqBusFileModel
	 */
	public void saveContFile(String[] fileid, ZqBusFileModel zqBusFileModel);

	/**
	 * 查询合同附件信息
	 * @param contId
	 * @return
	 */
	public List<ZqFileModel> queryContFile(Integer contId);

	/**
	 * 删除合同附件
	 * @param fileId
	 * @throws Exception 
	 */
	public void deltContFile(Integer fileId) throws Exception;

	/**
	 * 更新合同付费情况信息
	 * @param zqContractcharagestageModels
	 */
	public void updateRegFees(
			ZqContractcharagestageModel[] zqContractcharagestageModels);

	/**
	 * 检测合同编号是否已经存在
	 * @param number
	 * @return
	 */
	public boolean checkTheContNumIfExists(String number);

	/**
	 * 统计合同的附属案件的个数
	 * @param contId
	 * @return
	 */
	public int countCaseInCont(Integer contId);
}
