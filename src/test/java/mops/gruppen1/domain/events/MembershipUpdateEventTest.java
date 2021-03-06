package mops.gruppen1.domain.events;

import mops.gruppen1.domain.Group;
import mops.gruppen1.domain.Membership;
import mops.gruppen1.domain.MembershipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MembershipUpdateEventTest {

    private TestSetup testSetup;
    private String testGroupOneId;
    private String updatedUser;
    private String updatedBy;
    private MembershipUpdateEvent membershipUpdateEvent;

    @BeforeEach
    void setup() {
        //allgemeiner arrange - Schritt
        this.testSetup = new TestSetup();
        this.testGroupOneId = testSetup.groupOne.getGroupId().toString();
        this.updatedUser = testSetup.memberships.get(1).getUser().getUsername().getUsername();
        this.updatedBy = testSetup.memberships.get(0).getUser().getUsername().getUsername();
        this.membershipUpdateEvent = new MembershipUpdateEvent(testGroupOneId, updatedUser, updatedBy);
    }

    @Tag("EventTest")
    @DisplayName("Teste Member - Type Änderung von Viewer zu Admin in group-Hashmap.")
    @Test
    void testExecuteChangeTypeInGroups() {
        //arrange
        List<Membership> testMemberlist = testSetup.groups.get(testGroupOneId).getMembers();

        //act
        membershipUpdateEvent.execute(testSetup.groupToMembers, testSetup.userToMembers, testSetup.users, testSetup.groups);

        //assert
        assertThat(testMemberlist.get(0).getMembershipType()
                .equals((MembershipType.ADMIN))).isTrue();
    }

    @Tag("EventTest")
    @DisplayName("Teste Member - Type Änderung von Viewer zu Admin in groupToMembers-Hashmap.")
    @Test
    void testChangeTypeToAdmin() {
        //arrange
        String testGroupID = testSetup.groupOne.getGroupId().toString();
        List<Membership> testMemberlist = testSetup.groupToMembers.get(testGroupID);

        //act
        membershipUpdateEvent.execute(testSetup.groupToMembers, testSetup.userToMembers, testSetup.users, testSetup.groups);

        //assert
        assertThat(testMemberlist.get(1).getMembershipType()
                .equals((MembershipType.ADMIN))).isTrue();
    }

    @Tag("EventTest")
    @DisplayName("Teste Member - MembershipType Änderung von Viewer zu Admin in group-Hashmap.")
    @Test
    void testChangeTypeToViewer() {
        //arrange
        String testGroupID = testSetup.groupOne.getGroupId().toString();
        Group groupOne = testSetup.groups.get(testGroupID);
        List<Membership> testMemberlist = groupOne.getMembers();
        membershipUpdateEvent = new MembershipUpdateEvent(testGroupOneId, updatedUser, updatedBy);

        //act
        membershipUpdateEvent.execute(testSetup.groupToMembers, testSetup.userToMembers, testSetup.users, testSetup.groups);

        //assert
        //Da Membership - List von User, hat diese nur einen Eintrag, da jeder User im testSetup nur in genau
        //einer Gruppe ist.
        assertThat(testMemberlist.get(0).getMembershipType()
                .equals((MembershipType.ADMIN))).isTrue();
    }

    @Tag("EventTest")
    @DisplayName("Teste Member - Type Änderung von Admin zu Viewer in group-Hashmap.")
    @Test
    void testExecuteChangeTypeInGroupsAdminToViewer() {
        //arrange
        List<Membership> testMemberlist = testSetup.groupOne.getMembers();
        String adminMember = testSetup.memberships.get(0).getUser().getUsername().getUsername();
        String updatedBy2 = testSetup.memberships.get(1).getUser().getUsername().getUsername();
        MembershipUpdateEvent membershipUpdateEventToViewer = new MembershipUpdateEvent(testGroupOneId, adminMember,
                updatedBy2);

        //act
        //First: Change viewer to admin who will after that change another admin to viewer.
        membershipUpdateEvent.execute(testSetup.groupToMembers, testSetup.userToMembers, testSetup.users,
                testSetup.groups);
        membershipUpdateEventToViewer.execute(testSetup.groupToMembers, testSetup.userToMembers, testSetup.users,
                testSetup.groups);

        //assert
        assertThat(testMemberlist.get(0).getMembershipType()
                .equals((MembershipType.VIEWER))).isTrue();
    }
}