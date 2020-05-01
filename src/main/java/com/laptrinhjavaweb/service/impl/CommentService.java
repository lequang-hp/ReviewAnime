package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dao.ICommentDAO;
import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.entity.CommentEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CommentRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICommentServcie;

@Service
public class CommentService implements ICommentServcie{

	@Autowired
	private ICommentDAO commentDAO;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private NewRepository newRepository;
	
	@Override
	public void InsertComment(String username, String cmt, Long newID) {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_SATUS);
		if(userEntity != null) {
			CommentEntity commentEntity = new CommentEntity();
			commentEntity.setContent(cmt);
			commentEntity.setNewEntity(newRepository.findOne(newID));
			commentEntity.setUser(userEntity);
			commentRepository.save(commentEntity);
		}
	}

	
	@Override
	public List<CommentDTO> findByNewId(Long newID) {
		List<CommentDTO> model = commentDAO.findByNewId(newID);
		for(CommentDTO item: model) {
			item.setUsername(userRepository.findOne(item.getUser_id()).getUserName());
		}
		return model;
	}
	

	@Override
	public List<CommentDTO> findAll(Pageable pageable) {
		List<CommentDTO> models = new ArrayList<>();
		List<CommentEntity> entities = commentRepository.findAll(pageable).getContent();// findAll co san,Tra ve Entity
		
		for(CommentEntity item : entities) {
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.setId(item.getId());
			commentDTO.setContent(item.getContent());
			commentDTO.setUser_id(item.getUser().getId());
			commentDTO.setNewID(item.getNewEntity().getId());
			commentDTO.setCreatedDate((Timestamp)item.getCreatedDate());
			models.add(commentDTO);
		}
		return models; 
	}

	@Override
	public int getTotalItem() {
		return (int)commentRepository.count();
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id: ids)
			commentRepository.delete(id);
	}

	
	@Override
	public void deleteByNewId(long[] ids) {
		for(long id: ids) {
			commentDAO.deleteCommentByNewId(id);
		}
	}
		
}
