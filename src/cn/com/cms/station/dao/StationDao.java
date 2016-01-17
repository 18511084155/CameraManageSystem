package cn.com.cms.station.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.com.cms.camera.domain.Camera;
import cn.com.cms.station.domain.Station;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * 变电站持久层
 * @author Administrator
 *
 */
public class StationDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 查询500kv所有变电站
	 * @return
	 * @throws SQLException 
	 */
	public List<Station> findAll500KVStations() throws SQLException {
		/*
		 * 1. 查询出所有500KV变电站
		 */
		String sql = "select * from ob_d5000_station where voltage_class=500";
		List<Station> stations = qr.query(sql, new BeanListHandler<Station>(Station.class));
		
		/*
		 * 2. 查询出这些500KV变电站下面的摄像头
		 */
		for(Station station : stations) {
			// 查询出当前父分类的所有子分类
			List<Camera> cameraList = findCamerasByStationCode(station.getStation_code_videoplant());
			// 设置给父分类
			station.setCameraList(cameraList);
		}
		return stations;
	}
	
	/**
	 * 按照station_code查询变电站下的摄像头
	 * @param stationCode
	 * @return
	 * @throws SQLException
	 */
	public List<Camera> findCamerasByStationCode(String stationCode) throws SQLException{
		String sql = "select * from video_camera where dvr_id IN (select dvr_id from video_dvr where station_group=?)";
		List<Camera> cameras = qr.query(sql, new BeanListHandler<Camera>(Camera.class), stationCode);
		return cameras;
	}
}




