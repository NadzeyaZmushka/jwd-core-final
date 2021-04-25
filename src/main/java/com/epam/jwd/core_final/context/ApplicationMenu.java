package com.epam.jwd.core_final.context;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default void printAvailableOptions() {
        System.out.println("Welcome! Choose an action: \n" +
                "1. Info about crewmembers\n" +
                "2. Info about spaceships\n");
    }

    default String handleUserInput(String o) {
        return null;
    }
}
