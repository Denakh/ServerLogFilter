package com.github.denakh.serverlogfilter;

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
    Date FilteringTime;

    public Log(List<File> logFiles) {
        List<String> logLines = new ArrayList<>();
        for (File logFile : logFiles) {
            try {
                logLines.addAll(FileUtils.getStringLinesFromFile(logFile.getPath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        List<List<String>> logItemsAsStrings = new ArrayList<>();
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
            logItemsAsStrings.add(logItemAsStrings);
        }
    }
}
