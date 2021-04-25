package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;

    public static class CriteriaBuilder {
        SpaceshipCriteria spaceshipCriteria;

        public CriteriaBuilder() {
            spaceshipCriteria = new SpaceshipCriteria();
        }

        public CriteriaBuilder withCrew(Map<Role, Short> crew) {
            spaceshipCriteria.crew = crew;
            return this;
        }

        public CriteriaBuilder withRole(Long flightDistance) {
            spaceshipCriteria.flightDistance = flightDistance;
            return this;
        }

        public CriteriaBuilder withReadyForNextMissions(boolean isReadyForNextMissions) {
            spaceshipCriteria.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public SpaceshipCriteria build() {
            return spaceshipCriteria;
        }

    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }
}
