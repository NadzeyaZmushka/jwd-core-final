package com.epam.jwd.core_final.factory;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MissionsCreator {

    private static MissionsCreator instance;

    private final EntityFactory<FlightMission> factory = FlightMissionFactory.getInstance();
    private final CrewService crewService = CrewServiceImpl.getInstance();

    private MissionsCreator() {
    }

    public static MissionsCreator getInstance() {
        if (instance == null) {
            instance = new MissionsCreator();
        }
        return instance;
    }

    public FlightMission createMissions(Long id, String name, LocalDate start, LocalDate end,
                                        Long flightDistance,
                                        Spaceship spaceship,
                                        Planet planetFrom, Planet planetTo,
                                        MissionResult result
    ) throws EntityCreationException {
        Map<Role, Short> crews = spaceship.getCrew();
        Set<Role> roles = crews.keySet();
        List<CrewMember> crewMembers = new ArrayList<>();
        for (Role role : roles) {
            for (int i = 0; i < crews.get(role); i++) {
                crewMembers = crewService.findAllCrewMembersByCriteria(new CrewMemberCriteria.CriteriaBuilder()
                        .withRole(role.getId()).build());
            }
        }
        FlightMission mission = factory.create(id, name, start, end, flightDistance);

        mission.setFromPlanet(planetFrom);
        mission.setToPlanet(planetTo);
        mission.setAssignedCrew(crewMembers);
        mission.setAssignedSpaceShift(spaceship);
        mission.setMissionResult(result);

        return mission;
    }

}
