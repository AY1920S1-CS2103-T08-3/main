package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyData;

/**
 * A class to access Data data stored as a json file on the hard disk.
 */
public class JsonDataStorage implements DataStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDataStorage.class);

    private Path filePath;

    public JsonDataStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDataFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyData> readPersonData() throws DataConversionException {
        return readPersonData(filePath);
    }

    /**
     * Similar to {@link #readPersonData()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyData> readPersonData(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveData(ReadOnlyData readOnlyData) throws IOException {
        saveData(readOnlyData, filePath);
    }

    /**
     * Similar to {@link #saveData(ReadOnlyData)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveData(ReadOnlyData readOnlyData, Path filePath) throws IOException {
        requireNonNull(readOnlyData);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(readOnlyData), filePath);
    }

}
