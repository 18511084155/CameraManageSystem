package cn.com.cms.station.service;

import java.sql.SQLException;
import java.util.List;

import cn.com.cms.station.dao.StationDao;
import cn.com.cms.station.domain.Station;

/**
 * ���վҵ���
 * @author Administrator
 *
 */
public class StationService {
	private StationDao stationDao = new StationDao();
	
	
	/**
	 * ��ѯ���з���
	 * @return
	 */
	public List<Station> findAll500KVStations() {
		try {
			return stationDao.findAll500KVStations();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
