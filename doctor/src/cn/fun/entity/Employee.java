package cn.fun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import util.FD;

@Entity
@Table(name = "t_employee")
public class Employee {
	private int		id;
	@FD("姓名")
	private String	name;
	@FD("驾照")
	private String	dno;
	@FD("车牌号")
	private String	carno;
	@FD("车颜色")
	private String	yanse;
	@FD("车型")
	private String	chexing;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDno() {
		return dno;
	}

	@Column(unique = true)
	public String getCarno() {
		return carno;
	}

	public String getYanse() {
		return yanse;
	}

	public String getChexing() {
		return chexing;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public void setYanse(String yanse) {
		this.yanse = yanse;
	}

	public void setChexing(String chexing) {
		this.chexing = chexing;
	}

}
