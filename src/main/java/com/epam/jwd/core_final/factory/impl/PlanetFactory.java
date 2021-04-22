package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.EntityFactory;

public class PlanetFactory implements EntityFactory<Planet> {

    @Override
    public Planet create(Object... args) {
        if (args.length == 3) {
            return new Planet((String) args[0], (int) args[1], (int) args[2]);
        }
        throw new IllegalArgumentException("Invalid args");
    }
}
