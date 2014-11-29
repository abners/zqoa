package com.FileManage.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.FileManage.dao.dao.FileManageDao;
import com.FileManage.vo.ZqFileModel;
import com.caseManage.vo.ZqCasecontactModel;
import com.util.Log4j;

/**
 *
 * @author peng
 * @since 2013-10-22下午08:37:06
 */
public class FileManageDaoImpl extends HibernateDaoSupport implements
		FileManageDao {

	/* (non-Javadoc)
	 * @see com.FileManage.dao.dao.FileManageDao#addFile(com.FileManage.vo.ZqFileModel)
	 */
	@Override
	public Integer addFile(ZqFileModel fileModel) throws HibernateException{
		// TODO Auto-generated method stub
		Log4j.logMess("实际文件名："+fileModel.getRealFilename());
		try{
			Integer fileid = (Integer) getHibernateTemplate().save(fileModel);
			
			return fileid;
		}catch (HibernateException e) {
			// TODO: handle exception
			
			Log4j.errorLog(this, e);
			throw e;
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZqCasecontactModel> queryContactByCaseId(Integer caseId) {
		// TODO Auto-generated method stub
		String hql = "select new ZqCasecontactModel(a.id,a.name,a.email,a.address,a.tel,a.mobile,b.identityName)" +
	     			 "  from ZqCasecontactModel a,ZqContactidentityModel b where a.roleid=b.identityId and a.caseId=? ";
		return getHibernateTemplate().find(hql,caseId);
	}

	@Override
	public ZqFileModel queryFileById(String fileId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(ZqFileModel.class, Integer.valueOf(fileId));
	}

	@Override
	public void updateFile(ZqFileModel zqFileModel) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			
			 session.update(zqFileModel);
			
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
