package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.service.CrewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private static CrewServiceImpl instance;

    static final Logger LOGGER = LoggerFactory.getLogger(CrewServiceImpl.class);

    private final List<CrewMember> allCrewMembers;

    private CrewServiceImpl(ApplicationContext context) {
        this.allCrewMembers = (List<CrewMember>) context.retrieveBaseEntityList(CrewMember.class);
    }

    public static CrewServiceImpl getInstance(ApplicationContext context) {
        if (instance == null) {
            instance = new CrewServiceImpl(context);
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return allCrewMembers;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = new ArrayList<>();
        CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;

        if (crewMemberCriteria.getRole() != null) {
            crewMembers = allCrewMembers.stream()
                    .filter(crewMember -> crewMember.getRole().equals(crewMemberCriteria.getRole()))
                    .collect(Collectors.toList());
        }
        if (crewMemberCriteria.getRank() != null) {
            crewMembers = allCrewMembers.stream()
                    .filter(crewMember -> crewMember.getRank().equals(crewMemberCriteria.getRank()))
                    .collect(Collectors.toList());
        }
        return crewMembers;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return findAllCrewMembersByCriteria(criteria).stream().findAny();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        crewMember.setReadyForNextMission(false);
        return crewMember;
    }

    // todo create custom exception for case, when crewMember is not able to be assigned
    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        // ...
    }

    // todo create custom exception for case, when crewMember is not able to be created (for example - duplicate.
    // crewMember unique criteria - only name!
    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws RuntimeException {
        try {
            if (allCrewMembers.contains(crewMember)) {
                throw new EntityCreationException("Crewmember already exists");
            } else {
                allCrewMembers.add(crewMember);
            }
        } catch (EntityCreationException e) {
            LOGGER.error(e.getMessage());
        }
        return crewMember;
    }
}
