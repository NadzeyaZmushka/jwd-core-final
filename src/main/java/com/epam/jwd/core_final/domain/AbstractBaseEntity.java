package com.epam.jwd.core_final.domain;

import java.sql.Struct;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {
    private static long currentId = 0;
    private Long id;
    private String name;

    public AbstractBaseEntity(String name) {
        this.id = currentId++;
        this.name = name;
    }

    @Override
    public Long getId() {
        // todo
        return this.id;
    }

    @Override
    public String getName() {
        // todo
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
