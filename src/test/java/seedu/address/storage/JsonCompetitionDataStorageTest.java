package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CompetitionData;

public class JsonCompetitionDataStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCompetitionDataStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserPrefs(null));
    }

    private Optional<CompetitionData> readUserPrefs(String userPrefsFileInTestDataFolder)
            throws DataConversionException {
        Path prefsFilePath = addToTestDataPathIfNotNull(userPrefsFileInTestDataFolder);
        return new JsonCompetitionDataStorage(prefsFilePath).readUserPrefs(prefsFilePath);
    }

    @Test
    public void readUserPrefs_missingFile_emptyResult() throws DataConversionException {
        assertFalse(readUserPrefs("NonExistentFile.json").isPresent());
    }

    @Test
    public void readUserPrefs_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUserPrefs("NotJsonFormatCompetitionData.json"));
    }

    private Path addToTestDataPathIfNotNull(String userPrefsFileInTestDataFolder) {
        return userPrefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userPrefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readUserPrefs_fileInOrder_successfullyRead() throws DataConversionException {
        CompetitionData expected = getTypicalUserPrefs();
        CompetitionData actual = readUserPrefs("TypicalCompetitionData.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void readUserPrefs_valuesMissingFromFile_defaultValuesUsed() throws DataConversionException {
        CompetitionData actual = readUserPrefs("EmptyCompetitionData.json").get();
        assertEquals(new CompetitionData(), actual);
    }

    @Test
    public void readUserPrefs_extraValuesInFile_extraValuesIgnored() throws DataConversionException {
        CompetitionData expected = getTypicalUserPrefs();
        CompetitionData actual = readUserPrefs("ExtraValuesCompetitionData.json").get();

        assertEquals(expected, actual);
    }

    private CompetitionData getTypicalUserPrefs() {
        CompetitionData userPrefs = new CompetitionData();
        userPrefs.setGuiSettings(new GuiSettings(1000, 500, 300, 100));
        userPrefs.setAddressBookFilePath(Paths.get("addressbook.json"));
        return userPrefs;
    }

    @Test
    public void savePrefs_nullPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(null, "SomeFile.json"));
    }

    @Test
    public void saveUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(new CompetitionData(), null));
    }

    /**
     * Saves {@code userPrefs} at the specified {@code prefsFileInTestDataFolder} filepath.
     */
    private void saveUserPrefs(CompetitionData userPrefs, String prefsFileInTestDataFolder) {
        try {
            new JsonCompetitionDataStorage(addToTestDataPathIfNotNull(prefsFileInTestDataFolder))
                    .saveCompetitionData(userPrefs);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file", ioe);
        }
    }

    @Test
    public void saveUserPrefs_allInOrder_success() throws DataConversionException, IOException {

        CompetitionData original = new CompetitionData();
        original.setGuiSettings(new GuiSettings(1200, 200, 0, 2));

        Path pefsFilePath = testFolder.resolve("TempPrefs.json");
        JsonCompetitionDataStorage jsonCompetitionDataStorage = new JsonCompetitionDataStorage(pefsFilePath);

        //Try writing when the file doesn't exist
        jsonCompetitionDataStorage.saveCompetitionData(original);
        CompetitionData readBack = jsonCompetitionDataStorage.readCompetitionData().get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setGuiSettings(new GuiSettings(5, 5, 5, 5));
        jsonCompetitionDataStorage.saveCompetitionData(original);
        readBack = jsonCompetitionDataStorage.readCompetitionData().get();
        assertEquals(original, readBack);
    }

}
