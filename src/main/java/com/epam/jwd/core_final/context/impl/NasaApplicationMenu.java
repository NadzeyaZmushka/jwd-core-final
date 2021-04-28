package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpacemapService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceMapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class NasaApplicationMenu implements ApplicationMenu {

    private static NasaApplicationMenu instance;

    private static final Logger LOGGER = LoggerFactory.getLogger(NasaApplicationMenu.class);

    private final ActionMenu actionMenu = ActionMenu.getInstance();

    private final CrewService crewService = CrewServiceImpl.getInstance();
    private final SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private final SpacemapService spacemapService = SpaceMapServiceImpl.getInstance();
    private final MissionServiceImpl missionService = MissionServiceImpl.getInstance();
    private final List<FlightMission> flightMissions = (List<FlightMission>) getApplicationContext().retrieveBaseEntityList(FlightMission.class);

    private NasaApplicationMenu() {
    }

    public static NasaApplicationMenu getInstance() {
        if (instance == null) {
            instance = new NasaApplicationMenu();
        }
        return instance;
    }

    public void takeFirstUserInput() {
        String input = handleUserInput();
        switch (input) {
            case "1":
                crewService.findAllCrewMembers().forEach(System.out::println);
                availableOptionsAfterCrew();
                if (Integer.parseInt(input) == 1) {
                    actionMenu.userChoseUpdateCrewMember();
                    break;
                }
                if (Integer.parseInt(input) == 6) {
                    takeFirstUserInput();
                    break;
                }
                break;
            case "2":
                spaceshipService.findAllSpaceships().forEach(System.out::println);
                printAvailableOptionsAfterSpaceships();
                break;
            case "3":
                spacemapService.findAllPlanets().forEach(System.out::println);
                printAvailableOptionsAfterPlanets();
                break;
            case "4":
                actionMenu.createTheMissionByUserInput();
                if (Integer.parseInt(handleUserInput()) == 1) {
                    actionMenu.assignSpaceshipAndCrewToMission();
                    break;
                } else {
                    printAvailableOptions();
                    takeFirstUserInput();
                }
                break;
            case "5":
                System.out.println("Good bye!");
                break;
            default:
                LOGGER.error("\n---!!!Incorrect data entered, please, try again (enter 1 - 5)!!!---");
                printAvailableOptions();
                takeFirstUserInput();
        }
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return NasaContext.getInstance();
    }

}
