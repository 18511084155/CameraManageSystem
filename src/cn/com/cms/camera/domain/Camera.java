package cn.com.cms.camera.domain;

import cn.com.cms.dvr.domain.Dvr;
import cn.com.cms.station.domain.Station;

public class Camera {
	private int id;
	private int channel;
	private Dvr dvr;
	private String camera_id;
	private String name;
	private Station station;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public Dvr getDvr() {
		return dvr;
	}
	public void setDvr(Dvr dvr) {
		this.dvr = dvr;
	}
	public String getCamera_id() {
		return camera_id;
	}
	public void setCamera_id(String camera_id) {
		this.camera_id = camera_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	
}
