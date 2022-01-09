package com.github.denakh.serverlogfilter;

import lombok.Data;

import java.util.Date;

@Data
public class LogItem {

    Date dateTime;
    LogItemType type;
    String sourceClass;
    String mainMessage;
    String additionalInfo;
    String serviceName;
    boolean isValuable;

}
