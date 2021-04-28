package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Planet;

public class PlanetCriteria extends Criteria<Planet> {

    private PlanetCriteria(Long id, String name) {
        super(id);
        this.name = name;
    }

    public static class CriteriaBuilder {
        Long id;
        String name;

        public CriteriaBuilder() {
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
            return new PlanetCriteria(this.id, this.name);
        }
    }

}
