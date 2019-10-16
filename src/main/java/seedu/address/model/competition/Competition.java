package seedu.address.model.competition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import seedu.address.model.UniqueElement;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Lift;
import seedu.address.model.person.Name;

/**
 * Represents a Competition in the app.
 */
public class Competition extends UniqueElement {

    private static final Exercise squat = new Exercise(Lift.Squat);
    private static final Exercise bench = new Exercise(Lift.Bench);
    private static final Exercise deadlift = new Exercise(Lift.Deadlift);

    private final Name name;
    private final Date startDate;
    private final Date endDate;
    private final List<Exercise> exerciseList;

    public Competition(Name name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.exerciseList = new ArrayList<>();
        this.exerciseList.add(squat);
        this.exerciseList.add(bench);
        this.exerciseList.add(deadlift);
    }

    public Name getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    /**
     * Returns true if both exercises have the same identity and data fields.
     */
    public boolean isSameElement(UniqueElement otherElement) {

        if (!(otherElement instanceof Competition)) {
            return false;
        }

        return this.equals((Competition) otherElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Competition)) {
            return false;
        }

        Competition otherPerson = (Competition) other;
        return otherPerson.getName().equals(getName());
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
