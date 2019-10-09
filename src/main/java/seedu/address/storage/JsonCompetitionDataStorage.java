package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CompetitionData;
import seedu.address.model.ReadOnlyCompetitionData;

/**
 * A class to access CompetitionData stored in the hard disk as a json file
 */
public class JsonCompetitionDataStorage implements CompetitionDataStorage {

    private Path filePath;

    public JsonCompetitionDataStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getCompetitionDataFilePath() {
        return filePath;
    }

    @Override
    public Optional<CompetitionData> readCompetitionData() throws DataConversionException {
        return readUserPrefs(filePath);
    }

    /**
     * Similar to {@link #readCompetitionData()}
     * @param prefsFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<CompetitionData> readUserPrefs(Path prefsFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(prefsFilePath, CompetitionData.class);
    }

    @Override
    public void saveCompetitionData(ReadOnlyCompetitionData readOnlyCompetitionData) throws IOException {
        JsonUtil.saveJsonFile(readOnlyCompetitionData, filePath);
    }

}
