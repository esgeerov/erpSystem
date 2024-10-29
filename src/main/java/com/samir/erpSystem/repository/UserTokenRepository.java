package com.samir.erpSystem.repository;

import com.samir.erpSystem.entity.User;
import com.samir.erpSystem.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    UserToken findUserTokenByUserAndToken(User user, String token);
}
