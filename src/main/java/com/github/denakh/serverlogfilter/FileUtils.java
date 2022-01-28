package com.github.denakh.serverlogfilter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    // "src/test/resources/file.log"
    public static List<String> getStringLinesFromFile(String filePath) throws FileNotFoundException {
        List<String> stringLinesList = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            stringLinesList.add(scanner.nextLine());
        }
        stringLinesList.remove(stringLinesList.size() - 1);
        return stringLinesList;
    }

    public static File createFileFromString(String stringContent, String newFilePath) throws IOException {
        File newFile = new File(newFilePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(stringContent);
        }
        return newFile;
    }

    public static LogItem getObjectFromStringLine(String stringLine) {
        return new LogItem();
    }
}
