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

    private final String missionName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Long distance;
    private final Spaceship assignedSpaceShift;
    private final List<CrewMember> assignedCrew;
    private final MissionResult missionResult;
    private final Planet fromPlanet;
    private final Planet toPlanet;

    private FlightMissionCriteria(Long id, String missionName, LocalDate startDate,
                                  LocalDate endDate, Long distance,
                                  Spaceship assignedSpaceShift,
                                  List<CrewMember> assignedCrew,
                                  MissionResult missionResult,
                                  Planet fromPlanet, Planet toPlanet) {
        super(id);
        this.missionName = missionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.assignedSpaceShift = assignedSpaceShift;
        this.assignedCrew = assignedCrew;
        this.missionResult = missionResult;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
    }

    public static class CriteriaBuilder {

        Long id;
        String missionName;
        LocalDate startDate;
        LocalDate endDate;
        Long distance;
        Spaceship assignedSpaceShift;
        List<CrewMember> assignedCrew;
        MissionResult missionResult;
        Planet fromPlanet;
        Planet toPlanet;

        public CriteriaBuilder() {
        }

        public CriteriaBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CriteriaBuilder withMissionName(String missionName) {
            this.missionName = missionName;
            return this;
        }

        public CriteriaBuilder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public CriteriaBuilder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public CriteriaBuilder withDistance(Long distance) {
            this.distance = distance;
            return this;
        }

        public CriteriaBuilder withSpaceship(Spaceship assignedSpaceShift) {
            this.assignedSpaceShift = assignedSpaceShift;
            return this;
        }

        public CriteriaBuilder withCrew(List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
            return this;
        }

        public CriteriaBuilder withResult(MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }

        public CriteriaBuilder withFromPlanet(Planet fromPlanet) {
            this.fromPlanet = fromPlanet;
            return this;
        }

        public CriteriaBuilder withToPlanet(Planet toPlanet) {
            this.toPlanet = toPlanet;
            return this;
        }

        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(this.id, this.missionName,
                    this.startDate, this.endDate, this.distance,
                    this.assignedSpaceShift, this.assignedCrew,
                    this.missionResult, this.fromPlanet, this.toPlanet);
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
