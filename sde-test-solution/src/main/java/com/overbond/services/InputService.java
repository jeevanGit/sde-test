package com.overbond.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overbond.entities.GenericBond;
import com.overbond.entities.InputFormat;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.*;

public class InputService {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static GenericBond[] readDataFromSource(String fileName) throws IOException {

        byte[] fileBytes = readAllBytes(Paths.get(fileName));;
        System.out.println("Successfully read input files");
        InputFormat inputFormat = objectMapper.readValue(new String(fileBytes), InputFormat.class);
        return inputFormat.data;
    }
}
