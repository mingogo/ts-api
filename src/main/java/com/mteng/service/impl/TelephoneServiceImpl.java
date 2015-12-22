package com.mteng.service.impl;

import com.mteng.dto.PageContainer;
import com.mteng.service.PaginationHelper;
import com.mteng.service.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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

        int toIndex = paginationHelper.getPaginatedComboEndingIndex(
                pageSize,
                allCombinations.size(),
                fromIndex
        );

        List<String> paginatedCombos = allCombinations.subList(fromIndex, toIndex);
        return paginatedCombos;
    }

    @Override
    public List<String> getAllCombinations(String phoneNumber) {
        List<String> combos = new LinkedList<>();
        generateCombosHelper(combos, "", phoneNumber);
        return combos;
    }

    @Override
    public PageContainer getPagination(HttpServletRequest request, Integer pageNum, Integer pageSize, String phoneNumber) {
        PageContainer pageContainer = new PageContainer();
        List<String> resultCombo = getAllCombinations(phoneNumber);
        Integer totalPageNumber = paginationHelper.getTotalPageNum(Integer.valueOf(pageSize), resultCombo.size());
        pageContainer.setTotalPageNumber(String.valueOf(totalPageNumber));

        UriComponentsBuilder uriLast = paginationHelper.getLastPageURI(request, pageSize, totalPageNumber);
        UriComponentsBuilder uriNext = paginationHelper.getNextPageURI(request, pageSize, totalPageNumber, pageNum);
        UriComponentsBuilder uriPrev = paginationHelper.getPrevPageURI(request, pageSize, totalPageNumber, pageNum);

        pageContainer.setLastPage(uriLast.toUriString());
        pageContainer.setNextPage(uriNext.toUriString());
        pageContainer.setPreviousPage(uriPrev.toUriString());
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
