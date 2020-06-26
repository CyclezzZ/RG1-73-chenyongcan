package cn.fun.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import util.Constant;
import util.MD5;
import util.Page;
import cn.fun.common.BaseService;
import cn.fun.dao.ISysDao;
import cn.fun.entity.Place;
import cn.fun.entity.PlaceUse;
import cn.fun.entity.sys.SimpleUser;
import cn.fun.entity.sys.SysUser;
import cn.fun.entity.sys.User;

@Service("bizService")
@Repository
public class 	BizService extends BaseService {

	@Autowired
	private ISysDao dao;

	public List queryByHQL(String hql, Object... values) {
		return dao.queryByHQL(hql, values);
	}

	public void deleteSysUser(Class<SysUser> class1, String ids) {
		List<User> list = dao.queryByHQL("from User where uname in(select user.uname from SysUser where id in (" + ids + "))");
		for (User user : list) {
			dao.delete(user);
		}
	}

	public void deleteSimpleUser(Class<SimpleUser> class1, String ids) {
		List<User> list = dao.queryByHQL("from User where uname in(select user.uname from SimpleUser where id in (" + ids + "))");
		for (User user : list) {
			dao.delete(user);
		}
	}

	public void addSimpleUser(SimpleUser obj) {
		User user = obj.getUser();
		MD5 md = new MD5();
		user.setUserPassword(md.getMD5ofStr(user.getUserPassword()));
		dao.save(user);
		dao.save(obj);
	}

	public void updateSimpleUser(SimpleUser obj) {
		SimpleUser temp = (SimpleUser) dao.get(SimpleUser.class, obj.getId());
		User user = temp.getUser();
		user.setUserAddress(obj.getUser().getUserAddress());
		user.setUserBirth(obj.getUser().getUserBirth());
		user.setUserEmail(obj.getUser().getUserEmail());
		user.setUserGender(obj.getUser().getUserGender());
		user.setUserName(obj.getUser().getUserName());
		user.setUserPhone(obj.getUser().getUserPhone());
		if (StringUtils.isNotBlank(obj.getUser().getUserPassword())) {
			MD5 md = new MD5();
			user.setUserPassword(md.getMD5ofStr(obj.getUser().getUserPassword()));
		}
		dao.merge(user);
		obj.setUser(user);
		dao.merge(obj);

	}

	/**
	 * 添加对象
	 * 
	 * @param obj
	 */
	public void add(Object obj) {
		dao.save(obj);
	}

	/**
	 * 修改对象
	 * 
	 * @param obj
	 */
	public void update(Object obj) {
		dao.merge(obj);
	}

	/**
	 * 根据id获取对象
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public <T> T get(Class clazz, Serializable id) {
		return dao.get(clazz, id);
	}

	public void deleteUser(String ids) {
		dao.deleteByIds(User.class, ids);
	}

	public void delete(Class clazz, String ids) {
		dao.deleteByIds(clazz, ids);
	}

	public Object findUser(String type, String userid, String pwd) {
		return dao.queryUser(type, userid, pwd);
	}

	public User findUser(String userid) {
		return dao.queryUser(userid);
	}

	public Page findUser(Page page) {
		return dao.list(page, "User");
	}

	public Page find(Page page, Class clazz) {
		return dao.list(page, clazz.getSimpleName());
	}

	public List findAll(Class clazz) {
		return dao.queryByHQL("from " + clazz.getSimpleName());
	}

	public void addSysUser(SysUser obj) {
		User user = obj.getUser();
		MD5 md = new MD5();
		user.setUserPassword(md.getMD5ofStr(user.getUserPassword()));
		dao.save(user);
		dao.save(obj);
	}

	public void updateSysUser(SysUser obj) {
		SysUser temp = (SysUser) dao.get(SysUser.class, obj.getId());
		User user = temp.getUser();
		user.setUserAddress(obj.getUser().getUserAddress());
		user.setUserBirth(obj.getUser().getUserBirth());
		user.setUserEmail(obj.getUser().getUserEmail());
		user.setUserGender(obj.getUser().getUserGender());
		user.setUserName(obj.getUser().getUserName());
		user.setUserPhone(obj.getUser().getUserPhone());
		if (StringUtils.isNotBlank(obj.getUser().getUserPassword())) {
			MD5 md = new MD5();
			user.setUserPassword(md.getMD5ofStr(obj.getUser().getUserPassword()));
		}
		dao.merge(user);
		obj.setUser(user);
		dao.merge(obj);

	}

	public List findAll(Class clazz, Map<String, Object> params) {
		return dao.findAll(clazz, params);
	}

	public <T> T unique(final String hql, final Object... paramlist) {
		return dao.unique(hql, paramlist);
	}

	public void addIn(PlaceUse bean) {
		synchronized (Constant.IN_QUEUE) {
			Place p = dao.get(Place.class, bean.getPlace().getId());
			if (!p.getStatus().equals("空闲")) {
				throw new RuntimeException("车位已经被占用");
			}
			dao.updateByHQL("update Place set status='使用' where id=?", bean.getPlace().getId());
			dao.save(bean);
		}
	}

	public void addOut(PlaceUse temp) {
		dao.updateByHQL("update Place set status='空闲' where id=?", temp.getPlace().getId());
		dao.update(temp);
	}

	public Page findPlaceUse(Page p, Class<PlaceUse> class1) {
		return dao.findPlaceUse(p);
	}

	public boolean addInList(PlaceUse bean) {
		synchronized (Constant.IN_QUEUE) {
			List<Place> placeList = dao.queryByHQL("from Place where status='空闲'");
			for (Place p : placeList) {
				//p = dao.get(Place.class, p.getId());
				if (p.getStatus().equals("空闲")) {
					bean.setPlace(p);
					dao.updateByHQL("update Place set status='使用' where id=?", bean.getPlace().getId());
					dao.save(bean);
					return true;
				}
			}
			return false;
		}

	}

}
