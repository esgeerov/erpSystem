package com.samir.erpSystem.utility;

import com.samir.erpSystem.entity.User;
import com.samir.erpSystem.entity.UserToken;
import com.samir.erpSystem.exception.ExceptionConstants;
import com.samir.erpSystem.exception.ProjectException;
import com.samir.erpSystem.myenum.EnumAviable;
import com.samir.erpSystem.repository.UserRepository;
import com.samir.erpSystem.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class Utility {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    public UserToken checkToken(String token, Long userId) {
        if (token == null || userId == null) {
            throw new ProjectException(ExceptionConstants.INVALID_USER, "User id or token is null");
        }
        User user = userRepository.findUserByIdAndActive(userId, EnumAviable.ACTIVE.value);
        if (user == null) {
            throw new ProjectException(ExceptionConstants.INVALID_USER, "User not found");
        }
        UserToken userToken = userTokenRepository.findUserTokenByUserAndToken(user, token);
        if (userToken==null){
            throw  new ProjectException(ExceptionConstants.INVALID_TOKEN,"Token not found");
        }
        if (userToken.getActive()==EnumAviable.DEACTIVE.value){
            throw new ProjectException(ExceptionConstants.TOKEN_EXPIRED,"Invalid token");
        }

        return userToken;

    }

}
