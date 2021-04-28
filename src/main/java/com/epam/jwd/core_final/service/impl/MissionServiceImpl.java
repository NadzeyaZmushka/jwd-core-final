package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class MissionServiceImpl implements MissionService {

    private static MissionServiceImpl instance;

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
        List<FlightMission> flightMissions = null;
        FlightMissionCriteria missionCriteria = (FlightMissionCriteria) criteria;

        if (missionCriteria.getId() != null) {
            flightMissions = findAllMissions().stream()
                    .filter(flightMission -> flightMission.getId().equals(missionCriteria.getId()))
                    .collect(Collectors.toList());
        }
        if (missionCriteria.getMissionName() != null) {
            flightMissions = findAllMissions().stream()
                    .filter(flightMission -> flightMission.getName().equals(missionCriteria.getMissionName()))
                    .collect(Collectors.toList());
        }
        if (missionCriteria.getDistance() != null) {
            flightMissions = findAllMissions().stream()
                    .filter(flightMission -> flightMission.getDistance().equals(missionCriteria.getDistance()))
                    .collect(Collectors.toList());
        }
        if (missionCriteria.getStartDate() != null) {
            flightMissions = findAllMissions().stream()
                    .filter(flightMission -> flightMission.getStartDate().equals(missionCriteria.getStartDate()))
                    .collect(Collectors.toList());
        }
        if (missionCriteria.getEndDate() != null) {
            flightMissions = findAllMissions().stream()
                    .filter(flightMission -> flightMission.getEndDate().equals(missionCriteria.getEndDate()))
                    .collect(Collectors.toList());
        }
        if (missionCriteria.getAssignedSpaceShift() != null) {
            flightMissions = findAllMissions().stream()
                    .filter(flightMission -> flightMission.getAssignedSpaceShift().equals(missionCriteria.getAssignedSpaceShift()))
                    .collect(Collectors.toList());
        }
        if (missionCriteria.getFromPlanet() != null) {
            flightMissions = findAllMissions().stream()
                    .filter(flightMission -> flightMission.getFromPlanet().equals(missionCriteria.getFromPlanet()))
                    .collect(Collectors.toList());
        }
        if (missionCriteria.getToPlanet() != null) {
            flightMissions = findAllMissions().stream()
                    .filter(flightMission -> flightMission.getToPlanet().equals(missionCriteria.getToPlanet()))
                    .collect(Collectors.toList());
        }
        return flightMissions;
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return findAllMissionsByCriteria(criteria).stream().findFirst();
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
