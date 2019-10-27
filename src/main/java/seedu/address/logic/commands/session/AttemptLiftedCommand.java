package seedu.address.logic.commands.session;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.attempt.exceptions.AttemptHasBeenAttemptedException;
import seedu.address.model.participation.Participation;
import seedu.address.model.session.ParticipationAttempt;
import seedu.address.model.session.exceptions.IncompleteAttemptSubmissionException;
import seedu.address.model.session.exceptions.NoOngoingSessionException;

/**
 *
 */
public class AttemptLiftedCommand extends Command {

    public static final String COMMAND_WORD = "lift";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " Y/N (where 'Y' represents success and 'N' represents failure)";

    private final boolean isSuccess;

    public AttemptLiftedCommand(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * Executes AttemptLiftedCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ParticipationAttempt partAttempt;
        Participation participation;

        try {
            partAttempt = model.makeAttempt();
            participation = partAttempt.getParticipation();
            Participation updatedPart = participation;
            updatedPart.updateAttempt(partAttempt.getAttemptIndex(), isSuccess);
            model.setParticipation(participation, updatedPart);
        } catch (NoOngoingSessionException | AttemptHasBeenAttemptedException
                | IncompleteAttemptSubmissionException e) {
            return new CommandResult(e.getMessage());
        }

        return new CommandResult(participation.getName() + "'s "
                + partAttempt.toString() + getSuccessOrFailure());
    }

    private String getSuccessOrFailure() {
        if (isSuccess) {
            return " is a success.";
        } else {
            return " is a failure.";
        }
    }
}
