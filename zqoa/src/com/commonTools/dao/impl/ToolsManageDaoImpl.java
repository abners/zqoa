package com.commonTools.dao.impl;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.FileManage.business.ebi.FileManageEbi;
import com.FileManage.vo.ZqFileModel;
import com.commonTools.dao.dao.ToolsManageDao;
import com.commonTools.vo.ZqAssetModel;
import com.commonTools.vo.ZqAssetsTypeModel;
import com.commonTools.vo.ZqToolsModel;
import com.commonTools.vo.ZqlToolstypeModel;

/**
 * 工具管理持久层实现
 * @author Alert
 * @since 2013-9-7下午08:29:19
 */
public class ToolsManageDaoImpl extends HibernateDaoSupport implements
		ToolsManageDao {

	/* (non-Javadoc)
	 * @see com.commonTools.dao.dao.ToolsManageDao#addToolsType(com.commonTools.vo.TblToolstypeModel)
	 */
	@Override
	public void addToolsType(ZqlToolstypeModel tblToolstypeModel) throws DataAccessException{
		// TODO Auto-generated method stub
		//新增工具类型
		getHibernateTemplate().save(tblToolstypeModel);
	}

	@Override
	public List<ZqlToolstypeModel> queryAllToolsType() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from ZqlToolstypeModel");
	}

	@Override
	public int countTypeByName(String typeName) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) from ZqlToolstypeModel where typeName=?",typeName).iterator().next()).intValue();
	}

	@Override
	public int countTypeUserByTools(Integer typeId) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) from ZqToolsModel where typeId=?",typeId).iterator().next()).intValue();
	}

	@Override
	public void deltToolsType(Integer typeId) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.createQuery("delete from ZqlToolstypeModel where id=?").setInteger(0, typeId).executeUpdate();
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void updateToolsType(ZqlToolstypeModel zqlToolstypeModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.update(zqlToolstypeModel);
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void saveTools(ZqToolsModel zqToolsModel) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(zqToolsModel);
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public List<ZqToolsModel> queryAllTools() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select new ZqToolsModel(a.id,a.name,a.address,b.typeName,a.ywjm,a.fileId) " +
										   "    from ZqToolsModel a,ZqlToolstypeModel b where a.typeId=b.id ");
	}

	@Override
	public ZqToolsModel queryToolsById(Integer toolsId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(ZqToolsModel.class,toolsId);
	}

	@Override
	public void updateTools(ZqToolsModel zqToolsModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.update(zqToolsModel);
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void deltTools(Integer toolsId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			ZqToolsModel zqToolsModel = (ZqToolsModel) session.get(ZqToolsModel.class, toolsId);
			//存在附件
			if(zqToolsModel.getFileId()!=null){
				//删除附件
				ZqFileModel zqFileModel = (ZqFileModel) session.get(ZqFileModel.class, zqToolsModel.getFileId());
				//删除磁盘文件
				File file = new File(ServletActionContext.getServletContext().getRealPath(zqFileModel.getAddress()));
				if(file.exists()){
					file.delete();
				}
				session.delete(zqFileModel);
			}
			session.delete(zqToolsModel);
			//session.execute();
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public List<ZqAssetsTypeModel> queryAllAssetsType() {
		// TODO Auto-generated method stub
		String hql = "from ZqAssetsTypeModel ";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public int countAssetsTypeByName(String typeName) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) " +
												  "from ZqAssetsTypeModel where typeName=?",
												      typeName).listIterator().next()).intValue();
	}

	@Override
	public void addAssetsType(ZqAssetsTypeModel zqAssetsTypeModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.save(zqAssetsTypeModel);
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void updateAssetsType(ZqAssetsTypeModel zqAssetsTypeModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.update(zqAssetsTypeModel);
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public int countAssetsTypeInUse(Integer typeId) {
		// TODO Auto-generated method stub
		return ((Long)getHibernateTemplate().find("select count(*) " +
				  "from ZqAssetModel where type=?",
			      typeId).listIterator().next()).intValue();
	}

	@Override
	public void deltAssetsType(Integer typeId) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.createQuery("delete from ZqAssetsTypeModel where typeId=?").setInteger(0, typeId).executeUpdate();
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public void saveAssets(ZqAssetModel zqAssetModel) throws HibernateException{
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.save(zqAssetModel);
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public List<ZqAssetModel> queryAllAssets() {
		// TODO Auto-generated method stub
		String hql = "select new ZqAssetModel(a.id,a.sn,a.assetName,b.typeName,a.inTime) from ZqAssetModel a,ZqAssetsTypeModel b where a.type=b.typeId ";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public ZqAssetModel queryAssetbyId(Integer assetId) {
		// TODO Auto-generated method stub
		String hql = "select new ZqAssetModel(a.id,a.sn,a.assetName,a.type,b.typeName,a.inTime,a.outTime,a.value) from ZqAssetModel a,ZqAssetsTypeModel b where a.type=b.typeId and a.id=?";
		Session session = this.getSession();
		try{
			return (ZqAssetModel) session.createQuery(hql).setInteger(0, assetId).uniqueResult();
		}finally{
			session.close();
		}
		
	}

	@Override
	public void saveAssetsModify(ZqAssetModel zqAssetModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			session.update(zqAssetModel);
			transaction.commit();
		}catch (HibernateException e) {
			// TODO: handle exception
			if(transaction!=null)
				transaction.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

}
