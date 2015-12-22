package com.mteng.web.controller;

import com.mteng.dto.TSRespContainer;
import com.mteng.service.ContentWrapper;
import com.mteng.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api")
public class TelephoneController {

    @Autowired
    ContentWrapper contentWrapper;

    @RequestMapping(
            value = "/number/{phoneNumber}",
            params = {ServiceConstants.PAGE, ServiceConstants.SIZE},
            method = RequestMethod.GET
    )
    @ResponseBody
    public TSRespContainer findAllPaginated(
            HttpServletRequest request,
            @PathVariable("phoneNumber") String phoneNumber,
            @RequestParam(value = ServiceConstants.PAGE) Integer pageNumber,
            @RequestParam(value = ServiceConstants.SIZE) Integer pageSize
    ) {
        return contentWrapper.getResp(request, phoneNumber, pageNumber, pageSize);
    }
}
