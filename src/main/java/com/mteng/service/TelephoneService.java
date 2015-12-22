package com.mteng.service;

import com.mteng.dto.PageContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TelephoneService {
    List<String> getPaginatedCombinations(List<String> allCombinations, Integer pageNum, Integer pageSize);
    PageContainer getPagination(HttpServletRequest request, Integer pageNum, Integer pageSize, String phoneNumber);
    List getAllCombinations(String phoneNumber);
}
