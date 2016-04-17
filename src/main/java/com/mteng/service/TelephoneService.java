package com.mteng.service;

import com.mteng.dto.PageDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TelephoneService {
    List<String> getPaginatedCombinations(List<String> allCombinations, Integer pageNum, Integer pageSize);
    PageDto getPagination(HttpServletRequest request, Integer pageNum, Integer pageSize, String phoneNumber);
    List getAllCombinations(String phoneNumber);
}
