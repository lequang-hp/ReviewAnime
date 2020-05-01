package com.laptrinhjavaweb.converter;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;

@Component
public class NewConverter {
	public NewDTO toDTO(NewEntity entity) {
		NewDTO result = new NewDTO();
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setShortDescription(entity.getShortDescription());
		result.setContent(entity.getContent());
		result.setImg(entity.getImg());
		result.setStory(entity.getStory());
		result.setAnimation(entity.getAnimation());
		result.setMusic(entity.getMusic());
		result.setConclusion(entity.getConclusion());
		result.setCategoryCode(entity.getCategory().getCode());
		return result;
	}
	
	public NewEntity toEntity(NewDTO dto) {
		NewEntity result = new NewEntity();
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
	
		result.setImg(dto.getImg());
		result.setStory(dto.getStory());
		result.setAnimation(dto.getAnimation());
		result.setMusic(dto.getMusic());
		result.setConclusion(dto.getConclusion());
		return result;
	}
	
	public NewEntity toEntity(NewDTO dto,NewEntity result) {
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());

		result.setImg(dto.getImg());
		result.setStory(dto.getStory());
		result.setAnimation(dto.getAnimation());
		result.setMusic(dto.getMusic());
		result.setConclusion(dto.getConclusion());
		return result;
	}
}
