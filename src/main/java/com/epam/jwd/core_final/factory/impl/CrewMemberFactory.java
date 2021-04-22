package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    private static final CrewMemberFactory INSTANCE = new CrewMemberFactory();

    private CrewMemberFactory() {
    }

    public static CrewMemberFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public CrewMember create(Object... args) {
        if (args.length == 3) {
            return new CrewMember((String) args[0], (Role) args[1], (Rank) args[2]);
        }
        throw new IllegalArgumentException("Not enough args");
    }
}
