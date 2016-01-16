package cn.com.cms.dvr.dao;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

public class DvrDao {
	private QueryRunner qr = new TxQueryRunner();
}
