package com.hassen.users.services;

import com.hassen.users.model.User;
import com.hassen.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;



    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

   @Override
    public Iterable<User> getAllUsers(int page) {
        return this.userRepository.findAll(new PageRequest(page,4));
    }

  /*  @Override
    public Iterable<User> getAllUsersByName(String name,int page){
        return this.userRepository.findAll(new PageRequest(page,4),name);
    } */

    @Override
    public User getUserById(Integer id) {
        return this.userRepository.findOne(id);
    }


    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        this.userRepository.delete(id);
    }


}
