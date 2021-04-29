package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class CrewServiceImpl implements CrewService {

    private static CrewServiceImpl instance;

    private final ApplicationContext context = NasaContext.getInstance();

    private CrewServiceImpl() {
    }

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return new ArrayList<>(context.retrieveBaseEntityList(CrewMember.class));
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = null;
        CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;

        if (crewMemberCriteria.getRole() != null) {
            crewMembers = findAllCrewMembers().stream()
                    .filter(crewMember -> crewMember.getRole().equals(crewMemberCriteria.getRole()))
                    .collect(Collectors.toList());
        }
        if (crewMemberCriteria.getId() != null) {
            crewMembers = findAllCrewMembers().stream()
                    .filter(crewMember -> crewMember.getId().equals(crewMemberCriteria.getId()))
                    .collect(Collectors.toList());
        }
        if (crewMemberCriteria.getRank() != null) {
            crewMembers = findAllCrewMembers().stream()
                    .filter(crewMember -> crewMember.getRank().equals(crewMemberCriteria.getRank()))
                    .collect(Collectors.toList());
        }
        return crewMembers;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) throws UnknownEntityException {
        return findAllCrewMembersByCriteria(criteria).stream().findAny();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) throws UnknownEntityException {
        if (crewMember == null) {
            throw new UnknownEntityException("Crew is not exist");
        }
        crewMember.setRank(Rank.CAPTAIN);
        crewMember.setRole(Role.COMMANDER);

        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(FlightMission flightMission, CrewMember crewMember) throws InvalidStateException {

        if (flightMission == null || crewMember == null || !crewMember.isReadyForNextMission()) {
            throw new InvalidStateException("It is not possible to assign crew");
        }
        Objects.requireNonNull(flightMission).getAssignedCrew().add(crewMember);
    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws EntityCreationException {
        Optional<CrewMember> duplicateId = findAllCrewMembers().stream()
                .filter(c -> c.getId().equals(crewMember.getId()))
                .findAny();
        if (duplicateId.isPresent()) {
            throw new EntityCreationException("Such crew member already exists");
        }
        CrewMember member = CrewMemberFactory.getInstance()
                .create(crewMember.getId(), crewMember.getName(),
                        crewMember.getRole(), crewMember.getRank());
        findAllCrewMembers().add(crewMember);
        return member;
    }

}
