package com.github.denakh.serverlogfilter.system.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LogItemType {

    DEBUG("DEBUG"),
    WARN("WARN"),
    TRACE("TRACE"),
    ERROR("ERROR"),
    ANOTHER("");

    public final String value;

    LogItemType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static LogItemType forValue(String value) {
        List<LogItemType> logItemTypesForValue = Arrays.stream(values()).filter(logItemType ->
                logItemType.value.equals(value)).collect(Collectors.toList());
        if (logItemTypesForValue.size() == 1) return logItemTypesForValue.get(0);
        else return LogItemType.ANOTHER;
    }
}
