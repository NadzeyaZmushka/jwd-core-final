package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.reader.EntityPopulator;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    private static NassaContext instance;

    static final Logger LOGGER = LoggerFactory.getLogger(NassaContext.class);

    private NassaContext() {
    }

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<Planet> planetMap = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    ApplicationProperties applicationProperties = PropertyReaderUtil.getInstance().loadProperties();
    EntityPopulator entityPopulator = EntityPopulator.INSTANCE;
    String path = "src/main/resources/";
    String filePathToCrew = path + applicationProperties.getInputRootDir() + "/" + applicationProperties.getCrewFileName();
    String filePathToSpaceships = path + applicationProperties.getInputRootDir() + "/" + applicationProperties.getSpaceshipsFileName();
    String filePathToSpacemap = path + applicationProperties.getInputRootDir() + "/" + applicationProperties.getSpacemapFileName();

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
        crewMembers = entityPopulator.populateCrewFromReader(filePathToCrew);
        spaceships = entityPopulator.populateSpaceshipsFromReader(filePathToSpaceships);
        planetMap = entityPopulator.populateSpacemapFromReader(filePathToSpacemap);
        //        throw new InvalidStateException();
    }
}

