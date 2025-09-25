package org.example.demo_validation.exercise.ex1.service;

import org.example.demo_validation.exercise.ex1.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Override
    public void save(User user) {
        System.out.println("Saved user: " + user.getFirstName());
    }
}
