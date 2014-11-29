package com.executiveManage.business.ebo;

import java.util.List;

import org.hibernate.HibernateException;

import com.caseManage.vo.ZqCaseprocessModel;
import com.executiveManage.business.ebi.ExecutiveEbi;
import com.executiveManage.dao.dao.ExecutiveDao;
import com.executiveManage.vo.IndexModel;
import com.executiveManage.vo.ZqAsrModel;
import com.executiveManage.vo.ZqNoticeModel;
import com.executiveManage.vo.ZqSerrecordModel;
import com.login.vo.ZqUserModel;
import com.login.vo.ZqUserinfoModel;
import com.personalManage.vo.ZqDepartmentModel;
import com.util.Log4j;
import com.util.MethodUtils;

/**
 * 
 * 
 * @author peng
 * @since 2013-11-10上午11:37:38
 */
public class ExecutiveEbo implements ExecutiveEbi {

	private ExecutiveDao executiveDao;

	public ExecutiveDao getExecutiveDao() {
		return executiveDao;
	}

	public void setExecutiveDao(ExecutiveDao executiveDao) {
		this.executiveDao = executiveDao;
	}

	@Override
	public List<ZqCaseprocessModel> getCaseProcess() {
		// TODO Auto-generated method stub
		ZqUserModel zqUserModel = MethodUtils.getUserInfoModel();
		return executiveDao.queryProcessByUserId(zqUserModel.getId());
	}

	@Override
	public IndexModel getAllThings() {
		// TODO Auto-generated method stub
		ZqUserModel zqUserModel = MethodUtils.getUserInfoModel();
		return executiveDao.queryAllThings(zqUserModel);
	}

	@Override
	public List<ZqDepartmentModel> getAllDepartment() {
		// TODO Auto-generated method stub
		return executiveDao.queryAllDepartment();
	}

	@Override
	public String addAsr(ZqAsrModel zqAsrModel) {
		// TODO Auto-generated method stub
		String result = "0";
		// 创建人id
		zqAsrModel.setAuthor(MethodUtils.getUserInfoModel().getId());
		// 创建时间
		zqAsrModel.setCreateTime(MethodUtils.getToDayDate("yyyyMMdd"));
		try {
			executiveDao.saveAsr(zqAsrModel);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public List<ZqAsrModel> getAllAsr() {
		// TODO Auto-generated method stub

		return executiveDao.queryAllAsr();
	}

	@Override
	public ZqAsrModel getAsrById(Integer asrId) {
		// TODO Auto-generated method stub
		return executiveDao.queryAsrById(asrId);
	}

	@Override
	public String modifyAsr(ZqAsrModel zqAsrModel) {
		// TODO Auto-generated method stub
		String result = "0";
		try {
			executiveDao.updateAsr(zqAsrModel);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String removeAsrById(Integer asrId) {
		// TODO Auto-generated method stub
		String result = "0";
		try {
			executiveDao.deltAsrById(asrId);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String addZqAnnounce(ZqNoticeModel zqNoticeModel) {
		// TODO Auto-generated method stub

		String result = "0";
		// 创建人id
		zqNoticeModel.setAuthor(MethodUtils.getUserInfoModel().getId());

		try {
			executiveDao.saveZqNotice(zqNoticeModel);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public List<ZqNoticeModel> getAllNotice() {
		// TODO Auto-generated method stub
		return executiveDao.queryAllNotice();
	}

	@Override
	public ZqNoticeModel getNoticeById(Integer asrId) {
		// TODO Auto-generated method stub
		return executiveDao.queryNoticeById(asrId);
	}

	@Override
	public String deltAnnounce(Integer asrId) {
		// TODO Auto-generated method stub
		String result = "0";

		try {
			executiveDao.deltAnnounceById(asrId);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String saveModifyNotice(ZqNoticeModel zqNoticeModel) {
		// TODO Auto-generated method stub
		String result = "0";
		try {
			executiveDao.updateNotice(zqNoticeModel);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

	@Override
	public String addSerRecord(ZqSerrecordModel zqSerrecordModel) {
		// TODO Auto-generated method stub
		String result = "0";
		if (zqSerrecordModel != null) {
			//创建人id
			zqSerrecordModel.setAuthor(MethodUtils.getUserInfoModel().getId());
			//创建时间
			zqSerrecordModel.setCreateTime(MethodUtils.getToDayDate("yyyy-MM-dd"));
			try {
				executiveDao.saveSerRecord(zqSerrecordModel);
				result = "1";
			} catch (HibernateException e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);
			}
		}
		return result;
	}

	@Override
	public List<ZqSerrecordModel> queryAllSerRecord() {
		// TODO Auto-generated method stub
		ZqUserModel zqUserModel = MethodUtils.getUserInfoModel();
		
		return executiveDao.queryAllSerRecordByUser(zqUserModel);
	}

	@Override
	public ZqSerrecordModel getSerRecordById(Integer asrId) {
		// TODO Auto-generated method stub
		return executiveDao.SerRecordById(asrId);
	}

	@Override
	public IndexModel queryAllUserCase_Cont(Integer userId) {
		// TODO Auto-generated method stub
		return executiveDao.queryAllUserCase_Cont(userId);
	}

}
