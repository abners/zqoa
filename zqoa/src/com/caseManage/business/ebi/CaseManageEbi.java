package com.caseManage.business.ebi;

import java.util.List;
import java.util.Map;

import com.caseManage.vo.ZqCaseModel;
import com.caseManage.vo.ZqCaseTypeModel;
import com.caseManage.vo.ZqCasecontactModel;
import com.caseManage.vo.ZqCaseprocessModel;
import com.caseManage.vo.ZqContactidentityModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.login.vo.ZqUserModel;
import com.util.PageBean;

/**
 * 案件管理业务处接口
 * @author peng
 * @since 2013-10-7上午11:20:38
 */
public interface CaseManageEbi {

	/**
	 * 分页获取案件联系人身份记录
	 * @param conditionMap 条件map
	 * @return
	 */
	PageBean getCaseIdentityList(Map conditionMap);

	/**
	 * 添加案件联系人身份
	 * @param zqContactidentityModel
	 * @return 0添加失败 1添加成功 2已存在同名记录
	 */
	String addCaseIdentity(ZqContactidentityModel zqContactidentityModel);

	/**
	 * 修改案件联系人身份
	 * @param zqContactidentityModel
	 * @return 0修改失败 1修改成功 2已存在同名记录
	 */
	String modifyCaseIdentity(ZqContactidentityModel zqContactidentityModel);

	/**
	 * 根据id删除案件联系人身份
	 * @param identityId
	 * @return 0删除失败 1删除成功 2已被使用
	 */
	String deltCaseIdentity(Integer identityId);

	/**
	 * 分页获取案件类型
	 * @param conditionMap
	 * @return
	 */
	PageBean getCaseTypeList(Map conditionMap);

	/**
	 * 添加案件类型
	 * @param zqCaseTypeModel
	 * @return 0添加失败 1添加成功 2已存在同名记录
	 */
	String addCaseType(ZqCaseTypeModel zqCaseTypeModel);

	/**
	 * 修改案件类型信息
	 * @param zqCaseTypeModel
	 * @return
	 */
	String modifyCaseType(ZqCaseTypeModel zqCaseTypeModel);

	/**
	 * 删除案件类型
	 * @param typeId
	 * @return
	 */
	String deltCaseType(Integer typeId);

	/**
	 * 获取所有案件类型
	 * @return
	 */
	List<ZqCaseTypeModel> getAllCaseType();

	/**
	 * 获取所有案件联系人身份
	 * @return
	 */
	List<ZqContactidentityModel> getAllCaseIdentity();

	/**
	 * 获取当前登录用户所创建的所有合同
	 * @param userInfoModel
	 * @return
	 */
	List<ZqContractModel> getContCreatByUser(ZqUserModel userInfoModel);

	/**
	 * 根据案件名称获取案件信息
	 * @param caseName
	 * @return
	 */
	List<ZqCaseModel> getCaseByName(String caseName);

	/**
	 * 添加案件
	 * @param zqCaseModel 案件信息
	 * @param lawyerId 案件协办律师
	 * @param zqUserModel 用户信息
	 * @return 0失败 1成功
	 */
	String addCase(ZqCaseModel zqCaseModel, Integer[] lawyerId, ZqUserModel zqUserModel);

	/**
	 * 根据用户权限获取用户可以查看的案件列表
	 * @param conditionMap
	 * @return
	 */
	PageBean listCaseByUser(Map conditionMap);

	/**
	 * 根据案件id获取案件详情
	 * @param caseId
	 * @return
	 */
	ZqCaseModel getCaseById(Integer caseId);

	/**
	 * 根据案件id获取其所有的协办律师
	 * @param caseId
	 * @return
	 */
	List<ZqContractcoscusModel> getCoscusByCaseId(Integer caseId);

	/**
	 * 添加案件事件
	 * @param zqCaseprocessModel
	 * @param fileids 附件id集合
	 * @return 0添加失败，1添加成功
	 */
	String addCaseProcess(ZqCaseprocessModel zqCaseprocessModel, String fileids);

	/**
	 * 根据id删除案件事件
	 * @param caseProcessId 案件事件id
	 * @return 0 失败 1成功
	 */
	String deltCaseProcessById(String caseProcessId);

	/**
	 * 添加案件联系人
	 * @param zqCasecontactModel
	 * @return 0：失败 1：成功
	 */
	String addCaseContact(ZqCasecontactModel zqCasecontactModel);

	/**
	 * 删除案件联系人
	 * @param caseContactId
	 * @return 0：失败 1：成功
	 */
	String deltCaseContactById(Integer caseContactId);

	/**
	 * 更改案件状态
	 * @param caseId
	 * @param status 1:结案 0：出档
	 * @return 0：失败 1：成功
	 */
	String closeCase(Integer caseId,Integer status);

	/**
	 * 删除案件信息
	 * @param caseId
	 * @return 0：失败 1:成功
	 */
	String deltCaseById(Integer caseId);

	/**
	 * 修该案件信息初始化获取案件信息
	 * @param caseId
	 * @return
	 */
	ZqCaseModel getCaseMessById(Integer caseId);
	
	/**
	 * 保存修改的案件信息
	 * @param zqCaseModel 
	 * @param lawyerId
	 * @param zqUserModel
	 * @return 0：失败 1：成功
	 */
	String modifyCaseMessById(ZqCaseModel zqCaseModel, Integer[] lawyerId,
			ZqUserModel zqUserModel);

	/**
	 * 根据案件id获取合同前案件详情
	 * @param caseId
	 * @return
	 */
	ZqCaseModel getCaseBeforeContById(Integer caseId);

	/**
	 * 根据案件事件id获取案件事件信息
	 * @param processId
	 * @return
	 */
	ZqCaseprocessModel getCaseProcessById(String processId);

	/**
	 *  修改案件事件信息
	 * @param zqCaseprocessModel
	 * @param fileids 新上传的文件id
	 * @param deltFileIds
	 * @return
	 */
	String modifyCaseProcess(ZqCaseprocessModel zqCaseprocessModel,
			String fileids, Integer[] deltFileIds);

	/**
	 * 获取案件联系人信息
	 * @param caseContactId
	 * @return
	 */
	ZqCasecontactModel getCaseContactById(Integer caseContactId);

	/**
	 * 修改案件联系人信息
	 * @param zqCasecontactModel
	 * @return
	 */
	String modifyCaseContact(ZqCasecontactModel zqCasecontactModel);

}
