package com.example.demo.controller;

import com.example.demo.payload.ApiResponse;
import com.example.demo.service.ValCursService;
import com.example.demo.utils.AppConstant;
import com.example.demo.utils.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ValCursController {
    @Autowired
    ValCursService valCursService;
    // GetAll_VALCURS
    @GetMapping("/currencies")
    public HttpEntity<?> getList(@RequestParam(value = "page", defaultValue = AppConstant.DEFAULT_PAGE) int page,
                                 @RequestParam(value = "size", defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size) {
        return ResponseEntity.ok(new ApiResponse(MessageConst.GET_SUCCESS, true, valCursService.getList(page, size)));
    }
    // GetValue_By_CarCode
    @GetMapping("/currency/{code}")
    public HttpEntity<?> getCurrency(@PathVariable String code) {
        return ResponseEntity.ok(new ApiResponse(MessageConst.GET_SUCCESS, true, valCursService.getCurrencyByCode(code)));
    }
}
