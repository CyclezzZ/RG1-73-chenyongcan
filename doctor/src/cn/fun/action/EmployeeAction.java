package cn.fun.action;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.Constant;
import util.FieldUtil;
import util.MessageUtil;
import util.Page;
import cn.fun.common.BaseAction;
import cn.fun.entity.Employee;
import cn.fun.service.BizService;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class EmployeeAction extends BaseAction implements ModelDriven<Employee> {
	private String		actionname1		= "员工";
	private String		actionclass1	= "Employee";
	@Autowired
	private BizService	service;

	private int			uid;
	private Employee	bean			= new Employee();

	@Action(value = "add2Employee", results = { @Result(name = "add2", location = "/ahtml/addEmployee.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getEmployee", results = { @Result(name = "getOne", location = "/ahtml/modifyEmployee.jsp") })
	public String get() {
		try {
			Employee temp = (Employee) service.get(Employee.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteEmployee")
	public String delete() {
		try {
			service.delete(Employee.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainqueryEmployee");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updateEmployee")
	public String update() {
		try {
			service.update(bean);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.", "mainqueryEmployee");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addEmployee")
	public String add() {
		try {
			service.add(bean);
			MessageUtil.addRelMessage(getHttpServletRequest(), "添加成功.", "mainqueryEmployee");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	@Action(value = "queryEmployee", results = { @Result(name = "queryList", location = "/ahtml/listEmployee.jsp") })
	public String query() {
		try {
			// 字段名称集合
			LinkedList<String> parmnames = new LinkedList<String>();
			// 字段值集合
			LinkedList<Object> parmvalues = new LinkedList<Object>();
			Page p = FieldUtil.createPage(getHttpServletRequest(), Employee.class, parmnames, parmvalues);

			Page page = service.find(p, Employee.class);
			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public Employee getModel() {
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

	public Employee getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(Employee bean) {
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
