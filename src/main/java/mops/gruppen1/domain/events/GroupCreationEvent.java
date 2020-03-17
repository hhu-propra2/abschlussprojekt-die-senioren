package mops.gruppen1.domain.events;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mops.gruppen1.domain.Group;
import mops.gruppen1.domain.Membership;
import mops.gruppen1.domain.User;

import java.util.HashMap;
import java.util.List;

/**
 * create new Group with attributes groupId, name, description and, if added, GroupStatus (to 'active')
 * Add Group to HashSet Groups
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreationEvent implements Event {

    private String groupID;
    private String groupDescription;
    private String groupName;
    private String groupCourse;
    private String groupCreator;
    //TODO how to handle optional groupCourse?


    public GroupCreationEvent(String groupDescription, String groupName, String groupCourse, String groupCreator) {
        this.groupDescription = groupDescription;
        this.groupName = groupName;
        this.groupCourse = groupCourse;
        this.groupCreator = groupCreator;
    }

    @Override
    public void execute(HashMap<Group, List<Membership>> groupToMembers, HashMap<User, List<Membership>> userToMembers, HashMap<String, User> users, HashMap<String, Group> groups) {
        Group newGroup = createGroup();
        this.groupID = newGroup.getGroupId().toString();
        groups.put(groupID, newGroup);
        groupToMembers.put(newGroup, new ArrayList<>());
    }

    private Group createGroup() {
        List<Membership> members = new ArrayList<>();
        GroupName name = new GroupName(this.groupName);
        GroupDescription description = new GroupDescription(this.groupDescription);
        GroupStatus groupStatus = GroupStatus.ACTIVE;
        User groupCreator = new User(new Username(this.groupCreator));
        //add groupCourse?

        return new Group(members, name, description, groupCreator, groupStatus);
    }
}
