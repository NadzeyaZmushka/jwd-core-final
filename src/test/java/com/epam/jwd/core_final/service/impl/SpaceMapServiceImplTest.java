package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.Planet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpaceMapServiceImplTest {

    @Test
    public void testGetDistanceBetweenPlanets() {
        Planet first = new Planet(1, 2);
        Planet second = new Planet(3, 4);

        int expected = SpaceMapServiceImpl.getInstance().getDistanceBetweenPlanets(first, second);

        assertEquals(expected, 2);
    }
}