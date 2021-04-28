package com.epam.jwd.core_final.domain;

import java.util.Map;
import java.util.Objects;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {

    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions = true;

    public Spaceship(Long id, String name, Map<Role, Short> crew, Long flightDistance) {
        this.id = id;
        this.name = name;
        this.crew = crew;
        this.flightDistance = flightDistance;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public Boolean getReadyForNextMission() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMission(Boolean readyForNextMission) {
        isReadyForNextMissions = readyForNextMission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return Objects.equals(crew, spaceship.crew) &&
                Objects.equals(flightDistance, spaceship.flightDistance) &&
                Objects.equals(isReadyForNextMissions, spaceship.isReadyForNextMissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crew, flightDistance, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "name: " + name +
                ", id=" + id +
                ", flightDistance=" + flightDistance +
                ", crew=" + crew +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }

}
