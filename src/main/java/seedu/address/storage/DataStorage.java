package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Data;
import seedu.address.model.ReadOnlyData;

/**
 * Represents a storage for {@link Data}.
 */
public interface DataStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDataFilePath();

    /**
     * Returns Data data as a {@link ReadOnlyData}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyData> readPersonData() throws DataConversionException, IOException;

    /**
     * @see #getDataFilePath()
     */
    Optional<ReadOnlyData> readPersonData(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyData} to the storage.
     * @param readOnlyData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveData(ReadOnlyData readOnlyData) throws IOException;

    /**
     * @see #saveData(ReadOnlyData)
     */
    void saveData(ReadOnlyData readOnlyData, Path filePath) throws IOException;

}
