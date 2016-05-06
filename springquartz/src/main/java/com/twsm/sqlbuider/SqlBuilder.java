package com.twsm.sqlbuider;

import java.io.Reader;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlBuilder {
private static final Logger logger = Logger.getLogger(SqlBuilder.class);
	
	private static SqlMapClient sqlMapClient = null;
	
	public static SqlMapClient getSqlMapClient(){
		if(sqlMapClient == null){
			synchronized(SqlBuilder.class){
				if(sqlMapClient == null){
					try{
						Reader reader =Resources.getResourceAsReader("MysqlSqlMapConfig.xml");
						sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
						reader.close();
					} catch(Exception ex){
//						logger.error("创建数据库连接池失败. Cause by:" + ex.get);
						ex.printStackTrace();
						throw new RuntimeException(ex);
					}
				}
			}
		}
		return sqlMapClient;
	}
}
