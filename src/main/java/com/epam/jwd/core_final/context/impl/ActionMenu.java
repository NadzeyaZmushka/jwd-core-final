package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceMapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.JsonWriter;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ActionMenu implements ApplicationMenu {

    private static ActionMenu instance;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionMenu.class);

    private final CrewService crewService = CrewServiceImpl.getInstance();
    private final SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private final SpacemapService spacemapService = SpaceMapServiceImpl.getInstance();
    private final MissionServiceImpl missionService = MissionServiceImpl.getInstance();
    private final List<FlightMission> flightMissions = (List<FlightMission>) getApplicationContext().retrieveBaseEntityList(FlightMission.class);


    private ActionMenu() {
    }

    public static ActionMenu getInstance() {
        if (instance == null) {
            instance = new ActionMenu();
        }
        return instance;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return NasaContext.getInstance();
    }

    public void createTheMissionByUserInput() {
        String dateFormat = PropertyReaderUtil.getInstance().loadProperties().getDateTimeFormat();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        System.out.println("Enter the mission name");
        String flightMissionName = handleUserInput();

        System.out.println("Enter flight distance:");
        Long flightDistance = Long.parseLong(handleUserInput());

        System.out.println("Enter start date (in format yyyy-mm-dd HH:mm:ss)");
        LocalDate startDate = LocalDate.parse(handleUserInput(), formatter);

        System.out.println("Enter end date (in format yyyy-mm-dd HH:mm:ss)");
        LocalDate endDate = LocalDate.parse(handleUserInput(), formatter);

        long id = 1L;
        FlightMission flightMission = FlightMissionFactory.getInstance()
                .create(id++, flightMissionName, startDate, endDate, flightDistance);

        getApplicationContext().retrieveBaseEntityList(Planet.class).forEach(System.out::println);
        System.out.println("\t---------------------------\n" +
                "Enter the ID of the planet, where the mission should start\n" +
                "\t---------------------------");

        Long planeFromID = Long.parseLong(handleUserInput());
        Optional<Planet> planetFrom = spacemapService.findPlanetByCriteria(new PlanetCriteria
                .CriteriaBuilder()
                .withId(planeFromID).build());
        planetFrom.ifPresent(flightMission::setFromPlanet);

        getApplicationContext().retrieveBaseEntityList(Planet.class).forEach(System.out::println);
        System.out.println("\t---------------------------\n" +
                "Enter the ID of the planet, where the mission should end\n" +
                "\t---------------------------");

        Long planetToID = Long.parseLong(handleUserInput());
        Optional<Planet> planetTo = spacemapService.findPlanetByCriteria(new PlanetCriteria
                .CriteriaBuilder()
                .withId(planetToID)
                .build());
        planetTo.ifPresent(flightMission::setToPlanet);

        getApplicationContext().retrieveBaseEntityList(FlightMission.class).add(flightMission);

        LOGGER.info("\n-------Mission is created!-------\n" + flightMission.toString());
        System.out.println("\t-----------------\n" +
                "Do you wont to assign spaceship and crew members to mission?\n" +
                "1. Yes 2.No\n" +
                "\t-----------------");
    }

    public void assignSpaceshipAndCrewToMission() {
        FlightMission miss = flightMissions.get(flightMissions.size() - 1);

        spaceshipService.findAllSpaceships().forEach(System.out::println);
        System.out.println("\t-----------------\n" +
                "Chose spaceship by ID\n" +
                "\t-----------------\n");

        Long id = Long.parseLong(handleUserInput());
        Optional<Spaceship> chosenSpaceship = spaceshipService.findSpaceshipByCriteria(new SpaceshipCriteria
                .CriteriaBuilder()
                .withId(id)
                .build());
        chosenSpaceship.ifPresent(miss::setAssignedSpaceShift);

        crewService.findAllCrewMembers().forEach(System.out::println);
        System.out.println("\t-----------------\n" +
                "Chose crews by Role ID (enter 1 - 4)\n" +
                "\t-----------------\n");

        Long roleId = Long.parseLong(handleUserInput());

        List<CrewMember> chosenCrewMembers = crewService.findAllCrewMembersByCriteria(new CrewMemberCriteria
                .CriteriaBuilder()
                .withRole(roleId)
                .build());

        miss.setAssignedCrew(chosenCrewMembers);
        JsonWriter jsonWriter = JsonWriter.INSTANCE;
        jsonWriter.toJson(miss);

        LOGGER.info("\n-------Mission is created!-------\n" + miss.toString());
        System.out.println("---------------\n" +
                "Do you want to continue?\n" +
                "1. Yes  2.No\n" +
                "----------------");
        if (Integer.parseInt(handleUserInput()) == 1) {
            printAvailableOptionsAfterMissionCreation();
            NasaApplicationMenu.getInstance().takeFirstUserInput();
        }
    }

    public void userChoseUpdateCrewMember() {
        System.out.println("\n-----------------\n" +
                "Chose member by ID\n" +
                "-----------------");
        Long crewId = Long.parseLong(handleUserInput());
        Optional<CrewMember> findCrewMember = crewService.findCrewMemberByCriteria(new CrewMemberCriteria.CriteriaBuilder()
                .withId(crewId)
                .build());
        findCrewMember.ifPresent(crewService::updateCrewMemberDetails);
        LOGGER.info("\n-------!Crew member was updated!-------\n" + findCrewMember.toString());
        System.out.println("---------------\n" +
                "Do you want to continue?\n" +
                "1. Yes  2.No\n" +
                "----------------");
        if (Integer.parseInt(handleUserInput()) == 1) {
            printAvailableOptions();
            NasaApplicationMenu.getInstance().takeFirstUserInput();
        }
    }

}
