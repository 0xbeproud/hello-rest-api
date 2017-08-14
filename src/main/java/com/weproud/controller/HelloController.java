package com.weproud.controller;

import com.weproud.dto.ResponseBaseDto;
import com.weproud.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @ApiOperation(value = "Get Hello")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity getHello(@PathVariable @ApiParam(required = false, value = "id") @Valid final Long id) throws Exception {

        return ResponseBaseDto.ok(this.helloService.getHello(id));
    }

    @ApiOperation(value = "Create Hello")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "")
    })
    @PostMapping(value = "/")
    public ResponseEntity createHello(@RequestBody @Valid CreateHelloRequest request) throws Exception {

        return ResponseBaseDto.ok(this.helloService.createHello(request));
    }

    @ApiOperation(value = "validator")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "")
    })
    @PostMapping(value = "/validator/{id}")
    public ResponseEntity validator(@PathVariable @ApiParam(required = true, value = "id") final Long id,
                                    @Valid @RequestBody ValidatorTestRequest request) {
        log.info("request: {}", request);
        return ResponseBaseDto.ok();
    }

    @Getter
    public static class CreateHelloRequest {
        @NotBlank
        private String name;
    }

    @Getter
    public static class ValidatorTestRequest {
        @NotBlank
        private String name;
        @NotNull
        private Integer age;
    }
}
