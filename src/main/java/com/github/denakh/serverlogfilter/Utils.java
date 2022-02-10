package com.github.denakh.serverlogfilter;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Utils {

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

    public static Date getDateFromString(String dateString, String dateFormatString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(dateString + " time is not parsed. Please verify format: " + dateFormat);
        }
    }

//    public static LogItem getObjectFromStringLine(String stringLine) {
//        return new LogItem();
//    }
}
