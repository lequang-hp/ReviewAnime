package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.dto.CommentDTO;

public interface ICommentDAO {
	List<CommentDTO> findByNewId(Long id);
	void deleteCommentByNewId(long id);
}
