package com.FileManage.business.ebo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.FileManage.business.ebi.FileManageEbi;
import com.FileManage.dao.dao.FileManageDao;
import com.FileManage.vo.ZqFileModel;
import com.caseManage.vo.ZqCasecontactModel;
import com.util.ExcelFileBean;
import com.util.Log4j;

/**
 * 文件管理业务层实现
 * @author peng
 * @since 2013-10-22下午07:34:36
 */
public class FileManageEbo implements FileManageEbi {

	private FileManageDao fileManageDao;
	private static final String[] header = {"名称","称谓","联系电话","手机","电子邮箱","通讯地址"};
	
	public FileManageDao getFileManageDao() {
		return fileManageDao;
	}

	public void setFileManageDao(FileManageDao fileManageDao) {
		this.fileManageDao = fileManageDao;
	}

	@Override
	public Integer addFile(ZqFileModel zqFileModel) {
		// TODO Auto-generated method stub
		Integer fileId = null;
		try{
			fileId = fileManageDao.addFile(zqFileModel);
			
			
		}catch (HibernateException e) {
			// TODO: handle exception
			return null;
		}
		return fileId;
		
	}

	@Override
	public InputStream getExcelInputStreamByCaseId(Integer caseId,File contactTemplate) {
		// TODO Auto-generated method stub
		List<ZqCasecontactModel> zqCasecontactModels = fileManageDao.queryContactByCaseId(caseId);
		List<List<Object>> datalList = new ArrayList<List<Object>>();
		if(zqCasecontactModels!=null){
			for(ZqCasecontactModel zqCasecontactModel:zqCasecontactModels){
				List<Object> list = new ArrayList<Object>();
				list.add(zqCasecontactModel.getName());
				list.add(zqCasecontactModel.getRoleName());
				list.add(zqCasecontactModel.getTel());
				list.add(zqCasecontactModel.getMobile());
				list.add(zqCasecontactModel.getEmail());
				list.add(zqCasecontactModel.getAddress());
				
				datalList.add(list);
			}
		}
		ExcelFileBean efb = new ExcelFileBean();
		efb.setHeader(header);
		efb.setData(datalList);
		efb.setTemplateFile(contactTemplate);
		efb.setFileStrategy(ExcelFileBean.Excel_File_TEMPLATE);
		try {
			
			return efb.toByteArrayInputStream();
			
		}catch (Exception e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return null;
	}

	@Override
	public ZqFileModel getFileById(String fileId) {
		// TODO Auto-generated method stub
		return fileManageDao.queryFileById(fileId);
	}

	@Override
	public String updateFileByModel(ZqFileModel zqFileModel) {
		// TODO Auto-generated method stub
		String result = "0";
		try{
			fileManageDao.updateFile(zqFileModel);
			result = "1";
		}catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);
		}
		return result;
	}

}
