package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SpaceshipFactoryTest {

    @Test
    public void testCreate_returnsNewSpaceship() {
        Spaceship actual = SpaceshipFactory.getInstance().create(1L, "Spaceship",
                new HashMap<>() {{
                    put(Role.COMMANDER, 4);
                }},
                4321L);

        assertEquals(1L, (long) actual.getId());
        assertEquals("Spaceship", actual.getName());
        assertEquals(new HashMap<>() {{
            put(Role.COMMANDER, 4);
        }}, actual.getCrew());
        assertEquals(4321L, (long) actual.getFlightDistance());
    }
}