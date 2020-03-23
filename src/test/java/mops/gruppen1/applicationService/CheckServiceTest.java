package mops.gruppen1.applicationService;

import mops.gruppen1.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckServiceTest {

    CheckService checkService;

    @BeforeEach
    public void setUp() {
        this.checkService = new CheckService();
    }

    @Tag("CheckServiceTest")
    @Test
    void testDoesUserExistPositive() {
        //Arrange

        String userName1 = "Test User1";
        String userName2 = "Test User2";
        User user1 = new User(new Username(userName1));
        HashMap<String, User> users = new HashMap<>();
        users.put(userName1, user1);

        //act
        ValidationResult validationResult = checkService.doesUserExist(userName2, users);

        //assert
        assertThat(validationResult.isValid()).isTrue();
    }

    @Tag("CheckServiceTest")
    @Test
    void testDoesUserExistFalse() {
        //Arrange

        String userName1 = "Test User1";
        String userName2 = "Test User2";
        User user1 = new User(new Username(userName1));
        User user2 = new User(new Username(userName2));
        HashMap<String, User> users = new HashMap<>();
        users.put(userName1, user1);
        users.put(userName2, user2);

        //act
        ValidationResult validationResult = checkService.doesUserExist(userName2, users);

        //assert
        assertThat(validationResult.isValid()).isFalse();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsGroupActivePositive() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.ACTIVE;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        //act
        ValidationResult validationResult = checkService.isGroupActive(groupId, groups);

        //assert
        assertThat(validationResult.isValid()).isTrue();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsGroupActiveFalse() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        //act
        ValidationResult validationResult = checkService.isGroupActive(groupId, groups);

        //assert
        assertThat(validationResult.isValid()).isFalse();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsPublicPositive() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.ACTIVE;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        //act
        ValidationResult validationResult = checkService.isPublic(groupId, groups);

        //assert
        assertThat(validationResult.isValid()).isTrue();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsPublicFalse() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.RESTRICTED;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        //act
        ValidationResult validationResult = checkService.isPublic(groupId, groups);

        //assert
        assertThat(validationResult.isValid()).isFalse();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsRestrictedPositive() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.RESTRICTED;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        //act
        ValidationResult validationResult = checkService.isRestricted(groupId, groups);

        //assert
        assertThat(validationResult.isValid()).isTrue();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsRestrictedFalse() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        //act
        ValidationResult validationResult = checkService.isRestricted(groupId, groups);

        //assert
        assertThat(validationResult.isValid()).isFalse();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsAdminPositive() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        String userName2 = "Admin";
        User admin = new User(new Username(userName2));
        HashMap<String, User> users = new HashMap<>();
        users.put(userName1, admin);

        MembershipType membershipType = MembershipType.ADMIN;
        MembershipStatus membershipStatus = MembershipStatus.ACTIVE;

        Membership membership = new Membership(admin, group, membershipType, membershipStatus);
        HashMap<String, List<Membership>> userToMembers = new HashMap<>();
        userToMembers.put(userName1, new ArrayList<>());
        userToMembers.get(userName1).add(membership);

        //act
        ValidationResult validationResult = checkService.isAdmin(userName1, groupId, groups, users, userToMembers);

        //assert
        assertThat(validationResult.isValid()).isTrue();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsAdminFalse() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        String userName2 = "Admin";
        User admin = new User(new Username(userName2));
        HashMap<String, User> users = new HashMap<>();
        users.put(userName1, admin);

        MembershipType membershipType = MembershipType.VIEWER;
        MembershipStatus membershipStatus = MembershipStatus.ACTIVE;

        Membership membership = new Membership(admin, group, membershipType, membershipStatus);
        HashMap<String, List<Membership>> userToMembers = new HashMap<>();
        userToMembers.put(userName1, new ArrayList<>());
        userToMembers.get(userName1).add(membership);

        //act
        ValidationResult validationResult = checkService.isAdmin(userName1, groupId, groups, users, userToMembers);

        //assert
        assertThat(validationResult.isValid()).isFalse();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsMembershipActivePositive() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        String userName2 = "Admin";
        User admin = new User(new Username(userName2));
        HashMap<String, User> users = new HashMap<>();
        users.put(userName1, admin);

        MembershipType membershipType = MembershipType.VIEWER;
        MembershipStatus membershipStatus = MembershipStatus.ACTIVE;

        Membership membership = new Membership(admin, group, membershipType, membershipStatus);
        HashMap<String, List<Membership>> userToMembers = new HashMap<>();
        userToMembers.put(userName1, new ArrayList<>());
        userToMembers.get(userName1).add(membership);

        //act
        ValidationResult validationResult = checkService.isMembershipActive(userName1, groupId, groups, users, userToMembers);

        //assert
        assertThat(validationResult.isValid()).isTrue();
    }
    @Tag("CheckServiceTest")
    @Test
    void testIsMembershipActiveFalse() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        String userName2 = "Admin";
        User admin = new User(new Username(userName2));
        HashMap<String, User> users = new HashMap<>();
        users.put(userName1, admin);

        MembershipType membershipType = MembershipType.VIEWER;
        MembershipStatus membershipStatus = MembershipStatus.DEACTIVATED;

        Membership membership = new Membership(admin, group, membershipType, membershipStatus);
        HashMap<String, List<Membership>> userToMembers = new HashMap<>();
        userToMembers.put(userName1, new ArrayList<>());
        userToMembers.get(userName1).add(membership);

        //act
        ValidationResult validationResult = checkService.isMembershipActive(userName1, groupId, groups, users, userToMembers);

        //assert
        assertThat(validationResult.isValid()).isFalse();
    }
    @Tag("CheckServiceTest")
    @Test
    void testIsMembershipPendingPositive() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        String userName2 = "Admin";
        User admin = new User(new Username(userName2));
        HashMap<String, User> users = new HashMap<>();
        users.put(userName1, admin);

        MembershipType membershipType = MembershipType.VIEWER;
        MembershipStatus membershipStatus = MembershipStatus.PENDING;

        Membership membership = new Membership(admin, group, membershipType, membershipStatus);
        HashMap<String, List<Membership>> userToMembers = new HashMap<>();
        userToMembers.put(userName1, new ArrayList<>());
        userToMembers.get(userName1).add(membership);

        //act
        ValidationResult validationResult = checkService.isMembershipPending(userName1, groupId, groups, users, userToMembers);

        //assert
        assertThat(validationResult.isValid()).isTrue();
    }

    @Tag("CheckServiceTest")
    @Test
    void testIsMembershipPendingFalse() {
        //Arrange
        String userName1 = "groupCreator";
        List<Membership> members = new ArrayList<>();
        GroupName groupName = new GroupName("groupName");
        GroupDescription description = new GroupDescription("description");
        User groupCreator = new User(new Username(userName1));
        GroupStatus groupStatus = GroupStatus.DEACTIVATED;
        GroupType groupType = GroupType.PUBLIC;

        Group group = new Group(members, groupName, description, groupCreator, groupStatus, groupType);
        HashMap groups = new HashMap<String, Group>();
        String groupId = group.getGroupId().toString();
        groups.put(groupId, group);

        String userName2 = "Admin";
        User admin = new User(new Username(userName2));
        HashMap<String, User> users = new HashMap<>();
        users.put(userName1, admin);

        MembershipType membershipType = MembershipType.VIEWER;
        MembershipStatus membershipStatus = MembershipStatus.REJECTED;

        Membership membership = new Membership(admin, group, membershipType, membershipStatus);
        HashMap<String, List<Membership>> userToMembers = new HashMap<>();
        userToMembers.put(userName1, new ArrayList<>());
        userToMembers.get(userName1).add(membership);

        //act
        ValidationResult validationResult = checkService.isMembershipPending(userName1, groupId, groups, users, userToMembers);

        //assert
        assertThat(validationResult.isValid()).isFalse();
    }
}