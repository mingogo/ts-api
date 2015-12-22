package com.mteng.service.impl;

import com.mteng.dto.PageContainer;
import com.mteng.service.TelephoneService;
import com.mteng.util.ServiceConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Service
public class TelephoneServiceImpl implements TelephoneService {

    // Implementation

    @Override
    public List<String> getPaginatedCombinations(String phoneNumber, Integer pageNum, Integer pageSize) {

        List<String> combos = getAllCombinations(Integer.valueOf(phoneNumber));
        Integer totalPageNumber = getTotalPageNum(pageSize, combos.size());
        int fromIndex = getPaginatedComboStartingIndex(pageNum, pageSize, totalPageNumber);
        int toIndex = getPaginatedComboEndingIndex(pageSize, combos, fromIndex);

        List<String> paginatedCombos = combos.subList(fromIndex, toIndex);

        return paginatedCombos;
    }

    @Override
    public List<String> getAllCombinations(Integer phoneNumber) {
        List<String> combos = new LinkedList<>();
        generateCombosHelper(combos, "", String.valueOf(phoneNumber));
        return combos;
    }

    @Override
    public PageContainer getPagination(HttpServletRequest request, Integer pageNum, Integer pageSize, Integer phoneNumber) {
        PageContainer pageContainer = new PageContainer();
        List<String> resultCombo = getAllCombinations(phoneNumber);
        Integer totalNumber = getTotalPageNum(Integer.valueOf(pageSize), resultCombo.size());
        pageContainer.setTotalPageNumber(String.valueOf(totalNumber));
        UriComponentsBuilder uriLast = UriComponentsBuilder.newInstance().fromUriString(request.getRequestURI());
        uriLast.queryParam(ServiceConstants.SIZE,"100");
        uriLast.queryParam(ServiceConstants.PAGE,"100");
        uriLast.host(ServiceConstants.HOST);
        uriLast.port(ServiceConstants.PORT);
        pageContainer.setLastPage(uriLast.toUriString());
        pageContainer.setNextPage("");
        pageContainer.setPreviousPage("");
        return pageContainer;
    }

    // PAGINATION

    private int getPaginatedComboEndingIndex(Integer pageSize, List<String> combos, int fromIndex) {
        int toIndex = fromIndex + Integer.valueOf(pageSize);

        if (toIndex > combos.size()) {
            toIndex = combos.size();
        }
        return toIndex;
    }

    private int getPaginatedComboStartingIndex(Integer pageNum, Integer pageSize, Integer totalPageNumber) {
        if (pageNum > totalPageNumber) {
            pageNum = totalPageNumber;
        }

        int fromIndex = 0;

        if (!Integer.valueOf(pageNum).equals(1)) {
            fromIndex = Integer.valueOf(pageSize) * (Integer.valueOf(pageNum) - 1) + 1;
        } else {
            fromIndex = 0;
        }
        return fromIndex;
    }

    private Integer getTotalPageNum(Integer pageSize, Integer totalRecNumber) {
        Integer totalPagesNumber = totalRecNumber / pageSize;
        if (totalRecNumber % pageSize > 0) {
            totalPagesNumber++;
        }
        return totalPagesNumber;
    }


    // Combination

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
