package seedu.address.model.session;

import seedu.address.model.attempt.Attempt;
import seedu.address.model.participation.Participation;
import seedu.address.model.session.exceptions.WrongAttemptIndexException;

/**
 * This is the association class which keeps track of the attempts based on the participation athlete.
 */
public class ParticipationAttempt {
    private final Participation athlete;
    private final Attempt attempt;
    private final int attemptIndex; // 1,2,3 are squats attempts in order; 4,5,6 for bench; 7,8,9 deadlift

    public ParticipationAttempt(Participation participation, Attempt attempt, int index)
            throws WrongAttemptIndexException {
        this.athlete = participation;
        this.attempt = attempt;
        checkAttemptIndex(index);
        this.attemptIndex = index;
    }

    /**
     * Checks the validity of the attempt index, which should be between 1-9.
     *
     * @param index the attempt index to be checked
     * @throws WrongAttemptIndexException if the attempt index falls out of 1-9
     */
    private void checkAttemptIndex(int index) throws WrongAttemptIndexException {
        if (index < 1 || index > 9) {
            throw new WrongAttemptIndexException(index);
        }
    }

    public Participation getParticipation() {
        return athlete;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public int getWeight() {
        return attempt.getWeightAttempted();
    }

    public int getAttemptIndex() {
        return attemptIndex;
    }
}
