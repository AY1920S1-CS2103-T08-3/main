package seedu.address.testutil;

import seedu.address.model.PersonData;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PersonData ab = new PersonDataBuilder().withPerson("John", "Doe").build();}
 */
public class PersonDataBuilder {

    private PersonData personData;

    public PersonDataBuilder() {
        personData = new PersonData();
    }

    public PersonDataBuilder(PersonData personData) {
        this.personData = personData;
    }

    /**
     * Adds a new {@code Person} to the {@code PersonData} that we are building.
     */
    public PersonDataBuilder withPerson(Person person) {
        personData.addPerson(person);
        return this;
    }

    public PersonData build() {
        return personData;
    }
}
