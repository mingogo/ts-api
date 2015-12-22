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

        List list = telephoneService.getPaginatedCombinations(
                phoneNumber,
                Integer.valueOf(pageNum),
                Integer.valueOf(pageSize)
        );
        List allCombination = telephoneService.getAllCombinations(Integer.valueOf(phoneNumber));

        tsRespContainer.setCount(String.valueOf(allCombination.size()));
        tsRespContainer.setCombinations(list);
        tsRespContainer.setPagination(
                telephoneService.getPagination(
                        request,
                        pageNum,
                        pageSize,
                        Integer.valueOf(phoneNumber)
                )
        );

        return tsRespContainer;
    }
}
