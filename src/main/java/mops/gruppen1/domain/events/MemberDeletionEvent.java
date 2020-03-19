package mops.gruppen1.domain.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import mops.gruppen1.domain.Group;
import mops.gruppen1.domain.Membership;
import mops.gruppen1.domain.Status;
import mops.gruppen1.domain.User;

import java.util.HashMap;
import java.util.List;

/**
 * change attribute Status in Membership to 'Deactivated'. NO deletion from datastructures
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class MemberDeletionEvent implements IEvent {

    private String groupId;
    private String removedMemberId;
    private String removedByMemberId;

    @Override
    public void execute(HashMap<String, List<Membership>> groupToMembers, HashMap<String, List<Membership>> userToMembers, HashMap<String, User> users, HashMap<String, Group> groups) {
        //TODO Ziehe Suche nach Deletor & Prüfung ob Admin in den Groupservice
        //Membership deletor = findDeletor(groups);
        //if (deletor != null && deletor.getType().equals(Type.ADMIN)) {
        Membership toBeDeleted = findRemovedMember(groups);
        deactiveMembership(toBeDeleted);
    }

    /**
     * Finds the member that is to be removed in a group.
     *
     * @param groups The group in which a member is searched for.
     * @return The member that matches the removedMemberId.
     */
    private Membership findRemovedMember(HashMap<String, Group> groups) {
        Group group = groups.get(groupId);
        Membership membership = group.getMembers().stream()
                .filter(member -> removedMemberId.equals(member.getMemberid().toString()))
                .findFirst()
                .orElse(null);

        return membership;
    }

    /**
     * Finds the member that is removing a member.
     *
     * @param groups The group in which a member is searched for.
     * @return The member that matches the removedByMemberId.
     */
    private Membership findDeletor(HashMap<String, Group> groups) {
        Group group = groups.get(groupId);
        Membership membership = group.getMembers().stream()
                .filter(member -> removedByMemberId.equals(member.getMemberid().toString()))
                .findFirst()
                .orElse(null);

        return membership;
    }

    /**
     * Deactivates a given membership.
     *
     * @param membership The membership that is to be deactivated.
     */
    private void deactiveMembership(Membership membership) {
        membership.setStatus(Status.DEACTIVATED);
    }
}