package com.commonTools.dao.dao;

import java.util.List;

import com.commonTools.vo.ZqAssetModel;
import com.commonTools.vo.ZqAssetsTypeModel;
import com.commonTools.vo.ZqToolsModel;
import com.commonTools.vo.ZqlToolstypeModel;

/**
 * 工具管理持久层接口
 * @author Alert
 * @since 2013-9-7下午08:27:28
 */
public interface ToolsManageDao {
	/**
	 * 添加工具类型
	 * @param tblToolstypeModel
	 */
	public void addToolsType(ZqlToolstypeModel tblToolstypeModel);

	/**
	 * 工具类型
	 * @return
	 */
	public List<ZqlToolstypeModel> queryAllToolsType();

	/**
	 * 統計該類型名的工具類型個數
	 * @param typeName
	 * @return
	 */
	public int countTypeByName(String typeName);

	public int countTypeUserByTools(Integer typeId);

	public void deltToolsType(Integer typeId);

	/**
	 * 修改工具类型
	 * @param zqlToolstypeModel
	 */
	public void updateToolsType(ZqlToolstypeModel zqlToolstypeModel);

	/**
	 * 添加工具
	 * @param zqToolsModel
	 */
	public void saveTools(ZqToolsModel zqToolsModel);

	public List<ZqToolsModel> queryAllTools();

	/**
	 * 获取工具信息
	 * @param toolsId
	 * @return
	 */
	public ZqToolsModel queryToolsById(Integer toolsId);

	/**
	 * 更新工具信息
	 * @param zqToolsModel
	 */
	public void updateTools(ZqToolsModel zqToolsModel);

	public void deltTools(Integer toolsId);

	/**
	 * 资产类型记录
	 * @return
	 */
	public List<ZqAssetsTypeModel> queryAllAssetsType();

	/**
	 * 
	 * @param typeName
	 * @return
	 */
	public int countAssetsTypeByName(String typeName);

	public void addAssetsType(ZqAssetsTypeModel zqAssetsTypeModel);

	/**
	 * 
	 * @param zqAssetsTypeModel
	 */
	public void updateAssetsType(ZqAssetsTypeModel zqAssetsTypeModel);

	/**
	 * 统计资产类型被使用的个数
	 * @param typeId
	 * @return
	 */
	public int countAssetsTypeInUse(Integer typeId);

	public void deltAssetsType(Integer typeId);

	public void saveAssets(ZqAssetModel zqAssetModel);

	
	public List<ZqAssetModel> queryAllAssets();

	/**
	 * 获取资产详情
	 * @param assetId
	 * @return
	 */
	public ZqAssetModel queryAssetbyId(Integer assetId);

	public void saveAssetsModify(ZqAssetModel zqAssetModel);
}
