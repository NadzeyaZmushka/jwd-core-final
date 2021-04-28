package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.reader.EntityPopulate;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.ArrayList;
import java.util.Collection;

// todo
public final class NasaContext implements ApplicationContext {

    private static NasaContext instance;

    private NasaContext() {
    }

    public static NasaContext getInstance() {
        if (instance == null) {
            instance = new NasaContext();
        }
        return instance;
    }

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    ApplicationProperties applicationProperties = PropertyReaderUtil.getInstance().loadProperties();
    EntityPopulate entityPopulate = EntityPopulate.INSTANCE;
    private final String PATH = "src/main/resources/";
    private final String FILE_PATH_TO_CREW = PATH + applicationProperties.getInputRootDir() + "/" + applicationProperties.getCrewFileName();
    String FILE_PATH_TO_SPACESHIPS = PATH + applicationProperties.getInputRootDir() + "/" + applicationProperties.getSpaceshipsFileName();
    String FILE_PATH_TO_SPACE_MAP = PATH + applicationProperties.getInputRootDir() + "/" + applicationProperties.getSpacemapFileName();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        Collection<T> collection = null;
        if (tClass.equals(CrewMember.class)) {
            collection = (Collection<T>) crewMembers;
        }
        if (tClass.equals(Spaceship.class)) {
            collection = (Collection<T>) spaceships;
        }
        if (tClass.equals(Planet.class)) {
            collection = (Collection<T>) planetMap;
        }
        if (tClass.equals(FlightMission.class)) {
            collection = (Collection<T>) flightMissions;
        }
        return collection;
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        crewMembers = entityPopulate.populateCrewFromReader(FILE_PATH_TO_CREW);
        spaceships = entityPopulate.populateSpaceshipsFromReader(FILE_PATH_TO_SPACESHIPS);
        planetMap = entityPopulate.populateSpaceMapFromReader(FILE_PATH_TO_SPACE_MAP);
        //        throw new InvalidStateException();
    }
}

