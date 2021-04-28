package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CrewMemberFactoryTest {

    @Test
    public void testCreate_returnsNewCrewMember() {
        CrewMember actual = new CrewMember(1L, "Bob", Role.COMMANDER, Rank.CAPTAIN);
        CrewMember expected = CrewMemberFactory.getInstance().create(
                1L, "Bob", Role.COMMANDER, Rank.CAPTAIN);


        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.getRank(), actual.getRank());
    }

}