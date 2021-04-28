package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class FlightMissionFactoryTest {

    @Test
    public void testCreate_returnsNewFlightMission() {
        FlightMission actual = new FlightMission(1L, "Name",
                LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 2),
                1234L);

        assertEquals((Long) 1L, actual.getId());
        assertEquals("Name", actual.getName());
        assertEquals(LocalDate.of(2021, 1, 1), actual.getStartDate());
        assertEquals(LocalDate.of(2021, 1, 2), actual.getEndDate());
        assertEquals((Long) 1234L, actual.getDistance());
    }
}