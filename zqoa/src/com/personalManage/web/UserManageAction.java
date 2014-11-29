package com.personalManage.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.json.annotations.JSON;

import com.login.vo.ZqUserModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.personalManage.business.ebi.UserManageEbi;
import com.personalManage.vo.ZqDepartmentModel;
import com.personalManage.vo.ZqGroupModel;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.regexp.internal.REUtil;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.util.ExtremeTablePage;
import com.util.Log4j;
import com.util.MD5Encryption;
import com.util.PageBean;
import com.util.SystemProperties;

/**
 *
 * @author peng
 * @since 2013-10-29下午10:09:08
 */
public class UserManageAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private UserManageEbi userManageEbi;
	private ZqGroupModel zqGroupModel;
	private ZqUserModel zqUserModel;
	private ZqDepartmentModel zqDepartmentModel;
	
	private HttpServletRequest request;
	
	private String status;
	private String info;
	//用户组id
	private Integer powerId;
	//用户id
	private Integer userId;
	//部门id
	private Integer deptId;
	//新密码
	private String newpass;
	//原始密码
	private String password;
	
	public String listUserGroup(){
		List<ZqGroupModel> zqGroupModels = userManageEbi.getAllUserGroup();
		ActionContext.getContext().getValueStack().set("grouplist", zqGroupModels);
		return SUCCESS;
	}
	/**
	 * 权限管理初始化
	 * @return
	 */
	public String editPowerInit(){
		ZqGroupModel zqGroupModel = userManageEbi.getGroupPowersById(powerId);
		ActionContext.getContext().getValueStack().set("groupPowers", zqGroupModel);
		return SUCCESS;
	}
	/**
	 * 保存权限修改的结果
	 * @return
	 */
	public String saveEditPower(){
		//Log4j.logMess("权限信息:" + zqGroupModel.getPower()+":length" + zqGroupModel.getPower().replaceAll(" ", "").length());
		this.status = userManageEbi.saveEditPowers(zqGroupModel);
		if("1".equals(status)){
			this.info = "操作成功!";
		}else{
			this.info ="操作失败，请稍后重试!";
		}
		return SUCCESS;
	}
	
	/**
	 * 添加用户组
	 * @return
	 */
	public String addUserGroup(){
		this.status =userManageEbi.addUserGroup(zqGroupModel);
		if("1".equals(status)){
			this.info = "操作成功!";
		}else if("2".equals(status)){
			this.info = "已存在同名用户组!请更换其它名称";
		}else {
			this.info ="操作失败，请稍后重试!";
		}
		return SUCCESS;
	}
	
	public String deltUserGroup(){
		//删除的不是管理员用户组
		if(!"1".equals(powerId)){
			this.status = userManageEbi.deltUserGroupById(powerId);
			if("1".equals(status)){
				this.info = "操作成功!";
			}else if("2".equals(status)){
				this.info = "要删除的用户组已被使用，不能删除!";
			}else {
				this.info = "操作失败!";
			}
		}else{
			this.status = "0";
			this.info = "管理员用户组不能删除!";
		}
		return SUCCESS;
	}
	/**
	 * 添加用户初始化
	 * @return
	 */
	public String addUserInit(){
		List<ZqGroupModel> zqGroupModels = userManageEbi.getAllUserGroup();
		ActionContext.getContext().getValueStack().set("usergroup", zqGroupModels);
		return SUCCESS;
	}
	/**
	 * 添加用户
	 * @return
	 */
	public String addUser(){
		this.status = userManageEbi.addUser(zqUserModel);
		if("1".equals(status)){
			this.info = "操作成功!";
		}else if("2".equals(status)){
			this.info = "已存在同名用户，不能重复添加!";
		}else {
			this.info = "操作失败!";
		}
		return SUCCESS;
	}
	/**
	 * 分页获取用户列表
	 * @return
	 */
	public String listUser(){
		Map conditionMap = new HashMap();
		//初始化并获取limit
		conditionMap.put("_limit", ExtremeTablePage.getLimit(request, 10));
		PageBean pageBean = userManageEbi.getUserList(conditionMap);
		ActionContext.getContext().getValueStack().set("userlist", pageBean.getList());
		ActionContext.getContext().getValueStack().set("totalRows", pageBean.getTotalRows());
		return SUCCESS;
	}
	/**
	 * 停用账户
	 * @return
	 */
	public String closeAccount(){
		this.status = userManageEbi.closeAccountById(userId);
		return SUCCESS;
	}
	/**
	 * 启用账户
	 * @return
	 */
	public String openAccount(){
		this.status = userManageEbi.openAccountById(userId);
		return SUCCESS;
	}
	/**
	 * 查看用户详细信息
	 * @return
	 */
	public String userDetail(){
		ZqUserModel zqUserModel = userManageEbi.getUserDetailById(userId);
		ActionContext.getContext().getValueStack().set("userinfo", zqUserModel);
		return SUCCESS;
	}
	/**
	 * 修改用户信息初始化
	 * @return
	 */
	public String modifyUserMessInit(){
		//获取用户组列表
		List<ZqGroupModel> zqGroupModels = userManageEbi.getAllUserGroup();
		ActionContext.getContext().getValueStack().set("group", zqGroupModels);
		List<ZqDepartmentModel> zqDepartmentModels = userManageEbi.getAllDepartment();
		ActionContext.getContext().getValueStack().set("depart", zqDepartmentModels);
		if(userId==null){
			this.userId = ((ZqUserModel) ActionContext.getContext().getSession().get("user")).getId();
		}
		//获取用户详情
		ZqUserModel zqUserModel = userManageEbi.getUserDetailById(userId);
		ActionContext.getContext().getValueStack().set("userinfo", zqUserModel);
		return SUCCESS;
	}
	/**
	 * 保存用户信息修改的结果
	 */
	public String modifyUserMess(){
		this.status = userManageEbi.modifyUserMess(zqUserModel);
		if("1".equals(status)){
			this.info = SystemProperties.getPropsValue("operate_success");
		}else {
			this.info = SystemProperties.getPropsValue("operate_failed");
		}
		return SUCCESS;
	}
	/**
	 * 查看部门列表
	 * @return
	 */
	public String listDeparts(){
		List<ZqDepartmentModel> zqDepartmentModels = userManageEbi.getAllDepartment();
		ActionContext.getContext().getValueStack().set("depart", zqDepartmentModels);
		return SUCCESS;
		
	}
	/**
	 * 添加部门信息
	 * @return
	 */
	public String addDepart(){
		this.status = userManageEbi.addDepart(zqDepartmentModel);
		if("1".equals(status)){
			this.info = SystemProperties.getPropsValue("depart_add_success");
		}else if("2".equals(status)){
			this.info = SystemProperties.getPropsValue("depart_add_repeat");
		}else {
			this.info = SystemProperties.getPropsValue("operate_failed");
		}
		return SUCCESS;
	}
	/**
	 * 保存修改的部门信息
	 * @return
	 */
	public String modifyDepart(){
		this.status = userManageEbi.modifyDepart(zqDepartmentModel);
		if("1".equals(status)){
			this.info = SystemProperties.getPropsValue("operate_success");
		}else if("2".equals(status)){
			this.info = SystemProperties.getPropsValue("depart_add_repeat");
		}else {
			this.info = SystemProperties.getPropsValue("operate_failed");
		}
		return SUCCESS;
	}
	/**
	 * 删除部门
	 * @return
	 */
	public String deltDepart(){
		this.status = userManageEbi.deltDepartById(deptId);
		if("1".equals(status)){
			this.info = SystemProperties.getPropsValue("operate_success");
		}else if("2".equals(status)){
			this.info = SystemProperties.getPropsValue("depart_in_use");
		}else {
			this.info = SystemProperties.getPropsValue("operate_failed");
		}
		return SUCCESS;
	}
	/**
	 * 保存密码
	 * @return
	 */
	public String saveMyNewPass()throws Exception{
		ZqUserModel zqUserModel = (ZqUserModel) ActionContext.getContext().getSession().get("user");
		String md5Pass = MD5Encryption.getMD5(password);
		//原始密码输入正确
		if(zqUserModel.getPassword().equals(md5Pass)){
			zqUserModel.setPassword(MD5Encryption.getMD5(newpass));
			this.status = userManageEbi.updateUserPass(zqUserModel);
			if("1".equals("1")){
				this.info = SystemProperties.getPropsValue("operate_success");
			}else {
				this.info = SystemProperties.getPropsValue("operate_failed");
			}
		}else {
			this.status = "2";
			this.info = SystemProperties.getPropsValue("password_error");
		}
		return SUCCESS;
		
	}
	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JSON(serialize=false)
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	@JSON(serialize=false)
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@JSON(serialize=false)
	public ZqDepartmentModel getZqDepartmentModel() {
		return zqDepartmentModel;
	}
	public void setZqDepartmentModel(ZqDepartmentModel zqDepartmentModel) {
		this.zqDepartmentModel = zqDepartmentModel;
	}
	@JSON(serialize=false)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@JSON(serialize=false)
	public UserManageEbi getUserManageEbi() {
		return userManageEbi;
	}
	public void setUserManageEbi(UserManageEbi userManageEbi) {
		this.userManageEbi = userManageEbi;
	}
	@JSON(serialize=false)
	public Integer getPowerId() {
		return powerId;
	}
	
	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}
	@JSON(serialize=false)
	public ZqGroupModel getZqGroupModel() {
		return zqGroupModel;
	}
	public void setZqGroupModel(ZqGroupModel zqGroupModel) {
		this.zqGroupModel = zqGroupModel;
	}
	@JSON(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JSON(name="info")
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@JSON(serialize=false)
	public ZqUserModel getZqUserModel() {
		return zqUserModel;
	}
	public void setZqUserModel(ZqUserModel zqUserModel) {
		this.zqUserModel = zqUserModel;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	
	
	
	
}
