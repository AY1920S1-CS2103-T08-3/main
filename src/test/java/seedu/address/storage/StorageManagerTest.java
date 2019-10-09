package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.CompetitionData;
import seedu.address.model.PersonData;
import seedu.address.model.ReadOnlyPersonData;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPersonDataStorage personDataStorage = new JsonPersonDataStorage(getTempFilePath("ab"));
        JsonCompetitionDataStorage userPrefsStorage = new JsonCompetitionDataStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(personDataStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonCompetitionDataStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonCompetitionDataStorageTest} class.
         */
        CompetitionData original = new CompetitionData();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveCompetitionData(original);
        CompetitionData retrieved = storageManager.readCompetitionData().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPersonDataStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPersonDataStorageTest} class.
         */
        PersonData original = getTypicalAddressBook();
        storageManager.saveAddressBook(original);
        ReadOnlyPersonData retrieved = storageManager.readPersonData().get();
        assertEquals(original, new PersonData(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getPersonDataFilePath());
    }

}
