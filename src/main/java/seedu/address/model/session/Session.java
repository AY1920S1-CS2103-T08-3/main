package seedu.address.model.session;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.attempt.Attempt;
import seedu.address.model.competition.Competition;
import seedu.address.model.participation.Participation;

/**
 * Handles the competition session for a particular competition,
 * and the participation athletes associated with it.
 */
public class Session {
    private final Competition competition;
    private ObservableList<ParticipationAttempt> attemptList;

    public Session(Competition competition) {
        this.competition = competition;
        this.attemptList = FXCollections.observableArrayList();
    }

    /**
     * Loads all 9 attempts that the participation athlete is going to make.
     *
     * @param participation the participation whose just submitted all attempts
     * @param attemptList a list of the participation's 9 attempts for the different lifts
     */
    public void loadAttempts(Participation participation, List<Attempt> attemptList) {
        int index = 0; // 1,2,3 are squats attempts in order; 4,5,6 for bench; 7,8,9 deadlift
        for (Attempt attempt : attemptList) {
            index++;
            ParticipationAttempt partAttempt = new ParticipationAttempt(participation, attempt, index);
            this.attemptList.add(partAttempt);
        }
    }

    /**
     * Starts the session by sorting the participations according to their attempted lift and weights.
     */
    public void start() {
        // create exception if there exists a lifter who has not submitted their attempts.
        // to do this, we need to get the participation list from the competition
        attemptList.sort(new ParticipationAttemptComparator());
    }

    /**
     * Retrieves the next lifter in the queue to attempt his weight.
     *
     * @return the Participation of the next lifter to be attempting his weight
     */
    public Participation nextLifter() {
        assert attemptList != null;
        Participation nextLifter = attemptList.remove(0).getParticipation();
        return attemptList.remove(0).getParticipation();
    }

    /**
     * Retrieves the list of all ParticipationAttempts.
     *
     * @return an ObservableList of ParticipationAttempt list
     */
    public ObservableList<ParticipationAttempt> getAttemptList() {
        return attemptList;
    }
}
