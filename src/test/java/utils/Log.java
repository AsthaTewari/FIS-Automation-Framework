package utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private static Logger logger = Logger.getLogger("FISLogger");

    static {
        try {
            FileHandler fh = new FileHandler("reports/test-log.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.addHandler(new ConsoleHandler());
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void info(String message) {
        logger.info(message);
    }
}
