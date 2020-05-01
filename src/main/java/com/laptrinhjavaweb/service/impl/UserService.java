package com.laptrinhjavaweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean InsertAccount(String user, String fullname,String password) {
		if(userRepository.findOneByUserNameAndStatus(user, SystemConstant.ACTIVE_SATUS) == null) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUserName(user);
			userEntity.setFullName(fullname);
			userEntity.setPassword(password);
			userEntity.setStatus(SystemConstant.ACTIVE_SATUS);
			userRepository.save(userEntity);
			userEntity = userRepository.findOneByUserNameAndStatus(user, SystemConstant.ACTIVE_SATUS);
			userDAO.CreateRole(userEntity.getId());
			return true;
		}else {
			return false;
		}
	}

}
