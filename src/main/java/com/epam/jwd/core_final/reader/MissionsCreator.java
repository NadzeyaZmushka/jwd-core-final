package com.epam.jwd.core_final.reader;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;

import java.util.List;

public final class MissionsCreator {

    private static MissionsCreator instance;

    ApplicationContext context = NasaContext.getInstance();

    private final EntityFactory<FlightMission> factory = FlightMissionFactory.getInstance();
    private final List<CrewMember> members = (List<CrewMember>) context.retrieveBaseEntityList(CrewMember.class);
    private final List<Spaceship> spaceships = (List<Spaceship>) context.retrieveBaseEntityList(Spaceship.class);
    private final List<Planet> planets = (List<Planet>) context.retrieveBaseEntityList(Planet.class);

    private MissionsCreator() {
    }

    public static MissionsCreator getInstance() {
        if (instance == null) {
            instance = new MissionsCreator();
        }
        return instance;
    }

//    public FlightMission createMissions(Long id, List<CrewMember> members,
//                                        List<Spaceship> ships,
//                                        List<Planet> planets,
//                                        MissionResult result
//                                        ) throws InvalidStateException {
////        List<FlightMission> missions = new ArrayList<>();
//
//        Random random = new Random();
//        FlightMission mission = factory.create(id, LocalDate.now(), LocalDate.now().plusMonths(1), random.longs());
//        mission.setMissionResult(result);
//        mission.setAssignedCrew(members);
//        mission.setAssignedSpaceShift(ships.get(random.nextInt(members.size())));
//        return mission;
//    }

}
