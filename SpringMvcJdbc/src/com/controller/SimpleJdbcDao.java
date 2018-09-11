package com.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class SimpleJdbcDao extends JdbcDaoSupport{
	public int insert(String name, String mobile) {
		String sql="insert into user (name, mobile) values (?,?)";
		return getJdbcTemplate().update(sql, name,mobile);		
	}
	
	public List<User> getUsers() {
		String sql="select * from user";
		List<User> users=getJdbcTemplate().query(sql, new UserRowMapper());	
		return users;
	}
	
	class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int pos) throws SQLException {
			// TODO Auto-generated method stub
			User user=new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(3));
			user.setMobile(rs.getString(2));
			return user;
		}
		
	}
}
