package com.twsm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.twsm.dto.MobappNews;
import com.twsm.sqlbuider.SqlBuilder;


public class MobappNewsDao extends BasicDao<MobappNews> {
	private static MobappNewsDao mobappNewsDao = null;

	private MobappNewsDao() {
		super();
	}

	public static MobappNewsDao getInstance() {
		if (mobappNewsDao == null) {
			synchronized (MobappNewsDao.class) {
				if (mobappNewsDao == null) {
					mobappNewsDao = new MobappNewsDao();
				}
			}
		}
		return mobappNewsDao;
	}
	/**
	 * 根据条件查询新闻的数量
	 * @param whereMap
	 * @return
	 * @throws SQLException
	 */
	public int getCount(Map<String,Object> whereMap) throws SQLException{
		return (Integer)SqlBuilder.getSqlMapClient().queryForObject("MobappNews.getCount",whereMap);
	}
	/**
	 * 分页查询微信
	 * @param whereMap
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<MobappNews> getByPage(Map<String,Object> whereMap) throws SQLException{
		List<Integer> list = SqlBuilder.getSqlMapClient().queryForList("MobappNews.getByPage", whereMap);
		if (list != null && list.size() > 0)
			return (List<MobappNews>) this.getById(list);
		return null;
	}
	/**
	 * 根据条件查询微信
	 * 默认按照id倒序
	 * @param whereMap
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<MobappNews> getByWhere(Map<String,Object> whereMap) throws SQLException{
		List<Integer> list = SqlBuilder.getSqlMapClient().queryForList("MobappNews.getByWhere", whereMap);
		if(list != null && list.size() > 0)
			return this.getById(list);
		return null;
	}
}
