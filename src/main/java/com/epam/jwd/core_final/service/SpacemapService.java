package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.Planet;

import java.util.List;
import java.util.Optional;

public interface SpacemapService {

    List<Planet> findAllPlanets();

    Planet getRandomPlanet();

    // Dijkstra ?
    int getDistanceBetweenPlanets(Planet first, Planet second);

    List<Planet> findAllPlanetsByCriteria(Criteria<? extends Planet> criteria);

    Optional<Planet> findPlanetByCriteria(Criteria<? extends Planet> criteria);
}
