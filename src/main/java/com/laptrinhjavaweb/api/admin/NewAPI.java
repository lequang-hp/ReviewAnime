package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICommentServcie;
import com.laptrinhjavaweb.service.INewService;

@RestController(value = "newAPIOfAdmin") // Chu thich day la API
public class NewAPI {
	@Autowired
	private INewService newService;
	
	@Autowired
	private ICommentServcie commentService;
	
	@PostMapping("/api/new")
	public NewDTO createNew(@RequestBody NewDTO newDTO){
		return newService.save(newDTO);
	}
	
	@PutMapping("/api/new")
	public NewDTO upadateNew(@RequestBody NewDTO updateNew){
		return newService.save(updateNew);
	}
	
	@DeleteMapping("/api/new") // delete tat ca bai viet muon xoa theo id
	public void deleteNew(@RequestBody long[] ids){
		commentService.deleteByNewId(ids);
		newService.delete(ids);
	}
}
