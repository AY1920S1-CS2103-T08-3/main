package seedu.address.model.attempt;

import seedu.address.model.attempt.exceptions.AttemptHasBeenAttemptedException;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Lift;

/**
 * Represents a {@link seedu.address.model.person.Person}'s attempt in an {@link Exercise}.
 * Guarantees: immutable;
 */
public class Attempt {
    private final Lift lift;
    private final int weight;

    private boolean hasAttempted;
    private boolean isSuccessful;

    public Attempt(Lift lift, int weight) {
        this.lift = lift;
        this.weight = weight;
        this.hasAttempted = false;
    }

    /**
     * This method records the success of an athlete's attempt after his lift.
     * @param isSuccessful true if the athlete succeeds his lift, false otherwise
     */
    public void setSuccess(boolean isSuccessful) throws AttemptHasBeenAttemptedException {
        if (hasAttempted) {
            throw new AttemptHasBeenAttemptedException();
        }

        this.isSuccessful = isSuccessful;
        this.hasAttempted = true;
    }

    public int getWeightAttempted() {
        return weight;
    }
}
