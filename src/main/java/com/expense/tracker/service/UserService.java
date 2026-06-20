package com.expense.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.entity.User;
import com.expense.tracker.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public User register(User user) {
        return userRepository.save(user);
    }
	public User getUserById(Long id) {
	    return userRepository.findById(id)
	            .orElse(null);
	}
}
