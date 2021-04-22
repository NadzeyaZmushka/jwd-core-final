package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class CriteriaBuilder {
        CrewMemberCriteria crewMemberCriteria;

        public CriteriaBuilder() {
            crewMemberCriteria = new CrewMemberCriteria();
        }

        public CriteriaBuilder withRank(Rank rank) {
            crewMemberCriteria.rank = rank;
            return this;
        }

        public CriteriaBuilder withRole(Role role) {
            crewMemberCriteria.role = role;
            return this;
        }

        public CriteriaBuilder withReadyForNextMissions(Boolean isReadyForNextMissions) {
            crewMemberCriteria.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public CrewMemberCriteria build() {
            return crewMemberCriteria;
        }
    }
}
