package com.github.denakh.serverlogfilter.system.model;

import com.github.denakh.serverlogfilter.system.Utils;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class LogItem {

    private final String DATE_FORMAT = "dd MMM yyyy HH:mm:ss,SSS";
    // "04 Jan 2022 07:03:04,105"
    private Date dateTime;
    private LogItemType type;
    private String sourceClass;
    private String mainMessage;
    private String additionalInfo = "";
    private boolean isValuable;
    // String serviceName;

//    public boolean isValuable() {
//        return isValuable;
//    }

    public LogItem(List<String> logItemAsStrings, String expectedTextPart) {
        if (logItemAsStrings.size() < 2) throw new RuntimeException(
                "Line number should be more than 1, but actually is: " + logItemAsStrings.size());
        for (String line : logItemAsStrings) {
            if (line.toLowerCase().contains(expectedTextPart.toLowerCase())) {
                isValuable = true;
                break;
            }
        }
        List<String> mainLine = Arrays.asList(logItemAsStrings.get(0).split(" \\| "));
        if (mainLine.size() < 4) throw new RuntimeException(
                "Main line is invalid (number of elements should be more than 3), but actually is: " + mainLine.size());
        dateTime = Utils.getDateFromString(mainLine.get(0), DATE_FORMAT);
        type = LogItemType.forValue(mainLine.get(2));
        sourceClass = mainLine.get(3);
        mainMessage = logItemAsStrings.get(1);
        if (logItemAsStrings.size() > 2) {
            // try with Streams
            for (int i = 3; i < logItemAsStrings.size(); i++) additionalInfo += "\n" + logItemAsStrings.get(i);
        }
    }

    public String makeStringForFile() {
        return  """
                $dateTime | | $type | $sourceClass
                $mainMessage
                $additionalInfo
                
                
                """
                .replace("$dateTime", Utils.getFormattedStringFromDate(dateTime, DATE_FORMAT))
                .replace("$type", type.toString())
                .replace("$sourceClass", sourceClass)
                .replace("$mainMessage", mainMessage)
                .replace("$additionalInfo", additionalInfo);
    }
}
