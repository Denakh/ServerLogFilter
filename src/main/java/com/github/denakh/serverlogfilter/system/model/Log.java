package com.github.denakh.serverlogfilter.system.model;

import com.github.denakh.serverlogfilter.system.Utils;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Log {

    List<LogItem> logItemList = new ArrayList<>();
    List<String> fileNamesList = new ArrayList<>();
    Date filteringTime;

    public Log(List<File> logFiles, String expectedTextPart) {
        if (logFiles == null || logFiles.isEmpty()) throw new RuntimeException("Log files list is empty");
        filteringTime = new Date();
        List<String> logLines = new ArrayList<>();
        for (File logFile : logFiles) {
            fileNamesList.add(logFile.getName());
            try {
                logLines.addAll(Utils.getStringLinesFromFile(logFile.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        List<Integer> startLineIndexes = new ArrayList<>();
        for (int i = 0; i < logLines.size(); i++) {
            if (logLines.get(i).contains(" |  | ")) startLineIndexes.add(i);
        }
        startLineIndexes.add(logLines.size());
        for (int i = 0; i < startLineIndexes.size() - 1; i++) {
            List<String> logItemAsStrings = new ArrayList<>();
            for (int j = startLineIndexes.get(i); j < startLineIndexes.get(i + 1) - 1; j++) {
                logItemAsStrings.add(logLines.get(j));
            }
            LogItem logItem = new LogItem(logItemAsStrings, expectedTextPart);
            if (logItem.isValuable() && !logItemList.contains(logItem)) logItemList.add(logItem);
        }
    }

    public String makeFileString() {
        final String[] fileString = {""};
        logItemList.forEach(logItem -> fileString[0] += logItem.makeStringForFile());
        return fileString[0];
    }
}
