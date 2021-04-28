package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public enum JsonWriter {
    INSTANCE;

    private final static String ENTITY_JSON = "src/main/resources/output/missions.json";

    public void toJson(BaseEntity entity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(ENTITY_JSON), entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
