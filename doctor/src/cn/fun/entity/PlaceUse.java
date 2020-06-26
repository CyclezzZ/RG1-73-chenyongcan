package cn.fun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import util.FD;

@Entity
@Table(name = "t_place_use")
public class PlaceUse {
	private int		id;
	@FD("车位")
	private Place	place;
	@FD("车牌号")
	private String	carno;
	@FD("车颜色")
	private String	yanse;
	@FD("车型")
	private String	chexing;
	@FD("缴费")
	private double	price;
	@FD("入库时间")
	private String	startTime;
	@FD("离开时间")
	private String	outTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "place_id")
	public Place getPlace() {
		return place;
	}

	public String getCarno() {
		return carno;
	}

	public String getYanse() {
		return yanse;
	}

	public String getChexing() {
		return chexing;
	}

	public double getPrice() {
		return price;
	}

	@Column(length = 50, updatable = false)
	public String getStartTime() {
		return startTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPlace(Place place) {
		this.place = place;
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

	public void setPrice(double price) {
		this.price = price;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

}
