package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Data;
import seedu.address.model.ReadOnlyData;

public class JsonDataStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDataStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readData(null));
    }

    private java.util.Optional<ReadOnlyData> readData(String filePath) throws Exception {
        return new JsonDataStorage(Paths.get(filePath)).readPersonData(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readData("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readData("notJsonFormatPersonData.json"));
    }

    @Test
    public void readAddressBook_invalidPersonData_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readData("invalidPersonData.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonData_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readData("invalidAndValidPersonData.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Data original = getTypicalAddressBook();
        JsonDataStorage jsonAddressBookStorage = new JsonDataStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveData(original, filePath);
        ReadOnlyData readBack = jsonAddressBookStorage.readPersonData(filePath).get();
        assertEquals(original, new Data(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveData(original, filePath);
        readBack = jsonAddressBookStorage.readPersonData(filePath).get();
        assertEquals(original, new Data(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveData(original); // file path not specified
        readBack = jsonAddressBookStorage.readPersonData().get(); // file path not specified
        assertEquals(original, new Data(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyData addressBook, String filePath) {
        try {
            new JsonDataStorage(Paths.get(filePath))
                    .saveData(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Data(), null));
    }
}
