package com.egs.training.dao;

import com.egs.training.entity.User;

import java.util.List;

public interface UserDAO {

	public List<User> getAllUsers();

	public User getUser(Long id);

	public void saveUser(User user);

	public void deleteUser(Long id);
	
}
