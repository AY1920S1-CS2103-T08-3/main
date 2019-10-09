package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CompetitionData;
import seedu.address.model.ReadOnlyCompetitionData;
import seedu.address.model.ReadOnlyPersonData;

/**
 * API of the Storage component
 */
public interface Storage extends PersonDataStorage, CompetitionDataStorage {

    @Override
    Optional<CompetitionData> readCompetitionData() throws DataConversionException, IOException;

    @Override
    void saveCompetitionData(ReadOnlyCompetitionData readOnlyCompetitionData) throws IOException;

    @Override
    Path getPersonDataFilePath();

    @Override
    Optional<ReadOnlyPersonData> readPersonData() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyPersonData addressBook) throws IOException;

}
