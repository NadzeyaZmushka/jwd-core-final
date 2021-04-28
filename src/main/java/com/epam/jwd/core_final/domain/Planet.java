package com.epam.jwd.core_final.domain;

import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * location could be a simple class Point with 2 coordinates
 */

public class Planet extends AbstractBaseEntity {

    private final int x = 1 + (int) (Math.random() * 10);
    private final int y = 1 + (int) (Math.random() * 10);

    public Planet(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return x == planet.x &&
                y == planet.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name +
                ", x=" + x +
                ", y=" + y +
                ", id=" + id +
                '}';
    }
}
