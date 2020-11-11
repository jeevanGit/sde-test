package com.overbond.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overbond.entities.OutputFormat;
import com.overbond.entities.Spread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OutputService {
    private static Logger logger = LoggerFactory.getLogger(OutputService.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void printOutputToSpecifiedPath(List<Spread> spreadList, String fileName) throws IOException {
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setData(spreadList);
        String valueStr = objectMapper.writeValueAsString(outputFormat);
        logger.info("Successfully calculated the spread");
        Files.write(Paths.get(fileName), valueStr.getBytes(), StandardOpenOption.CREATE);
        logger.info("Final spread: " + valueStr);
    }
}
