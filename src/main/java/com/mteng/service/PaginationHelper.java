package com.mteng.service;

import org.springframework.stereotype.Service;

@Service
public class PaginationHelper {

    public int getPaginatedComboEndingIndex(Integer pageSize, Integer totalRecNumber, int fromIndex) {
        int toIndex = fromIndex + pageSize;

        if (toIndex > totalRecNumber) {
            toIndex = totalRecNumber;
        }
        return toIndex;
    }

    public int getPaginatedComboStartingIndex(Integer pageNum, Integer pageSize, Integer totalPageNumber, Integer totalRecNumber) {
        if (pageNum > totalPageNumber) {
            pageNum = totalPageNumber;
        }

        int fromIndex = 0;

        if (pageSize < totalRecNumber) {
            fromIndex = pageSize * (pageNum - 1);
        } else {
            fromIndex = 0;
        }
        return fromIndex;
    }

    public Integer getTotalPageNum(Integer pageSize, Integer totalRecNumber) {
        Integer totalPagesNumber = totalRecNumber / pageSize;
        if (totalRecNumber % pageSize > 0) {
            totalPagesNumber++;
        }
        return totalPagesNumber;
    }

    public Integer getLastPage(Integer pageSize, Integer totalPageNumber) {
        return totalPageNumber;
    }

    public Integer getNextPage(Integer pageSize, Integer totalPageNumber, Integer pageNum) {
        Integer newPageNum = pageNum + 1;
        return newPageNum > totalPageNumber ? totalPageNumber : newPageNum;
    }

    public Integer getPrevPage(Integer pageSize, Integer totalPageNumber, Integer pageNum) {
        Integer newPageNum = pageNum - 1;
        return newPageNum < 1 ? 1 : newPageNum;
    }
}
