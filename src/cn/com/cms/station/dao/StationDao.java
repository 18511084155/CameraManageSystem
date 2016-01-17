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
 * ���վ�־ò�
 * @author Administrator
 *
 */
public class StationDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * ��ѯ500kv���б��վ
	 * @return
	 * @throws SQLException 
	 */
	public List<Station> findAll500KVStations() throws SQLException {
		/*
		 * 1. ��ѯ������500KV���վ
		 */
		String sql = "select * from ob_d5000_station where voltage_class=500";
		List<Station> stations = qr.query(sql, new BeanListHandler<Station>(Station.class));
		
		/*
		 * 2. ��ѯ����Щ500KV���վ���������ͷ
		 */
		for(Station station : stations) {
			// ��ѯ����ǰ������������ӷ���
			List<Camera> cameraList = findCamerasByStationCode(station.getStation_code_videoplant());
			// ���ø�������
			station.setCameraList(cameraList);
		}
		return stations;
	}
	
	/**
	 * ����station_code��ѯ���վ�µ�����ͷ
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




