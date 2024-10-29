package com.samir.erpSystem.repository;

import com.samir.erpSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailAndPasswordAndActive(String email,String password,Integer active);
    User findUserByIdAndActive(Long userId,Integer active);

}
