package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlanetFactoryTest {

    @Test
    public void testCreate_returnsNewPlanet() {
        Planet actual = PlanetFactory.getInstance().create("Mars");

        assertEquals("Mars", actual.getName());
    }
}