package mops.gruppen1.domain.events;

import mops.gruppen1.domain.Group;
import mops.gruppen1.domain.MembershipType;
import mops.gruppen1.domain.User;
import mops.gruppen1.domain.Username;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


public class MembershipAssignmentEventTest {

    private TestSetup testSetup;

    @BeforeEach
    void setup() {
        this.testSetup = new TestSetup();
    }

    @Tag("EventTest")
    @DisplayName("Test MembershipAssignmentEvent")
    @Test
    void execute() {
        //arrange
        Group groupOne = testSetup.groupOne;
        String groupId = testSetup.groupOne.getGroupId().toString();
        Username username = new Username("Hand-Test");
        User newUser = new User(username);
        testSetup.users.put(username.toString(), newUser);
        testSetup.userToMembers.put(username.toString(), new ArrayList<>());
        MembershipType membershipType = MembershipType.VIEWER;

        MembershipAssignmentEvent membershipAssignmentEvent = new MembershipAssignmentEvent(groupId, username.toString(), membershipType.toString());

        //act
        membershipAssignmentEvent.execute(testSetup.groupToMembers, testSetup.userToMembers, testSetup.users, testSetup.groups);

        //assert
        assertThat(groupOne.getMembers().stream().filter(membership -> membership.getUser().equals(newUser))).isNotEmpty();
        assertThat(groupOne.getMembers().get(2).getMembershipStatus().toString()).isEqualTo("ACTIVE");
        assertThat(testSetup.userToMembers.get(username.toString()).get(0).getUser().getUsername()).isEqualTo(username);
        assertThat(testSetup.userToMembers.get(username.toString()).get(0).getMembershipStatus().toString()).isEqualTo("ACTIVE");
        assertThat(testSetup.groupToMembers.get(groupId).get(2).getUser().getUsername()).isEqualTo(username);
        assertThat(testSetup.groupToMembers.get(groupId).get(2).getMembershipStatus().toString()).isEqualTo("ACTIVE");
    }
}
