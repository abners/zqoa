package com.FileManage.business.ebi;

import java.io.File;
import java.io.InputStream;

import com.FileManage.vo.ZqFileModel;

/**
 * 文件管理业务层接口
 * @author peng
 * @since 2013-10-22下午07:33:51
 */
public interface FileManageEbi {
	
	/**
	 * 添加文件
	 * @param zqFileModel 文件实例
	 * @return 文件id
	 */
	public Integer addFile(ZqFileModel zqFileModel);

	/**
	 * 获取案件联系人的信息并将其通过poi转换为输入流
	 * @param caseId
	 * @param file 模板文件
	 * @return
	 */
	public InputStream getExcelInputStreamByCaseId(Integer caseId, File file);

	/**
	 * 获取附件详细信息
	 * @param fileId
	 * @return
	 */
	public ZqFileModel getFileById(String fileId);

	/**
	 * 更新附件信息
	 * @param zqFileModel
	 * @return
	 */
	public String updateFileByModel(ZqFileModel zqFileModel);
}
