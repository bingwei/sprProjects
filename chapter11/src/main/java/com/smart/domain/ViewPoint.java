package com.smart.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "t_view_point")
public class ViewPoint extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "point_id")
	protected int pointId;

	@Column(name = "point_name")
	protected String pointName;

	@Column(name = "ticket_price")
	protected float ticketPrice;

	protected String description;
	
	@Column(name = "img_file")
	protected String imgFile;

	@ManyToOne
	@JoinColumn(name = "space_id")
	protected ViewSpace viewSpace = new ViewSpace();

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ViewSpace getViewSpace() {
		return viewSpace;
	}

	public void setViewSpace(ViewSpace viewSpace) {
		this.viewSpace = viewSpace;
	}

	public String getImgFile() {
		return imgFile;
	}

	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
}