package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * location could be a simple class Point with 2 coordinates
 */
public class Planet extends AbstractBaseEntity {

    int x;
    int y;

    public Planet(String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
