package mops.gruppen1.domain.events;

import lombok.Data;
import mops.gruppen1.applicationService.GroupService;
import mops.gruppen1.domain.Group;
import mops.gruppen1.domain.Membership;
import mops.gruppen1.domain.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * create new Group with attributes groupId, name and description.
 */
@Data
public class GroupCreationEvent implements Event {

    public String testKey;



    @Override
    public void execute(HashMap<Group, List<Membership>> groupToMembers, HashMap<User, List<Membership>> userToMembers, HashSet<User> users, HashSet<Group> groups) {

    }
}
