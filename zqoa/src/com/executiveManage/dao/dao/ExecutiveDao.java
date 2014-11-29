package com.executiveManage.dao.dao;

import java.util.List;

import com.caseManage.vo.ZqCaseprocessModel;
import com.executiveManage.vo.IndexModel;
import com.executiveManage.vo.ZqAsrModel;
import com.executiveManage.vo.ZqNoticeModel;
import com.executiveManage.vo.ZqSerrecordModel;
import com.login.vo.ZqUserModel;
import com.personalManage.vo.ZqDepartmentModel;

public interface ExecutiveDao {

	/**
	 * 查询用户的近期提醒事件
	 * @param id
	 * @return
	 */
	List<ZqCaseprocessModel> queryProcessByUserId(Integer id);

	/**
	 * 查询用户的所有待办和已办的合同或案件（对于管理员获取全部信息)
	 * @param zqUserModel
	 * @return
	 */
	IndexModel queryAllThings(ZqUserModel zqUserModel);

	/**
	 * 查询所有部门信息
	 * @return
	 */
	List<ZqDepartmentModel> queryAllDepartment();

	/**
	 * 保存行政事务
	 * @param zqAsrModel
	 */
	void saveAsr(ZqAsrModel zqAsrModel);

	/**
	 * c查询所有行政事务
	 * @return
	 */
	List<ZqAsrModel> queryAllAsr();

	/**
	 * 根据id查询行政事务信息
	 * @param asrId
	 * @return
	 */
	ZqAsrModel queryAsrById(Integer asrId);

	/**
	 * 更新行政事务信息
	 * @param zqAsrModel
	 */
	void updateAsr(ZqAsrModel zqAsrModel);

	/**
	 * 删除行政事务
	 * @param asrId
	 */
	void deltAsrById(Integer asrId);

	/**
	 * 添加公告
	 * @param zqNoticeModel
	 */
	void saveZqNotice(ZqNoticeModel zqNoticeModel);

	List<ZqNoticeModel> queryAllNotice();

	/**
	 * 
	 * @param asrId
	 * @return
	 */
	ZqNoticeModel queryNoticeById(Integer asrId);

	/**
	 * 
	 * @param asrId
	 */
	void deltAnnounceById(Integer asrId);

	void updateNotice(ZqNoticeModel zqNoticeModel);

	void saveSerRecord(ZqSerrecordModel zqSerrecordModel);

	/**
	 * 查询所有每日事物
	 * @param zqUserModel
	 * @return
	 */
	List<ZqSerrecordModel> queryAllSerRecordByUser(ZqUserModel zqUserModel);

	/**
	 * 
	 * @param asrId
	 * @return
	 */
	ZqSerrecordModel SerRecordById(Integer asrId);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	IndexModel queryAllUserCase_Cont(Integer userId);

}
