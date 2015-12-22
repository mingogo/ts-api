package com.mteng.service;

import com.mteng.dto.PageContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TelephoneService {
    List getPaginatedCombinations(String phoneNumber, Integer pageNum, Integer pageSize);
    PageContainer getPagination(HttpServletRequest request, Integer pageNum, Integer pageSize, Integer phoneNumber);
    List getAllCombinations(Integer phoneNumber);
}
