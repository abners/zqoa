package com.FileManage.dao.dao;

import java.util.List;

import com.FileManage.vo.ZqFileModel;
import com.caseManage.vo.ZqCasecontactModel;

/**
 * 文件管理持久层接口
 * @author peng
 * @since 2013-10-22下午08:35:32
 */
public interface FileManageDao {

	/**
	 * 添加文件
	 * @param fileModel
	 * @return 文件id
	 */
	public Integer addFile(ZqFileModel fileModel);

	/**
	 * 查询案件联系人信息
	 * @param caseId
	 * @return
	 */
	public List<ZqCasecontactModel> queryContactByCaseId(Integer caseId);

	/**
	 * 查询附件详细信息
	 * @param fileId
	 * @return
	 */
	public ZqFileModel queryFileById(String fileId);

	/**
	 * 更新附加信息
	 * @param zqFileModel
	 */
	public void updateFile(ZqFileModel zqFileModel);
}
