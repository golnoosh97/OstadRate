package com.example.ostadrate.logger;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pulp {

    // ------ Configuration

    private static boolean isLogEnabled = true;
    private static boolean isLogHandlerEnabled = true;
    private static String mainTag = "Pulp";
    private static List<LogHandler> handlers;

    public static void setLogEnabled(boolean enabled) {
        isLogEnabled = enabled;
    }

    public static void setLogHandlerEnabled(boolean enabled) {
        isLogHandlerEnabled = enabled;
    }

    public static void setMainTag(String tag) {
        mainTag = tag;
    }

    public static void addHandler(LogHandler handler) {
        handlers.add(handler);
    }

    public static void clearhandlers(){
        handlers.clear();
    }


    // ------ LOGGER methods

    public static void info(String tag, String message, Throwable throwable, String... data) {
        log(LogLevel.INFO, tag, message, throwable, data);
    }

    public static void info(String tag, String message, String... data) {
        info(tag, message, null, data);
    }

    public static LogData info(String tag, String message, Throwable throwable) {
        return new LogData(LogLevel.INFO, tag, message, throwable);
    }

    public static LogData info(String tag, String message) {
        return new LogData(LogLevel.INFO, tag, message, null);
    }

    public static void debug(String tag, String message, Throwable throwable, String... data) {
        log(LogLevel.DEBUG, tag, message, throwable, data);
    }

    public static void debug(String tag, String message, String... data) {
        debug(tag, message, null, data);
    }

    public static LogData debug(String tag, String message, Throwable throwable) {
        return new LogData(LogLevel.DEBUG, tag, message, throwable);
    }

    public static LogData debug(String tag, String message) {
        return new LogData(LogLevel.DEBUG, tag, message, null);
    }

    public static void warn(String tag, String message, Throwable throwable, String... data) {
        log(LogLevel.WARN, tag, message, throwable, data);
    }

    public static void warn(String tag, String message, String... data) {
        warn(tag, message, null, data);
    }

    public static LogData warn(String tag, String message, Throwable throwable) {
        return new LogData(LogLevel.WARN, tag, message, throwable);
    }

    public static LogData warn(String tag, String message) {
        return new LogData(LogLevel.WARN, tag, message, null);
    }

    public static void error(String tag, String message, Throwable throwable, String... data) {
        log(LogLevel.ERROR, tag, message, throwable, data);
    }

    public static void error(String tag, String message, String... data) {
        error(tag, message, null, data);
    }

    public static LogData error(String tag, String message, Throwable throwable) {
        return new LogData(LogLevel.ERROR, tag, message, throwable);
    }

    public static LogData error(String tag, String message) {
        return new LogData(LogLevel.ERROR, tag, message, null);
    }

    public static void wtf(String tag, String message, Throwable throwable, String... data) {
        log(LogLevel.WTF, tag, message, throwable, data);
    }

    public static void wtf(String tag, String message, String... data) {
        wtf(tag, message, null, data);
    }

    public static LogData wtf(String tag, String message, Throwable throwable) {
        return new LogData(LogLevel.WTF, tag, message, throwable);
    }

    public static LogData wtf(String tag, String message) {
        return new LogData(LogLevel.WTF, tag, message, null);
    }

    // ------ Experimental log data class

    public static class LogData {
        private LogLevel level;
        private String tag, message;
        private Throwable throwable;
        private Map<String, String> data = new HashMap<>();

        public LogData(LogLevel level, String tag, String message, Throwable throwable) {
            this.level = level;
            this.tag = tag;
            this.message = message;
            this.throwable = throwable;
        }

        public LogData addMessage(String key, String message) {
            data.put(key, message);
            return this;
        }

        public void log() {
            Pulp.log(level, tag, message, throwable, data);
        }
    }

    // ------ Log utilities

    private static void log(LogLevel logLevel, String tag, String message, Throwable throwable, String... data) {
        if (!isLogEnabled) return;
        Map<String, String> map = getMap(data);
        log(logLevel, tag, message, throwable, map);
    }

    private static void log(LogLevel logLevel, String tag, String message, Throwable throwable, Map<String, String> map) {
        if (!isLogEnabled) return;
        String text = getMessage(tag, message, map);
        if (isLogHandlerEnabled) {
            for (LogHandler handler : handlers) {
                handler.onLog(logLevel, tag, message, throwable, map);
            }
        }
        switch (logLevel) {
            case INFO:
                if (throwable == null) Log.i(mainTag, text);
                else Log.i(mainTag, text, throwable);
                break;
            case DEBUG:
                if (throwable == null) Log.d(mainTag, text);
                else Log.d(mainTag, text, throwable);
                break;
            case WARN:
                if (throwable == null) Log.w(mainTag, text);
                else Log.w(mainTag, text, throwable);
                break;
            case ERROR:
                if (throwable == null) Log.e(mainTag, text);
                else Log.e(mainTag, text, throwable);
                break;
            case WTF:
                if (throwable == null) Log.wtf(mainTag, text);
                else Log.wtf(mainTag, text, throwable);
                break;
        }
    }

    private static Map<String, String> getMap(String... data) {
        Map<String, String> extraData = new HashMap<>();
        if (data.length != 0) {
            for (int i = 0; i < data.length; i++) {
                if (i + 1 < data.length) {
                    extraData.put(data[i], data[i+1]);
                } else {
                    extraData.put(data[i], "None");
                }
                i++;
            }
        }
        return extraData;
    }

    private static String getMessage(String tag, String message, Map<String, String> data) {
        StringBuilder text = new StringBuilder("Tag: \n" + tag + "\n" +
                "Message: " + message + "\n");
        if (data.size() != 0) {
            text.append("Data:\n");
        }
        for (Map.Entry<String, String> item : data.entrySet()) {
            text.append("\n").append(item.getKey()).append(":\t").append(item.getValue());
        }
        return text.toString();
    }


    private enum LogLevel {
        INFO,
        DEBUG,
        WARN,
        ERROR,
        WTF
    }

    public interface LogHandler {
        void onLog(LogLevel logLevel, String tag, String message, Throwable throwable, Map<String, String> data);
    }
}
