package cn.com.cms.station.service;

import java.sql.SQLException;
import java.util.List;

import cn.com.cms.station.dao.StationDao;
import cn.com.cms.station.domain.Station;

/**
 * 变电站业务层
 * @author Administrator
 *
 */
public class StationService {
	private StationDao stationDao = new StationDao();
	
	
	/**
	 * 查询所有分类
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
