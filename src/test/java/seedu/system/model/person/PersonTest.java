package seedu.system.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.system.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.system.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.system.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.system.testutil.TypicalPersons.getAlice;
import static seedu.system.testutil.TypicalPersons.getBob;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import seedu.system.testutil.PersonBuilder;


public class PersonTest {

    private Person alice = getAlice();
    private Person bob = getBob();

    @Test
    public void isSamePerson() throws ParseException {
        // same object -> returns true
        assertTrue(alice.isSameElement(alice));

        // null -> returns false
        assertFalse(alice.isSameElement(null));

        // different DOB and gender, but same name -> returns true
        Person editedAlice = new PersonBuilder(alice).withDateOfBirth(VALID_DOB_BOB)
                .withGender(VALID_GENDER_BOB).build();
        assertTrue(alice.isSameElement(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(alice).withName(VALID_NAME_BOB).build();
        assertFalse(alice.isSameElement(editedAlice));

        // same name, same DOB, different gender -> returns true
        editedAlice = new PersonBuilder(alice).withGender(VALID_GENDER_BOB).build();
        assertTrue(alice.isSameElement(editedAlice));

        // same name, different DOB, same gender -> returns true
        editedAlice = new PersonBuilder(alice).withGender(VALID_GENDER_BOB).build();
        assertTrue(alice.isSameElement(editedAlice));

        // different name, same DOB, same gender -> returns false
        editedAlice = new PersonBuilder(alice).withName(VALID_NAME_BOB).build();
        assertFalse(alice.isSameElement(editedAlice));
    }

    @Test
    public void equals() throws ParseException {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(alice).build();
        assertTrue(alice.equals(aliceCopy));

        // same object -> returns true
        assertTrue(alice.equals(alice));

        // null -> returns false
        assertFalse(alice.equals(null));

        // different type -> returns false
        assertFalse(alice.equals(5));

        // different person -> returns false
        assertFalse(alice.equals(bob));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(alice).withName(VALID_NAME_BOB).build();
        assertFalse(alice.equals(editedAlice));

        // different DOB -> returns true
        editedAlice = new PersonBuilder(alice).withDateOfBirth(VALID_DOB_BOB).build();
        assertTrue(alice.equals(editedAlice));

        // different gender -> returns true
        editedAlice = new PersonBuilder(alice).withGender(VALID_GENDER_BOB).build();
        assertTrue(alice.equals(editedAlice));

    }
}
