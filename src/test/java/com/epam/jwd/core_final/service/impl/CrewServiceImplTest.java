package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CrewServiceImplTest {

    ApplicationContext context = NasaContext.getInstance();
    CrewServiceImpl service = CrewServiceImpl.getInstance();
    List<CrewMember> list = (List<CrewMember>) context.retrieveBaseEntityList(CrewMember.class);

    @Before
    public void setUp() throws Exception {
        list.add(new CrewMember(1L, "Name", Role.COMMANDER, Rank.CAPTAIN));
        list.add(new CrewMember(1L, "Name1", Role.FLIGHT_ENGINEER, Rank.FIRST_OFFICER));
        list.add(new CrewMember(1L, "Name2", Role.MISSION_SPECIALIST, Rank.TRAINEE));
        list.add(new CrewMember(1L, "Name3", Role.COMMANDER, Rank.CAPTAIN));
    }

    @Test
    public void testFindAllCrewMembers_returnsAllCrewMembers() {
        List<CrewMember> expected = service.findAllCrewMembers();

        assertEquals(expected, list);
    }

    @Test
    public void testFindAllCrewMembersByCriteria_withId() {
        Criteria<CrewMember> criteria = new CrewMemberCriteria
                .CriteriaBuilder()
                .withId(1L)
                .build();

        List<CrewMember> expected = service.findAllCrewMembersByCriteria(criteria);

        assertEquals(expected, list);

    }

    @Test
    public void testFindCrewMemberByCriteria() {
        Criteria<CrewMember> criteria = new CrewMemberCriteria
                .CriteriaBuilder()
                .withRank(1L)
                .build();

        CrewMember actual = new CrewMember(1L, "Name2", Role.MISSION_SPECIALIST, Rank.TRAINEE);

        CrewMember expected = service.findCrewMemberByCriteria(criteria).get();

        assertEquals(expected, actual);
    }

    @Test(expected = UnknownEntityException.class)
    public void testUpdateCrewMemberDetails_throwsUnknownEntityException_whenCrewNull() {
        service.updateCrewMemberDetails(null);
    }

    @Test
    public void testUpdateCrewMemberDetails_whenCrewIsNotNull() {
        CrewMember crewMember = new CrewMember(1L, "Name", Role.PILOT, Rank.TRAINEE);
        CrewMember actual = new CrewMember(1L, "Name", Role.COMMANDER, Rank.CAPTAIN);

        assertEquals(service.updateCrewMemberDetails(crewMember), actual);
    }

    @Test(expected = InvalidStateException.class)
    public void testAssignCrewMemberOnMission_throwsInvalidStateException_whenEntitiesAreNull() throws InvalidStateException {
        service.assignCrewMemberOnMission(null, null);
    }

    @Test(expected = EntityCreationException.class)
    public void testCreateCrewMember_throwsEntityCreationException_whenCrewWithSuchIdAlreadyExists() throws EntityCreationException {
        CrewMember crewMember1 = new CrewMember(1L, "Name", Role.COMMANDER, Rank.CAPTAIN);
        list.add(crewMember1);
        CrewMember crewMember2 = new CrewMember(1L, "NameName", Role.COMMANDER, Rank.CAPTAIN);
        service.createCrewMember(crewMember2);
    }

    @After
    public void tearDown() throws Exception {
        list.clear();
    }

}