package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private NewConverter newConverter;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();// findAll co san,Tra ve Entity
		//Convert to NewDTO
		for(NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
		}
		return models; 
	}


	@Override
	public int getTotalItem() {
		return (int)newRepository.count();
	}


	@Override
	public NewDTO findById(long id) {
		NewEntity entity = newRepository.findOne(id);
		return newConverter.toDTO(entity);
	}

	/* ham save dc cung cap boi jpa repository
	 * id == null -> them moi / id != null -> cap nhap
	 * Lay thong tin entity cu tu db -> set lai cac thong tin nay voi cac thong tin ms trong dto
	 * Luu lai xuong db va convert lai sang dto
	 */
	@Override
	@Transactional
	public NewDTO save(NewDTO dto) {
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		NewEntity newEntity = new NewEntity();
		if(dto.getId() != null) {
			NewEntity oldNew = newRepository.findOne(dto.getId());
			oldNew.setCategory(category);
			newEntity = newConverter.toEntity(dto,oldNew);
		}
		else {
			newEntity = newConverter.toEntity(dto);
			newEntity.setCategory(category);
		}
		
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}


	@Override
	@Transactional
	public void delete(long []ids) {
		for(long id : ids) {
			newRepository.delete(id);
		}
	}


	@Override
	public List<NewDTO> findByCategoryCode(String code) {
		List<NewDTO> model = new ArrayList<>();
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);
		List<NewEntity> entities = newRepository.findByCategoryId(categoryEntity.getId());
		for(NewEntity item: entities) {
			NewDTO dto = newConverter.toDTO(item);
			model.add(dto);
		}
		return model;
	}


	@Override
	public List<NewDTO> findAll(Pageable pageable, String searchValue) {
		List<NewDTO> models = new ArrayList<>();
		List<NewEntity> entities = new ArrayList<>();
		if(searchValue != null) {
			entities = newRepository.findByTitleContainingIgnoreCase(pageable,searchValue);		
		}
		else {
			entities = newRepository.findAll(pageable).getContent();// findAll co san,Tra ve Entity
		}
		//Convert to NewDTO
		for(NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			models.add(newDTO);
		}
		return models; 
	}
}