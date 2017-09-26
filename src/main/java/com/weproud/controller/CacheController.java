package com.weproud.controller;

import com.weproud.dto.ResponseBaseDto;
import com.weproud.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Logan. k
 */
@Slf4j
@Api(tags = {"Cache"})
@RestController
@RequestMapping("/v1/cache")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @ApiOperation(value = "With cache")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping(value = "/with-cache")
    public ResponseEntity getWithCache() throws Exception {
        int sum = this.cacheService.getWithCache();
        log.info("sum: {}", sum);
        return ResponseBaseDto.ok();
    }

    @ApiOperation(value = "With cache")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping(value = "/without-cache")
    public ResponseEntity getWithoutCache() throws Exception {
        int sum = this.cacheService.getWithoutCache();
        log.info("sum: {}", sum);
        return ResponseBaseDto.ok();
    }
}
