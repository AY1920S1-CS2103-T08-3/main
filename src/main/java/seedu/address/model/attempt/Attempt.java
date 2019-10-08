package seedu.address.model.attempt;

import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Type;
import seedu.address.model.participation.Participation;

/**
 * Represents a {@link seedu.address.model.person.Person}'s attempt in an {@link Exercise}.
 * Guarantees: immutable;
 */
public class Attempt {
    // private final Exercise exercise;
    private final Type typeOfLift;
    private final Participation participation;
    private final int attemptNo;
    private final int weight; // weight of the athlete's attempt
    private boolean hasAttempted;
    private boolean isSuccess;

    public Attempt(Type exercise, Participation participation, int attemptNo, int weight) {
        // this.exercise = exercise;
        this.typeOfLift = exercise;
        this.participation = participation;
        this.attemptNo = attemptNo;
        this.weight = weight;
        this.hasAttempted = false;
    }

    /**
     * This method records the success of an athlete's attempt after his lift.
     * @param isSuccess true if the athlete succeeds his lift, false otherwise
     */
    public void lifted(boolean isSuccess) {
        this.hasAttempted = true;
        this.isSuccess = isSuccess;
    }
}
