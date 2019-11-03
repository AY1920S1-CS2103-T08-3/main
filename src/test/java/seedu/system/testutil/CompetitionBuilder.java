package seedu.system.testutil;

import java.text.ParseException;

import seedu.system.model.competition.Competition;
import seedu.system.model.person.CustomDate;
import seedu.system.model.person.Name;

/**
 * A utility class to help with building Competition objects.
 */
public class CompetitionBuilder {

    public static final String DEFAULT_NAME = "NUS Powerlifting Open";
    private static CustomDate defaultStartDate;

    static {
        try {
            defaultStartDate = new CustomDate("01/01/2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static CustomDate defaultEndDate;

    static {
        try {
            defaultEndDate = new CustomDate("01/01/2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Name name;
    private CustomDate startDate;
    private CustomDate endDate;

    public CompetitionBuilder() {
        name = new Name(DEFAULT_NAME);
        startDate = defaultStartDate;
        endDate = defaultEndDate;
    }

    /**
     * Initializes the CompetitionBuilder with the data of {@code competitionToCopy}.
     */
    public CompetitionBuilder(Competition competitionToCopy) {
        name = competitionToCopy.getName();
        startDate = competitionToCopy.getStartDate();
        endDate = competitionToCopy.getEndDate();
    }

    /**
     * Sets the {@code Name} of the {@code Competition} that we are building.
     */
    public CompetitionBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code Competition} that we are building.
     */
    public CompetitionBuilder withStartDate(String dateString) throws ParseException {
        this.startDate = new CustomDate(dateString);
        return this;
    }

    /**
     * Sets the {@code EndDate} of the {@code Competition} that we are building.
     */
    public CompetitionBuilder withEndDate(String dateString) throws ParseException {
        this.endDate = new CustomDate(dateString);
        return this;
    }

    public Competition build() {
        return new Competition(name, startDate, endDate);
    }

}

