package com.samir.erpSystem.service;

import com.samir.erpSystem.dto.request.ReqLogin;
import com.samir.erpSystem.dto.request.ReqUser;
import com.samir.erpSystem.dto.response.Response;

public interface UserService {
    Response login(ReqLogin reqLogin);
}
