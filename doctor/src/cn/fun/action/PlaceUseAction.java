package cn.fun.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.Constant;
import util.DateUtil;
import util.FieldUtil;
import util.MessageUtil;
import util.Page;
import cn.fun.common.BaseAction;
import cn.fun.entity.Employee;
import cn.fun.entity.PlaceUse;
import cn.fun.service.BizService;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class PlaceUseAction extends BaseAction implements ModelDriven<PlaceUse> {
	private String		actionname1		= "入库";
	private String		actionclass1	= "PlaceUse";
	@Autowired
	private BizService	service;

	private int			uid;
	private PlaceUse	bean			= new PlaceUse();

	@Action(value = "add2In", results = { @Result(name = "add2", location = "/ahtml/add2In.jsp") })
	public String add2In() {
		List<?> list = service.queryByHQL("from Employee where carno not in(select carno from PlaceUse where outTime is null)");
		putRequestValue("list", list);
		List<?> list1 = service.queryByHQL("from Place where status='空闲' ");
		putRequestValue("list1", list1);

		return "add2";
	}

	@Action(value = "add2Out", results = { @Result(name = "add2", location = "/ahtml/add2Out.jsp") })
	public String add2Out() {
		List<?> list = service.queryByHQL("from PlaceUse where outTime is null");
		putRequestValue("list", list);

		return "add2";
	}

	@Action(value = "add2PlaceUse", results = { @Result(name = "add2", location = "/ahtml/addPlaceUse.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getPlaceUse", results = { @Result(name = "getOne", location = "/ahtml/modifyPlaceUse.jsp") })
	public String get() {
		try {
			PlaceUse temp = (PlaceUse) service.get(PlaceUse.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deletePlaceUse")
	public String delete() {
		try {
			service.delete(PlaceUse.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainqueryPlaceUse");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updatePlaceUse")
	public String update() {
		try {
			service.update(bean);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.", "mainqueryPlaceUse");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addPlaceUse")
	public String add() {
		try {
			service.add(bean);
			MessageUtil.addRelMessage(getHttpServletRequest(), "添加成功.", "mainqueryPlaceUse");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	@Action(value = "addOut")
	public String addOut() {
		try {
			PlaceUse temp = (PlaceUse) service.get(PlaceUse.class, uid);
			if (temp.getOutTime() != null) {
				MessageUtil.addMessage(getRequest(), "车辆已经出库");
				return ERROR;
			}
			
			temp.setOutTime(DateUtil.getCurrentTime(DateUtil.FULL));
			double hours = DateUtil.getHours(temp.getOutTime())-DateUtil.getHours(temp.getStartTime())+1;
			service.addOut(temp);
			MessageUtil.addRelMessage(getHttpServletRequest(), "操作成功.费用为："+Math.abs(temp.getPrice()*hours), "mainqueryPlaceUse");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "操作失败");
			return ERROR;
		}
	}

	@Action(value = "addIn")
	public String addIn() {
		try {
			bean.setCarno(bean.getCarno().trim());
			Employee e = service.unique("from Employee where carno=?", bean.getCarno());
			if (e != null) {
				bean.setYanse(e.getYanse());
				bean.setChexing(e.getChexing());
				bean.setPrice(0);
			} else {
				if (bean.getPrice() == 0d || StringUtils.isBlank(bean.getChexing()) || StringUtils.isBlank(bean.getYanse())) {
					MessageUtil.addMessage(getRequest(), "非员工车辆必须输入缴费金额,车型和颜色");
					return ERROR;
				}
			}

			bean.setStartTime(DateUtil.getCurrentTime(DateUtil.FULL));
			if (bean.getPlace() != null && bean.getPlace().getId() != 0) {
				service.addIn(bean);
				MessageUtil.addRelMessage(getHttpServletRequest(), "入库成功.", "mainqueryPlaceUse");
			} else {
				List<PlaceUse> plist = (List<PlaceUse>) getHttpSession().getServletContext().getAttribute(Constant.IN_QUEUE);
				if (plist == null) {
					plist = new ArrayList<PlaceUse>();
					getHttpSession().getServletContext().setAttribute(Constant.IN_QUEUE, plist);
				}
				plist.add(bean);
				MessageUtil.addRelMessage(getHttpServletRequest(), "没有选择车位.车辆进入等待队列", "mainqueryPlaceUse");
			}
			MessageUtil.addRelMessage(getHttpServletRequest(), "操作成功.", "mainqueryPlaceUse");

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "入库失败. " + e.getMessage());
			return ERROR;
		}
	}

	@Action(value = "queryPlaceUse", results = { @Result(name = "queryList", location = "/ahtml/listPlaceUse.jsp") })
	public String query() {
		try {
			// 字段名称集合
			LinkedList<String> parmnames = new LinkedList<String>();
			// 字段值集合
			LinkedList<Object> parmvalues = new LinkedList<Object>();
			Page p = FieldUtil.createPage(getHttpServletRequest(), PlaceUse.class, parmnames, parmvalues);

			Page page = service.findPlaceUse(p, PlaceUse.class);
			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public PlaceUse getModel() {
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

	public PlaceUse getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(PlaceUse bean) {
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
