package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {
    //todo
    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionFileName;
    private final String spaceShipFileName;
    private final Integer fileRefreshRate;
    private final String dateTimeFormat;

    public ApplicationProperties() {
        Properties properties = PropertyReaderUtil.getProperties();
        this.inputRootDir = properties.getProperty("inputRootDir");
        this.outputRootDir = properties.getProperty("outputRootDir");
        this.crewFileName = properties.getProperty("crewFileName");
        this.missionFileName = properties.getProperty("missionFileName");
        this.spaceShipFileName = properties.getProperty("spaceShipFileName");
        this.fileRefreshRate = Integer.getInteger(properties.getProperty("fileRefreshRate"));
        this.dateTimeFormat = properties.getProperty("dateTimeFormat");
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionFileName() {
        return missionFileName;
    }

    public String getSpaceShipFileName() {
        return spaceShipFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
}
