package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompetitionDataTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        CompetitionData userPref = new CompetitionData();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        CompetitionData userPrefs = new CompetitionData();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

}
