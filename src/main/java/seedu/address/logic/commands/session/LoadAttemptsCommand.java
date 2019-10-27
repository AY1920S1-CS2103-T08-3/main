package seedu.address.logic.commands.session;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_NO_ONGOING_COMPETITION_SESSION;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BENCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SQUAT;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Name;
import seedu.address.model.session.Session;
import seedu.address.model.session.exceptions.AttemptsSubmittedException;
import seedu.address.model.session.exceptions.NoOngoingSessionException;

/**
 * Loads attempts for the participant in this competition.
 */
public class LoadAttemptsCommand extends Command {

    public static final String COMMAND_WORD = "attempts";
    public static final String MESSAGE_PARTICIPANT_NOT_FOUND = " is not a participant for this competition session.";
    public static final String MESSAGE_SUCCESS = "Attempts loaded for ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_NAME + "Athlete_Name "
            + PREFIX_SQUAT + "S1/S2/S3 "
            + PREFIX_BENCH + "B1/B2/B3 "
            + PREFIX_DEADLIFT + "D1/D2/D3 ";

    private final Name participationName;
    private final List<Integer> nineAttempts;

    public LoadAttemptsCommand(Name lifterName, List<Integer> attemptWeights) {
        requireNonNull(lifterName);
        requireAllNonNull(attemptWeights);
        this.participationName = lifterName;
        this.nineAttempts = attemptWeights;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        Session session = model.getSession();

        if (!session.getIsOngoing()) {
            return new CommandResult(MESSAGE_NO_ONGOING_COMPETITION_SESSION);
        }

        Participation participation = session.getParticipationByName(participationName);
        Participation loadedParticipation = participation;

        if (loadedParticipation == null) {
            return new CommandResult(participationName + MESSAGE_PARTICIPANT_NOT_FOUND);
        }

        try {
            loadedParticipation.addAttempts(nineAttempts);
            model.setParticipation(participation, loadedParticipation);
            model.loadParticipationAttempts(participation, loadedParticipation.getAttempts());
        } catch (AttemptsSubmittedException e) {
            return new CommandResult(e.getMessage());
        } catch (NoOngoingSessionException e) {
            return new CommandResult(e.getMessage());
        }

        return new CommandResult(MESSAGE_SUCCESS + participationName);
    }
}
