package com.caseManage.dao.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.FileManage.vo.ZqBusFileModel;
import com.caseManage.vo.ZqCaseModel;
import com.caseManage.vo.ZqCaseTypeModel;
import com.caseManage.vo.ZqCasecontactModel;
import com.caseManage.vo.ZqCaseprocessModel;
import com.caseManage.vo.ZqContactidentityModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.util.PageBean;

/**
 * 案件管理持久层接口
 * @author peng
 * @since 2013-10-7上午11:21:11
 */
public interface CaseManageDao {
	/**
	 * 查询案件联系人身份
	 */
	public static final String SELECT_CASEIDENTITY = " from ZqContactidentityModel";
	
	/**
	 * 统计案件联系人身份个数
	 */
	public static final String SELECT_COUNT_CASEIDENTITY = " select count(*) from ZqContactidentityModel ";
	
	/**
	 * 按条件获取案件联系人身份
	 */
	public static final String SELECT_CASEIDENTITY_BY_NAME = " from ZqContactidentityModel where identityName=? ";
	/**
	 * 统计使用某身份的联系人个数
	 */
	public static final String SELECT_COUNT_CASEIDENTITY_USEDBY_CASECONTACT= " select count(*) from ZqCasecontactModel where roleId=? ";
	/**
	 * 删除案件联系人身份
	 */
	public static final String DELETE_CASEIDENTITY = "delete from ZqContactidentityModel where identityId=:identityId ";
	/**
	 * 查询案件类型
	 */
	public static final String SELECT_CASETYPE = " from ZqCaseTypeModel ";
	/**
	 * 统计案件类型个数
	 */
	public static final String SELECT_COUNT_CASETYPE = " select count(*) from ZqCaseTypeModel ";
	/**
	 * 根据名称统计案件类型个数
	 */
	public static final String SELECT_COUNT_CASETYPE_BY_NAME = " select count(*) from ZqCaseTypeModel where typeName=? ";
	/**
	 * 统计使用案件类型的案件个数
	 */
	public static final String SELECT_COUNT_TYPE_USERBY_CASE = " select count(*) from ZqCaseModel where typeId=? ";
	/**
	 * 删除案件类型
	 */
	public static final String DELETE_CASETYPE = " delete from ZqCaseTypeModel where typeId=:id ";
	/**
	 * 查询合同信息
	 */
	public static final String SELECT_CONTRACT = "select a.id,a.contName,a.custId,custName from ZqContractModel a where archived=? ";
	/**
	 * 关联案件选择时查询未完结案件信息
	 */
	public static final String SELECT_CASE = " from ZqCaseModel where status=0 ";
	/**
	 * 统计案件个数
	 */
	public static final String SELECT_COUNT_CASE = " select count(*) from ZqCaseModel where status=? ";
	/**
	 * 查询案件信息
	 */
	public static final String SELECT_NOW_CASE = "select a,b.typeName,c.name from ZqCaseModel a,ZqCaseTypeModel b,ZqUserModel c where a.lawyer=c.id and a.typeId=b.typeId and a.status=? ";
	/**
	 * 查询案件详情
	 */
	public static final String SELECT_CASE_BY_ID = " select new ZqCaseModel(a.id,a.number,a.caseName,f.name,b.contName,e.name,c.typeName,d.identityName,a.relative,a.notes,a.contId,a.custId) " +
    											   "          from ZqCaseModel a,ZqContractModel b,ZqCaseTypeModel c,ZqCustomerModel f,ZqContactidentityModel d,ZqUserModel e " +
    											   "               where a.contId=b.id and a.typeId=c.typeId and a.lawyer=e.id and a.litigant=d.identityId and a.custId=f.id and a.id=:id ";
	/**
	 * 
	 */
	public static final String SELECT_CASEBEFORECONT_BY_ID = " select new ZqCaseModel(a.id,a.number,a.caseName,f.name,e.name,c.typeName,d.identityName,a.relative,a.notes,a.contId,a.custId) " +
															 "          from ZqCaseModel a,ZqCaseTypeModel c,ZqCustomerModel f,ZqContactidentityModel d,ZqUserModel e " +
	                                                         "               where a.typeId=c.typeId and a.lawyer=e.id and a.litigant=d.identityId and a.custId=f.id and a.id=:id ";
	/**
	 * 查询案件事件流程
	 */
	public static final String SELECT_CASEPROCESS = " select a,b.name from ZqCaseprocessModel a,ZqUserModel b where a.creater=b.id and a.caseId=? order by a.processTime desc";
	//2013-10-14注掉此种查询方法，原因是总是报找不到合适的构造方法
	//（当类中确实存在，并且参数一模一样，时间问题无法找原因了,（怀疑是文件没有被编译），现改为上面的方法）
	//2013-10-15 由于createTime字段为timestamp类型的原因
	/*" select new ZqCaseprocessModel(a.id,a.caseId,a.createTime,a.content,a.creater,b.name) from ZqCaseprocessModel a,ZqUserModel b where a.creater=b.id and a.caseId=? ";*/
	/**
	 * 查询案件事件的附件
	 */
	public static final String SELECT_CASEPROCESS_FILE = " select new ZqFileModel(a.id,a.ywjm,a.realFilename,a.address,a.creater,a.createTime,b.busId,b.id)" +
			                                             "        from ZqFileModel a,ZqBusFileModel b where a.id=b.fileId and b.busId=? ";
	/**
	 * 分页查询案件联系人身份
	 * @param conditionMap
	 * @return
	 */
	PageBean queryContactIdentityList(Map conditionMap);
	/**
	 * 添加案件联系人身份
	 * @param zqContactidentityModel
	 */
	void saveCaseIdentity(ZqContactidentityModel zqContactidentityModel);
	/**
	 * 根据联系人身份名称获取记录
	 * @param identityName
	 * @return
	 */
	List<ZqContactidentityModel> queryCaseIdentityByName(String identityName);
	/**
	 * 更新联系人身份名称
	 * @param zqContactidentityModel
	 */
	void updateCaseIdentity(ZqContactidentityModel zqContactidentityModel);
	/**
	 * 统计使用该身份的联系人个数
	 * @param identityId
	 * @return
	 */
	int countIdentityUsedByCaseContact(Integer identityId);
	/**
	 * 
	 * @param identityId
	 */
	void deltCaseIdentityById(Integer identityId);
	/**
	 * 分页查询案件类型
	 * @param conditionMap
	 * @return
	 */
	PageBean queryContactTypeList(Map conditionMap);
	/**
	 * 统计使用typeName的个数
	 * @param typeName
	 * @return
	 */
	int countCaseTypeByName(String typeName);
	/**
	 * 添加案件类型
	 * @param zqCaseTypeModel
	 */
	void saveCaseType(ZqCaseTypeModel zqCaseTypeModel);
	/**
	 * 修改案件类型信息
	 * @param zqCaseTypeModel
	 */
	void updateCaseType(ZqCaseTypeModel zqCaseTypeModel);
	/**
	 * 统计使用案件类型的案件个数
	 * @param typeId
	 * @return
	 */
	int countTypeUsedByCase(Integer typeId);
	/**
	 * 删除案件类型
	 * @param typeId
	 */
	void deltCaseTypeById(Integer typeId);
	/**
	 * 查询所有案件类型
	 * @return
	 */
	List<ZqCaseTypeModel> queryAllCaseType();
	/**
	 * 查询所有的案件联系人身份
	 * @return
	 */
	List<ZqContactidentityModel> queryAllCaseIdentity();
	/**
	 * 查询所有未归档的合同
	 * @return
	 */
	List<ZqContractModel> queryAllContract();
	/**
	 * 查询用户创建的所有未归档合同
	 * @param id
	 * @return
	 */
	@Deprecated
	List<ZqContractModel> queryContractCreatedByUser(Integer id);
	/**
	 * 根据案件名称查询案件
	 * @param caseName
	 * @return
	 */
	List queryCaseByName(String caseName);
	/**
	 * 添加案件
	 * @param zqCaseModel
	 * @param zqContractcoscusModels
	 * @throws Exception 
	 */
	void saveCaseIdentity(ZqCaseModel zqCaseModel,
			ZqContractcoscusModel[] zqContractcoscusModels) throws Exception;
	/**
	 * 分页查询案件信息
	 * @param conditionMap
	 * @return
	 */
	PageBean queryCaseByPage(Map conditionMap);
	/**
	 * 查询案件详情
	 * @param caseId
	 * @return
	 */
	ZqCaseModel queryCaseById(Integer caseId);
	/**
	 * 查询案件的协办律师
	 * @param caseId
	 * @return
	 */
	List<ZqContractcoscusModel> queryCoscusByCaseId(Integer caseId);
	/**
	 * 查询案件名称集合
	 * @param relativeIds
	 * @return
	 */
	List queryCaseNameArray(String[] relativeIds);
	/**
	 * 查询案件的事件流程信息记录
	 * @param caseId
	 * @return
	 */
	List<ZqCaseprocessModel> queryCaseProcessByCaseId(Integer caseId);
	/**
	 * 添加案件事件
	 * @param zqCaseprocessModel
	 * @param zqBusFileModel 业务附件
	 */
	void addCaseProcess(ZqCaseprocessModel zqCaseprocessModel,
			ZqBusFileModel[] zqBusFileModel);
	/**
	 * 根据文件id批量删除文件
	 * @param fileid_array id集合
	 * @throws Exception 
	 */
	void deltAllFileByids(String[] fileid_array) throws Exception;
	/**
	 * 根据id删除案件事件的相关信息
	 * @param caseProcessId
	 * @throws Exception 
	 */
	void deltCaseProcessById(String caseProcessId) throws Exception;
	/**
	 * 添加案件联系人
	 * @param zqCasecontactModel
	 */
	void saveCaseContact(ZqCasecontactModel zqCasecontactModel)throws HibernateException;
	/**
	 * 查询案件联系人信息
	 * @param caseId
	 * @return
	 */
	List<ZqCasecontactModel> queryContactByCaseId(Integer caseId);
	/**
	 * 删除案件联系人
	 * @param caseContactId
	 * @return
	 */
	void deltCaseContactById(Integer caseContactId);
	/**
	 * 更新案件状态
	 * @param caseId 案件id
	 * @param status 案件状态值 0：出档 1：归档
	 */
	void updateCaseStatusById(Integer caseId, Integer status);
	/**
	 * 删除案件信息
	 * @param caseId
	 * @throws Exception 
	 */
	void deltCaseById(Integer caseId) throws Exception;
	/**
	 *根据案件id查询案件信息
	 * @param caseId
	 * @return
	 */
	ZqCaseModel queryCaseMessById(Integer caseId);
	/**
	 * 更新案件信息
	 * @param zqCaseModel
	 * @param zqContractcoscusModels
	 */
	void updateCaseAndIdentity(ZqCaseModel zqCaseModel,
			ZqContractcoscusModel[] zqContractcoscusModels);
	/**
	 * 获取合同前案件信息
	 * @param caseId
	 * @return
	 */
	ZqCaseModel queryCaseBeforeContById(Integer caseId);
	/**
	 * 获取案件事件
	 * @param processId
	 * @return
	 */
	ZqCaseprocessModel queryCaseProcessById(String processId);
	
	/**
	 * 保存修改的案件事件信息
	 * @param zqCaseprocessModel
	 * @param zqBusFileModel_Array
	 * @param deltFileIds
	 * @throws Exception 
	 */
	void modifyCaseProcess(ZqCaseprocessModel zqCaseprocessModel,
			ZqBusFileModel[] zqBusFileModel_Array, Integer[] deltFileIds) throws Exception;
	
	ZqCasecontactModel queryCasecontactById(Integer caseContactId);
	/**
	 * 修改案件联系人信息
	 * @param zqCasecontactModel
	 */
	void updateCaseContact(ZqCasecontactModel zqCasecontactModel);

}
