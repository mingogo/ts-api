package com.mteng.service;

import com.mteng.dto.TSRespContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ContentWrapper {

    @Autowired
    TelephoneService telephoneService;

    public TSRespContainer getResp(HttpServletRequest request, String phoneNumber, Integer pageNum, Integer pageSize) {

        TSRespContainer tsRespContainer = new TSRespContainer();
        List allCombination = telephoneService.getAllCombinations(phoneNumber);
        List paginatedCombinations = telephoneService.getPaginatedCombinations(
                allCombination,
                pageNum,
                pageSize
        );

        tsRespContainer.setCount(String.valueOf(allCombination.size()));
        tsRespContainer.setCombinations(paginatedCombinations);
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
