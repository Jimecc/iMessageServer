package com.jim.sprjfx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class JsonServiceImpl implements JsonService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean JsonData(JSONObject jsonObject,String url) {
        return true;
    }

}
