package com.weproud.controller;

import com.weproud.dto.ResponseBaseDto;
import com.weproud.exception.InvalidArgumentException;
import com.weproud.service.HelloService;
import com.weproud.validator.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Logan. k
 */
@Slf4j
@Api(tags = {"Hello"})
@RestController
@RequestMapping("/v1/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @ApiOperation(value = "world")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping(value = "/world/{name}")
    public ResponseEntity telegram(@PathVariable("name") @ApiParam(required = true, value = "name") final String name) throws Exception {
        Preconditions.requireNonNull(name, () -> new InvalidArgumentException("name"));
        String response = this.helloService.hello(name);
        return ResponseBaseDto.ok(response);
    }
}
