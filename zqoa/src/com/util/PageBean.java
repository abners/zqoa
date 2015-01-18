package com.util;

import java.util.List;

import org.extremecomponents.table.limit.Limit;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 
 * @author peng
 * @since 2013-9-14下午12:30:26
 */
public class PageBean {
	// private int count = 0; // 记录总数
	private int pageSize = 10; // 每页显示记录数
	private int pageCount = 0; // 总页数
	private int page = 1; // 当前页数
	private Integer totalRows; // 总记录数
	private Limit limit;

	private List list; // 结果集

	public PageBean(Limit limit) {
		// TODO Auto-generated constructor stub
		this.limit = limit;
		this.page = limit.getPage();
		this.pageSize = limit.getCurrentRowsDisplayed();
	}

	/**
	 * 获取总记录数
	 * 
	 * @param dao
	 * @param hql
	 * @return
	 */
	public Integer getTotalRows(HibernateTemplate dao, String hql,
			Object[] objects) {
		/**
		 * 查询总记录数目
		 */
		int allRows = 0;
		try {
			allRows = ((Long) dao.find(hql, objects).iterator().next())
					.intValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.totalRows = allRows;

		limit.setRowAttributes(totalRows, 0);

		return totalRows;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	/** */
	/**
	 * 计算当前页开始记录
	 * 
	 * @param pageSize
	 *            每页记录数
	 * @param currentPage
	 *            当前第几页
	 * @return 当前页开始记录号
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}

	/** */
	/**
	 * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
	 * 
	 * @param page
	 *            传入的参数(可能为空,即0,则返回1)
	 * @return 当前页
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}

	public Limit getLimit() {
		return limit;
	}
}
