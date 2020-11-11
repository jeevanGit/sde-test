package com.overbond.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overbond.entities.GenericBond;
import com.overbond.entities.InputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.*;

public class InputService {
    private static Logger logger = LoggerFactory.getLogger(InputService.class);
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static GenericBond[] readDataFromSource(String fileName) throws IOException {

        byte[] fileBytes = readAllBytes(Paths.get(fileName));;
        System.out.println();
        logger.info("Successfully read input files");
        InputFormat inputFormat = objectMapper.readValue(new String(fileBytes), InputFormat.class);
        return inputFormat.data;
    }
}
