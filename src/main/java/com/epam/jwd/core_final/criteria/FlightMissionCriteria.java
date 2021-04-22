package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    String missionName;
    LocalDate startDate;
    LocalDate endDate;
    Long distance;
    Spaceship assignedSpaceShift;
    List<CrewMember> assignedCrew;
    MissionResult missionResult;
    Planet fromPlanet;
    Planet toPlanet;

    public static class Builder {
        FlightMissionCriteria flightMissionCriteria;

        public Builder() {
            flightMissionCriteria = new FlightMissionCriteria();
        }

        public Builder withMissionName(String missionName) {
            flightMissionCriteria.missionName = missionName;
            return this;
        }

        public Builder withStartDate(LocalDate startDate) {
            flightMissionCriteria.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDate endDate) {
            flightMissionCriteria.endDate = endDate;
            return this;
        }

        public Builder withDistance(Long distance) {
            flightMissionCriteria.distance = distance;
            return this;
        }

        public Builder withSpaceship(Spaceship assignedSpaceShift) {
            flightMissionCriteria.assignedSpaceShift = assignedSpaceShift;
            return this;
        }

        public Builder withCrew(List<CrewMember> assignedCrew) {
            flightMissionCriteria.assignedCrew = assignedCrew;
            return this;
        }

        public Builder withResult(MissionResult missionResult) {
            flightMissionCriteria.missionResult = missionResult;
            return this;
        }

        public Builder withFromPlanet(Planet fromPlanet) {
            flightMissionCriteria.fromPlanet = fromPlanet;
            return this;
        }
        public Builder withToPlanet(Planet toPlanet) {
            flightMissionCriteria.toPlanet = toPlanet;
            return this;
        }
        public FlightMissionCriteria build() {
            return flightMissionCriteria;
        }
    }

    public String getMissionName() {
        return missionName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceShift() {
        return assignedSpaceShift;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }
}
