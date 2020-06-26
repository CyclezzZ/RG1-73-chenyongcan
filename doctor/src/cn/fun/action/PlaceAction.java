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
import cn.fun.entity.Place;
import cn.fun.service.BizService;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class PlaceAction extends BaseAction implements ModelDriven<Place> {
	private String		actionname1		= "车位";
	private String		actionclass1	= "Place";
	@Autowired
	private BizService	service;

	private int			uid;
	private Place		bean			= new Place();

	@Action(value = "anlPlace", results = { @Result(name = "add2", location = "/ahtml/anlPlace.jsp") })
	public String anlPlace() {
		List<Place> list = service.queryByHQL("from Place order by sid");
		JSONArray nameary = new JSONArray();
		JSONArray dataary = new JSONArray();
		for (Place p : list) {
			nameary.add(p.getSid());
			Long count = service.unique("select count(*) from PlaceUse where place.id=?", p.getId());
			dataary.add(count);
		}
		putRequestValue("namejson", nameary.toJSONString());
		putRequestValue("datajson", dataary.toJSONString());
		return "add2";
	}

	@Action(value = "add2Place", results = { @Result(name = "add2", location = "/ahtml/addPlace.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getPlace", results = { @Result(name = "getOne", location = "/ahtml/modifyPlace.jsp") })
	public String get() {
		try {
			Place temp = (Place) service.get(Place.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deletePlace")
	public String delete() {
		try {
			service.delete(Place.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainqueryPlace");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updatePlace")
	public String update() {
		try {
			service.update(bean);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.", "mainqueryPlace");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addPlace")
	public String add() {
		try {
			bean.setStatus("空闲");
			service.add(bean);
			MessageUtil.addRelMessage(getHttpServletRequest(), "添加成功.", "mainqueryPlace");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	@Action(value = "queryPlace", results = { @Result(name = "queryList", location = "/ahtml/listPlace.jsp") })
	public String query() {
		try {
			// 字段名称集合
			LinkedList<String> parmnames = new LinkedList<String>();
			// 字段值集合
			LinkedList<Object> parmvalues = new LinkedList<Object>();
			Page p = FieldUtil.createPage(getHttpServletRequest(), Place.class, parmnames, parmvalues);

			Page page = service.find(p, Place.class);
			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public Place getModel() {
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

	public Place getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(Place bean) {
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
