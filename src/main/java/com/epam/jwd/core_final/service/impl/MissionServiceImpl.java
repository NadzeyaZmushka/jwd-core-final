package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.MissionService;

import java.util.List;
import java.util.Optional;

public class MissionServiceImpl implements MissionService {

    private static MissionServiceImpl instance;
    private List<FlightMission> flightMissions;

    private MissionServiceImpl(ApplicationContext context) {
        this.flightMissions = (List<FlightMission>) context.retrieveBaseEntityList(FlightMission.class);
    }

    public static MissionServiceImpl getInstance(ApplicationContext context) {
        if (instance == null) {
            instance = new MissionServiceImpl(context);
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return flightMissions;
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return null;
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return Optional.empty();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission, MissionResult result) {
        flightMission.setMissionResult(result);
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        // ...
        flightMissions.add(flightMission);
        return flightMission;
    }
}
