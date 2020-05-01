package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.CommentDTO;

public interface ICommentServcie {
	void InsertComment(String username,String cmt,Long newID);
	List<CommentDTO> findByNewId(Long newID); // hien thi cmt tuong ung vs moi bai viet
	List<CommentDTO> findAll(Pageable pageable);  
	int getTotalItem();
	void delete(long [] ids);
	void deleteByNewId(long []ids);// Xoa tat ca comment cua bai viet do(Trc khi xoa bai viet)
}
