package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlanetFactoryTest {

    @Test
    public void testCreate_returnsNewPlanet() {
        Planet actual = new Planet(1L, "Mars");

        assertEquals((Long) 1L, actual.getId());
        assertEquals("Mars", actual.getName());
    }
}