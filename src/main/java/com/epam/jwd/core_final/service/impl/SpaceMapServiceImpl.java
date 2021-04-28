package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpaceMapService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SpaceMapServiceImpl implements SpaceMapService {

    private static SpaceMapServiceImpl instance;

    private SpaceMapServiceImpl() {
    }

    public static SpaceMapServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceMapServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Planet> findAllPlanets() {
        return new ArrayList<>(NasaContext.getInstance().retrieveBaseEntityList(Planet.class));
    }

    @Override
    public Planet getRandomPlanet() {
        int randomIndex = (int) (Math.random() * findAllPlanets().size() - 1);
        return findAllPlanets().get(randomIndex);
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return (int) (Math.sqrt(Math.pow((second.getX() - first.getX()), 2) +
                Math.pow((second.getY() - first.getY()), 2)));
    }

    public List<Planet> findAllPlanetsByCriteria(Criteria<? extends Planet> criteria) {
        List<Planet> planets = null;
        PlanetCriteria planetCriteria = (PlanetCriteria) criteria;

        if (planetCriteria.getId() != null) {
            planets = findAllPlanets().stream()
                    .filter(planet -> planet.getId().equals(planetCriteria.getId()))
                    .collect(Collectors.toList());
        }
        if (planetCriteria.getName() != null) {
            planets = findAllPlanets().stream()
                    .filter(planet -> planet.getName().equals(planetCriteria.getName()))
                    .collect(Collectors.toList());
        }
        return planets;
    }

    public Planet findPlanetByCriteria(Criteria<? extends Planet> criteria) {
        Planet planet = null;
        Optional<Planet> planetOptional = findAllPlanetsByCriteria(criteria).stream().findFirst();
        if (planetOptional.isPresent()) {
            planet = planetOptional.get();
        }
        return planet;
    }

}
