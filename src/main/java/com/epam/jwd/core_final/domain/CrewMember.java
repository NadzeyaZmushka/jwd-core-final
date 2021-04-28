package com.epam.jwd.core_final.domain;

import java.util.Objects;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */

public class CrewMember extends AbstractBaseEntity {

    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions = true;

    public CrewMember(Long id, String name, Role role, Rank rank) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.rank = rank;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean isReadyForNextMission() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMission(Boolean readyForNextMission) {
        isReadyForNextMissions = readyForNextMission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMember that = (CrewMember) o;
        return id.equals(that.id) &&
                role == that.role &&
                rank == that.rank &&
                Objects.equals(isReadyForNextMissions, that.isReadyForNextMissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, rank, isReadyForNextMissions);
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "name=" + name +
                ", id=" + id +
                ", role=" + role +
                ", rank=" + rank +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                '}';
    }

}
