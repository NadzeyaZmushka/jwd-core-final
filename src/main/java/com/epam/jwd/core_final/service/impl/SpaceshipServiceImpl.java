package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SpaceshipServiceImpl implements SpaceshipService {

    private static SpaceshipServiceImpl instance;

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
        if (spaceshipCriteria.getReadyForNextMissions() != null) {
            spaceships = findAllSpaceships().stream()
                    .filter(spaceship -> spaceship.getReadyForNextMission().equals(spaceshipCriteria.getReadyForNextMissions()))
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
        if (spaceship == null) {
            throw new UnknownEntityException("Spaceship is not exist");
        }
        spaceship.setFlightDistance(1000000L);
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(FlightMission flightMission, Spaceship spaceship) throws InvalidStateException {
        if (flightMission == null || spaceship == null || !spaceship.getReadyForNextMission()) {
            throw new InvalidStateException("It is not possible to assign spaceship");
        }
        flightMission.setAssignedSpaceShift(spaceship);
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws EntityCreationException {
        Optional<Spaceship> duplicateId = findAllSpaceships().stream()
                .filter(s -> s.getId().equals(spaceship.getId()))
                .findAny();
        if (duplicateId.isPresent()) {
            throw new EntityCreationException("Such spaceship already exist");
        }
        Spaceship ship = SpaceshipFactory.getInstance()
                .create(spaceship.getId(), spaceship.getName(),
                        spaceship.getCrew(), spaceship.getFlightDistance());
        context.retrieveBaseEntityList(Spaceship.class).add(ship);
        return ship;
    }

}
