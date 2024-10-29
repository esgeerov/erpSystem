package com.samir.erpSystem.service.impl;

import com.samir.erpSystem.dto.request.ReqClient;
import com.samir.erpSystem.dto.request.ReqToken;
import com.samir.erpSystem.dto.response.RespClient;
import com.samir.erpSystem.dto.response.RespStatus;
import com.samir.erpSystem.dto.response.Response;
import com.samir.erpSystem.entity.Client;
import com.samir.erpSystem.exception.ExceptionConstants;
import com.samir.erpSystem.exception.ProjectException;
import com.samir.erpSystem.myenum.EnumAviable;
import com.samir.erpSystem.repository.ClientRepositry;
import com.samir.erpSystem.service.ClientService;
import com.samir.erpSystem.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepositry clientRepositry;
    private final Utility utility;

    @Override
    public Response addClient(ReqClient reqClient) {
        Response response = new Response();
        try {
            ReqToken reqToken=reqClient.getReqToken();
            utility.checkToken(reqToken.getToken(),reqToken.getUserId());
            Client client = new Client();
            if (reqClient == null) {
                throw new ProjectException(ExceptionConstants.INVALID_REQEUST_DATA, "Data not found");
            }
            client.setName(reqClient.getName());
            client.setPhone(reqClient.getPhone());
            client.setCompanyName(reqClient.getCompanyName());
            clientRepositry.save(client);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ProjectException projectException) {
            response.setRespStatus(new RespStatus(projectException.getCode(), projectException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }



    @Override
    public Response<List<RespClient>> getAllClient(ReqToken reqToken) {
        Response<List<RespClient>> response = new Response<>();
        List<RespClient> respClientList;
        try {
            utility.checkToken(reqToken.getToken(),reqToken.getUserId());
           List<Client> clientList= clientRepositry.findAllByActive(EnumAviable.ACTIVE.value);
            if (clientList.isEmpty()) {
                throw new ProjectException(ExceptionConstants.INTERNAL_EXCEPTION, "Client not found");
            }
            respClientList = clientList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respClientList);
            response.setRespStatus(RespStatus.getSuccesMessage());
        } catch (ProjectException projectException) {
            response.setRespStatus(new RespStatus(projectException.getCode(), projectException.getMessage()));
        } catch (Exception exception) {
            response.setRespStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    public RespClient mapping(Client client) {
        return RespClient
                .builder()
                .companyName(client.getCompanyName())
                .name(client.getName())
                .phone(client.getPhone())
                .build();
    }
}
