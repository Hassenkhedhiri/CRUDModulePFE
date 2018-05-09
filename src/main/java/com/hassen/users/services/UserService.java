package com.hassen.users.services;

import com.hassen.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;


public interface UserService  {

    //public Page findByUsernameContainingIgnoreCase(String name, Pageable pages);
    Iterable<User> getAllUsers(int page);
    User getUserById(Integer id);
    User saveUser(User user);
    void deleteUser(Integer id);
    //Iterable<User> getAllUsersByName(String name);



}
