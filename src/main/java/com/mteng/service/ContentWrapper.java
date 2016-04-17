package com.mteng.service;

import com.mteng.dto.PhoneNumberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ContentWrapper {

    @Autowired
    TelephoneService telephoneService;

    public PhoneNumberDto getResp(HttpServletRequest request, String phoneNumber, Integer pageNum, Integer pageSize) {
        PhoneNumberDto tsRespContainer = new PhoneNumberDto();
        tsRespContainer.setCount(String.valueOf(telephoneService.getAllCombinations(phoneNumber).size()));
        tsRespContainer.setCombinations(telephoneService.getPaginatedCombinations(
                telephoneService.getAllCombinations(phoneNumber),
                pageNum,
                pageSize
        ));
        tsRespContainer.setPagination(
                telephoneService.getPagination(
                        request,
                        pageNum,
                        pageSize,
                        phoneNumber
                )
        );
        return tsRespContainer;
    }
}
