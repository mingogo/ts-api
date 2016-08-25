package com.mteng.service.impl;

import com.mteng.service.PaginationHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.util.Assert.notEmpty;

@RunWith(MockitoJUnitRunner.class)
public class TelephoneServiceImplTest {

    private static final String TEST_NUMBER = "12345";
    private static final Integer PAGE = 1;
    private static final Integer SIZE = 100;
    @Mock
    private PaginationHelper mockPaginationHelper;
    @InjectMocks
    private TelephoneServiceImpl telephoneService;


    @Test
    public void getAllCombinations_NumbersCalculated(){
        List<String> lists = telephoneService.getAllCombinations("12345");
        assertThat(lists, not(empty()));
    }

    @Test
    public void getPaginatedCombinations_NumbersCalculated(){
        when(mockPaginationHelper.getPaginatedComboStartingIndex(isA(Integer.class),isA(Integer.class),isA(Integer.class), isA(Integer.class))).thenReturn(1);
        when(mockPaginationHelper.getTotalPageNum(isA(Integer.class),isA(Integer.class))).thenReturn(100);
        when(mockPaginationHelper.getPaginatedComboEndingIndex(isA(Integer.class),isA(Integer.class),isA(Integer.class))).thenReturn(1);
        List<String> lists =
                telephoneService.getPaginatedCombinations(telephoneService.getAllCombinations(TEST_NUMBER),PAGE, SIZE);
        assertThat(lists, empty());
    }

}