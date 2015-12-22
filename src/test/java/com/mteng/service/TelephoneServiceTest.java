package com.mteng.service;

import com.mteng.ApiApplication;
import com.mteng.dto.TSRespContainer;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by mteng on 12/20/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiApplication.class)
public class TelephoneServiceTest extends TestCase {

    @Autowired
    TelephoneService telephoneService;

    @Test
    public void testGenerateCombos() throws Exception {
        // List<String> container = telephoneService.getPaginatedCombinations("","","");
        // List<String> tsRespContainer = telephoneService.getPaginatedCombinations(36,1,3);
    }
}