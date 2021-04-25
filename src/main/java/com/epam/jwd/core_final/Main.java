package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    static final Logger LOGGER = LoggerFactory.getLogger("LOGGER");

    public static void main(String[] args) {
        try {
            Application.start();
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
    }
}