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
import seedu.address.model.UniqueElement;
import seedu.address.model.competition.Competition;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Person;

/**
 * A class to access Data data stored as a json file on the hard disk.
 */
public class JsonSystemStorage implements SystemStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSystemStorage.class);

    private Path personDataFilePath;
    private Path competitionDataFilePath;
    private Path participationDataFilePath;

    public JsonSystemStorage(Path personDataFilePath, Path competitionDataFilePath, Path participationDataFilePath) {
        this.personDataFilePath = personDataFilePath;
        this.competitionDataFilePath = competitionDataFilePath;
        this.participationDataFilePath = participationDataFilePath;
    }

    public Path getPersonDataFilePath() {
        return personDataFilePath;
    }

    @Override
    public Optional<ReadOnlyData<Person>> readPersonData() throws DataConversionException {
        return readData(personDataFilePath, JsonSerializablePersonData.class);
    }

    @Override
    public Optional<ReadOnlyData<Person>> readPersonData(Path filePath) throws DataConversionException {
        return readData(filePath, JsonSerializablePersonData.class);
    }

    @Override
    public void savePersonData(ReadOnlyData<Person> readOnlyData) throws IOException {
        saveData(new JsonSerializablePersonData(readOnlyData), personDataFilePath);
    }

    @Override
    public void savePersonData(ReadOnlyData<Person> readOnlyData, Path filePath) throws IOException {
        saveData(new JsonSerializablePersonData(readOnlyData), filePath);
    }

    public Path getCompetitionDataFilePath() {
        return competitionDataFilePath;
    }

    @Override
    public Optional<ReadOnlyData<Competition>> readCompetitionData() throws DataConversionException {
        return readData(competitionDataFilePath, JsonSerializableCompetitionData.class);
    }

    @Override
    public Optional<ReadOnlyData<Competition>> readCompetitionData(Path filePath) throws DataConversionException {
        return readData(filePath, JsonSerializableCompetitionData.class);
    }

    @Override
    public void saveCompetitionData(ReadOnlyData<Competition> readOnlyData) throws IOException {
        saveData(new JsonSerializableCompetitionData(readOnlyData), competitionDataFilePath);
    }

    @Override
    public void saveCompetitionData(ReadOnlyData<Competition> readOnlyData, Path filePath) throws IOException {
        saveData(new JsonSerializableCompetitionData(readOnlyData), filePath);
    }

    public Path getParticipationDataFilePath() {
        return participationDataFilePath;
    }

    @Override
    public Optional<ReadOnlyData<Participation>> readParticipationData() throws DataConversionException {
        return readData(participationDataFilePath, JsonSerializableParticipationData.class);
    }
    @Override
    public Optional<ReadOnlyData<Participation>> readParticipationData(Path filePath) throws DataConversionException {
        return readData(filePath, JsonSerializableParticipationData.class);
    }

    @Override
    public void saveParticipationData(ReadOnlyData<Participation> readOnlyData) throws IOException {
        saveData(new JsonSerializableParticipationData(readOnlyData), participationDataFilePath);
    }

    @Override
    public void saveParticipationData(ReadOnlyData<Participation> readOnlyData, Path filePath) throws IOException {
        saveData(new JsonSerializableParticipationData(readOnlyData), filePath);
    }

    /**
     * Similar to {@link #readPersonData()}, {@link #readCompetitionData()}, {@link #readPersonData()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public <T extends UniqueElement, J extends JsonSerializableData> Optional<ReadOnlyData<T>> readData(
        Path filePath,
        Class<J> classOfData
    ) throws DataConversionException {
        requireNonNull(filePath);

        Optional<J> jsonAddressBook = JsonUtil.readJsonFile(filePath, classOfData);
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

    /**
     * Similar to {@link #savePersonData(ReadOnlyData)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveData(JsonSerializableData jsonSerializableData, Path filePath) throws IOException {
        requireNonNull(jsonSerializableData);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(jsonSerializableData, filePath);
    }

}
