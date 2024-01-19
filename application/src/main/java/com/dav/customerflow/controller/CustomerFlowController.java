package com.dav.customerflow.controller;

import com.dav.customerflow.dto.ProductDto;
import com.dav.customerflow.service.CustomerFlowService;
import com.dav.customerflow.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.dav.customerflow.constant.HeaderConstant.REQUEST_JWT;
import static com.dav.customerflow.constant.UrlConstant.*;

@RestController
@CrossOrigin
@RequestMapping(V1_CUSTOMER_FLOW)
public class CustomerFlowController {

    @Autowired
    private CustomerFlowService customerFlowService;

    @Autowired
    private HttpServletRequest requestHeader;

    @PostMapping(value = SAVE_PRODUCT)
    @Operation(summary = "Get product(s) details")
    public ResponseEntity<Object> saveProductDetails(@RequestBody ProductDto productDto) {
        String user = SecurityUtils.getUser(requestHeader.getHeader(REQUEST_JWT));
        customerFlowService.save(productDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
