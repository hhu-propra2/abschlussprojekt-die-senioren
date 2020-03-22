package mops.gruppen1.domain.events;

import mops.gruppen1.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MembershipRequestEventTest {

    private TestSetup testSetup;

    @BeforeEach
    void setup() {
        this.testSetup = new TestSetup();
    }

    @Tag("EventTest")
    @DisplayName("Test MembershipRequestEvent")
    @Test
    void execute() {
        // Arrange
        Group groupThree = testSetup.groupThree; // Group Three is restricted
        String groupThreeID = groupThree.getGroupId().toString();

        Username username = new Username("Willi will beitreten");

        MembershipType Type = MembershipType.VIEWER;
        String membershipType = Type.toString();

        MembershipRequestEvent membershipRequestEvent = new MembershipRequestEvent(groupThreeID, username.getUsername(), membershipType);

        //Act
        membershipRequestEvent.execute(testSetup.groupToMembers, testSetup.userToMembers, testSetup.users, testSetup.groups);

        //Assert
        assertThat(testSetup.groups.get(groupThreeID).getMembers().get(2)).isNotNull();
        assertThat(testSetup.groups.get(groupThreeID).getMembers().get(2).getUser().getUsername().getUsername()).isEqualTo("Willi will beitreten");

        assertThat(testSetup.groupToMembers.get(groupThreeID).get(2).getUser()).isNotNull();
        assertThat(testSetup.groupToMembers.get(groupThreeID).get(2).getUser().getUsername().getUsername()).isEqualTo("Willi will beitreten");

        assertThat(testSetup.userToMembers.get("Willi will beitreten").get(0).getGroup().getGroupId().toString()).isEqualTo(groupThreeID);
    }
}
