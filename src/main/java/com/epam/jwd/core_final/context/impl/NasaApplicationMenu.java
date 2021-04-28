package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.SpaceMapService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceMapServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NasaApplicationMenu implements ApplicationMenu {

    private static NasaApplicationMenu instance;

    private static final Logger LOGGER = LoggerFactory.getLogger(NasaApplicationMenu.class);

    private final ActionMenu actionMenu = ActionMenu.getInstance();

    private final CrewService crewService = CrewServiceImpl.getInstance();
    private final SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private final SpaceMapService spacemapService = SpaceMapServiceImpl.getInstance();
    private final MissionServiceImpl missionService = MissionServiceImpl.getInstance();

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
                printAvailableOptionsAfterCrew();
                String s = handleUserInput();
                if (Integer.parseInt(s) == 1) {
                    actionMenu.userChoseUpdateCrewMember();
                    break;
                } else if (Integer.parseInt(s) == 6) {
                    printAvailableOptions();
                    takeFirstUserInput();
                    break;
                }
                break;
            case "2":
                spaceshipService.findAllSpaceships().forEach(System.out::println);
                printAvailableOptionsAfterSpaceships();
                String s1 = handleUserInput();
                if (Integer.parseInt(s1) == 2) {
                    actionMenu.userChoseUpdateSpaceship();
                } else {
                    takeFirstUserInput();
                }
                break;
            case "3":
                spacemapService.findAllPlanets().forEach(System.out::println);
                printAvailableOptionsAfterPlanets();
                break;
            case "4":
                actionMenu.userChoseCreateMission();
                String s2 = handleUserInput();
                if (Integer.parseInt(s2) == 4) {
                    missionService.findAllMissions().forEach(System.out::println);
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
