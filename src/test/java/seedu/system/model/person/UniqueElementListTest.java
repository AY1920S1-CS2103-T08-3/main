package seedu.system.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.system.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.system.testutil.Assert.assertThrows;
import static seedu.system.testutil.TypicalPersons.getAlice;
import static seedu.system.testutil.TypicalPersons.getBob;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.system.model.UniqueElementList;
import seedu.system.model.exceptions.DuplicateElementException;
import seedu.system.model.exceptions.ElementNotFoundException;
import seedu.system.testutil.PersonBuilder;

public class UniqueElementListTest {

    private final UniqueElementList<Person> uniquePersonList = new UniqueElementList<>();
    private Person alice = getAlice();
    private Person bob = getBob();
    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniquePersonList.contains(alice));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniquePersonList.add(alice);
        assertTrue(uniquePersonList.contains(alice));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() throws ParseException {
        uniquePersonList.add(alice);
        Person editedAlice = new PersonBuilder(alice).withDateOfBirth(VALID_DOB_BOB).build();
        assertTrue(uniquePersonList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniquePersonList.add(alice);
        assertThrows(DuplicateElementException.class, () -> uniquePersonList.add(alice));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setElement(null, alice));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setElement(alice, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniquePersonList.setElement(alice, alice));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniquePersonList.add(alice);
        uniquePersonList.setElement(alice, alice);
        UniqueElementList<Person> expectedUniquePersonList = new UniqueElementList<>();
        expectedUniquePersonList.add(alice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() throws ParseException {
        uniquePersonList.add(alice);
        Person editedAlice = new PersonBuilder(alice).withDateOfBirth(VALID_DOB_BOB).build();
        uniquePersonList.setElement(alice, editedAlice);
        UniqueElementList<Person> expectedUniquePersonList = new UniqueElementList<>();
        expectedUniquePersonList.add(editedAlice);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniquePersonList.add(alice);
        uniquePersonList.setElement(alice, bob);
        UniqueElementList<Person> expectedUniquePersonList = new UniqueElementList<>();
        expectedUniquePersonList.add(bob);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePersonList.add(alice);
        uniquePersonList.add(bob);
        assertThrows(DuplicateElementException.class, () -> uniquePersonList.setElement(alice, bob));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> uniquePersonList.remove(alice));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniquePersonList.add(alice);
        uniquePersonList.remove(alice);
        UniqueElementList<Person> expectedUniquePersonList = new UniqueElementList<>();
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setElements((UniqueElementList<Person>) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniquePersonList.add(alice);
        UniqueElementList<Person> expectedUniquePersonList = new UniqueElementList<>();
        expectedUniquePersonList.add(bob);
        uniquePersonList.setElements(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePersonList.setElements((List<Person>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniquePersonList.add(alice);
        List<Person> personList = Collections.singletonList(bob);
        uniquePersonList.setElements(personList);
        UniqueElementList<Person> expectedUniquePersonList = new UniqueElementList<>();
        expectedUniquePersonList.add(bob);
        assertEquals(expectedUniquePersonList, uniquePersonList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Person> listWithDuplicatePersons = Arrays.asList(alice, alice);
        assertThrows(DuplicateElementException.class, () -> uniquePersonList.setElements(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePersonList.asUnmodifiableObservableList().remove(0));
    }
}
