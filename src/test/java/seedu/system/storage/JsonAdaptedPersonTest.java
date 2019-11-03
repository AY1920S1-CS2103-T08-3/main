package seedu.system.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.system.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.system.testutil.Assert.assertThrows;
import static seedu.system.testutil.TypicalPersons.getBenson;

import org.junit.jupiter.api.Test;

import seedu.system.commons.exceptions.IllegalValueException;
import seedu.system.model.person.CustomDate;
import seedu.system.model.person.Gender;
import seedu.system.model.person.Name;
import seedu.system.model.person.Person;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DOB = "+65/12/34";
    private static final String INVALID_GENDER = "hemale";
    private Person benson = getBenson();
    private final String validName = benson.getName().toString();
    private final String validDob = benson.getDateOfBirth().toString();
    private final String validGender = benson.getGender().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(benson);
        assertEquals(benson, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, validDob, validGender);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, validDob, validGender);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(validName, INVALID_DOB, validGender);
        String expectedMessage = CustomDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirthThrowsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(validName, null, validGender);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CustomDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(validName, validDob, INVALID_GENDER);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(validName, validDob, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
