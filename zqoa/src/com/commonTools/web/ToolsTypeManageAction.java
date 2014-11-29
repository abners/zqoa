package com.commonTools.web;

import java.io.File;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Service;

import com.FileManage.business.ebi.FileManageEbi;
import com.FileManage.vo.ZqFileModel;
import com.commonTools.business.ebi.ToolsManageEbi;
import com.commonTools.vo.ZqAssetModel;
import com.commonTools.vo.ZqAssetsTypeModel;
import com.commonTools.vo.ZqToolsModel;
import com.commonTools.vo.ZqlToolstypeModel;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.FtpUtil;
import com.util.Log4j;
import com.util.MethodUtils;

/**
 * 工具类型管理控制类
 * 
 * @author Alert
 * @since 2013-9-7下午07:23:40
 */
public class ToolsTypeManageAction extends ActionSupport {
	// 工具类型名称
	private String typeName;
	private ToolsManageEbi toolsManageEbi;
	private FileManageEbi fileManageEbi;
	// 新增结果
	private String result;

	private ZqlToolstypeModel zqlToolstypeModel;

	//
	private Integer typeId;
	//工具id
	private Integer toolsId;

	// 文件名
	private File upload;
	private String uploadFileName;
	// 工具
	private ZqToolsModel zqToolsModel;
	private ZqAssetsTypeModel zqAssetsTypeModel;
	private ZqAssetModel zqAssetModel;
	private Integer assetId;

	public String addType() throws Exception {

		result = toolsManageEbi.addType(typeName);
		// 添加成功
		if ("1".equals(result)) {
			// this.result = "success";
			// 将用户的操作写入日志
			Log4j.logMess("用户： " + MethodUtils.getUserInfoModel().getName()
					+ "添加工具类型：" + typeName);
		} else {
			// this.result = "errror";
			Log4j.logMess("用户： " + MethodUtils.getUserInfoModel().getName()
					+ "尝试添加工具类型：" + typeName + ",但失败.....");
		}
		return SUCCESS;

	}

	/**
	 * 工具类型列表
	 * 
	 * @return
	 */
	public String toolsTypeManage() {
		List<ZqlToolstypeModel> zqlToolstypeModels = toolsManageEbi
				.getAllTooleType();
		ActionContext.getContext().getValueStack()
				.set("toolsTypeList", zqlToolstypeModels);
		return SUCCESS;
	}

	/**
	 * 删除工具类型
	 * 
	 * @return
	 */
	public String deltToolsType() {
		this.result = toolsManageEbi.removeTypeById(typeId);
		return SUCCESS;
	}

	/**
	 * 修改工具类型
	 * 
	 * @return
	 */
	public String modifyToolsType() {
		this.result = toolsManageEbi.modifyToolsType(zqlToolstypeModel);
		return SUCCESS;
	}

	/**
	 * 添加工具初始化
	 * 
	 * @return
	 */
	public String addToolsInit() {
		List<ZqlToolstypeModel> zqlToolstypeModels = toolsManageEbi
				.getAllTooleType();
		ActionContext.getContext().getValueStack()
				.set("toolsType", zqlToolstypeModels);
		return SUCCESS;
	}

	/**
	 * 添加工具
	 * 
	 * @return
	 */
	public String addTools() {
		this.result = "1";
		// 存在要上传的文件
		Log4j.logMess("-----------" + uploadFileName + "------------");
		if (uploadFileName != null) {
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/upload");
			// 生成新的文件名UUID
			String newFileName = MethodUtils.getUUID()
					+ uploadFileName.substring(uploadFileName.lastIndexOf("."));
			/* File target = new File(targetDirectory, newFileName); */

			targetDirectory += "/" + newFileName;
			try {
				// 复制文件到指定目录
				// 上传文件
				FtpUtil.uploadFiles(upload, newFileName);
				// fileId = "w332";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log4j.errorLog(this, e);
				// 文件上传失败
				result = "2";
			}
			if ("1".equals(result)) {
				// 附件实例
				ZqFileModel zqFileModel = new ZqFileModel();
				// 附件地址
				zqFileModel.setAddress("upload/" + newFileName);
				// 实际文件名
				zqFileModel.setRealFilename(newFileName);
				// 原文件名
				zqFileModel.setYwjm(uploadFileName);
				// 创建人
				zqFileModel.setCreater(MethodUtils.getUserInfoModel().getId());

				Integer newFileId = fileManageEbi.addFile(zqFileModel);
				// 文件记录添加失败
				if (newFileId == null) {
					// 删除已上传的文件
					File file = new File(targetDirectory);
					if (file.exists()) {
						file.delete();
					}
					result = "2";
				} else {
					result = "1";
					// 附件id
					zqToolsModel.setFileId(newFileId);
					// 文件名
					zqToolsModel.setYwjm(uploadFileName);
				}
			}
		}
		// 文件上传成功或没有要上传的文件
		if (result.equals("1")) {
			this.result = toolsManageEbi.addTools(zqToolsModel);
		}
		if ("1".equals(result)) {
			return SUCCESS;
		} else {
			return ERROR;
		}

	}

	/**
	 * 工具列表
	 * 
	 * @return
	 */
	public String toolsList() {
		List<ZqToolsModel> zqToolsModels = toolsManageEbi.queryAllTools();
		ActionContext.getContext().getValueStack()
				.set("toolsList", zqToolsModels);
		return SUCCESS;
	}

	public String modifyToolsInit() {
		List<ZqlToolstypeModel> zqlToolstypeModels = toolsManageEbi
				.getAllTooleType();
		ActionContext.getContext().getValueStack()
				.set("toolsType", zqlToolstypeModels);
		ZqToolsModel zqToolsModel = toolsManageEbi.getToolsById(toolsId);
		ActionContext.getContext().getValueStack().set("zqToolsModel", zqToolsModel);
		return SUCCESS;
	}
	
	public String updateTools(){
		result="1";
		if (uploadFileName != null) {
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/upload");
			// 生成新的文件名UUID
			String newFileName = MethodUtils.getUUID()
					+ uploadFileName.substring(uploadFileName.lastIndexOf("."));
			/* File target = new File(targetDirectory, newFileName); */

			targetDirectory += "/" + newFileName;
			try {
				// 复制文件到指定目录
				// 上传文件
				FtpUtil.uploadFiles(upload, newFileName);
				// fileId = "w332";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log4j.errorLog(this, e);
				// 文件上传失败
				result = "2";
			}
			//文件上传成功
			if ("1".equals(result)) {
				// 附件实例
				ZqFileModel zqFileModel = new ZqFileModel();
				// 附件地址
				zqFileModel.setAddress("upload/" + newFileName);
				// 实际文件名
				zqFileModel.setRealFilename(newFileName);
				// 原文件名
				zqFileModel.setYwjm(uploadFileName);
				// 创建人
				zqFileModel.setCreater(MethodUtils.getUserInfoModel().getId());

				// 原来不存在附件，新增附加
				if (zqToolsModel.getFileId() == null) {
					Integer newFileId = fileManageEbi.addFile(zqFileModel);
					// 文件记录添加失败
					if (newFileId == null) {
						// 删除已上传的文件
						File file = new File(targetDirectory);
						if (file.exists()) {
							file.delete();
						}
						result = "2";
					} else {
						result = "1";
						// 附件id
						zqToolsModel.setFileId(newFileId);
						// 文件名
						zqToolsModel.setYwjm(uploadFileName);
					}
				} else {
					// 更新附件信息
					zqFileModel.setId(zqToolsModel.getFileId());
					result = fileManageEbi.updateFileByModel(zqFileModel);
				}
			}
		}
		// 文件上传成功或没有要上传的文件
		if (result.equals("1")) {
			this.result = toolsManageEbi.modifyTools(zqToolsModel);
		}
		if ("1".equals(result)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	/**
	 * 删除工具
	 * @return
	 */
	public String deltTools(){
		result = toolsManageEbi.deltToolsById(toolsId);
		return SUCCESS;
	}
	/**
	 * 资产类型列表
	 * @return
	 */
	public String assetsType(){
		List<ZqAssetsTypeModel> zqAssetsTypeModels = toolsManageEbi.getAllAssetsType();
		ActionContext.getContext().getValueStack().set("assets", zqAssetsTypeModels);
		return SUCCESS;
	}
	/**
	 * 添加资产类型
	 * @return
	 * 
	 */
	public String addAssetsType(){
		this.result = toolsManageEbi.addAssetsType(typeName);
		return SUCCESS;
	}
	
	/**
	 * 修改资产类型名称
	 * @return
	 */
	public String modifyAssetsType(){
		this.result = toolsManageEbi.modifyAssetsType(zqAssetsTypeModel);
		return SUCCESS;
	}
	
	public String deltAssetsType(){
		this.result = toolsManageEbi.deltAssetsById(typeId);
		return SUCCESS;
	}
	/**
	 * 添加资产初始化
	 * @return
	 */
	public String addAssetsInit(){
		List<ZqAssetsTypeModel> zqAssetsTypeModels = toolsManageEbi.getAllAssetsType();
		ActionContext.getContext().getValueStack().set("assets", zqAssetsTypeModels);
		return SUCCESS;
	}
	/**
	 * 添加从资产
	 * @return
	 */
	public String addAssets(){
		this.result = toolsManageEbi.addAssets(zqAssetModel);
		return SUCCESS;
	}
	public String assetsList(){
		List<ZqAssetModel> zqAssetModels = toolsManageEbi.getAllAssets();
		ActionContext.getContext().getValueStack().set("assets", zqAssetModels);
		return SUCCESS;
	}
	
	/**
	 * 修改资产初始化
	 * @return
	 */
	public String modifyAssetInit(){
		ZqAssetModel zqAssetModel = toolsManageEbi.getAssetById(assetId);
		ActionContext.getContext().getValueStack().set("zqAssetModel", zqAssetModel);
		List<ZqAssetsTypeModel> zqAssetsTypeModels = toolsManageEbi.getAllAssetsType();
		ActionContext.getContext().getValueStack().set("assetsType", zqAssetsTypeModels);
		return SUCCESS;
	}
	public String assetDetail(){
		ZqAssetModel zqAssetModel = toolsManageEbi.getAssetById(assetId);
		ActionContext.getContext().getValueStack().set("asset", zqAssetModel);
		return SUCCESS;
	}
	
	public String modifyAssets(){
		this.result = toolsManageEbi.saveAssetModify(zqAssetModel);
		return SUCCESS;
	}
	@JSON(serialize=false)
	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	@JSON(serialize=false)
	public ZqAssetModel getZqAssetModel() {
		return zqAssetModel;
	}

	public void setZqAssetModel(ZqAssetModel zqAssetModel) {
		this.zqAssetModel = zqAssetModel;
	}

	@JSON(serialize=false)
	public Integer getToolsId() {
		return toolsId;
	}

	public void setToolsId(Integer toolsId) {
		this.toolsId = toolsId;
	}
	@JSON(serialize=false)
	public ZqAssetsTypeModel getZqAssetsTypeModel() {
		return zqAssetsTypeModel;
	}

	public void setZqAssetsTypeModel(ZqAssetsTypeModel zqAssetsTypeModel) {
		this.zqAssetsTypeModel = zqAssetsTypeModel;
	}

	@JSON(serialize = false)
	public ZqToolsModel getZqToolsModel() {
		return zqToolsModel;
	}

	public void setZqToolsModel(ZqToolsModel zqToolsModel) {
		this.zqToolsModel = zqToolsModel;
	}

	@JSON(serialize = false)
	public FileManageEbi getFileManageEbi() {
		return fileManageEbi;
	}

	public void setFileManageEbi(FileManageEbi fileManageEbi) {
		this.fileManageEbi = fileManageEbi;
	}

	@JSON(serialize = false)
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	@JSON(serialize = false)
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@JSON(serialize = false)
	public ZqlToolstypeModel getZqlToolstypeModel() {
		return zqlToolstypeModel;
	}

	public void setZqlToolstypeModel(ZqlToolstypeModel zqlToolstypeModel) {
		this.zqlToolstypeModel = zqlToolstypeModel;
	}

	@JSON(name = "result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@JSON(serialize = false)
	public ToolsManageEbi getToolsManageEbi() {
		return toolsManageEbi;
	}

	public void setToolsManageEbi(ToolsManageEbi toolsManageEbi) {
		this.toolsManageEbi = toolsManageEbi;
	}

	@JSON(serialize = false)
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@JSON(serialize = false)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
