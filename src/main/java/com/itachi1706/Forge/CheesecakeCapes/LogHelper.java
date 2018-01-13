package com.itachi1706.Forge.CheesecakeCapes;

import org.apache.logging.log4j.*;

/**
 * Created by Kenneth on 13/7/2016.
 * for com.itachi1706.Forge.CheesecakeCapes in CheesecakeCapes
 */
public class LogHelper {

    public static final Marker MOD_MARKER = MarkerManager.getMarker(CheesecakeCapes.MOD_ID);
    private static final Logger LOGGER = LogManager.getLogger(CheesecakeCapes.MOD_ID);

    public static void log(Level level, Marker marker, Object  object) {
        LOGGER.log(level, marker, object);
    }

    public static void all(Object object)
    {
        log(Level.ALL, MOD_MARKER, object);
    }

    public static void debug(Object object)
    {
        log(Level.DEBUG, MOD_MARKER, object);
    }

    public static void error(Object object)
    {
        log(Level.ERROR, MOD_MARKER, object);
    }

    public static void fatal(Object object)
    {
        log(Level.FATAL, MOD_MARKER, object);
    }

    public static void info(Object object)
    {
        log(Level.INFO, MOD_MARKER, object);
    }

    public static void off(Object object)
    {
        log(Level.OFF, MOD_MARKER, object);
    }

    public static void trace(Object object)
    {
        log(Level.TRACE, MOD_MARKER, object);
    }

    public static void warn(Object object)
    {
        log(Level.WARN, MOD_MARKER, object);
    }
}
