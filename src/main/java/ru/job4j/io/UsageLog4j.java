package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
       /* LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);*/

        int x = 1;
        long i = 2L;
        byte a1 = 3;
        short r1 = 4;
        char symbol = '5';
        float x1 = 6.0f;
        double z = 7.0;
        boolean flag = true;

        LOG.debug("Java Primitive Types: "
                        + "int: {}, "
                        + "long: {}, "
                        + "byte: {}, "
                        + "short: {}, "
                        + "char: {}, "
                        + "float: {}, "
                        + "double: {}, "
                        + "boolean: {}, ",
                x, i, a1, r1, symbol, x1, z, flag);
    }

}