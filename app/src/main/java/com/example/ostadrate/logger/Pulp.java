package com.example.ostadrate.logger;

import java.util.List;
import java.util.Map;

public class Pulp {

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


    public static void info(String tag, String message, String throwable, String... data) {
    }

    public static void info(String tag, String message, String... data) {
    }


    private void log(LogLevel logLevel, String tag, String message, Throwable throwable, String... data) {

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
