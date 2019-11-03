package seedu.system.logic.commands.outofsession;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.system.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.system.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.system.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.system.logic.commands.CommandTestUtil.getDescAmy;
import static seedu.system.logic.commands.CommandTestUtil.getDescBob;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import seedu.system.logic.commands.outofsession.EditPersonCommand.EditPersonDescriptor;
import seedu.system.testutil.EditPersonDescriptorBuilder;


public class EditPersonDescriptorTest {
    private EditPersonDescriptor descAmy = getDescAmy();
    private EditPersonDescriptor descBob = getDescBob();
    @Test
    public void equals() throws ParseException {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(descAmy);
        assertTrue(descAmy.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(descAmy.equals(descAmy));

        // null -> returns false
        assertFalse(descAmy.equals(null));

        // different types -> returns false
        assertFalse(descAmy.equals(5));

        // different values -> returns false
        assertFalse(descAmy.equals(descBob));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(descAmy).withName(VALID_NAME_BOB).build();
        assertFalse(descAmy.equals(editedAmy));

        // different DOB -> returns false
        editedAmy = new EditPersonDescriptorBuilder(descAmy).withDateOfBirth(VALID_DOB_BOB).build();
        assertFalse(descAmy.equals(editedAmy));

        // different gender -> returns false
        editedAmy = new EditPersonDescriptorBuilder(descAmy).withGender(VALID_GENDER_BOB).build();
        assertFalse(descAmy.equals(editedAmy));

    }
}
