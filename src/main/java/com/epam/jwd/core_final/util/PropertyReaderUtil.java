package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReaderUtil.class);

    private static final Properties properties = new Properties();
    private static final PropertyReaderUtil instance = new PropertyReaderUtil();

    private PropertyReaderUtil() {
    }

    public static PropertyReaderUtil getInstance() {
        return instance;
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public ApplicationProperties loadProperties() {
        final String propertiesFileName = "application.properties";

        try (InputStream stream= this.getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            properties.load(stream);
        } catch (IOException e) {
            LOGGER.error("Error reading file");
        }
        return ApplicationProperties.getInstance(
                properties.getProperty("inputRootDir"),
                properties.getProperty("outputRootDir"),
                properties.getProperty("crewFileName"),
                properties.getProperty("missionsFileName"),
                properties.getProperty("spaceshipsFileName"),
                properties.getProperty("spacemapFileName"),
                Integer.parseInt(properties.getProperty("fileRefreshRate")),
                properties.getProperty("dateTimeFormat")
        );
    }


}
