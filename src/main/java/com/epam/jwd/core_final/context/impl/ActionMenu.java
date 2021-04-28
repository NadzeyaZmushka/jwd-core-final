package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.factory.MissionsCreator;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpaceMapService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceMapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.util.JsonWriter;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public final class ActionMenu implements ApplicationMenu {

    private static ActionMenu instance;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionMenu.class);

    private final CrewService crewService = CrewServiceImpl.getInstance();
    private final SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private final SpaceMapService spacemapService = SpaceMapServiceImpl.getInstance();
    private final MissionsCreator missionsCreator = MissionsCreator.getInstance();
    private final String dateFormat = PropertyReaderUtil.getInstance().loadProperties().getDateTimeFormat();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
    private final JsonWriter jsonWriter = JsonWriter.INSTANCE;

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

    public void userChoseCreateMission() {
        System.out.println("Enter the mission name");
        String flightMissionName = handleUserInput();

        System.out.println("Enter flight distance:");
        Long flightDistance = Long.parseLong(handleUserInput());

        System.out.println("Enter start date (in format yyyy-mm-dd HH:mm:ss)");
        LocalDate startDate = LocalDate.parse(handleUserInput(), formatter);

        System.out.println("Enter end date (in format yyyy-mm-dd HH:mm:ss)");
        LocalDate endDate = LocalDate.parse(handleUserInput(), formatter);

        getApplicationContext().retrieveBaseEntityList(Planet.class).forEach(System.out::println);
        System.out.println("\t---------------------------\n" +
                "Enter the planet's ID, where the mission should be start (look above)\n" +
                "\t---------------------------");

        Long planeFromID = Long.parseLong(handleUserInput());
        Planet planetFrom = spacemapService.findPlanetByCriteria(new PlanetCriteria
                .CriteriaBuilder()
                .withId(planeFromID).build());

        getApplicationContext().retrieveBaseEntityList(Planet.class).forEach(System.out::println);
        System.out.println("\t---------------------------\n" +
                "Enter the planet's ID, where the mission should be end (look above)\n" +
                "\t---------------------------");

        Long planetToID = Long.parseLong(handleUserInput());
        Planet planetTo = spacemapService.findPlanetByCriteria(new PlanetCriteria
                .CriteriaBuilder()
                .withId(planetToID)
                .build());

        spaceshipService.findAllSpaceships().forEach(System.out::println);
        System.out.println("\t-----------------\n" +
                "Chose spaceship by ID (look above)\n" +
                "\t-----------------\n");

        Long id = Long.parseLong(handleUserInput());
        Optional<Spaceship> spaceshipOptional = spaceshipService.findSpaceshipByCriteria(new SpaceshipCriteria
                .CriteriaBuilder()
                .withId(id)
                .build());
        Spaceship spaceship = null;
        if (spaceshipOptional.isPresent()) {
            spaceship = spaceshipOptional.get();
        }

        long ID = 0L;
        FlightMission mission = null;
        try {
            mission = missionsCreator.createMissions(++ID, flightMissionName,
                    startDate, endDate,
                    flightDistance, Objects.requireNonNull(spaceship),
                    planetFrom, planetTo,
                    MissionResult.PLANNED);
        } catch (EntityCreationException e) {
            e.printStackTrace();
        }

        getApplicationContext().retrieveBaseEntityList(FlightMission.class).add(mission);
        jsonWriter.toJson(mission);

        LOGGER.info("\n-------Mission is created!-------\n" + Objects.requireNonNull(mission).toString());
        System.out.println("---------------\n" +
                "Do you want to continue?\n" +
                "1. Yes  2.No\n" +
                "----------------");
        if (Integer.parseInt(handleUserInput()) == 1) {
            printAvailableOptionsAfterMissionCreation();
        }
    }


    public void userChoseUpdateCrewMember() {
        System.out.println("\n-----------------\n" +
                "Chose member by ID (look above)\n" +
                "-----------------");
        Long crewId = Long.parseLong(handleUserInput());
        Optional<CrewMember> findCrewMember = crewService.findCrewMemberByCriteria(new CrewMemberCriteria.CriteriaBuilder()
                .withId(crewId)
                .build());
        findCrewMember.ifPresent(crewService::updateCrewMemberDetails);

        LOGGER.info("\n-------!Crew member is updated!-------\n" + findCrewMember.toString());
        System.out.println("---------------\n" +
                "Do you want to continue?\n" +
                "1. Yes  2.No\n" +
                "----------------");
        if (Integer.parseInt(handleUserInput()) == 1) {
            printAvailableOptions();
            NasaApplicationMenu.getInstance().takeFirstUserInput();
        }
    }

    public void userChoseUpdateSpaceship() {
        System.out.println("\n-----------------\n" +
                "Chose spaceship by ID (look above)\n" +
                "-----------------");
        Long spaceshipId = Long.parseLong(handleUserInput());
        Optional<Spaceship> foundSpaceship = spaceshipService.findSpaceshipByCriteria(new SpaceshipCriteria.CriteriaBuilder()
                .withId(spaceshipId)
                .build());
        foundSpaceship.ifPresent(spaceshipService::updateSpaceshipDetails);

        LOGGER.info("\n\t-------!Spaceship is updated!-------\n" +
                "-------Allowable distance is 1000000 now-------\n" + foundSpaceship.toString());

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
