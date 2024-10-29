package com.samir.erpSystem.service;



import com.samir.erpSystem.dto.request.ReqClient;
import com.samir.erpSystem.dto.request.ReqToken;
import com.samir.erpSystem.dto.response.RespClient;
import com.samir.erpSystem.dto.response.Response;

import java.util.List;


public interface ClientService {
     Response addClient(ReqClient reqClient) ;

     Response<List<RespClient>> getAllClient(ReqToken reqToken);
}
