package com.FileManage.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import sun.net.www.protocol.ftp.FtpURLConnection;

import com.FileManage.business.ebi.FileManageEbi;
import com.FileManage.vo.ZqFileModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.util.FtpUtil;
import com.util.JSONUtil;
import com.util.Log4j;
import com.util.MethodUtils;

/**
 * 文件管理控制类
 * @author peng
 * @since 2013-10-16下午04:24:43
 */
public class FileManage extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	
	private File attachMent;
	private String attachMentFileName;
	private FileManageEbi fileManageEbi;
	
	private String fileId;
	
	private Integer caseId; //案件id
	private String fileName; //导出excel文件的名称
	private InputStream caseContactMess; //导出文件输入流
	
	private InputStream fileInputStream; //附件下载输入流
	
	private String filePath;
	
	private Integer contId; //合同id
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}
	/**
	 * 上传文件
	 * @return
	 * @throws Exception
	 */
	public String addFile() throws Exception{
		
		String targetDirectory = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		//生成新的文件名UUID
		String newFileName = MethodUtils.getUUID() + attachMentFileName.substring(attachMentFileName.lastIndexOf("."));
		/*File target = new File(targetDirectory, newFileName);*/
		
		targetDirectory += "/" + newFileName;
		//if (target.createNewFile()) {
			try {
				// 复制文件到指定目录
				 //FileUtils.copyFile(attachMent, target);
				// FileUtils.cop
				// 上传文件
				FtpUtil.uploadFiles(attachMent,newFileName);

				// fileId = "w332";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log4j.errorLog(this, e);
				throw e;
			}
		//}
		//附件实例
		ZqFileModel zqFileModel = new ZqFileModel();
		//附件地址
		zqFileModel.setAddress("upload/"+newFileName);
		//实际文件名
		zqFileModel.setRealFilename(newFileName);
		//原文件名
		zqFileModel.setYwjm(attachMentFileName);
		//创建人
		zqFileModel.setCreater(MethodUtils.getUserInfoModel().getId());
		
		Integer newFileId = fileManageEbi.addFile(zqFileModel);
		//文件记录添加失败
		if(newFileId==null){
			//删除已上传的文件
			File file = new File(targetDirectory);
			if(file.exists()){
				file.delete();
			}
			
			throw new Exception();
		}else{
			fileId = newFileId.toString();
		}
		
		return SUCCESS;
	}
	/**
	 * 导出案件联系人excel
	 * @return
	 */
	public String exportCaseContact(){
		//导出文件的名称
		try {
			fileName = new String(("案件联系人信息("+MethodUtils.getToDayDate("yyyy-MM-dd")+")").getBytes("gbk"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取模板文件
		String path = ServletActionContext.getServletContext().getRealPath("/excelTemplate"); //模板文件所在目录
		File file = new File(path + "/contactModel.xls");
		this.caseContactMess = fileManageEbi.getExcelInputStreamByCaseId(caseId,file);
		return SUCCESS;
	}
	
	/**
	 * 下载文件
	 * @return
	 */
	public String dowloadFile(){
		ZqFileModel zqFileModel= fileManageEbi.getFileById(fileId);
		filePath = zqFileModel.getAddress();
		if(ServletActionContext.getServletContext().getResourceAsStream("/"+filePath)==null){
			ActionContext.getContext().getValueStack().set("errorMessage", "您要下载的文件不存在或已被删除，请联系管理员!");
			return INPUT;
		}
		try {
			fileName = new String(zqFileModel.getYwjm().getBytes(),"iso8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 合同附件上传初始化
	 * @return
	 */
	public String addContFileInit(){
		
		return SUCCESS;
	}
	@JSON(serialize=false)
	public Integer getContId() {
		return contId;
	}

	public void setContId(Integer contId) {
		this.contId = contId;
	}

	@JSON(serialize=false)
	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	@JSON(serialize=false)
	public InputStream getCaseContactMess() {
		return caseContactMess;
	}

	/*public void setCaseContactMess(InputStream caseContactMess) {
		this.caseContactMess = caseContactMess;
	}*/

	@JSON(serialize=false)
	public FileManageEbi getFileManageEbi() {
		return fileManageEbi;
	}

	public void setFileManageEbi(FileManageEbi fileManageEbi) {
		this.fileManageEbi = fileManageEbi;
	}
	@JSON(serialize=false)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JSON(serialize=false)
	public File getAttachMent() {
		return attachMent;
	}
	public void setAttachMent(File attachMent) {
		this.attachMent = attachMent;
	}

	@JSON(serialize=false)
	public String getAttachMentFileName() {
		return attachMentFileName;
	}
	public void setAttachMentFileName(String attachMentFileName) {
		this.attachMentFileName = attachMentFileName;
	}
	
	@JSON(name="fileid")
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	@JSON(serialize=false)
	public InputStream getInputStream() {
		InputStream in = ServletActionContext.getServletContext().getResourceAsStream("/"+filePath);
		return in;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	
	

}
