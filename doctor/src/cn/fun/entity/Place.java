package cn.fun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import util.FD;

@Entity
@Table(name = "t_place")
public class Place {
	private int		id;
	@FD("车位编号")
	private String	sid;
	@FD("车位名称")
	private String	name;
	@FD("车位状态")
	private String	status;	//空闲,使用

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	@Column(unique = true)
	public String getSid() {
		return sid;
	}

	public String getName() {
		return name;
	}

	@Column(updatable = false)
	public String getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
