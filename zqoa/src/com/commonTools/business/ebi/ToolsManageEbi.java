package com.commonTools.business.ebi;

import java.util.List;

import com.commonTools.vo.ZqAssetModel;
import com.commonTools.vo.ZqAssetsTypeModel;
import com.commonTools.vo.ZqToolsModel;
import com.commonTools.vo.ZqlToolstypeModel;

/**
 * 工具管理业务层接口
 * @author Alert
 * @since 2013-9-7下午08:22:03
 */
public interface ToolsManageEbi {
	/**
	 * 添加工具类型
	 * @param typeName 类型名称
	 * @return 0:true, else 失败
	 */
	public String addType(String typeName);

	/**
	 * 查询所有的工具类型
	 * @return
	 */
	public List<ZqlToolstypeModel> getAllTooleType();

	/**
	 * 
	 * @param typeId
	 * @return 0：删除失败，1：删除成功2：已被使用
	 */
	public String removeTypeById(Integer typeId);

	/**
	 * 修改工具类型
	 * @param zqlToolstypeModel
	 * @return
	 */
	public String modifyToolsType(ZqlToolstypeModel zqlToolstypeModel);

	/**
	 * 添加工具
	 * @param zqToolsModel
	 * @return 1：成功 0：失败 3：存在同名工具
	 */
	public String addTools(ZqToolsModel zqToolsModel);

	/**
	 * 
	 * @return
	 */
	public List<ZqToolsModel> queryAllTools();

	/**
	 * 
	 * @param toolsId
	 * @return
	 */
	public ZqToolsModel getToolsById(Integer toolsId);

	/**
	 * 更新工具信息
	 * @param zqToolsModel
	 * @return 1：更新成功 0：更新失败
	 */
	public String modifyTools(ZqToolsModel zqToolsModel);

	/**
	 * 
	 * @param toolsId
	 * @return 0删除失败 1：删除成功
	 */
	public String deltToolsById(Integer toolsId);

	/**
	 * 获取所有资产类型
	 * @return
	 */
	public List<ZqAssetsTypeModel> getAllAssetsType();

	/**
	 * 
	 * @param typeName
	 * @return 0：失败 1：成功 2：同名类型已经存在
	 */
	public String addAssetsType(String typeName);

	public String modifyAssetsType(ZqAssetsTypeModel zqAssetsTypeModel);

	/**
	 * 
	 * @param typeId
	 * @return
	 */
	public String deltAssetsById(Integer typeId);

	/**
	 * 添加资产
	 * @param zqAssetModel
	 * @return
	 */
	public String addAssets(ZqAssetModel zqAssetModel);

	/**
	 * 资产列表
	 * @return
	 */
	public List<ZqAssetModel> getAllAssets();

	/**
	 * 资产详情
	 * @param assetId
	 * @return
	 */
	public ZqAssetModel getAssetById(Integer assetId);

	public String saveAssetModify(ZqAssetModel zqAssetModel);
}
