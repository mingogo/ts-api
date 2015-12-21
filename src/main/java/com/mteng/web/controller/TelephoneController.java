package com.mteng.web.controller;

import com.mteng.dto.TSRespContainer;
import com.mteng.service.TelephoneService;
import com.mteng.util.QueryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mteng on 12/21/2015.
 */
@Controller
@RequestMapping(value = "/api")
public class TelephoneController {

    @Autowired
    TelephoneService telephoneService;

    @RequestMapping(value = "/number/{phoneNumber}", params = {QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY}, method = RequestMethod.GET)
    @ResponseBody
    public TSRespContainer findAllPaginatedAndSorted(
            @PathVariable("phoneNumber") final String number
            // @PathVariable("phoneNumber") final String number,
            // @RequestParam(value = QueryConstants.PAGE) final int page,
            // @RequestParam(value = QueryConstants.SIZE) final int size
    ) {
        return telephoneService.getResp(number);
    }
}
