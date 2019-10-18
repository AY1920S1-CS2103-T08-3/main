package seedu.address.model.session;

import java.util.Comparator;

/**
 * Compares two PartcipationAttempt objects based on their attempt index.
 */
public class ParticipationAttemptComparator implements Comparator {
    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     *
     * First, we compare the ParticipationAttempt objects by their index,
     * to group all the attempts with the same lift and attempt number together.
     * Then, we will sort the attempts with the same attempt index by their weights attempted,
     * in increasing order.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(Object o1, Object o2) {
        ParticipationAttempt pa1 = (ParticipationAttempt) o1;
        ParticipationAttempt pa2 = (ParticipationAttempt) o2;

        return pa2.getAttemptIndex() - pa1.getAttemptIndex();
    }
}
