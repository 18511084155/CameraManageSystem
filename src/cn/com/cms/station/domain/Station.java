package cn.com.cms.station.domain;

import java.util.List;

import cn.com.cms.camera.domain.Camera;
import cn.com.cms.dvr.domain.Dvr;

/**
 * 变电站实体类
 * @author Administrator
 *
 */
public class Station {
	private int station_id;
	private String station_name_videoplant;
	private String station_code_videoplant;
	
	private List<Dvr> dvrList;
	private List<Camera> cameraList;
	public int getStation_id() {
		return station_id;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public String getStation_name_videoplant() {
		return station_name_videoplant;
	}
	public void setStation_name_videoplant(String station_name_videoplant) {
		this.station_name_videoplant = station_name_videoplant;
	}
	public String getStation_code_videoplant() {
		return station_code_videoplant;
	}
	public void setStation_code_videoplant(String station_code_videoplant) {
		this.station_code_videoplant = station_code_videoplant;
	}
	public List<Dvr> getDvrList() {
		return dvrList;
	}
	public void setDvrList(List<Dvr> dvrList) {
		this.dvrList = dvrList;
	}
	public List<Camera> getCameraList() {
		return cameraList;
	}
	public void setCameraList(List<Camera> cameraList) {
		this.cameraList = cameraList;
	}
	
	
	
}
