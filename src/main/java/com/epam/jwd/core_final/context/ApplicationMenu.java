package com.epam.jwd.core_final.context;

import java.util.Scanner;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default void printAvailableOptions() {
        System.out.println("-----------------\n" +
                "Welcome! \n" +
                "-----------------\n" +
                "Choose an action: \n" +
                "1. Info about crew members\n" +
                "2. Info about spaceships\n" +
                "3. Info about planets \n" +
                "4. Create mission\n" +
                "5. Exit");
    }

    default void printAvailableOptionsAfterCrew() {
        System.out.println("----------------\n" +
                "Choose an action:\n" +
                "1. Update crew member\n" +
                "2. Info about spaceships\n" +
                "3. Info about planets \n" +
                "4. Create mission\n" +
                "5. Exit\n" +
                "6. Return to main menu");
    }

    default void printAvailableOptionsAfterSpaceships() {
        System.out.println("----------------\n" +
                "Choose an action:\n" +
                "1. Info about crew members\n" +
                "2. Update spaceship\n" +
                "3. Info about planets \n" +
                "4. Create mission\n" +
                "5. Exit\n" +
                "6. Return to main menu\n");
    }

    default void printAvailableOptionsAfterPlanets() {
        System.out.println("----------------\n" +
                "Choose an action:\n" +
                "1. Info about crew members\n" +
                "2. Info about spaceships \n" +
                "3. Create mission\n" +
                "4. Return to main menu\n" +
                "5. Exit");
    }

    default void printAvailableOptionsAfterMissionCreation() {
        System.out.println("----------------\n" +
                "Choose an action:\n" +
                "1. Info about crew members\n" +
                "2. Info about spaceships \n" +
                "3. Info about planets\n" +
                "4. Info about missions\n" +
                "5. Return to main menu\n" +
                "6. Exit");
    }

    default String handleUserInput() {
        Scanner scannerUserInput = new Scanner(System.in);
        return scannerUserInput.nextLine();
    }

}
