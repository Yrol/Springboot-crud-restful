package com.yrol.springbootrestfulwebservices.repository;

import com.yrol.springbootrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
