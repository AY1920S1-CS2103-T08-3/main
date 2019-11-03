package seedu.system.testutil;

import java.text.ParseException;

import seedu.system.model.person.CustomDate;
import seedu.system.model.person.Gender;
import seedu.system.model.person.Name;
import seedu.system.model.person.Person;


/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final Name DEFAULT_NAME = new Name("Alice Pline");
    public static final Gender DEFAULT_GENDER = Gender.FEMALE;
    private static CustomDate defaultDOB;

    static {
        try {
            defaultDOB = new CustomDate("12/02/1995");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Name name;
    private CustomDate dateOfBirth;
    private Gender gender;

    public PersonBuilder() {
        name = DEFAULT_NAME;
        dateOfBirth = defaultDOB;
        gender = DEFAULT_GENDER;

    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        dateOfBirth = personToCopy.getDateOfBirth();
        gender = personToCopy.getGender();

    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateOfBirth(String dateOfBirth) throws ParseException {
        this.dateOfBirth = new CustomDate(dateOfBirth);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = Gender.getGenderCorrespondingToName(gender);
        return this;
    }

    public Person build() {
        return new Person(name, dateOfBirth, gender);
    }
}
