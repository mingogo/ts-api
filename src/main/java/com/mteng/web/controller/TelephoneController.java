package com.mteng.web.controller;

import com.mteng.dto.PhoneNumberDto;
import com.mteng.service.ContentWrapper;
import com.mteng.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api/")
public class TelephoneController {

    private ContentWrapper contentWrapper;
    private final static String API_VERSION = "v1/";
    private final static String NUMBER_RESOURCE = "number/";
    private final static String PHONE_RESOURCE = "{phoneNumber}";

    @Autowired
    public TelephoneController(ContentWrapper contentWrapper) {
        this.contentWrapper = contentWrapper;
    }

    // v1 --

    @RequestMapping(
            value = API_VERSION + NUMBER_RESOURCE + PHONE_RESOURCE,
            params = {ServiceConstants.PAGE, ServiceConstants.SIZE},
            method = RequestMethod.GET
    )
    @ResponseBody
    public PhoneNumberDto findAllPaginated(
            HttpServletRequest request,
            @PathVariable("phoneNumber") String phoneNumber,
            @RequestParam(value = ServiceConstants.PAGE) Integer pageNumber,
            @RequestParam(value = ServiceConstants.SIZE) Integer pageSize
    ) {
        return contentWrapper.getResp(request, phoneNumber, pageNumber, pageSize);
    }
}