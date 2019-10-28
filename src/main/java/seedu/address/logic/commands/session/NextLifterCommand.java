package seedu.address.logic.commands.session;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.session.ParticipationAttempt;
import seedu.address.model.session.exceptions.CompetitionEndedException;
import seedu.address.model.session.exceptions.IncompleteAttemptSubmissionException;
import seedu.address.model.session.exceptions.NoOngoingSessionException;
import seedu.address.model.session.exceptions.PreviousAttemptNotDoneException;

/**
 * Gets the next lifter in line to make his/her attempt.
 */
public class NextLifterCommand extends Command {

    public static final String COMMAND_WORD = "next";
    public static final String MESSAGE_SUCCESS = "The next lift is ";

    /**
     * Executes the NextLifterCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        ParticipationAttempt next = null;
        try {
            next = model.getNextLifter();
            model.setNextAttemptForSession(next);
        } catch (NoOngoingSessionException | IncompleteAttemptSubmissionException
                | PreviousAttemptNotDoneException e) {
            return new CommandResult(e.getMessage());
        } catch (CompetitionEndedException e) {
            model.endSession();
            return new CommandResult(e.getMessage());
        }
        return new CommandResult(MESSAGE_SUCCESS + next.getParticipation().getName()
                + "'s " + next.toString());
    }
}
