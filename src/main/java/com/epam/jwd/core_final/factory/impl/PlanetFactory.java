package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlanetFactory implements EntityFactory<Planet> {

    private static final PlanetFactory INSTANCE = new PlanetFactory();

    static final Logger LOGGER = LoggerFactory.getLogger(CrewMemberFactory.class);

    public static PlanetFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public Planet create(Object... args) {
//        LOGGER.info("Planet...");
        return new Planet((String) args[0]);
    }
}
