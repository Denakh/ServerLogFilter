package com.github.denakh.serverlogfilter.system;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Utils {

    // "src/test/resources/file.log"
    public static List<String> getStringLinesFromFile(String filePath) {
        List<String> stringLinesList = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            stringLinesList.add(scanner.nextLine());
        }
        stringLinesList.remove(stringLinesList.size() - 1);
        return stringLinesList;
    }

    public static File createFileFromString(String stringContent, String newFilePath) {
        File newFile = new File(newFilePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(stringContent);
        } catch (IOException e) {
            e.printStackTrace();
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

    public static String getFormattedStringFromDate(Date date, String dateFormatString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        return dateFormat.format(date);
    }

//    public static LogItem getObjectFromStringLine(String stringLine) {
//        return new LogItem();
//    }
}
