package mops.gruppen1.domain.events;

import mops.gruppen1.domain.*;

import java.util.HashMap;
import java.util.List;

/**
 * For restricted Groups:
 * Create a Membership with Status 'PENDING'
 * Add Membership to groupToMembers and userToMembers
 */
public class MembershipRequestEvent implements IEvent {

    String groupId;
    String userName;
    String membershipType;

    @Override
    public void execute(HashMap<Group, List<Membership>> groupToMembers, HashMap<User, List<Membership>> userToMembers, HashMap<String, User> users, HashMap<String, Group> groups) {
        Group group = groups.get(groupId);
        User user = users.get(userName);
        Type membershipType = Type.valueOf(this.membershipType);

        Membership membership = new Membership(user,group, membershipType, Status.PENDING);
        groupToMembers.get(group).add(membership);
        userToMembers.get(user).add(membership);

    }
}
