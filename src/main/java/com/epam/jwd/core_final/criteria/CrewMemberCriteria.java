package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */

public class CrewMemberCriteria extends Criteria<CrewMember> {

    private final Role role;
    private final Rank rank;
    private final Boolean isReadyForNextMissions;

    private CrewMemberCriteria(Long id, Role role, Rank rank, Boolean isReadyForNextMissions) {
        super(id);
        this.id = id;
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    public static class CriteriaBuilder {

        Long id;

        Role role;
        Rank rank;
        Boolean isReadyForNextMissions;

        public CriteriaBuilder() {
        }

        public CriteriaBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CriteriaBuilder withRank(Long id) {
            this.rank = Rank.resolveRankById(id);
            return this;
        }

        public CriteriaBuilder withRole(Long id) {
            this.role = Role.resolveRoleById(id);
            return this;
        }

        public CriteriaBuilder withReadyForNextMissions(Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(this.id, this.role, this.rank, this.isReadyForNextMissions);
        }

    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }
}
