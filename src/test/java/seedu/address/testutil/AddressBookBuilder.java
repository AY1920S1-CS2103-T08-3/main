package seedu.address.testutil;

import seedu.address.model.Data;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Data ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Data addressBook;

    public AddressBookBuilder() {
        addressBook = new Data();
    }

    public AddressBookBuilder(Data addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code Data} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addUniqueElement(person);
        return this;
    }

    public Data build() {
        return addressBook;
    }
}
