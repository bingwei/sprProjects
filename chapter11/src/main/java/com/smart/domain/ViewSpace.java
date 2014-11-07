package com.smart.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "t_view_space")
public class ViewSpace extends BaseDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "space_id")
	protected int spaceId;

	@Column(name = "space_name")
	protected String spaceName;

	protected String description;
	protected String website;
	protected String address;

	@Column(name = "want_num")
	protected int wantNum;

	@Column(name = "been_num")
	protected int beenNum;

	@Column(name = "notwant_num")
	protected int notwantNum;

	@ManyToOne
	@JoinColumn(name = "user_id")
	protected User user;

	@OneToMany(mappedBy = "viewSpace", cascade = CascadeType.ALL)
	protected List<ViewPoint> viewPoints;

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getDescription() {
		return description;
	}

	public String getBriefDesc(){
		if (description == null) {
			return "";
		}else{
			int len = description.length()>100?100:description.length();
			return description.substring(0,len);
		}
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getWantNum() {
		return wantNum;
	}

	public void setWantNum(int wantNum) {
		this.wantNum = wantNum;
	}

	public int getBeenNum() {
		return beenNum;
	}

	public void setBeenNum(int beenNum) {
		this.beenNum = beenNum;
	}

	public int getNotwantNum() {
		return notwantNum;
	}

	public void setNotwantNum(int notwantNum) {
		this.notwantNum = notwantNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ViewPoint> getViewPoints() {
		return viewPoints;
	}

	public void setViewPoints(List<ViewPoint> viewPoints) {
		this.viewPoints = viewPoints;
	}
}