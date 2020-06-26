package cn.fun.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.fun.common.BaseHibernateDao;
import cn.fun.dao.ISysDao;
import cn.fun.entity.sys.User;
import util.Page;
import util.SearchParamBean;

@Repository
public class SysDaoImpl extends BaseHibernateDao implements ISysDao {
	public void saveUser(User user) {
		save(user);
	}

	public Object queryUser(String type, String userid, String pwd) {
		User user = unique("from User where uname=? and userPassword=?", userid, pwd);
		if (user == null)
			return null;
		Object ret = null;
		ret = unique("from SimpleUser where user.id=?", user.getUserId());
		if (ret != null)
			return ret;
		ret = unique("from SysUser where user.id=?", user.getUserId());
		if (ret != null)
			return ret;

		return null;

	}

	public User queryUser(String userid) {
		return unique("from User where uname=?", userid);
	}

	public List queryByHQLLimit(final String hql, final int start, final int max) {
		try {
			List resultList = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
					Query query = arg0.createQuery(hql);
					query.setFirstResult(start);
					query.setMaxResults(max);
					return query.list();
				}
			});
			return resultList;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List queryByHQLLimit(final String hql, final Object[] parms, final int start, final int max) {
		try {
			List resultList = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
					Query query = arg0.createQuery(hql);
					if (parms != null) {
						for (int i = 0; i < parms.length; i++) {
							query.setParameter(i, parms[i]);
						}
					}
					query.setFirstResult(start);
					query.setMaxResults(max);
					return query.list();
				}
			});
			return resultList;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public Page findPlaceUse(final Page p) {
		return (Page) getHibernateTemplate().execute(new HibernateCallback<Page>() {
			public Page doInHibernate(Session session) throws HibernateException, SQLException {
				SearchParamBean bean = (SearchParamBean) p.getConditonObject();
				LinkedList<String> parmnames = bean.getParmnames();
				LinkedList<Object> parmvalues = bean.getParmvalues();

				StringBuilder sb = new StringBuilder(200);
				sb.append("from  PlaceUse where 1=1");
				// for (String name : parmnames) {
				// sb.append(" and ").append(name).append("=? ");
				// }
				for (int i = 0; i < parmnames.size(); i++) {
					String name = parmnames.get(i);
					if ("startTime".endsWith(name)) {
						sb.append(" and  startTime >=? ");
						continue;
					}
					if ("outTime".endsWith(name)) {
						sb.append(" and  startTime <=? ");
						continue;
					}
					if (parmvalues.get(i) instanceof String) {
						sb.append(" and ").append(name).append(" like ? ");
					} else {
						sb.append(" and ").append(name).append("=? ");
					}
				}
				sb.append(" order by id desc");
				String hql = sb.toString();

				Page temp = queryByPage(hql, parmvalues, (p.getCurrentPageNumber() - 1) * p.getItemsPerPage(), p.getItemsPerPage(),
						p.getTotalNumber(), session);

				p.setList(temp.getList());
				if (p.getTotalNumber() <= 0) {
					p.setTotalNumber(temp.getTotalNumber());
				}
				return p;

			}
		});
	}
}
