package com.github.denakh.serverlogfilter;

import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class LogItem {

    Date dateTime;
    LogItemType type;
    String sourceClass;
    String mainMessage;
    String additionalInfo = "";
    // String serviceName;
    boolean isValuable;

    public LogItem(List<String> logItemAsStrings, String expectedTextPart) {
        // if (logItemAsStrings.size() < 2) throw new lineNumberShouldBeNotLessThanException;
        for (String line : logItemAsStrings) {
            if (line.toLowerCase().contains(expectedTextPart.toLowerCase())) isValuable = true;
            break;
        }
        List<String> mainLine = Arrays.asList(logItemAsStrings.get(0).split(" | "));
        // if (mainLine.size() < 4) throw new mainLineIsInvalidException;
        // dateTime = dateFromString(mainLine.get(0));
        // type = typeFromString(mainLine.get(2));
        sourceClass = mainLine.get(3);
        mainMessage = logItemAsStrings.get(1);
        if (logItemAsStrings.size() > 2) {
            // try with Streams
            for (int i = 2; i < logItemAsStrings.size(); i++) additionalInfo += logItemAsStrings.get(i);
        }
    }
}
