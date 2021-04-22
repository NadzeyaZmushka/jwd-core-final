package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
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

    public static class Builder {
        SpaceshipCriteria spaceshipCriteria;

        public Builder() {
            spaceshipCriteria = new SpaceshipCriteria();
        }

        public Builder withCrew(Map<Role, Short> crew) {
            spaceshipCriteria.crew = crew;
            return this;
        }

        public Builder withRole(Long flightDistance) {
            spaceshipCriteria.flightDistance = flightDistance;
            return this;
        }

        public Builder withReadyForNextMissions(boolean isReadyForNextMissions) {
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
