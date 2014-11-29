package com.executiveManage.business.ebi;

import java.util.List;

import com.caseManage.vo.ZqCaseprocessModel;
import com.executiveManage.vo.IndexModel;
import com.executiveManage.vo.ZqAsrModel;
import com.executiveManage.vo.ZqNoticeModel;
import com.executiveManage.vo.ZqSerrecordModel;
import com.personalManage.vo.ZqDepartmentModel;

/**
 * 
 *
 * @author peng
 * @since 2013-11-10上午11:33:15
 */
public interface ExecutiveEbi {

	/**
	 * 获取登录用户的近期提醒事件
	 * @return
	 */
	List<ZqCaseprocessModel> getCaseProcess();

	/**
	 * 获取用户的所有待办和已办的合同或案件
	 * @return
	 */
	IndexModel getAllThings();

	/**
	 * 获取所有部门信息
	 * @return
	 */
	List<ZqDepartmentModel> getAllDepartment();

	/**
	 * 保存添加的行政事务
	 * @param zqAsrModel
	 * @return 0:失败 1:成功
	 */
	String addAsr(ZqAsrModel zqAsrModel);

	/**
	 * 查询所有行政事务
	 * @return
	 */
	List<ZqAsrModel> getAllAsr();

	/**
	 * 获取行政事务信息
	 * @param asrId 行政事务id
	 * @return
	 */
	ZqAsrModel getAsrById(Integer asrId);

	/**
	 * 更新行政事务信息
	 * @param zqAsrModel
	 * @return
	 */
	String modifyAsr(ZqAsrModel zqAsrModel);
	/**
	 * 移除行政事务by id
	 * @param asrId
	 * @return
	 */
	String removeAsrById(Integer asrId);

	/**
	 * 添加公告
	 * @param zqNoticeModel 公告信息
	 * @return
	 */
	String addZqAnnounce(ZqNoticeModel zqNoticeModel);

	/**
	 * 查询所有公告信息
	 * @return
	 */
	List<ZqNoticeModel> getAllNotice();

	/**
	 * 获取公告信息
	 * @param asrId
	 * @return
	 */
	ZqNoticeModel getNoticeById(Integer asrId);

	/**
	 * 删除公告信息
	 * @param asrId
	 * @return 0:删除失败 1：操做成功
	 */
	String deltAnnounce(Integer asrId);

	/**
	 * 保存修改的公告
	 * @param zqNoticeModel
	 * @return
	 */
	String saveModifyNotice(ZqNoticeModel zqNoticeModel);

	/**
	 * 
	 * @param zqSerrecordModel
	 * @return
	 */
	String addSerRecord(ZqSerrecordModel zqSerrecordModel);

	/**
	 * 根据用户权限查询其下的事物
	 * @return
	 */
	List<ZqSerrecordModel> queryAllSerRecord();

	/**
	 * 
	 * @param asrId
	 * @return
	 */
	ZqSerrecordModel getSerRecordById(Integer asrId);

	/**
	 * 查询用户的承办相关信息
	 * @param userId
	 * @return
	 */
	IndexModel queryAllUserCase_Cont(Integer userId);

}
