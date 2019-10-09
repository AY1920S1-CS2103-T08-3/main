package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CompetitionData;
import seedu.address.model.ReadOnlyCompetitionData;
import seedu.address.model.ReadOnlyPersonData;

/**
 * Manages storage of PersonData data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PersonDataStorage personDataStorage;
    private CompetitionDataStorage competitionDataStorage;


    public StorageManager(PersonDataStorage personDataStorage, CompetitionDataStorage competitionDataStorage) {
        super();
        this.personDataStorage = personDataStorage;
        this.competitionDataStorage = competitionDataStorage;
    }

    // ================ CompetitionData methods ==============================

    @Override
    public Path getCompetitionDataFilePath() {
        return competitionDataStorage.getCompetitionDataFilePath();
    }

    @Override
    public Optional<CompetitionData> readCompetitionData() throws DataConversionException, IOException {
        return competitionDataStorage.readCompetitionData();
    }

    @Override
    public void saveCompetitionData(ReadOnlyCompetitionData readOnlyCompetitionData) throws IOException {
        competitionDataStorage.saveCompetitionData(readOnlyCompetitionData);
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
