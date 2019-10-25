package seedu.address.model.session;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.attempt.Attempt;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Name;
import seedu.address.model.session.exceptions.AttemptsSubmittedException;
import seedu.address.model.session.exceptions.CompetitionNotFinishedException;
import seedu.address.model.session.exceptions.IncompleteAttemptSubmissionException;
import seedu.address.model.session.exceptions.NoOngoingSessionException;
import seedu.address.model.session.exceptions.OngoingSessionException;
import seedu.address.model.session.exceptions.PreviousAttemptNotDoneException;

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
    private boolean isReady; // where the next lifter is ready to make his attempt

    private Session() {
        this.participationList = FXCollections.observableArrayList();
        this.attemptList = FXCollections.observableArrayList();
        this.loadedParticipations = new ArrayList<>();
        this.isOngoing = false;
        this.isPrepared = false;
        this.isReady = false;
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
     * @throws OngoingSessionException if there is already an ongoing session
     */
    public void start(ObservableList<Participation> participations) throws OngoingSessionException {
        if (isOngoing) {
            throw new OngoingSessionException();
        }

        this.participationList = participations;
        this.isOngoing = true;
    }

    /**
     * Loads all 9 attempts that the participation athlete is going to make.
     *
     * @param participation the participation whose just submitted all attempts
     * @param attempts a list of the participation's 9 attempts for the different lifts
     *
     * @throws AttemptsSubmittedException when a participant has submitted his/her attempts
     * @throws NoOngoingSessionException if there is no ongoing session to load attempts for
     */
    public void loadAttempts(Participation participation, List<Attempt> attempts)
            throws AttemptsSubmittedException, NoOngoingSessionException {
        if (!isOngoing) {
            throw new NoOngoingSessionException();
        }

        if (loadedParticipations.contains(participation)) {
            throw new AttemptsSubmittedException(participation.getPerson());
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
     *
     * @throws NoOngoingSessionException if there is no ongoing session to prepare for
     * @throws IncompleteAttemptSubmissionException if there exists athletes who have not submitted their attempts
     */
    private void prepare() throws NoOngoingSessionException, IncompleteAttemptSubmissionException {
        if (!isOngoing) {
            throw new NoOngoingSessionException();
        }

        // create list of participations names who have not submitted their attempts
        List<Name> nonSubmissionNames = new ArrayList<>();
        for (Participation p : participationList) {
            if (!loadedParticipations.contains(p)) {
                nonSubmissionNames.add(p.getName());
            }
        }
        if (!nonSubmissionNames.isEmpty()) {
            throw new IncompleteAttemptSubmissionException(nonSubmissionNames);
        }

        attemptList.sort(new ParticipationAttemptComparator());
        isPrepared = true;
        isReady = true;
    }

    /**
     * Retrieves the next lifter in the queue to attempt his weight.
     *
     * @return the Participation of the next lifter to be attempting his weight
     *
     * @throws NoOngoingSessionException if there is no ongoing session to get the next lifter
     * @throws IncompleteAttemptSubmissionException if there exists athletes who have not submitted their attempts
     * @throws PreviousAttemptNotDoneException if the previous lifter has not completed his attempt,
     *                                         and the next lifter is not ready to be called
     */
    public Participation nextLifter() throws NoOngoingSessionException, IncompleteAttemptSubmissionException,
            PreviousAttemptNotDoneException {
        if (!isOngoing) {
            throw new NoOngoingSessionException();
        }

        if (!isPrepared) {
            prepare();
        }

        if (!isReady) {
            throw new PreviousAttemptNotDoneException();
        }

        if (attemptList.isEmpty()) {
            end();
        }

        ParticipationAttempt nextAttempt = attemptList.get(0);
        Participation nextLifter = nextAttempt.getParticipation();
        isReady = false;
        return nextLifter;
    }

    /**
     * Records the attempt made by the lifter.
     *
     * @throws NoOngoingSessionException
     */
    public void attemptMade()
            throws NoOngoingSessionException {
        if (!isOngoing) {
            throw new NoOngoingSessionException();
        }

        attemptList.remove(0);
        isReady = true;
    }

    /**
     * Ends the session, and resets all the data stored by the ended session.
     *
     * @throws NoOngoingSessionException if there is no ongoing session
     * @throws CompetitionNotFinishedException if there are still lifters who have not made their attempt
     */
    public void end() throws NoOngoingSessionException, CompetitionNotFinishedException {
        if (!isOngoing) {
            throw new NoOngoingSessionException();
        }

        if (!attemptList.isEmpty()) {
            throw new CompetitionNotFinishedException();
        }

        isOngoing = false;
        isPrepared = false;
        loadedParticipations = new ArrayList<>();
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
