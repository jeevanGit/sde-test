package com.overbond.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overbond.entities.OutputFormat;
import com.overbond.entities.Spread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OutputService {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void printOutputToSpecifiedPath(List<Spread> spreadList, String fileName) throws IOException {
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setData(spreadList);
        System.out.println("About to write the function");
        String valueStr = objectMapper.writeValueAsString(outputFormat);
        Files.write(Paths.get(fileName), valueStr.getBytes(), StandardOpenOption.CREATE);
        System.out.println(valueStr);
    }
}
