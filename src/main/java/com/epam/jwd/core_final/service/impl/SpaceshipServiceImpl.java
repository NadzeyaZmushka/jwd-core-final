package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
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

public final class SpaceshipServiceImpl implements SpaceshipService {

    private static SpaceshipServiceImpl instance;

    static final Logger LOGGER = LoggerFactory.getLogger(SpaceMapServiceImpl.class);
    private final ApplicationContext context = NasaContext.getInstance();

    private SpaceshipServiceImpl() {
    }

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return new ArrayList<>(context.retrieveBaseEntityList(Spaceship.class));
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = null;
        SpaceshipCriteria spaceshipCriteria = (SpaceshipCriteria) criteria;

        if (spaceshipCriteria.getId() != null) {
            spaceships = findAllSpaceships().stream()
                    .filter(spaceship -> spaceship.getId().equals(spaceshipCriteria.getId()))
                    .collect(Collectors.toList());
        }
        if (spaceshipCriteria.getCrew() != null) {
            spaceships = findAllSpaceships().stream()
                    .filter(spaceship -> spaceship.getCrew().equals(spaceshipCriteria.getCrew()))
                    .collect(Collectors.toList());
        }
        if (spaceshipCriteria.getFlightDistance() != null) {
            spaceships = findAllSpaceships().stream()
                    .filter(spaceship -> spaceship.getFlightDistance().equals(spaceshipCriteria.getFlightDistance()))
                    .collect(Collectors.toList());
        }
        return spaceships;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return findAllSpaceshipsByCriteria(criteria).stream().findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        spaceship.setFlightDistance(1000000L);
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
            if (findAllSpaceships().contains(spaceship)) {
                throw new EntityCreationException("Spaceship already exist");
            } else {
                findAllSpaceships().add(spaceship);
            }
        } catch (EntityCreationException e) {
            LOGGER.error(e.getMessage());
        }
        return spaceship;
    }
}
