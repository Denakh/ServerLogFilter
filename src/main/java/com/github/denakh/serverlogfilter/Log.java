package com.github.denakh.serverlogfilter;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Log {

    List<LogItem> logItemList = new ArrayList<>();
    List<String> fileNamesList = new ArrayList<>();
    Date FilteringTime;

}
