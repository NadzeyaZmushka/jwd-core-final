package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.List;

public class SpacemapServiseImpl implements SpacemapService {

    private static SpacemapServiseImpl instance;
    private final List<Planet> planetMap;

    private SpacemapServiseImpl(ApplicationContext context) {
        this.planetMap = (List<Planet>) context.retrieveBaseEntityList(Planet.class);
    }

    public static SpacemapServiseImpl getInstance(ApplicationContext context) {
        if (instance == null) {
            instance = new SpacemapServiseImpl(context);
        }
        return instance;
    }

    @Override
    public Planet getRandomPlanet() {
        int randomIndex = (int) (Math.random() * planetMap.size() - 1);
        return planetMap.get(randomIndex);
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return (int) (Math.sqrt(Math.pow((second.getX() - first.getX()), 2) +
                Math.pow((second.getY() - first.getY()), 2)));
    }
}
