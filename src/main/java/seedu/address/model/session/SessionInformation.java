package seedu.address.model.session;

import seedu.address.model.competition.Competition;
import seedu.address.model.participation.Participation;

/**
 * Stores important information to display on the session card.
 * This includes the competition that is being held, the participation,
 * the lift and attempt to be made, as well as the weight to be attempted.
 */
public class SessionInformation {
    private static SessionInformation sessionInfo = null;

    private Competition competition;
    private Integer attemptWeight;
    private String liftAndAttemptNumber;
    private Participation participation;
    private boolean hasOngoingCompetition;
    private boolean hasNextAttempt;

    private SessionInformation() {
        this.competition = null;
        this.attemptWeight = null;
        this.liftAndAttemptNumber = null;
        this.participation = null;
        this.hasOngoingCompetition = false;
        this.hasNextAttempt = false;
    }

    public static SessionInformation getInstance() {
        if (sessionInfo == null) {
            sessionInfo = new SessionInformation();
        }
        return sessionInfo;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
        this.hasOngoingCompetition = true;
    }

    public void setAttemptWeight(int weight) {
        this.attemptWeight = weight;
        this.hasNextAttempt = true;
    }

    public void setLiftAndAttemptNumber(String liftAndAttemptNumber) {
        this.liftAndAttemptNumber = liftAndAttemptNumber;
        this.hasNextAttempt = true;
    }

    public void setParticipation(Participation participation) {
        this.participation = participation;
        this.hasNextAttempt = true;
    }

    /**
     * Restores the session information to default when a competition session has come to an end.
     */
    public void endSession() {
        this.competition = null;
        this.attemptWeight = null;
        this.liftAndAttemptNumber = null;
        this.participation = null;
        this.hasOngoingCompetition = false;
        this.hasNextAttempt = false;
    }

    public String getCompetitionName() {
        return competition.toString();
    }

    public String getAttemptWeight() {
        return attemptWeight.toString();
    }

    public String getLiftAndAttemptNumber() {
        return liftAndAttemptNumber;
    }

    public String getParticipationName() {
        return participation.getName().toString();
    }

    public boolean getHasOngoingCompetition() {
        return hasOngoingCompetition;
    }

    public boolean getHasNextAttempt() {
        return hasNextAttempt;
    }
}
