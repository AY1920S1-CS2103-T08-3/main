package seedu.address.model.session;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.attempt.Attempt;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Name;

/**
 * Handles the competition session for a particular competition,
 * and the participation athletes associated with it.
 */
public class Session {
    private static Session session = null;

    private ObservableList<Participation> participationList;
    private ObservableList<ParticipationAttempt> attemptList;
    private List<Participation> loadedParticipations; // list of participations who have loaded their attempts
    private boolean isOngoing; // where session has been session loaded with participations of a particular competition
    private boolean isPrepared; // where competition and attempts are ongoing

    private Session() {
        this.participationList = FXCollections.observableArrayList();
        this.attemptList = FXCollections.observableArrayList();
        this.loadedParticipations = new ArrayList<>();
        this.isOngoing = false;
        this.isPrepared = false;
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    /**
     * Starts a new session by loading the participations of the ongoing competition.
     *
     * @param participations the list of participations in the competition session
     */
    public void start(ObservableList<Participation> participations) {
        if (isOngoing) {
            // throw there is an ongoing session exception
        }
        this.participationList = participations;
        this.isOngoing = true;
    }

    /**
     * Ends the session, and resets all the data stored by the ended session.
     */
    public void end() {
        if (!this.attemptList.isEmpty()) {
            // throw there are still attempts left exception
        }
        isOngoing = false;
        isPrepared = false;
        loadedParticipations = new ArrayList<>();
    }

    /**
     * Loads all 9 attempts that the participation athlete is going to make.
     *
     * @param participation the participation whose just submitted all attempts
     * @param attempts a list of the participation's 9 attempts for the different lifts
     */
    public void loadAttempts(Participation participation, List<Attempt> attempts) {
        if (loadedParticipations.contains(participation)) {
            // throw exception where this lifter has made an attempt
        }
        int index = 0; // 1,2,3 are squats attempts in order; 4,5,6 for bench; 7,8,9 deadlift
        for (Attempt attempt : attempts) {
            index++;
            ParticipationAttempt partAttempt = new ParticipationAttempt(participation, attempt, index);
            attemptList.add(partAttempt);
        }
        loadedParticipations.add(participation);
    }

    /**
     * Prepares the session by sorting the participants and their attempts accordingly.
     */
    private void prepare() {
        List<Name> nameOfParticipationsWithoutAttempts = new ArrayList<>();
        for (Participation p : participationList) {
            if (!loadedParticipations.contains(p)) {
                nameOfParticipationsWithoutAttempts.add(p.getName());
            }
        }
        if (!nameOfParticipationsWithoutAttempts.isEmpty()) {
            // throw exception where there exists lifters who has not submitted their attempts
            // exception should take in the list of names
        }
        attemptList.sort(new ParticipationAttemptComparator());
        isPrepared = true;
    }

    /**
     * Retrieves the next lifter in the queue to attempt his weight.
     *
     * @return the Participation of the next lifter to be attempting his weight
     */
    public Participation nextLifter() {
        if (!isPrepared) {
            prepare();
        }
        if (attemptList.isEmpty()) {
            end();
            // throw an exception where there is no more attempts left, and return new end command
        }
        Participation nextLifter = attemptList.remove(0).getParticipation();
        return nextLifter;
    }

    /**
     * Retrieves the list of all ParticipationAttempts.
     *
     * @return an ObservableList of ParticipationAttempt list
     */
    public ObservableList<ParticipationAttempt> getAttemptList() {
        return attemptList;
    }

    public boolean getIsOngoing() {
        return isOngoing;
    }

    public boolean getIsPrepared() {
        return isPrepared;
    }
}
