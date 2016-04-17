package com.mteng.service;

import com.mteng.ApiApplication;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mteng on 12/20/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
public class TelephoneServiceTest extends TestCase {

    @Autowired
    PaginationHelper paginationHelper;

    @Test
    public void testGenerateCombos() throws Exception {
        Integer totalRecNum = 9;
        Integer pageNum = 2;
        Integer pageSize = 2;
        Integer totalPageNumber = 5;
        int startingIndex = paginationHelper.getPaginatedComboStartingIndex(pageNum, pageSize, totalPageNumber, totalRecNum);
        int endingIndex = paginationHelper.getPaginatedComboEndingIndex(pageSize, totalRecNum, startingIndex);
        System.out.println(startingIndex + " and " + endingIndex);
    }
}