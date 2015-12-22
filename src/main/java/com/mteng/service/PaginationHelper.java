package com.mteng.service;

import com.mteng.util.ServiceConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Service
public class PaginationHelper {

    public UriComponentsBuilder getLastPageURI(HttpServletRequest request, Integer pageSize, Integer totalPageNumber) {
        UriComponentsBuilder uriLast = UriComponentsBuilder.newInstance().fromUriString(request.getRequestURI());
        uriLast.queryParam(ServiceConstants.SIZE, pageSize);
        uriLast.queryParam(ServiceConstants.PAGE, totalPageNumber);
        uriLast.host(ServiceConstants.HOST);
        uriLast.port(ServiceConstants.PORT);
        return uriLast;
    }

    public UriComponentsBuilder getNextPageURI(HttpServletRequest request, Integer pageSize, Integer totalPageNumber, Integer pageNum) {
        UriComponentsBuilder uriNext = UriComponentsBuilder.newInstance().fromUriString(request.getRequestURI());
        uriNext.queryParam(ServiceConstants.SIZE, pageSize);
        Integer newPageNum = pageNum + 1;
        uriNext.queryParam(ServiceConstants.PAGE, newPageNum > totalPageNumber ? totalPageNumber : newPageNum);
        uriNext.host(ServiceConstants.HOST);
        uriNext.port(ServiceConstants.PORT);
        return uriNext;
    }

    public UriComponentsBuilder getPrevPageURI(HttpServletRequest request, Integer pageSize, Integer totalPageNumber, Integer pageNum) {
        UriComponentsBuilder uriPrev = UriComponentsBuilder.newInstance().fromUriString(request.getRequestURI());
        uriPrev.queryParam(ServiceConstants.SIZE, pageSize);
        Integer newPageNum = pageNum - 1;
        uriPrev.queryParam(ServiceConstants.PAGE, newPageNum < 1 ? 1 : newPageNum);
        uriPrev.host(ServiceConstants.HOST);
        uriPrev.port(ServiceConstants.PORT);
        return uriPrev;
    }

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
}
