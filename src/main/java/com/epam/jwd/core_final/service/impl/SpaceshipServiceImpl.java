package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.service.SpaceshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {

    private static SpaceshipServiceImpl instance;

    static final Logger LOGGER = LoggerFactory.getLogger(SpacemapServiseImpl.class);

    private final List<Spaceship> allSpaceships;

    private SpaceshipServiceImpl(ApplicationContext context) {
        this.allSpaceships = (List<Spaceship>) context.retrieveBaseEntityList(Spaceship.class);
    }

    public static SpaceshipServiceImpl getInstance(ApplicationContext context) {
        if (instance == null) {
            instance = new SpaceshipServiceImpl(context);
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return allSpaceships;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = new ArrayList<>();
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;
        if (spaceshipCriteria.getCrew() != null) {
            spaceships = allSpaceships.stream()
                    .filter(spaceship -> spaceship.getCrew().equals(spaceshipCriteria.getCrew()))
                    .collect(Collectors.toList());
        }
        if (spaceshipCriteria.getFlightDistance() != null) {
            spaceships = allSpaceships.stream()
                    .filter(spaceship -> spaceship.getFlightDistance().equals(spaceshipCriteria.getFlightDistance()))
                    .collect(Collectors.toList());
        }
        return spaceships;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return findSpaceshipByCriteria(criteria).stream().findAny();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        spaceship.setReadyForNextMission(false);
        return spaceship;
    }

    // todo create custom exception for case, when spaceship is not able to be assigned
    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {

    }

    // todo create custom exception for case, when crewMember is not able to be created (for example - duplicate.
    // spaceship unique criteria - only name!
    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        // ...
        try {
            if (allSpaceships.contains(spaceship)) {
                throw new EntityCreationException("Spaceship already exist");
            } else {
                allSpaceships.add(spaceship);
            }
        } catch (EntityCreationException e) {
            LOGGER.error(e.getMessage());
        }
        return spaceship;
    }
}
