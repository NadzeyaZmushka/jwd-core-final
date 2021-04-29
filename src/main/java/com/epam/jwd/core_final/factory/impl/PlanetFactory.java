package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Location;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.EntityFactory;

public final class PlanetFactory implements EntityFactory<Planet> {

    private static final PlanetFactory INSTANCE = new PlanetFactory();

    public static PlanetFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public Planet create(Object... args) {
        return new Planet((Long) args[0],
                (String) args[1],
                (Location) args[2]);
    }

}
