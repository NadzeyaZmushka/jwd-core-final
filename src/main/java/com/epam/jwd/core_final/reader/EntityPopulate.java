package com.epam.jwd.core_final.reader;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum EntityPopulate {
    INSTANCE;

    EntityFileReader reader = EntityFileReader.INSTANCE;
    private static final String COMMA = ",";
    private static final String COLON = ":";
    private Long id = 1L;

    public Collection<CrewMember> populateCrewFromReader(String filePath) throws EntityCreationException {
        EntityFactory<CrewMember> crewMemberFactory = CrewMemberFactory.getInstance();
        List<CrewMember> crewMembers = new ArrayList<>();
        List<List<String>> list = reader.readCrewFromFile(filePath);

        for (List<String> lines : list) {
            crewMembers.add(crewMemberFactory.create(id++,
                    lines.get(1),
                    Role.resolveRoleById(Long.parseLong(lines.get(0))),
                    Rank.resolveRankById(Long.parseLong(lines.get(2))))
            );
        }
        return crewMembers;
    }

    public Collection<Spaceship> populateSpaceshipsFromReader(String filePath) throws EntityCreationException {
        EntityFactory<Spaceship> spaceshipFactory = SpaceshipFactory.getInstance();
        List<Spaceship> spaceships = new ArrayList<>();
        List<List<String>> list = reader.readSpaceshipsFromFile(filePath);

        for (List<String> lines : list) {
            spaceships.add(spaceshipFactory.create(id++, lines.get(0),
                    mapFromStringToMap(lines.get(2)),
                    Long.parseLong(lines.get(1)))
            );
        }
        return spaceships;
    }

    public Collection<Planet> populateSpaceMapFromReader(String filePath) throws EntityCreationException {
        EntityFactory<Planet> planetFactory = PlanetFactory.getInstance();
        List<Planet> planets = new ArrayList<>();
        List<String> list = reader.readSpaceMapFromFile(filePath);

        for (String lines : list) {
            planets.add(planetFactory.create(id++, lines));
        }
        return planets;
    }

    private Map<Role, Short> mapFromStringToMap(String string) {
        Map<Role, Short> crew = new HashMap<>();
        string = string.substring(1, string.length() - 1);
        String[] crewRolesShorts = string.split(COMMA);

        for (String crewRoleShort : crewRolesShorts) {
            String[] roleAndShort = crewRoleShort.split(COLON);
            crew.put(Role.resolveRoleById(Long.parseLong(roleAndShort[0])), Short.parseShort(roleAndShort[1]));
        }
        return crew;
    }

}
