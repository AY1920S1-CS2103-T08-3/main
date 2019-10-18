package seedu.address.model.participation;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.UniqueElement;
import seedu.address.model.attempt.Attempt;
import seedu.address.model.competition.Competition;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Lift;
import seedu.address.model.person.Person;

/**
 * Represents a {@link Person}'s participation in a {@link Competition}.
 * Guarantees: immutable; person-competition pair is unique.
 */
public class Participation extends UniqueElement {
    private final Person person;
    private final Competition competition;

    private List<Attempt> attempts;

    public Participation(Person person, Competition competition) {
        this.person = person;
        this.competition = competition;
        this.attempts = new ArrayList<>(9);
    }

    public Participation(Person person, Competition competition, List<Attempt> attempts) {
        this.person = person;
        this.competition = competition;
        this.attempts = attempts;
    }

    /**
     * This method adds all the weight to be attempted for this participation.
     *
     * @param weightOfAttemptsList a list of the weight to be attempted for eaCh lift and attempt
     * @return list of attempts to track the athlete progress throughout the competition
     */
    public List<Attempt> addAttempts(List<Integer> weightOfAttemptsList) {
        List<Exercise> exerciseList = competition.getExerciseList();
        List<Attempt> attempts = new ArrayList<>();
        int index = 0;
        for (Exercise exercise : exerciseList) {
            Lift lift = exercise.getLift();
            int noOfAttempts = exercise.getNoOfAttempts();
            for (int i = 0; i < noOfAttempts && index < 9; i++) {
                attempts.add(new Attempt(lift, weightOfAttemptsList.get(index)));
                index++;
            }
        }
        return attempts;
    }

    /**
     * Updates the success of the attempt after the lift.
     *
     * @param index attempt index which relates to the lift and attempt
     * @param isSuccess a boolean indicating the success of the attempt
     */
    public void updateAttempt(int index, boolean isSuccess) {
        final boolean hasAttempted = true;
        Attempt attempt = attempts.get(index - 1);
        Attempt updatedAttempt = new Attempt(attempt.getLift(), attempt.getWeightAttempted(),
                hasAttempted, isSuccess);
        attempts.set(index - 1, updatedAttempt);
    }

    public Person getPerson() {
        return person;
    }

    public Competition getCompetition() {
        return competition;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    /**
     * Returns true if both exercises have the same identity and data fields.
     */
    public boolean isSameElement(UniqueElement otherElement) {

        if (!(otherElement instanceof Participation)) {
            return false;
        }

        return this.equals((Participation) otherElement);
    }

    /**
     * @return the total score of the person's participation at a specified competition
     */
    public int getTotalScore() {
        int score = 0;
        for (Exercise exercise : competition.getExerciseList()) {
            score += getLiftScore(exercise.getLift());
        }
        return score;
    }

    /**
     * @return the total score of the person's participation at a specified competition for a specified lift
     */
    public int getLiftScore(Lift lift) {
        int score = 0;
        for (Attempt attempt : attempts) {
            if (attempt.getLift() == lift && attempt.getIsSuccessful()) {
                score = Math.max(score, attempt.getWeightAttempted());
            }
        }
        return score;
    }

    /**
     * Gets the highest score of each of the three lifts in this format: Squat/Bench/Deadlift.
     * @return a string representation of the three lift score
     */
    public String getThreeLiftScore() {
        StringBuilder topAttemptsString = new StringBuilder();
        for (Exercise exercise : competition.getExerciseList()) {
            Lift lift = exercise.getLift();
            topAttemptsString.append("/").append(getLiftScore(lift));
        }
        String outputAttempts = topAttemptsString.toString();
        return outputAttempts.substring(1);
    }

    /**
     * Returns true if both participations have the same identity and data fields.
     * This defines a stronger notion of equality between two participations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Participation)) {
            return false;
        }

        Participation otherParticipation = (Participation) other;
        return otherParticipation.getPerson().equals(getPerson())
            && otherParticipation.getCompetition().equals(getCompetition());
    }
}
