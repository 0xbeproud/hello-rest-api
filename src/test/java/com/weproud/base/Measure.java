package com.weproud.base;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * Logan. k
 */
@Slf4j
public class Measure {

    public static void time(Supplier supplier) {
        long start = System.currentTimeMillis();
        supplier.get();
        long end = System.currentTimeMillis();
        log.debug("taken: {} ms", (end - start));
    }
}
