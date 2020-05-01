package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.DB.DBConnection;
import com.laptrinhjavaweb.dao.ICommentDAO;
import com.laptrinhjavaweb.dto.CommentDTO;

@Repository
public class CommentDAO implements ICommentDAO{
	@Override
	public List<CommentDTO> findByNewId(Long id) {
		List<CommentDTO> list = new ArrayList<>();
		Connection conn = DBConnection.CreateConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "SELECT *FROM comment WHERE new_id=" + id;
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				CommentDTO item = new CommentDTO();
				item.setContent(rs.getString("content"));
				item.setNewID(rs.getLong("new_id"));
				item.setUser_id(rs.getLong("user_id"));
				list.add(item);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteCommentByNewId(long id) {
		Connection conn = DBConnection.CreateConnection();
		PreparedStatement ptmt = null;
		String sql = "DELETE FROM comment WHERE new_id=" + id;
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
