package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public final class CrewMemberFactory implements EntityFactory<CrewMember> {

    private static final CrewMemberFactory INSTANCE = new CrewMemberFactory();

    private CrewMemberFactory() {
    }

    public static CrewMemberFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public CrewMember create(Object... args) {
        return new CrewMember(
                (Long) args[0],
                (String) args[1],
                (Role) args[2],
                (Rank) args[3]);
    }

}

