package mops.gruppen1.domain.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mops.gruppen1.domain.*;

import java.util.HashMap;
import java.util.List;

/**
 * For public Groups:
 * Create a Membership with Status 'ACTIVE'.
 * Add Membership to groupToMembers and userToMembers
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class MembershipAssignmentEvent implements IEvent {

    private String groupId;
    private String userName;
    private String membershipType;


    @Override
    public void execute(HashMap<String, List<Membership>> groupToMembers, HashMap<String,
            List<Membership>> userToMembers, HashMap<String, User> users, HashMap<String, Group> groups) {
        Group group = groups.get(groupId);
        User user = users.get(userName);
        MembershipType membershipType = MembershipType.valueOf(this.membershipType);

        Membership membership = new Membership(user, group, membershipType, MembershipStatus.ACTIVE);
        group.addMember(membership);
        groupToMembers.get(groupId).add(membership);
        userToMembers.get(userName).add(membership);

    }
}
