package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CrewMemberFactoryTest {

    @Test
    public void testCreate_returnsNewCrewMember() {
        CrewMember actual = CrewMemberFactory.getInstance().create(1L, "Bob", Role.COMMANDER, Rank.CAPTAIN);

        assertEquals((Long) 1L, actual.getId());
        assertEquals("Bob", actual.getName());
        assertEquals(Role.COMMANDER, actual.getRole());
        assertEquals(Rank.CAPTAIN, actual.getRank());
    }

}