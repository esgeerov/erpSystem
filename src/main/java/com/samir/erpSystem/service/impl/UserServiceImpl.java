package com.samir.erpSystem.service.impl;

import com.samir.erpSystem.dto.request.ReqLogin;
import com.samir.erpSystem.dto.request.ReqUser;
import com.samir.erpSystem.dto.response.RespStatus;
import com.samir.erpSystem.dto.response.RespToken;
import com.samir.erpSystem.dto.response.RespUser;
import com.samir.erpSystem.dto.response.Response;
import com.samir.erpSystem.entity.User;
import com.samir.erpSystem.entity.UserToken;
import com.samir.erpSystem.exception.ExceptionConstants;
import com.samir.erpSystem.exception.ProjectException;
import com.samir.erpSystem.myenum.EnumAviable;
import com.samir.erpSystem.repository.UserRepository;
import com.samir.erpSystem.repository.UserTokenRepository;
import com.samir.erpSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    @Override
    public Response<RespUser> login(ReqLogin reqLogin) {
        Response<RespUser> response = new Response<>();
        RespUser respUser = new RespUser();
        try {
            String email = reqLogin.getEmail();
            String password = reqLogin.getPassword();
            if (email == null || password == null) {
                throw new ProjectException(ExceptionConstants.INVALID_REQEUST_DATA, "username or password is null");
            }
            User user = userRepository.findUserByEmailAndPasswordAndActive(email, password, EnumAviable.ACTIVE.value);
            if (user == null) {
                throw new ProjectException(ExceptionConstants.INVALID_USER, "Invalid user");
            }
            String token = UUID.randomUUID().toString();
            UserToken userToken = new UserToken();
            userToken.setUser(user);
            userToken.setToken(token);
            userTokenRepository.save(userToken);
            respUser.setName(user.getName());
            respUser.setEmail(user.getEmail());
            respUser.setRole(user.getRole());
            respUser.setRespToken(new RespToken(user.getId(), token));
            response.setRespStatus(RespStatus.getSuccesMessage());
            response.setT(respUser);


        } catch (ProjectException projectException) {
            response.setRespStatus(new RespStatus(projectException.getCode(), projectException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }
}
