package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class MissionServiceImpl implements MissionService {

    private static MissionServiceImpl instance;

    private List<FlightMission> flightMissions;
    private static final ApplicationContext context = NasaContext.getInstance();

    private MissionServiceImpl() {
    }

    public static MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl();
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return new ArrayList<>(context.retrieveBaseEntityList(FlightMission.class));
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
        if (!(findAllMissions().contains(flightMission))) {
            findAllMissions().add(flightMission);
        }
        return flightMission;
    }

}
