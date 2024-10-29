package com.samir.erpSystem.controller;


import com.samir.erpSystem.dto.request.ReqClient;
import com.samir.erpSystem.dto.request.ReqToken;
import com.samir.erpSystem.dto.response.RespClient;
import com.samir.erpSystem.dto.response.Response;
import com.samir.erpSystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
   private  final ClientService clientService;
    @PostMapping("/addClient")
    public Response addClient(@RequestBody ReqClient reqClient){
        return clientService.addClient(reqClient);
    }
    @GetMapping("getAllClient")
    public Response<List<RespClient>> getAllClient(@RequestBody ReqToken reqToken){
        return clientService.getAllClient(reqToken);
    }



}
