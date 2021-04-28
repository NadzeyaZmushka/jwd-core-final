package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlanetFactoryTest {

    @Test
    public void testCreate_returnsNewPlanet() {
        Planet actual = new Planet(1L, "Mars");
        Planet expected = PlanetFactory.getInstance().create(
                1L, "Mars"
        );

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}