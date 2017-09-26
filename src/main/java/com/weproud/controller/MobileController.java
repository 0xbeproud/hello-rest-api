package com.weproud.controller;

import com.weproud.dto.ResponseBaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Logan. k
 */
@Slf4j
@Api(tags = {"Mobile"})
@RestController
@RequestMapping("/v1/mobile")
public class MobileController {

    @ApiOperation(value = "Get Hello")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping(value = "/")
    public ResponseEntity getHello(final Device device) throws Exception {
        log.info("device: {}", device);
        log.info("device.getDevicePlatform(): {}", device.getDevicePlatform());
        log.info("device.isNormal(): {}", device.isNormal());
        log.info("device.isMobile(): {}", device.isMobile());
        log.info("device.isTablet(): {}", device.isTablet());
        return ResponseBaseDto.ok();
    }
}
