package com.gys.main;

import org.apache.ibatis.session.SqlSession;

import com.gys.dao.IRoleDao;
import com.gys.po.Role;
import com.gys.util.SqlSessionFactoryUtil;


public class MyMain {
	public static void main(String[] args) {
		SqlSession sqlSession=null;
		try {
			sqlSession=SqlSessionFactoryUtil.openSqlSession();
			IRoleDao iRoleDao=sqlSession.getMapper(IRoleDao.class);
			Role role=new Role();
			role.setRoleName("testName");
			role.setNote("testNote");
			 iRoleDao.insertRole(role);
			 //iRoleDao.deleteRole(1L)
			 sqlSession.commit();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			sqlSession.rollback();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
	}
}
