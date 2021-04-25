package com.epam.jwd.core_final.reader;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum EntityPopulator {
    INSTANCE;

    EntityFileReader reader = EntityFileReader.INSTANCE;


    public Collection<CrewMember> populateCrewFromReader(String filePath) throws InvalidStateException {
        EntityFactory<CrewMember> crewMemberFactory = CrewMemberFactory.getInstance();
        List<CrewMember> crewMembers = new ArrayList<>();
        List<List<String>> list = reader.readCrew(filePath);

        for (List<String> lines : list) {
            crewMembers.add(crewMemberFactory.create(
                    lines.get(1),
                    Role.resolveRoleById(Long.parseLong(lines.get(0))),
                    Rank.resolveRankById(Long.parseLong(lines.get(2))))
            );
        }
        return crewMembers;
    }

    public Collection<Spaceship> populateSpaceshipsFromReader(String filePath) throws InvalidStateException {
        EntityFactory<Spaceship> spaceshipFactory = SpaceshipFactory.getInstance();
        List<Spaceship> spaceships = new ArrayList<>();
        List<List<String>> list = reader.readSpaceships(filePath);

        for (List<String> lines : list) {
            spaceships.add(spaceshipFactory.create(lines.get(0),
                    mapFromStringToMap(lines.get(2)),
                    Long.parseLong(lines.get(1)))
            );
        }
        return spaceships;
    }

    public Collection<Planet> populateSpacemapFromReader(String filePath) throws InvalidStateException {
        EntityFactory<Planet> planetFactory = PlanetFactory.getInstance();
        List<Planet> planets = new ArrayList<>();
        List<String> list = reader.readSpacemap(filePath);

        for (String lines : list) {
            planets.add(planetFactory.create(lines));
        }
        return planets;
    }

    private Map<Role, Short> mapFromStringToMap(String string) {
        Map<Role, Short> crew = new HashMap<>(); // new LinkedHashMap<>();?
        string = string.substring(1, string.length() - 1);
        String[] crewRolesShorts = string.split(",");

        for (String crewRoleShort : crewRolesShorts) {
            String[] roleAndShort = crewRoleShort.split(":");
            crew.put(Role.resolveRoleById(Long.parseLong(roleAndShort[0])), Short.parseShort(roleAndShort[1]));
        }
        return crew;
    }

}
