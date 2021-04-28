package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private final Map<Role, Short> crew;
    private final Long flightDistance;
    private final Boolean isReadyForNextMissions;

    public SpaceshipCriteria(Long id, Map<Role, Short> crew,
                             Long flightDistance, Boolean isReadyForNextMissions) {
        super(id);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    public static class CriteriaBuilder {
        Long id;
        Map<Role, Short> crew;
        Long flightDistance;
        Boolean isReadyForNextMissions;

        public CriteriaBuilder() {
        }

        public CriteriaBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CriteriaBuilder withCrew(Map<Role, Short> crew) {
            this.crew = crew;
            return this;
        }

        public CriteriaBuilder withDistance(Long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        public CriteriaBuilder withReadyForNextMissions(boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(this.id, this.crew, this.flightDistance, this.isReadyForNextMissions);
        }

    }

    @Override
    public Long getId() {
        return id;
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
