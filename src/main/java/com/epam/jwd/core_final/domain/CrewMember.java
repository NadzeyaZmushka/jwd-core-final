package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    public CrewMember(String name, Role role, Rank rank) {
        super(name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = true;
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
    public String toString() {
        return "CrewMember{" +
                "role=" + role +
                ", rank=" + rank +
                ", isReadyForNextMission=" + isReadyForNextMissions +
                '}';
    }
}
