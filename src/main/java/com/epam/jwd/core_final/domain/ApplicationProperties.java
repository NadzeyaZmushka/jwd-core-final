package com.epam.jwd.core_final.domain;

import java.time.format.DateTimeFormatter;

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
    String inputRootDir;
    String outputRootDir;
    String crewFileName;
    String missionFileName;
    String spaceShipFileName;
    Integer fileRefreshRate;
    String dateTimeFormat;
    //todo
}
