package com.gys.util;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	//SqlSessionFactory对象
	private static SqlSessionFactory sqlSessionFactory=null;
	
	//类线程锁
	private static final Class ClASS_LOCK=SqlSessionFactoryUtil.class;
	/**
	 * 私有化构造参数
	 */
	private SqlSessionFactoryUtil(){}
	/**
	 * 构建SqlSessionFactory
	 */
	public static SqlSessionFactory initSqlSessionFactory(){
		String resource="com/gys/config/mybatis-config.xml";
		InputStream inputStream=null;
		try {
			inputStream=Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE,null,e);
		}
		synchronized (ClASS_LOCK) {
			if(sqlSessionFactory==null){
				sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession openSqlSession(){
		if(sqlSessionFactory==null){
			initSqlSessionFactory();
		}
		return sqlSessionFactory.openSession();
	}
	
}
