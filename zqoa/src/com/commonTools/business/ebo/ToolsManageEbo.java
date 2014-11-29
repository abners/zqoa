package com.commonTools.business.ebo;

import java.util.List;

import org.hibernate.HibernateException;

import com.commonTools.business.ebi.ToolsManageEbi;
import com.commonTools.dao.dao.ToolsManageDao;
import com.commonTools.vo.ZqAssetModel;
import com.commonTools.vo.ZqAssetsTypeModel;
import com.commonTools.vo.ZqToolsModel;
import com.commonTools.vo.ZqlToolstypeModel;
import com.util.Log4j;
import com.util.MethodUtils;

/**
 * 工具管理业务层实现
 * 
 * @author Alert
 * @since 2013-9-7下午08:23:42
 */
public class ToolsManageEbo implements ToolsManageEbi {
	private ToolsManageDao toolsManageDao;

	@Override
	public String addType(String typeName) {
		// TODO Auto-generated method stub
		// 创建工具类型实例
		ZqlToolstypeModel tblToolstypeModel = new ZqlToolstypeModel();
		tblToolstypeModel.setTypeName(typeName);
		// 检测是否已存在同名类型
		if (checkExistTypeName(typeName)) {
			try {
				toolsManageDao.addToolsType(tblToolstypeModel);
				return "1";
			} catch (Exception e) {
				// TODO: handle exception
				// 写入日志
				Log4j.logMess("工具类型新增失败" + e.getMessage());
				e.printStackTrace();
				return "0";
			}

		}
		return "2";
	}

	/**
	 * 不存在返回true
	 * 
	 * @param typeName
	 * @return
	 */
	private boolean checkExistTypeName(String typeName) {
		// TODO Auto-generated method stub
		return toolsManageDao.countTypeByName(typeName) == 0 ? true : false;
	}

	public ToolsManageDao getToolsManageDao() {
		return toolsManageDao;
	}

	public void setToolsManageDao(ToolsManageDao toolsManageDao) {
		this.toolsManageDao = toolsManageDao;
	}

	@Override
	public List<ZqlToolstypeModel> getAllTooleType() {
		// TODO Auto-generated method stub
		return toolsManageDao.queryAllToolsType();
	}

	@Override
	public String removeTypeById(Integer typeId) {
		// TODO Auto-generated method stub
		// 检测是否已存在同名类型
		if (checkIfUsedByTools(typeId)) {
			try {
				toolsManageDao.deltToolsType(typeId);
				return "1";
			} catch (Exception e) {
				// TODO: handle exception
				// 写入日志
				Log4j.errorLog(this, e);
				return "0";
			}

		}
		return "2";
	}

	private boolean checkIfUsedByTools(Integer typeId) {
		// TODO Auto-generated method stub

		return toolsManageDao.countTypeUserByTools(typeId) == 0 ? true : false;
	}

	@Override
	public String modifyToolsType(ZqlToolstypeModel zqlToolstypeModel) {
		// TODO Auto-generated method stub
		// 检测是否已存在同名类型
		if (checkExistTypeName(zqlToolstypeModel.getTypeName())) {
			try {
				toolsManageDao.updateToolsType(zqlToolstypeModel);
				return "1";
			} catch (Exception e) {
				// TODO: handle exception
				// 写入日志
				Log4j.errorLog(this, e);
				return "0";
			}
		}
		return "2";
	}

	@Override
	public String addTools(ZqToolsModel zqToolsModel) {
		// TODO Auto-generated method stub
		String result = "0";
		zqToolsModel.setCreateTime(MethodUtils.getToDayDate("yyyy-MM-dd"));
		zqToolsModel.setAuthor(MethodUtils.getUserInfoModel().getId());
		try {
			toolsManageDao.saveTools(zqToolsModel);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);

		}
		return result;
	}

	@Override
	public List<ZqToolsModel> queryAllTools() {
		// TODO Auto-generated method stub
		return toolsManageDao.queryAllTools();
	}

	@Override
	public ZqToolsModel getToolsById(Integer toolsId) {
		// TODO Auto-generated method stub
		return toolsManageDao.queryToolsById(toolsId);
	}

	@Override
	public String modifyTools(ZqToolsModel zqToolsModel) {
		// TODO Auto-generated method stub
		String result = "0";
		zqToolsModel.setCreateTime(MethodUtils.getToDayDate("yyyy-MM-dd"));
		zqToolsModel.setAuthor(MethodUtils.getUserInfoModel().getId());
		try {
			toolsManageDao.updateTools(zqToolsModel);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);

		}
		return result;
	}

	@Override
	public String deltToolsById(Integer toolsId) {
		// TODO Auto-generated method stub
		String result = "0";
		try {
			toolsManageDao.deltTools(toolsId);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);

		}
		return result;
	}

	@Override
	public List<ZqAssetsTypeModel> getAllAssetsType() {
		// TODO Auto-generated method stub
		return toolsManageDao.queryAllAssetsType();
	}

	@Override
	public String addAssetsType(String typeName) {
		// TODO Auto-generated method stub
		ZqAssetsTypeModel zqAssetsTypeModel = new ZqAssetsTypeModel(typeName);
		String result = "0";
		if (checkExistAssetsTypeName(typeName)) {
			try {
				toolsManageDao.addAssetsType(zqAssetsTypeModel);
				result = "1";
			} catch (HibernateException e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);

			}
		}else {
			result = "2";
		}
		return result;

	}

	private boolean checkExistAssetsTypeName(String typeName) {
		// TODO Auto-generated method stub
		return toolsManageDao.countAssetsTypeByName(typeName)==0?true:false;
	}

	@Override
	public String modifyAssetsType(ZqAssetsTypeModel zqAssetsTypeModel) {
		// TODO Auto-generated method stub
		String result = "0";
		if (checkExistAssetsTypeName(zqAssetsTypeModel.getTypeName())) {
			try {
				toolsManageDao.updateAssetsType(zqAssetsTypeModel);
				result = "1";
			} catch (HibernateException e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);

			}
		}else {
			result = "2";
		}
		return result;
	}

	@Override
	public String deltAssetsById(Integer typeId) {
		// TODO Auto-generated method stub
		String result = "0";
		if (checkIfAssetsTypeInUse(typeId)) {
			try {
				toolsManageDao.deltAssetsType(typeId);
				result = "1";
			} catch (HibernateException e) {
				// TODO: handle exception
				Log4j.errorLog(this, e);

			}
		}else {
			result = "2";
		}
		return result;
	}

	private boolean checkIfAssetsTypeInUse(Integer typeId) {
		// TODO Auto-generated method stub
		return toolsManageDao.countAssetsTypeInUse(typeId)==0?true:false;
	}

	@Override
	public String addAssets(ZqAssetModel zqAssetModel) {
		// TODO Auto-generated method stub
		String result = "0";

		try {
			toolsManageDao.saveAssets(zqAssetModel);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);

		}
		return result;
	}

	@Override
	public List<ZqAssetModel> getAllAssets() {
		// TODO Auto-generated method stub
		return toolsManageDao.queryAllAssets();
	}

	@Override
	public ZqAssetModel getAssetById(Integer assetId) {
		// TODO Auto-generated method stub
		return toolsManageDao.queryAssetbyId(assetId);
	}

	@Override
	public String saveAssetModify(ZqAssetModel zqAssetModel) {
		// TODO Auto-generated method stub
		String result = "0";

		try {
			toolsManageDao.saveAssetsModify(zqAssetModel);
			result = "1";
		} catch (HibernateException e) {
			// TODO: handle exception
			Log4j.errorLog(this, e);

		}
		return result;
	}

}
