package com.example.lcmsapp.client;

import com.example.lcmsapp.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "${client.host.url}", name = "RestClient")
public interface JsonPlaceholderClient {

    @GetMapping
    List<UserResponse> getAllUsers();

}
