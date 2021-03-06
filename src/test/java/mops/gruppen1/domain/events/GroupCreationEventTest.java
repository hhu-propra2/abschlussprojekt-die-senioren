package mops.gruppen1.domain.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GroupCreationEventTest {

    private TestSetup testSetup;

    @BeforeEach
    void setup() {
        this.testSetup = new TestSetup();
    }

    @Test
    @Tag("EventTest")
    @DisplayName("Test GroupCreationEvent")
    void execute() {
        //arrange
        GroupCreationEvent groupCreationEvent = new GroupCreationEvent("Ärger machen = aufs maul", "das neue Lager", "Wildschweine jagen", "Gomez", "public");

        //act
        groupCreationEvent.execute(testSetup.groupToMembers, testSetup.userToMembers, testSetup.users, testSetup.groups);

        //assert
        assertThat(testSetup.groups).hasSize(4);
        assertThat(testSetup.groupToMembers).hasSize(4);
    }
}