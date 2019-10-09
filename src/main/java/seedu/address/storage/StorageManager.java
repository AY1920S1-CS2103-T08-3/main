package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPersonData;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of PersonData data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PersonDataStorage personDataStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(PersonDataStorage personDataStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.personDataStorage = personDataStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ PersonData methods ==============================

    @Override
    public Path getPersonDataFilePath() {
        return personDataStorage.getPersonDataFilePath();
    }

    @Override
    public Optional<ReadOnlyPersonData> readPersonData() throws DataConversionException, IOException {
        return readPersonData(personDataStorage.getPersonDataFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonData> readPersonData(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return personDataStorage.readPersonData(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyPersonData addressBook) throws IOException {
        saveAddressBook(addressBook, personDataStorage.getPersonDataFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyPersonData addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        personDataStorage.saveAddressBook(addressBook, filePath);
    }

}
