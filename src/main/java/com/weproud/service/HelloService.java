package com.weproud.service;

import com.weproud.controller.HelloController;
import com.weproud.entities.Hello;
import com.weproud.repositories.HelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Logan. k
 */
@Slf4j
@Service
public class HelloService {

    @Autowired
    private HelloRepository helloRepository;

    public Hello getHello(final Long id) {
        return this.helloRepository.findOne(id);
    }

    @Transactional
    public Hello createHello(final HelloController.CreateHelloRequest request) {
        return this.helloRepository.save(new Hello(request.getName()));
    }
}
