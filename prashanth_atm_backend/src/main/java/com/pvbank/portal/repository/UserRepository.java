package com.pvbank.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pvbank.portal.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByLoginUserNameAndLoginPassword(String loginUsername, String loginPassWord);
}
