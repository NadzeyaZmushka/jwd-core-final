package com.epam.jwd.core_final.domain;

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
public final class ApplicationProperties {

    private static ApplicationProperties instance;

    private final String inputRootDir;
    private final String outputRootDir;
    private final String crewFileName;
    private final String missionsFileName;
    private final String spaceshipsFileName;
    private final String spacemapFileName;
    private final Integer fileRefreshRate;
    private final String dateTimeFormat;


    private ApplicationProperties(String inputRootDir, String outputRootDir, String crewFileName,
                                  String missionsFileName, String spaceshipsFileName, String spacemapFileName,
                                  Integer fileRefreshRate, String dateTimeFormat) {
        this.inputRootDir = inputRootDir;
        this.outputRootDir = outputRootDir;
        this.crewFileName = crewFileName;
        this.missionsFileName = missionsFileName;
        this.spaceshipsFileName = spaceshipsFileName;
        this.spacemapFileName = spacemapFileName;
        this.fileRefreshRate = fileRefreshRate;
        this.dateTimeFormat = dateTimeFormat;
    }

    public static ApplicationProperties getInstance(String inputRootDir, String outputRootDir, String crewFileName,
                                                    String missionsFileName, String spaceshipsFileName,
                                                    String spacemapFileName, Integer fileRefreshRate,
                                                    String dateTimeFormat) {
        if (instance == null) {
            instance = new ApplicationProperties(inputRootDir, outputRootDir, crewFileName,
                    missionsFileName, spaceshipsFileName, spacemapFileName, fileRefreshRate,
                    dateTimeFormat);
        }
        return instance;
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

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public String getSpacemapFileName() {
        return spacemapFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

}
