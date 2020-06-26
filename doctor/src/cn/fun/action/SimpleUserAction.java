package cn.fun.action;

import java.util.LinkedList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;

import cn.fun.common.BaseAction;
import cn.fun.entity.sys.SimpleUser;
import cn.fun.service.BizService;
import util.Constant;
import util.FieldUtil;
import util.MessageUtil;
import util.Page;

@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class SimpleUserAction extends BaseAction implements ModelDriven<SimpleUser> {
	private String		actionname1		= "操作员";
	private String		actionclass1	= "SimpleUser";
	@Autowired
	private BizService	service;

	private int			uid;
	private SimpleUser	bean			= new SimpleUser();

	@Action(value = "queryPlaceUseWait", results = { @Result(name = "add2", location = "/ahtml/queryPlaceUseWait.jsp") })
	public String queryPlaceUseWait() {
		return "add2";
	}
	@Action(value = "add2SimpleUser", results = { @Result(name = "add2", location = "/ahtml/addSimpleUser.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getSimpleUser", results = { @Result(name = "getOne", location = "/ahtml/modifySimpleUser.jsp") })
	public String get() {
		try {
			SimpleUser temp = (SimpleUser) service.get(SimpleUser.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteSimpleUser")
	public String delete() {
		try {
			service.deleteSimpleUser(SimpleUser.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainquerySimpleUser");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updateSimpleUser")
	public String update() {
		try {
			service.updateSimpleUser(bean);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.", "mainquerySimpleUser");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addSimpleUser")
	public String add() {
		try {
			service.addSimpleUser(bean);
			MessageUtil.addRelMessage(getHttpServletRequest(), "添加成功.", "mainquerySimpleUser");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	@Action(value = "querySimpleUser", results = { @Result(name = "queryList", location = "/ahtml/listSimpleUser.jsp") })
	public String query() {
		try {
			// 字段名称集合
			LinkedList<String> parmnames = new LinkedList<String>();
			// 字段值集合
			LinkedList<Object> parmvalues = new LinkedList<Object>();
			Page p = FieldUtil.createPage(getHttpServletRequest(), SimpleUser.class, parmnames, parmvalues);

			//			if (parmnames.contains("type")) {
			//				actionname1 = (String) parmvalues.get(parmnames.indexOf("type"));
			//			}

			Page page = service.find(p, SimpleUser.class);
			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public SimpleUser getModel() {
		return bean;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public BizService getService() {
		return service;
	}

	public SimpleUser getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(SimpleUser bean) {
		this.bean = bean;
	}

	public String getActionname1() {
		return actionname1;
	}

	public void setActionname1(String actionname1) {
		this.actionname1 = actionname1;
	}

	public String getActionclass1() {
		return actionclass1;
	}

	public void setActionclass1(String actionclass1) {
		this.actionclass1 = actionclass1;
	}

}
