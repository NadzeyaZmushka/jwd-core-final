package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Location;
import com.epam.jwd.core_final.domain.Planet;

public class PlanetCriteria extends Criteria<Planet> {

    private final Location location;

    private PlanetCriteria(Long id, String name, Location location) {
        super(id);
        this.name = name;
        this.location = location;
    }

    public static class CriteriaBuilder {
        Long id;
        String name;
        Location location;

        public CriteriaBuilder() {
        }

        public CriteriaBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public CriteriaBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CriteriaBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PlanetCriteria build() {
            return new PlanetCriteria(this.id, this.name, this.location);
        }
    }

    public Location getLocation() {
        return location;
    }
}
