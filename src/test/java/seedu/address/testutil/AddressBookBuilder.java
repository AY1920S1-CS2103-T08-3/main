package seedu.address.testutil;

import seedu.address.model.PersonData;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PersonData ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private PersonData addressBook;

    public AddressBookBuilder() {
        addressBook = new PersonData();
    }

    public AddressBookBuilder(PersonData addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code PersonData} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public PersonData build() {
        return addressBook;
    }
}
