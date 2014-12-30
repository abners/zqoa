package com.contManage.business.ebo;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;

import sun.util.logging.resources.logging;

import com.FileManage.vo.ZqBusFileModel;
import com.FileManage.vo.ZqFileModel;
import com.caseManage.vo.ZqCaseModel;
import com.contManage.business.ebi.ContracterManageEbi;
import com.contManage.dao.dao.ContracterManageDao;
import com.contManage.vo.ZqContractAffairModel;
import com.contManage.vo.ZqContractModel;
import com.contManage.vo.ZqContractcharagestageModel;
import com.contManage.vo.ZqContractcoscusModel;
import com.contManage.vo.ZqContracttypeModel;
import com.customerManage.vo.ZqCustomerModel;
import com.executiveManage.vo.IndexModel;
import com.login.vo.ZqUserModel;
import com.util.EcPageHelper;
import com.util.Log4j;
import com.util.PageBean;
import com.util.SolrJUtil;

/**
 * 合同信息管理业务层实现
 * 
 * @author peng
 * @since 2013-9-15下午08:01:40
 */
public class ContracterManageEbo extends EcPageHelper implements
		ContracterManageEbi {
	private ContracterManageDao contracterManageDao;

	public ContracterManageDao getContracterManageDao() {
		return contracterManageDao;
	}

	public void setContracterManageDao(ContracterManageDao contracterManageDao) {
		this.contracterManageDao = contracterManageDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.contManage.business.ebi.ContracterManageEbi#getCotracterTypeList(
	 * java.util.Map)
	 */
	@Override
	public PageBean getCotracterTypeList(Map conMap) {
		// TODO Auto-generated method stub
		// 初始化分页信息类
		PageBean pageBean = getPageBean(conMap);

		conMap.put("pageBean", pageBean);

		return contracterManageDao.queryContTypeList(conMap);
	}

	@Override
	public String addContractType(ZqContracttypeModel zqContracttypeModel) {
		// TODO Auto-generated method stub
		String result = "";
		String contractTypeName = zqContracttypeModel.getContractTypeName();
		Integer contractTypeId = zqContracttypeModel.getContractTypeId();

		List<ZqContracttypeModel> zqContracttypeModels = contracterManageDao
				.queryContracttypeModelsByName(contractTypeName, contractTypeId);
		// 不存在要添加或修改成的合同类型
		if (zqContracttypeModel == null || zqContracttypeModels.size() == 0) {
			try {
				contracterManageDao.addContractType(zqContracttypeModel);
				result = "1";
			} catch (Exception e) {
				// TODO: handle exception
				result = "0";
				Log4j.errorLog(this, e);
			}
		} else {
			// 已存在此合同类型
			result = "2";
		}
		return result;
	}

	@Override
	public String deltContractType(ZqContracttypeModel zqContracttypeModel) {
		// TODO Auto-generated method stub
		String result = "";
		// 检测其是否已有关联合同
		List<ZqContractModel> zqContractModels = contracterManageDao
				.queryContractByType(zqContracttypeModel.getContractTypeId());
		// 不存在关联合同
		if (zqContractModels == null || zqContractModels.size() == 0) {
			try {
				contracterManageDao.deltContractType(zqContracttypeModel);
				result = "1";
			} catch (Exception e) {
				// TODO: handle exception
				result = "0";
				Log4j.errorLog(this, e);
			}
		}
		return result;
	}

	@Override
	public List<ZqContracttypeModel> getAllCotracterType() {
		// TODO Auto-generated method stub
		return contracterManageDao.queryAllCotracterType();
	}

	@Override
	public List<ZqUserModel> getAllUser() {
		// TODO Auto-generated method stub
		return contracterManageDao.queryAllUsers();
	}

	@Override
	public List<ZqCustomerModel> getAllNowCust() {
		// TODO Auto-generated method stub
		return contracterManageDao.queryAllNowCust();
	}

	@Override
	public String addContract(ZqContractModel zqContractModel,
			Integer[] lawyerId, String[] lawyerName, Integer[] pay,
			String[] payTime) {
		// TODO Auto-generated method stub
		// 检测合同编号是否已经存在
		if (contracterManageDao.checkTheContNumIfExists(zqContractModel
				.getNumber())) {
			// 协办律师信息集合
			ZqContractcoscusModel[] zqContractcoscusModels = null;
			ZqContractcoscusModel zqContractcoscusModel = null;
			// 付款阶段信息集合
			List<ZqContractcharagestageModel> zqContractcharagestageModels = new ArrayList<ZqContractcharagestageModel>();
			ZqContractcharagestageModel zqContractcharagestageModel = null;
			// 合同存在协办律师
			if (lawyerId != null) {
				// 获取协助律师的个数
				int length = lawyerId.length;
				System.out.println(lawyerId[0]);
				zqContractcoscusModels = new ZqContractcoscusModel[length];
				System.out.println(zqContractcoscusModels.length);
				// 放入协助律师信息
				for (int i = 0; i < length; i++) {
					zqContractcoscusModel = new ZqContractcoscusModel();
					zqContractcoscusModel.setLawyerId(lawyerId[i]);
					zqContractcoscusModel.setLawyerName(lawyerName[i]);

					zqContractcoscusModels[i] = zqContractcoscusModel;
				}
			}
			// 放入付款阶段信息
			for (int i = 0; i < pay.length; i++) { 
				// 应付款时间或应付款数目不为空
				if (pay[i] != null
						|| (payTime[i] != null && !"".equals(payTime[i]))) {
					zqContractcharagestageModel = new ZqContractcharagestageModel();
					zqContractcharagestageModel.setChargeMoney(pay[i]);
					zqContractcharagestageModel.setChargeTime(payTime[i]);

					zqContractcharagestageModels
							.add(zqContractcharagestageModel);
				}
			}
			try {
				zqContractModel.setArchived("0");
				contracterManageDao.saveContract(zqContractModel,
						zqContractcoscusModels, zqContractcharagestageModels);
			} catch (Exception e) {
				return "0";
			}
			//已存在该合同编号
		} else {
			return "2";
		}

		return "1";
	}

	@Override
	public PageBean getCotracterList(Map conditionMap) {
		// TODO Auto-generated method stub
		PageBean pageBean = getPageBean(conditionMap);

		conditionMap.put("pageBean", pageBean);

		return contracterManageDao.queryContractList(conditionMap);
	}

	@Override
	public ZqContractModel getContractById(Integer contId) {
		// TODO Auto-generated method stub
		ZqContractModel zqContractModel = contracterManageDao
				.queryContractById(contId);
		if (zqContractModel != null) {
			// if("0".equals(anObject))
			// 查询合同附属案件
			List<ZqCaseModel> zqCaseModels = contracterManageDao
					.queryContTypeList(contId);
			zqContractModel.setZqCaseModels(zqCaseModels);
			// 获取合同附件
			List<ZqFileModel> zqFileModels = contracterManageDao
					.queryContFile(contId);
			zqContractModel.setZqFileModels(zqFileModels);
		}
		return zqContractModel;
	}

	@Override
	public List<ZqContractcharagestageModel> getCharageStageByContid(
			Integer contId) {
		// TODO Auto-generated method stub
		return contracterManageDao.queryCharageStageByContid(contId);
	}

	@Override
	public List<ZqContractcoscusModel> getCoscusByContid(Integer contId) {
		// TODO Auto-generated method stub
		return contracterManageDao.queryCoscusByContid(contId);
	}

	@Override
	public String deltCoscusById(Integer coscusid) {
		// TODO Auto-generated method stub
		ZqContractcoscusModel contractcoscusModel = new ZqContractcoscusModel();
		contractcoscusModel.setId(coscusid);
		try {
			contracterManageDao.deltCoscusByid(contractcoscusModel);
		} catch (DataAccessException e) {
			// TODO: handle exception
			Log4j.logMess(e.getMessage());
			return "0";
		}
		return "1";
	}

	@Override
	public String updateContract(Integer[] chargeId,
			ZqContractModel zqContractModel, Integer[] lawyerId,
			String[] lawyerName, Integer[] pay, String[] payTime) {
		// TODO Auto-generated method stub
		// 协办律师信息集合
		ZqContractcoscusModel[] zqContractcoscusModels = null;
		ZqContractcoscusModel zqContractcoscusModel = null;
		// 付款阶段信息集合
		List<ZqContractcharagestageModel> zqContractcharagestageModels = new ArrayList<ZqContractcharagestageModel>();
		ZqContractcharagestageModel zqContractcharagestageModel = null;
		String result = "0";

		// 合同id
		Integer contId = zqContractModel.getId();

		// 合同存在协办律师
		if (lawyerId != null) {
			// 获取协助律师的个数
			int length = lawyerId.length;
			// System.out.println(lawyerId[0]);
			zqContractcoscusModels = new ZqContractcoscusModel[length];
			// System.out.println(zqContractcoscusModels.length);
			// 放入协助律师信息
			for (int i = 0; i < length; i++) {
				zqContractcoscusModel = new ZqContractcoscusModel();
				zqContractcoscusModel.setContractId(contId);
				zqContractcoscusModel.setLawyerId(lawyerId[i]);
				zqContractcoscusModel.setLawyerName(lawyerName[i]);

				zqContractcoscusModels[i] = zqContractcoscusModel;
			}
		}
		// 放入付款阶段信息
		for (int i = 0; i < pay.length; i++) {
			// 应付款时间或应付款数目不为空
			if (pay[i] != null
					|| (payTime[i] != null && !"".equals(payTime[i]))) {
				zqContractcharagestageModel = new ZqContractcharagestageModel();
				zqContractcharagestageModel.setContractId(contId);
				zqContractcharagestageModel.setChargeMoney(pay[i]);
				zqContractcharagestageModel.setChargeTime(payTime[i]);

				zqContractcharagestageModels.add(zqContractcharagestageModel);
			}
		}
		try {
			contracterManageDao.updateContract(chargeId, zqContractModel,
					zqContractcoscusModels, zqContractcharagestageModels);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			Log4j.logMess(e.getMessage());

		}
		return result;
	}

	@Override
	public String deltContractById(Integer contId) {
		// TODO Auto-generated method stub
		String result = "0";
		try {
			if (contracterManageDao.countCaseInCont(contId) == 0) {
				contracterManageDao.deletContById(contId);
				result = "1";
			} else {
				result = "2";
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public List<Map> getContractByTypeId(Integer contTypeId) {
		// TODO Auto-generated method stub
		if (contTypeId != null) {
			try {
				return contracterManageDao.queryContractByTypeId(contTypeId);
			} catch (Exception e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);
			}
		}
		return null;
	}

	@Override
	public String addContAffair(ZqContractAffairModel zqContractAffairModel) {
		// TODO Auto-generated method stub
		String result = "0";
		if (zqContractAffairModel != null) {
			try {
				contracterManageDao.addContAffair(zqContractAffairModel);
				result = "1";
			} catch (HibernateException e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);
			}
		}
		return result;
	}

	@Override
	public List<ZqContractAffairModel> viewAffairByContId(Integer contId) {
		// TODO Auto-generated method stub
		List<ZqContractAffairModel> zqContractAffairModels = new ArrayList<ZqContractAffairModel>();
		if (contId != null) {
			List list = contracterManageDao.queryContAffairByContId(contId);
			if (list != null) {
				Iterator<Object[]> iterator = list.iterator();
				while (iterator.hasNext()) {
					Object[] objects = iterator.next();
					ZqContractAffairModel zqContractAffairModel = (ZqContractAffairModel) objects[0];
					zqContractAffairModel.setContName((String) objects[1]);
					zqContractAffairModel.setCreater((String) objects[2]);

					zqContractAffairModels.add(zqContractAffairModel);
				}
			}

		}
		return zqContractAffairModels;
	}

	@Override
	public String arachiveCont(Integer contId, String archived) {
		// TODO Auto-generated method stub
		String result = "0";
		try {
			contracterManageDao.updateContractArchived(contId, archived);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String addContFile(String fileids, ZqBusFileModel zqBusFileModel) {
		// TODO Auto-generated method stub
		String result = "0";
		// 文件id不为空
		if (fileids != null) {
			String[] fileid = fileids.split(",");
			try {
				contracterManageDao.saveContFile(fileid, zqBusFileModel);
				result = "1";
			} catch (HibernateException e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);

			}
		}
		return result;
	}

	@Override
	public String deltContFileById(Integer fileId) {
		// TODO Auto-generated method stub
		String result = "0";
		// 文件id不为空
		if (fileId != null) {
			try {
				contracterManageDao.deltContFile(fileId);
				result = "1";
			} catch (Exception e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);
			}
		}
		return result;
	}

	@Override
	public String updateRegFees(Integer contId, Integer[] chargeId,
			Integer[] payMoney, String[] payTime, String[] chargeTime,
			Integer[] chargeMoney) {
		// TODO Auto-generated method stub
		String result = "0";
		try {
			if (chargeId != null) {
				int length = chargeId.length;
				// 付费阶段集合数组
				ZqContractcharagestageModel[] zqContractcharagestageModels = new ZqContractcharagestageModel[length];
				// 放入要付费的信息
				for (int i = 0; i < length; i++) {
					ZqContractcharagestageModel zqContractcharagestageModel = new ZqContractcharagestageModel();
					zqContractcharagestageModel.setChargeMoney(chargeMoney[i]);
					zqContractcharagestageModel.setId(chargeId[i]);
					zqContractcharagestageModel.setChargeTime(chargeTime[i]);
					zqContractcharagestageModel.setContractId(contId);
					// 当用户未填写任何值时，应付费为null
					if (payMoney[i] == null) {
						zqContractcharagestageModel.setRealChargeMoney(0);
					} else {
						zqContractcharagestageModel
								.setRealChargeMoney(payMoney[i]);
					}
					zqContractcharagestageModel.setRealChargeTime(payTime[i]);
					zqContractcharagestageModels[i] = zqContractcharagestageModel;
				}

				contracterManageDao.updateRegFees(zqContractcharagestageModels);

				result = "1";
			} else {
				// 合同付费阶段不存在
				result = "2";
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String addArchive(ZqContractModel zqContractModel,
			Integer[] lawyerId, String[] lawyerName, Integer[] pay,
			String[] payTime) {
		// TODO Auto-generated method stub
		// 检测合同编号是否已经存在
		if (contracterManageDao.checkTheContNumIfExists(zqContractModel
				.getNumber())) {
			// 协办律师信息集合
			ZqContractcoscusModel[] zqContractcoscusModels = null;
			ZqContractcoscusModel zqContractcoscusModel = null;
			// 付款阶段信息集合
			List<ZqContractcharagestageModel> zqContractcharagestageModels = new ArrayList<ZqContractcharagestageModel>();
			ZqContractcharagestageModel zqContractcharagestageModel = null;
			
			// 合同存在协办律师
			if (lawyerId != null) {
				// 获取协助律师的个数
				int length = lawyerId.length;
//				System.out.println(lawyerId[0]);
				zqContractcoscusModels = new ZqContractcoscusModel[length];
//				System.out.println(zqContractcoscusModels.length);
				// 放入协助律师信息
				for (int i = 0; i < length; i++) {
					zqContractcoscusModel = new ZqContractcoscusModel();
					zqContractcoscusModel.setLawyerId(lawyerId[i]);
					zqContractcoscusModel.setLawyerName(lawyerName[i]);

					zqContractcoscusModels[i] = zqContractcoscusModel;
				}
			}
			// 放入付款阶段信息
			for (int i = 0; i < pay.length; i++) { 
				// 应付款时间或应付款数目不为空
				if (pay[i] != null
						|| (payTime[i] != null && !"".equals(payTime[i]))) {
					zqContractcharagestageModel = new ZqContractcharagestageModel();
					zqContractcharagestageModel.setChargeMoney(pay[i]);
					zqContractcharagestageModel.setChargeTime(payTime[i]);

					zqContractcharagestageModels
							.add(zqContractcharagestageModel);
				}
			}
			//是否归档：1已归档
			zqContractModel.setArchived("1");
			try {
				contracterManageDao.saveContract(zqContractModel,
						zqContractcoscusModels, zqContractcharagestageModels);
			} catch (Exception e) {
				// TODO: handle exception
				return "0";
			}
			//已存在该合同编号
		} else {
			return "2";
		}

		return "1";
	}

	@Override
	public IndexModel fullSearchCaseAndContract(String searchContent) {
		// TODO Auto-generated method stub
		try {
			SolrResponse response = SolrJUtil.fullTextSearch(searchContent);
			Log4j.logMess(response.toString());
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
