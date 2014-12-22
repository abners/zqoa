package com.caseManage.business.ebo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.FileManage.vo.ZqBusFileModel;
import com.caseManage.business.ebi.CaseManageEbi;
import com.caseManage.dao.dao.CaseManageDao;
import com.caseManage.vo.ZqCaseModel;
import com.caseManage.vo.ZqCaseTypeModel;
import com.caseManage.vo.ZqCasecontactModel;
import com.caseManage.vo.ZqCaseprocessModel;
import com.caseManage.vo.ZqContactidentityModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.login.vo.ZqUserModel;
import com.util.EcPageHelper;
import com.util.Log4j;
import com.util.MethodUtils;
import com.util.PageBean;

/**
 * 案件管理业务层实现
 * 
 * @author peng
 * @since 2013-10-7下午02:26:48
 */
public class CaseManageEbo extends EcPageHelper implements CaseManageEbi {
	private CaseManageDao caseManageDao;

	public CaseManageDao getCaseManageDao() {
		return caseManageDao;
	}

	public void setCaseManageDao(CaseManageDao caseManageDao) {
		this.caseManageDao = caseManageDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.caseManage.business.ebi.CaseManageEbi#getCaseIdentityList(java.util
	 * .Map)
	 */
	@Override
	public PageBean getCaseIdentityList(Map conditionMap) {
		// TODO Auto-generated method stub
		// 初始化分页信息类
		PageBean pageBean = getPageBean(conditionMap);

		conditionMap.put("pageBean", pageBean);

		return caseManageDao.queryContactIdentityList(conditionMap);
	}

	@Override
	public String addCaseIdentity(ZqContactidentityModel zqContactidentityModel) {
		// TODO Auto-generated method stub
		String result = "0";

		if (zqContactidentityModel != null) {
			if (!checkifExistRepeatIdentity(zqContactidentityModel)) {
				try {
					// 保存记录
					caseManageDao.saveCaseIdentity(zqContactidentityModel);
					result = "1";
				} catch (HibernateException e) {
					// TODO: handle exception
					Log4j.errorLog(this, e);
				}
			} else {
				result = "2";
			}
		}
		return result;
	}

	/**
	 * 检测是否存在同名身份类型
	 * 
	 * @param zqContactidentityModel
	 * @return 存在 true 否则 false
	 */
	private boolean checkifExistRepeatIdentity(
			ZqContactidentityModel zqContactidentityModel) {
		// TODO Auto-generated method stub
		List<ZqContactidentityModel> contactidentityModels = caseManageDao
				.queryCaseIdentityByName(zqContactidentityModel
						.getIdentityName());
		if (contactidentityModels != null && contactidentityModels.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String modifyCaseIdentity(
			ZqContactidentityModel zqContactidentityModel) {
		// TODO Auto-generated method stub
		String result = "0";

		if (zqContactidentityModel != null) {
			if (!checkifExistRepeatIdentity(zqContactidentityModel)) {
				try {
					// 保存记录
					caseManageDao.updateCaseIdentity(zqContactidentityModel);
					result = "1";
				} catch (HibernateException e) {
					// TODO: handle exception
					Log4j.errorLog(this, e);
				}
			} else {
				result = "2";
			}
		}
		return result;
	}

	@Override
	public String deltCaseIdentity(Integer identityId) {
		// TODO Auto-generated method stub
		String result = "0";
		if (identityId != null) {
			if (!checkifUsedBycaseContact(identityId)) {
				try {
					// 保存记录
					caseManageDao.deltCaseIdentityById(identityId);
					result = "1";
				} catch (HibernateException e) {
					// TODO: handle exception
					Log4j.errorLog(this, e);
				}
			} else {
				result = "2";
			}
		}
		return result;
	}

	/**
	 * 检测是否已有案件联系人使用该身份
	 * 
	 * @param identityId
	 * @return true已存在 否则false
	 */
	private boolean checkifUsedBycaseContact(Integer identityId) {
		// TODO Auto-generated method stub
		return caseManageDao.countIdentityUsedByCaseContact(identityId) == 0 ? false
				: true;
	}

	@Override
	public PageBean getCaseTypeList(Map conditionMap) {
		// TODO Auto-generated method stub
		// 初始化分页信息类
		PageBean pageBean = getPageBean(conditionMap);

		conditionMap.put("pageBean", pageBean);

		return caseManageDao.queryContactTypeList(conditionMap);
	}

	@Override
	public String addCaseType(ZqCaseTypeModel zqCaseTypeModel) {
		// TODO Auto-generated method stub
		String result = "0";
		if (zqCaseTypeModel != null) {
			String typeName = zqCaseTypeModel.getTypeName();
			if (typeName != null) {
				if (!checkifExistRepeatType(typeName)) {
					try {
						// 保存记录
						caseManageDao.saveCaseType(zqCaseTypeModel);
						result = "1";
					} catch (HibernateException e) {
						// TODO: handle exception
						Log4j.errorLog(this, e);
					}
				} else {
					result = "2";
				}
			}
		}
		return result;
	}

	/**
	 * 检测是否已经存在同名案件类型
	 * 
	 * @param zqCaseTypeModel
	 * @return 是true否则false
	 */
	private boolean checkifExistRepeatType(String typeName) {
		// TODO Auto-generated method stub

		return caseManageDao.countCaseTypeByName(typeName) == 0 ? false : true;
	}

	@Override
	public String modifyCaseType(ZqCaseTypeModel zqCaseTypeModel) {
		// TODO Auto-generated method stub
		String result = "0";

		if (zqCaseTypeModel != null) {
			String typeName = zqCaseTypeModel.getTypeName();
			if (typeName != null) {
				if (!checkifExistRepeatType(typeName)) {
					try {
						// 保存记录
						caseManageDao.updateCaseType(zqCaseTypeModel);
						result = "1";
					} catch (HibernateException e) {
						// TODO: handle exception
						Log4j.errorLog(this, e);
					}
				} else {
					result = "2";
				}
			}
		}
		return result;
	}

	@Override
	public String deltCaseType(Integer typeId) {
		// TODO Auto-generated method stub
		String result = "0";
		if (typeId != null) {
			if (!checkifUsedBycase(typeId)) {
				try {
					// 保存记录
					caseManageDao.deltCaseTypeById(typeId);
					result = "1";
				} catch (HibernateException e) {
					// TODO: handle exception
					Log4j.errorLog(this, e);
				}
			} else {
				result = "2";
			}
		}
		return result;
	}

	/**
	 * 检测是否已有案件使用该类型
	 * 
	 * @param typeId
	 *            类型id
	 * @return 已使用true 否则false
	 */
	private boolean checkifUsedBycase(Integer typeId) {
		// TODO Auto-generated method stub
		return caseManageDao.countTypeUsedByCase(typeId) == 0 ? false : true;
	}

	@Override
	public List<ZqCaseTypeModel> getAllCaseType() {
		// TODO Auto-generated method stub
		return caseManageDao.queryAllCaseType();
	}

	@Override
	public List<ZqContactidentityModel> getAllCaseIdentity() {
		// TODO Auto-generated method stub
		return caseManageDao.queryAllCaseIdentity();
	}

	@Override
	public List<ZqContractModel> getContCreatByUser(ZqUserModel userInfoModel) {
		// TODO Auto-generated method stub
		List<ZqContractModel> zqContractModels = null;
		// 判断用户是否为超级管理员
		// String isManage = userInfoModel.getIsManage();
		// 是超级管理员
		// 可以获取所有合同，不再判断是否为超级管理员
		/* if(isManage.equals("1")){ */
		// 获取所有合同
		zqContractModels = caseManageDao.queryAllContract();
		/*
		 * }else { //获取用户创建的合同 zqContractModels =
		 * caseManageDao.queryContractCreatedByUser(userInfoModel.getId());
		 * 
		 * }
		 */
		return zqContractModels;
	}

	@Override
	public List<ZqCaseModel> getCaseByName(String caseName) {
		// TODO Auto-generated method stub
		return caseManageDao.queryCaseByName(caseName);
	}

	@Override
	public String addCase(ZqCaseModel zqCaseModel, Integer[] lawyerId,
			ZqUserModel zqUserModel) {
		// TODO Auto-generated method stub
		String result = "0";
		ZqContractcoscusModel[] zqContractcoscusModels = null;
		// 存在协办律师
		if (lawyerId != null && lawyerId.length > 0) {
			int length = lawyerId.length;
			zqContractcoscusModels = new ZqContractcoscusModel[length];
			for (int i = 0; i < length; i++) {
				ZqContractcoscusModel zqContractcoscusModel = new ZqContractcoscusModel();
				zqContractcoscusModel.setLawyerId(lawyerId[i]);
				// 生成协办律师信息实例
				zqContractcoscusModels[i] = zqContractcoscusModel;
			}
		}
		if (zqCaseModel != null) {
			// 创建者id
			zqCaseModel.setCreater(zqUserModel.getId());
			// 案件状态0，未完结
			zqCaseModel.setStatus(0);
			try {
				caseManageDao.saveCaseIdentity(zqCaseModel,
						zqContractcoscusModels);
				result = "1";
			} catch (Exception e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);
			}
		}
		return result;
	}

	@Override
	public PageBean listCaseByUser(Map conditionMap) {
		ZqUserModel zqUserModel = (ZqUserModel) conditionMap.get("user");
		// TODO Auto-generated method stub
		// 初始化分页信息类
		PageBean pageBean = getPageBean(conditionMap);

		conditionMap.put("pageBean", pageBean);

		pageBean = caseManageDao.queryCaseByPage(conditionMap);
		List list = pageBean.getList();
		List<ZqCaseModel> zqCaseModels = new ArrayList<ZqCaseModel>();
		if (list != null) {
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = iterator.next();
				ZqCaseModel zqCaseModel = (ZqCaseModel) objects[0];
				// 案件类型名称
				zqCaseModel.setTypeName((String) objects[1]);
				// 主办律师名称
				zqCaseModel.setLawyerName((String) objects[2]);

				zqCaseModels.add(zqCaseModel);
			}
		}
		pageBean.setList(zqCaseModels);

		return pageBean;
	}

	@Override
	public ZqCaseModel getCaseById(Integer caseId) {
		// TODO Auto-generated method stub
		ZqCaseModel zqCaseModel = caseManageDao.queryCaseById(caseId);
		if (zqCaseModel != null) {
			// 关联案件
			String relative = zqCaseModel.getRelative();
			if (relative != null && relative.length() > 0) {
				// 关联案件id集合
				String[] relativeIds = relative.split(",");
				// 获取关联案件的名称集合
				List relativeName = caseManageDao
						.queryCaseNameArray(relativeIds);
				// 放入案件信息中
				zqCaseModel.setRelativeCaseNames(relativeName);
			}
			// 获取案件事件流程信息
			List<ZqCaseprocessModel> zqCaseprocessModels = caseManageDao
					.queryCaseProcessByCaseId(caseId);
			zqCaseModel.setZqCaseprocessModels(zqCaseprocessModels);
			// 获取案件联系人信息
			List<ZqCasecontactModel> zqCasecontactModels = caseManageDao
					.queryContactByCaseId(caseId);
			zqCaseModel.setZqCasecontactModels(zqCasecontactModels);
		}
		return zqCaseModel;
	}

	@Override
	public List<ZqContractcoscusModel> getCoscusByCaseId(Integer caseId) {
		// TODO Auto-generated method stub
		return caseManageDao.queryCoscusByCaseId(caseId);
	}

	/*
	 * public static void main(String args[]){ String string= "323,3";
	 * System.out.println(string.split(",").length); }
	 */
	@Override
	public String addCaseProcess(ZqCaseprocessModel zqCaseprocessModel,
			String fileids) {
		// TODO Auto-generated method stub
		String[] fileid_array = null;
		ZqBusFileModel[] zqBusFileModel_Array = null;
		// 案件事件id
		String processId = MethodUtils.getUUID();
		try {
			// 存在附件
			if (fileids.length() > 0) {
				fileid_array = fileids.substring(0, fileids.length() - 1)
						.split(",");
				int length = fileid_array.length;
				zqBusFileModel_Array = new ZqBusFileModel[length];
				for (int i = 0; i < length; i++) {
					ZqBusFileModel zqBusFileModel = new ZqBusFileModel();
					// 业务id
					zqBusFileModel.setBusId(processId);
					// 附件id
					zqBusFileModel.setFileId(Integer.valueOf(fileid_array[i]));
					zqBusFileModel_Array[i] = zqBusFileModel;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			return "0";
		}

		// 创建人id
		zqCaseprocessModel.setCreater(MethodUtils.getUserInfoModel().getId());
		zqCaseprocessModel.setId(processId);
		try {
			// 添加案件事件
			caseManageDao.addCaseProcess(zqCaseprocessModel,
					zqBusFileModel_Array);
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			try {
				// 删除全部附件
				if (fileid_array != null) {
					caseManageDao.deltAllFileByids(fileid_array);
				}
			} catch (Exception ex) {
				// TODO: handle exception
				Log4j.errorLog(this, ex);
			}
			return "0";
		}
		return "1";
	}

	@Override
	public String deltCaseProcessById(String caseProcessId) {
		// TODO Auto-generated method stub
		try {
			caseManageDao.deltCaseProcessById(caseProcessId);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
	}

	@Override
	public String addCaseContact(ZqCasecontactModel zqCasecontactModel) {
		// TODO Auto-generated method stub
		try {
			caseManageDao.saveCaseContact(zqCasecontactModel);
			return "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			return "0";
		}
	}

	@Override
	public String deltCaseContactById(Integer caseContactId) {
		// TODO Auto-generated method stub
		try {
			caseManageDao.deltCaseContactById(caseContactId);
			return "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			return "0";
		}
	}

	@Override
	public String closeCase(Integer caseId, Integer status) {
		// TODO Auto-generated method stub
		try {
			caseManageDao.updateCaseStatusById(caseId, status);
			return "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			return "0";
		}
	}

	@Override
	public String deltCaseById(Integer caseId) {
		// TODO Auto-generated method stub
		String result = "0";
		try {
			caseManageDao.deltCaseById(caseId);
			result = "1";
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);

		}
		return result;
	}

	@Override
	public ZqCaseModel getCaseMessById(Integer caseId) {
		// TODO Auto-generated method stub
		ZqCaseModel zqCaseModel = caseManageDao.queryCaseMessById(caseId);
		if (zqCaseModel != null) {
			// 关联案件
			String relative = zqCaseModel.getRelative();
			if (relative != null && relative.length() > 0) {
				// 关联案件id集合
				String[] relativeIds = relative.split(",");
				// 获取关联案件的名称集合
				List relativeName = caseManageDao
						.queryCaseNameArray(relativeIds);
				// 放入案件信息中
				zqCaseModel.setRelativeCaseNames(relativeName);
			}
			// 获取案件事件流程信息
			List<ZqCaseprocessModel> zqCaseprocessModels = caseManageDao
					.queryCaseProcessByCaseId(caseId);
			zqCaseModel.setZqCaseprocessModels(zqCaseprocessModels);
			// 获取案件联系人信息
			List<ZqCasecontactModel> zqCasecontactModels = caseManageDao
					.queryContactByCaseId(caseId);
			zqCaseModel.setZqCasecontactModels(zqCasecontactModels);
		}
		return zqCaseModel;
	}

	@Override
	public String modifyCaseMessById(ZqCaseModel zqCaseModel,
			Integer[] lawyerId, ZqUserModel zqUserModel) {
		// TODO Auto-generated method stub
		String result = "0";
		ZqContractcoscusModel[] zqContractcoscusModels = null;
		// 存在协办律师
		if (lawyerId != null && lawyerId.length > 0) {
			int length = lawyerId.length;
			zqContractcoscusModels = new ZqContractcoscusModel[length];
			for (int i = 0; i < length; i++) {
				ZqContractcoscusModel zqContractcoscusModel = new ZqContractcoscusModel();
				zqContractcoscusModel.setLawyerId(lawyerId[i]);
				// 生成协办律师信息实例
				zqContractcoscusModels[i] = zqContractcoscusModel;
			}
		}
		if (zqCaseModel != null) {

			try {
				caseManageDao.updateCaseAndIdentity(zqCaseModel,
						zqContractcoscusModels);
				result = "1";
			} catch (HibernateException e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);
			}
		}
		return result;
	}

	@Override
	public ZqCaseModel getCaseBeforeContById(Integer caseId) {
		// TODO Auto-generated method stub
		ZqCaseModel zqCaseModel = caseManageDao.queryCaseBeforeContById(caseId);
		if (zqCaseModel != null) {
			// 关联案件
			String relative = zqCaseModel.getRelative();
			if (relative != null && relative.length() > 0) {
				// 关联案件id集合
				String[] relativeIds = relative.split(",");
				// 获取关联案件的名称集合
				List relativeName = caseManageDao
						.queryCaseNameArray(relativeIds);
				// 放入案件信息中
				zqCaseModel.setRelativeCaseNames(relativeName);
			}
			// 获取案件事件流程信息
			List<ZqCaseprocessModel> zqCaseprocessModels = caseManageDao
					.queryCaseProcessByCaseId(caseId);
			zqCaseModel.setZqCaseprocessModels(zqCaseprocessModels);
			// 获取案件联系人信息
			List<ZqCasecontactModel> zqCasecontactModels = caseManageDao
					.queryContactByCaseId(caseId);
			zqCaseModel.setZqCasecontactModels(zqCasecontactModels);
		}
		return zqCaseModel;
	}

	@Override
	public ZqCaseprocessModel getCaseProcessById(String processId) {
		// TODO Auto-generated method stub
		return caseManageDao.queryCaseProcessById(processId);
	}

	@Override
	public String modifyCaseProcess(ZqCaseprocessModel zqCaseprocessModel,
			String fileids, Integer[] deltFileIds) {
		// TODO Auto-generated method stub
		String[] fileid_array = null;
		ZqBusFileModel[] zqBusFileModel_Array = null;
		// 案件事件id
		String processId = zqCaseprocessModel.getId();
		try {
			// 存在需要新增的附件
			if (fileids!=null&&fileids.length() > 0) {
				fileid_array = fileids.substring(0, fileids.length() - 1)
						.split(",");
				int length = fileid_array.length;
				zqBusFileModel_Array = new ZqBusFileModel[length];
				for (int i = 0; i < length; i++) {
					ZqBusFileModel zqBusFileModel = new ZqBusFileModel();
					// 业务id
					zqBusFileModel.setBusId(processId);
					// 附件id
					zqBusFileModel.setFileId(Integer.valueOf(fileid_array[i]));
					zqBusFileModel_Array[i] = zqBusFileModel;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			return "0";
		}

		// 操作人id
		zqCaseprocessModel.setCreater(MethodUtils.getUserInfoModel().getId());
		try {
			// 添加案件事件
			caseManageDao.modifyCaseProcess(zqCaseprocessModel,
					zqBusFileModel_Array,deltFileIds);
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
			try {
				// 删除全部附件
				if (fileid_array != null) {
					caseManageDao.deltAllFileByids(fileid_array);
				}
			} catch (Exception ex) {
				// TODO: handle exception
				Log4j.errorLog(this, ex);
			}
			return "0";
		}
		return "1";
	}

	@Override
	public ZqCasecontactModel getCaseContactById(Integer caseContactId) {
		// TODO Auto-generated method stub
		return caseManageDao.queryCasecontactById(caseContactId);
	}

	@Override
	public String modifyCaseContact(ZqCasecontactModel zqCasecontactModel) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			caseManageDao.updateCaseContact(zqCasecontactModel);
			result = "1";
		}catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

}
