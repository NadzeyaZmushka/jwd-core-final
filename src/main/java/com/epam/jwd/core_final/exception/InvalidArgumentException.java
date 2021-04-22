package com.epam.jwd.core_final.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidArgumentException extends InvalidStateException {
    static final Logger LOGGER = LogManager.getLogger();

    public InvalidArgumentException() {
        LOGGER.error("Invalid arguments");
    }
}
