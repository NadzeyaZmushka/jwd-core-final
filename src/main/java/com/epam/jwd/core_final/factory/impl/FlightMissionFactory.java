package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;

public final class FlightMissionFactory implements EntityFactory<FlightMission> {

    private static final FlightMissionFactory INSTANCE = new FlightMissionFactory();

    public FlightMissionFactory() {
    }

    public static FlightMissionFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public FlightMission create(Object... args) {
        return new FlightMission((Long) args[0],
                (String) args[1],
                (LocalDate) args[2],
                (LocalDate) args[3],
                (Long) args[4]);
    }

}
