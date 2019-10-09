package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyData;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Data data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private DataStorage dataStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(DataStorage dataStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.dataStorage = dataStorage;
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


    // ================ Data methods ==============================

    @Override
    public Path getDataFilePath() {
        return dataStorage.getDataFilePath();
    }

    @Override
    public Optional<ReadOnlyData> readPersonData() throws DataConversionException, IOException {
        return readPersonData(dataStorage.getDataFilePath());
    }

    @Override
    public Optional<ReadOnlyData> readPersonData(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return dataStorage.readPersonData(filePath);
    }

    @Override
    public void saveData(ReadOnlyData readOnlyData) throws IOException {
        saveData(readOnlyData, dataStorage.getDataFilePath());
    }

    @Override
    public void saveData(ReadOnlyData readOnlyData, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        dataStorage.saveData(readOnlyData, filePath);
    }

}
