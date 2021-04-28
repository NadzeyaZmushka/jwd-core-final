package com.epam.jwd.core_final.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EntityFileReader {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityFileReader.class);
    private static final String SHARP = "#";
    private static final String COMMA = ",";
    private static final String SEMICOLON = ";";

    public List<List<String>> readCrewFromFile(String filePath) {
        List<List<String>> crews = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            crews = reader.lines()
                    .filter(line -> !line.startsWith(SHARP))
                    .map(str -> Arrays.asList(str.split(SEMICOLON)))
                    .flatMap(list -> list.stream().map(str -> Arrays.asList(str.split(COMMA))))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crews;
    }

    public List<List<String>> readSpaceshipsFromFile(String filePath) {
        List<List<String>> spaceships = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            spaceships = reader.lines()
                    .filter(line -> !line.startsWith(SHARP))
                    .map(str -> Arrays.asList(str.split(SEMICOLON)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Cannot read file");
        }
        return spaceships;
    }

    public List<String> readSpaceMapFromFile(String filePath) {
        List<String> spacemap = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            spacemap = reader.lines()
                    .flatMap(s -> Arrays.stream(s.split(COMMA)))
                    .filter(s -> !s.equals("null"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Cannot read file");
        }
        return spacemap;
    }

}
