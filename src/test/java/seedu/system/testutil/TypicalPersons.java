package seedu.system.testutil;
import static seedu.system.logic.commands.CommandTestUtil.VALID_DOB_AMY;
import static seedu.system.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.system.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.system.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.system.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.system.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.system.model.Data;
import seedu.system.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    private static Person alice;

    static {
        try {
            alice = new PersonBuilder().withName("Alice Pauline")
                        .withDateOfBirth("01/01/2019").withGender("female").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Person benson;

    static {
        try {
            benson = new PersonBuilder().withName("Benson Meier")
                        .withDateOfBirth("01/02/2018").withGender("male").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Person carl;

    static {
        try {
            carl = new PersonBuilder().withName("Carl Kurz")
                    .withDateOfBirth("05/01/1992").withGender("male").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Person daniel;

    static {
        try {
            daniel = new PersonBuilder().withName("Daniel Meier")
                    .withDateOfBirth("17/11/1982").withGender("male").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Person elle;

    static {
        try {
            elle = new PersonBuilder().withName("Elle Meyer")
                    .withDateOfBirth("03/05/2010").withGender("female").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Person fiona;

    static {
        try {
            fiona = new PersonBuilder().withName("Fiona Kunz")
                    .withDateOfBirth("01/10/2010").withGender("female").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Person george;

    static {
        try {
            george = new PersonBuilder().withName("George Best")
                    .withDateOfBirth("19/06/2019").withGender("male").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Manually added
    private static Person hoon;

    static {
        try {
            hoon = new PersonBuilder().withName("Hoon Meier").withDateOfBirth("07/07/2007").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Person ida;

    static {
        try {
            ida = new PersonBuilder().withName("Ida Mueller").withDateOfBirth("08/08/2008").build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Manually added - Person's details found in {@code CommandTestUtil}
    private static Person amy;

    static {
        try {
            amy = new PersonBuilder().withName(VALID_NAME_AMY).withDateOfBirth(VALID_DOB_AMY)
                    .withGender(VALID_GENDER_AMY).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Person bob;

    static {
        try {
            bob = new PersonBuilder().withName(VALID_NAME_BOB).withDateOfBirth(VALID_DOB_BOB)
                        .withGender(VALID_GENDER_BOB).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Data} with all the typical persons.
     */
    public static Data<Person> getTypicalPersonData() {
        Data ab = new Data();
        for (Person person : getTypicalPersons()) {
            ab.addUniqueElement(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(alice, benson, carl, daniel, elle, fiona, george));
    }

    public static Person getAlice() {
        return alice;
    }

    public static Person getBenson() {
        return benson;
    }

    public static Person getCarl() {
        return carl;
    }

    public static Person getDaniel() {
        return daniel;
    }

    public static Person getElle() {
        return elle;
    }

    public static Person getFiona() {
        return fiona;
    }

    public static Person getGeorge() {
        return george;
    }

    public static Person getHoon() {
        return hoon;
    }

    public static Person getIda() {
        return ida;
    }

    public static Person getAmy() {
        return amy;
    }

    public static Person getBob() {
        return bob;
    }

}
