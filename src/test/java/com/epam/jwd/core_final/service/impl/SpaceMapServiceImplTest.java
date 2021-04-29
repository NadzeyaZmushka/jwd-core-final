package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.Location;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpaceMapService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpaceMapServiceImplTest {

    ApplicationContext context = NasaContext.getInstance();
    SpaceMapService service = SpaceMapServiceImpl.getInstance();
    List<Planet> planets = (List<Planet>) context.retrieveBaseEntityList(Planet.class);

    @Before
    public void setUp() {
        planets.add(new Planet(1L, "One", new Location(1, 2)));
        planets.add(new Planet(1L, "Two", new Location(3, 4)));
        planets.add(new Planet(1L, "Three", new Location(1, 4)));
        planets.add(new Planet(1L, "Four", new Location(3, 2)));
    }

    @Test
    public void testFindAllPlanets() {
        List<Planet> actual = planets;
        List<Planet> expected = service.findAllPlanets();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetDistanceBetweenPlanets() {
        Planet first = new Planet(1L, "One", new Location(1, 5));
        Planet second = new Planet(1L, "Two", new Location(3, 4));

        int expected = SpaceMapServiceImpl.getInstance().getDistanceBetweenPlanets(first, second);

        assertEquals(expected, 2);
    }

    @Test
    public void testFindAllPlanetsByCriteria_withId() {
        Criteria<Planet> criteria = new PlanetCriteria.CriteriaBuilder()
                .withId(1L)
                .build();

        List<Planet> expected = service.findAllPlanetsByCriteria(criteria);

        assertEquals(expected, planets);
    }

    @Test
    public void testFindPlanetByCriteria_withName() {
        Criteria<Planet> criteria = new PlanetCriteria.CriteriaBuilder()
                .withName("One")
                .build();

        Planet actual = new Planet(1L, "One", new Location(1, 2));
        Planet expected = service.findPlanetByCriteria(criteria);

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        planets.clear();
    }

}