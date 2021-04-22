package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    private static final FlightMissionFactory INSTANCE = new FlightMissionFactory();

    public FlightMissionFactory() {
    }

    public static FlightMissionFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public FlightMission create(Object... args) {
        if (args.length == 10) {
            return new FlightMission((String) args[0], (LocalDate) args[1],
                    (LocalDate) args[2], (Long) args[3], (Spaceship) args[4], (List<CrewMember>) args[6],
                    (MissionResult) args[7], (Planet) args[8], (Planet) args[9]);
        }
        throw new IllegalArgumentException("Not enough args");
    }
}
