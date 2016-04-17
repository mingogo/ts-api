package com.mteng.service.impl;

import com.mteng.dto.PageDto;
import com.mteng.service.PaginationHelper;
import com.mteng.service.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Service
public class TelephoneServiceImpl implements TelephoneService {

    @Autowired
    PaginationHelper paginationHelper;

    public List<String> getPaginatedCombinations(List<String> allCombinations, Integer pageNum, Integer pageSize) {

        int fromIndex = paginationHelper.getPaginatedComboStartingIndex(
                pageNum,
                pageSize,
                paginationHelper.getTotalPageNum(
                        pageSize,
                        allCombinations.size()
                ),
                allCombinations.size()
        );

        return allCombinations.subList(fromIndex, paginationHelper.getPaginatedComboEndingIndex(
                pageSize,
                allCombinations.size(),
                fromIndex
        ));
    }

    @Override
    public List<String> getAllCombinations(String phoneNumber) {
        List<String> combos = new LinkedList<>();
        generateCombosHelper(combos, "", phoneNumber);
        return combos;
    }

    @Override
    public PageDto getPagination(HttpServletRequest request, Integer pageNum, Integer pageSize, String phoneNumber) {
        PageDto pageContainer = new PageDto();
        Integer totalPageNumber = paginationHelper.getTotalPageNum(Integer.valueOf(pageSize), getAllCombinations(phoneNumber).size());
        pageContainer.setTotalPageNumber(String.valueOf(totalPageNumber));
        pageContainer.setLastPage(String.valueOf(paginationHelper.getLastPage(pageSize, totalPageNumber)));
        pageContainer.setNextPage(String.valueOf(paginationHelper.getNextPage(pageSize, totalPageNumber, pageNum)));
        pageContainer.setPreviousPage(String.valueOf(paginationHelper.getPrevPage(pageSize, totalPageNumber, pageNum)));
        return pageContainer;
    }

    // Combination --

    private void generateCombosHelper(List<String> combos, String prefix, String remaining) {
        int digit = Integer.parseInt(remaining.substring(0, 1));
        if (remaining.length() == 1) {
            for (int i = 0; i < mappings[digit].length; i++) {
                combos.add(prefix + mappings[digit][i]);
            }
        } else {
            for (int i = 0; i < mappings[digit].length; i++) {
                generateCombosHelper(
                        combos,
                        prefix + mappings[digit][i],
                        remaining.substring(1)
                );
            }
        }
    }

    private String mappings[][] = {
            {"0"},
            {"1"},
            {"A", "B", "C"},
            {"D", "E", "F"},
            {"G", "H", "I"},
            {"J", "K", "L"},
            {"M", "N", "O"},
            {"P", "Q", "R", "S"},
            {"T", "U", "V"},
            {"W", "X", "Y", "Z"}
    };
}
