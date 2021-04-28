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
        FlightMission expected = FlightMissionFactory.getInstance().create(
                1L, "Name", LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2), 1234L
        );

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
        assertEquals(expected.getDistance(), actual.getDistance());
    }
}