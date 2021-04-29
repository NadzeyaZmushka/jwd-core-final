package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SpaceshipFactoryTest {

    @Test
    public void testCreate_returnsNewSpaceship() {
        Spaceship actual = new Spaceship(1L, "Spaceship",
                new HashMap<>() {{
                    put(Role.COMMANDER, (short) 4);
                }},
                4321L);
        Spaceship expected = SpaceshipFactory.getInstance().create(
                1L, "Spaceship", new HashMap<>() {{
                    put(Role.COMMANDER, (short) 4);
                }}, 4321L);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCrew(), actual.getCrew());
        assertEquals(expected.getFlightDistance(), actual.getFlightDistance());
    }

}