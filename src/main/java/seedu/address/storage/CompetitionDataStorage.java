package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CompetitionData;
import seedu.address.model.ReadOnlyCompetitionData;

/**
 * Represents a storage for {@link CompetitionData}.
 */
public interface CompetitionDataStorage {

    /**
     * Returns the file path of the CompetitionData data file.
     */
    Path getCompetitionDataFilePath();

    /**
     * Returns CompetitionData data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<CompetitionData> readCompetitionData() throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCompetitionData} to the storage.
     * @param readOnlyCompetitionData cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCompetitionData(ReadOnlyCompetitionData readOnlyCompetitionData) throws IOException;

}
