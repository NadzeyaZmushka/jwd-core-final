package com.epam.jwd.core_final.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidStateException extends Exception {
    // todo
    static final Logger LOGGER = LogManager.getLogger();
    public InvalidStateException() {
        LOGGER.warn("Invalid state");
    }


}
