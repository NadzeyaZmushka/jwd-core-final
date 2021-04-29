package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.impl.NasaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.EntityCreationException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.SpaceshipService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SpaceshipServiceImplTest {

    ApplicationContext context = NasaContext.getInstance();
    SpaceshipService service = SpaceshipServiceImpl.getInstance();
    List<Spaceship> spaceships = (List<Spaceship>) context.retrieveBaseEntityList(Spaceship.class);

    @Before
    public void setUp() {
        Map<Role, Short> crew1 = new HashMap<>() {{
            put(Role.COMMANDER, (short) 3);
            put(Role.PILOT, (short) 4);
            put(Role.FLIGHT_ENGINEER, (short) 2);
            put(Role.MISSION_SPECIALIST, (short) 5);
        }};
        Map<Role, Short> crew2 = new HashMap<>() {{
            put(Role.COMMANDER, (short) 2);
            put(Role.PILOT, (short) 4);
            put(Role.FLIGHT_ENGINEER, (short) 3);
            put(Role.MISSION_SPECIALIST, (short) 1);
        }};
        Map<Role, Short> crew3 = new HashMap<>() {{
            put(Role.COMMANDER, (short) 1);
            put(Role.PILOT, (short) 5);
            put(Role.FLIGHT_ENGINEER, (short) 2);
            put(Role.MISSION_SPECIALIST, (short) 6);
        }};
        Map<Role, Short> crew4 = new HashMap<>() {{
            put(Role.COMMANDER, (short) 3);
            put(Role.PILOT, (short) 3);
            put(Role.FLIGHT_ENGINEER, (short) 2);
            put(Role.MISSION_SPECIALIST, (short) 3);
        }};
        spaceships.add(new Spaceship(1L, "One", crew1, 1234L));
        spaceships.add(new Spaceship(1L, "Two", crew2, 1235L));
        spaceships.add(new Spaceship(1L, "Three", crew3, 1236L));
        spaceships.add(new Spaceship(1L, "Four", crew4, 1237L));
    }

    @Test
    public void testFindAllSpaceships() {
        List<Spaceship> expected = service.findAllSpaceships();

        assertEquals(expected, spaceships);
    }

    @Test
    public void findAllSpaceshipsByCriteria_withId() {
        Criteria<Spaceship> criteria = new SpaceshipCriteria.CriteriaBuilder()
                .withId(1L)
                .build();

        List<Spaceship> expected = service.findAllSpaceshipsByCriteria(criteria);

        assertEquals(expected, spaceships);
    }

    @Test
    public void findSpaceshipByCriteria_withDistance() {
        Criteria<Spaceship> criteria = new SpaceshipCriteria.CriteriaBuilder()
                .withDistance(1234L)
                .build();

        Spaceship actual = spaceships.get(0);
        Spaceship expected = service.findSpaceshipByCriteria(criteria).get();

        assertEquals(expected, actual);
    }

    @Test(expected = UnknownEntityException.class)
    public void testUpdateSpaceshipDetails_whenSpaceshipNull() {
        service.updateSpaceshipDetails(null);
    }

    @Test
    public void testUpdateSpaceshipDetails_whenSpaceshipIsNotNull() {
        Spaceship spaceship = new Spaceship(1L, "One", new HashMap<>() {{
            put(Role.COMMANDER, (short) 3);
            put(Role.PILOT, (short) 4);
            put(Role.FLIGHT_ENGINEER, (short) 2);
            put(Role.MISSION_SPECIALIST, (short) 5);
        }}, 1234L);
        Spaceship actual = new Spaceship(1L, "One", new HashMap<>() {{
            put(Role.COMMANDER, (short) 3);
            put(Role.PILOT, (short) 4);
            put(Role.FLIGHT_ENGINEER, (short) 2);
            put(Role.MISSION_SPECIALIST, (short) 5);
        }}, 1000000L);

        assertEquals(service.updateSpaceshipDetails(spaceship), actual);
    }


    @Test(expected = InvalidStateException.class)
    public void testAssignSpaceshipOnMission_throwsInvalidStateException_whenEntitiesNull() throws InvalidStateException {
        service.assignSpaceshipOnMission(null, null);
    }

    @Test(expected = EntityCreationException.class)
    public void testCreateSpaceship_throwsEntityCreationException_whenAlreadyExistWithSuchId() throws EntityCreationException {
        Spaceship spaceship1 = new Spaceship(1L, "One", new HashMap<>() {{
            put(Role.COMMANDER, (short) 3);
            put(Role.PILOT, (short) 4);
            put(Role.FLIGHT_ENGINEER, (short) 2);
            put(Role.MISSION_SPECIALIST, (short) 5);
        }}, 1234L);
        Spaceship spaceship2 = new Spaceship(1L, "One", new HashMap<>() {{
            put(Role.COMMANDER, (short) 3);
            put(Role.PILOT, (short) 4);
            put(Role.FLIGHT_ENGINEER, (short) 2);
            put(Role.MISSION_SPECIALIST, (short) 5);
        }}, 1234L);

        service.createSpaceship(spaceship2);
    }

    @After
    public void tearDown() {
        spaceships.clear();
    }

}