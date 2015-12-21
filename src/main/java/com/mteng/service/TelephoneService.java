package com.mteng.service;

import com.mteng.dto.TSRespContainer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TelephoneService {
    TSRespContainer getResp(String phoneNumber);
}
