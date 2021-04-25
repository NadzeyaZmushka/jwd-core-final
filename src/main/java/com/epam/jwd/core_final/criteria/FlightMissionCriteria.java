package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;

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

    public static class CriteriaBuilder {
        FlightMissionCriteria flightMissionCriteria;

        public CriteriaBuilder() {
            flightMissionCriteria = new FlightMissionCriteria();
        }

        public CriteriaBuilder withMissionName(String missionName) {
            flightMissionCriteria.missionName = missionName;
            return this;
        }

        public CriteriaBuilder withStartDate(LocalDate startDate) {
            flightMissionCriteria.startDate = startDate;
            return this;
        }

        public CriteriaBuilder withEndDate(LocalDate endDate) {
            flightMissionCriteria.endDate = endDate;
            return this;
        }

        public CriteriaBuilder withDistance(Long distance) {
            flightMissionCriteria.distance = distance;
            return this;
        }

        public CriteriaBuilder withSpaceship(Spaceship assignedSpaceShift) {
            flightMissionCriteria.assignedSpaceShift = assignedSpaceShift;
            return this;
        }

        public CriteriaBuilder withCrew(List<CrewMember> assignedCrew) {
            flightMissionCriteria.assignedCrew = assignedCrew;
            return this;
        }

        public CriteriaBuilder withResult(MissionResult missionResult) {
            flightMissionCriteria.missionResult = missionResult;
            return this;
        }

        public CriteriaBuilder withFromPlanet(Planet fromPlanet) {
            flightMissionCriteria.fromPlanet = fromPlanet;
            return this;
        }

        public CriteriaBuilder withToPlanet(Planet toPlanet) {
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
