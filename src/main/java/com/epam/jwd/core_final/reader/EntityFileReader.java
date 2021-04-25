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

    public List<List<String>> readCrew(String filePath) {
        List<List<String>> crews = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            crews = reader.lines()
                    .filter(line -> !line.startsWith("#"))
                    .map(str -> Arrays.asList(str.split(";")))
                    .flatMap(list -> list.stream().map(str -> Arrays.asList(str.split(","))))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crews;
    }

    public List<List<String>> readSpaceships(String filePath) {
        List<List<String>> spaceships = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            spaceships = reader.lines()
                    .filter(line -> !line.startsWith("#"))
                    .map(str -> Arrays.asList(str.split(";")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Cannot read file");
        }
        return spaceships;
    }

    public List<String> readSpacemap(String filePath) {
        List<String> spacemap = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            spacemap = reader.lines()
                    .flatMap(s -> Arrays.stream(s.split(",")))
                    .filter(s -> !s.equals("null"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Cannot read file");
        }
        return spacemap;
    }
}
