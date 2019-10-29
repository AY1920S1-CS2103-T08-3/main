package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Data;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyData;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.competition.Competition;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Person;
import seedu.address.model.session.ParticipationAttempt;
import seedu.address.model.session.Session;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getUserPrefsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefsFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        // ===== Person =====

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPersons(ReadOnlyData<Person> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyData<Person> getPersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // ===== Competition =====

        @Override
        public void addCompetition(Competition competition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompetitions(ReadOnlyData<Competition> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyData<Competition> getCompetitions() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompetition(Competition competition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompetition(Competition target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompetition(Competition target, Competition editedCompetition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Competition> getFilteredCompetitionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompetitionList(Predicate<Competition> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        // ===== Participation =====

        @Override
        public void addParticipation(Participation person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setParticipations(ReadOnlyData<Participation> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyData<Participation> getParticipations() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasParticipation(Participation person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteParticipation(Participation target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDependentParticipations(Competition competition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDependentParticipations(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setParticipation(Participation target, Participation editedParticipation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Participation> getFilteredParticipationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredParticipationList(Predicate<Participation> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Session getSession() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void startSession(ObservableList<Participation> participations) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ParticipationAttempt makeAttempt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ParticipationAttempt getNextLifter() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ParticipationAttempt getFollowingLifter() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSameElement(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSameElement);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyData<Person> getPersons() {
            return new Data();
        }

    }

    // TODO: private class ModelStubWithCompetition extends ModelStub
    // TODO: private class ModelStubWithParticipation extends ModelStub

}
