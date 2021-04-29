package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Location;
import com.epam.jwd.core_final.domain.Planet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlanetFactoryTest {

    @Test
    public void testCreate_returnsNewPlanet() {
        Planet actual = new Planet(1L, "Mars", new Location(1, 2));
        Planet expected = PlanetFactory.getInstance().create(
                1L, "Mars", new Location(1, 2)
        );

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLocation(), actual.getLocation());
    }

}