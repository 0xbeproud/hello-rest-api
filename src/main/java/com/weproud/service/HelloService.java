package com.weproud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Logan. k
 */
@Slf4j
@Service
public class HelloService {

    public String hello(final String name){
        return "hello " + name;
    }
}
