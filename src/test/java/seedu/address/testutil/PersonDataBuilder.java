package seedu.address.testutil;

import seedu.address.model.Data;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Data ab = new PersonDataBuilder().withPerson("John", "Doe").build();}
 */
public class PersonDataBuilder {

    private Data data;

    public PersonDataBuilder() {
        data = new Data();
    }

    public PersonDataBuilder(Data data) {
        this.data = data;
    }

    /**
     * Adds a new {@code Person} to the {@code Data} that we are building.
     */
    public PersonDataBuilder withPerson(Person person) {
        data.addPerson(person);
        return this;
    }

    public Data build() {
        return data;
    }
}
