package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.DB.DBConnection;
import com.laptrinhjavaweb.dao.IUserDAO;

@Repository
public class UserDAO implements IUserDAO{
	@Override
	public void CreateRole(Long userID) {
		Connection conn = DBConnection.CreateConnection();
		PreparedStatement ptmt = null;
		String sql = "insert into user_role(userid,roleid) values(?,?)";
		
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, userID);
			ptmt.setLong(2, 2);
			ptmt.executeUpdate();
			ptmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
