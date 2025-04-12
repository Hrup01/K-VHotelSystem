package com.Hrup.HotelHelper.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logger {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}