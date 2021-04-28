package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public enum JsonWriter {
    INSTANCE;

    private final ApplicationProperties applicationProperties = PropertyReaderUtil.getInstance().loadProperties();
    private final String PATH = "src/main/resources/";
    private final String FILE_PATH_TO_ENTITY_JSON = PATH + applicationProperties.getOutputRootDir() +
            "/" + applicationProperties.getMissionsFileName() + ".json";

    public void toJson(BaseEntity entity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(FILE_PATH_TO_ENTITY_JSON), entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
