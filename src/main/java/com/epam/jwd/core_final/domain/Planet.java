package com.epam.jwd.core_final.domain;

import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * location could be a simple class Point with 2 coordinates
 */

public class Planet extends AbstractBaseEntity {

    private final Location location;

    public Planet(Long id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(location, planet.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "location=" + location +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
