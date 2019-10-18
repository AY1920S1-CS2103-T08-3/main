package seedu.system.logic.commands.outofsession;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.system.commons.core.Messages;
import seedu.system.commons.core.index.Index;
import seedu.system.logic.commands.Command;
import seedu.system.logic.commands.CommandResult;
import seedu.system.logic.commands.CommandType;
import seedu.system.logic.commands.exceptions.CommandException;
import seedu.system.logic.commands.exceptions.InSessionCommandException;
import seedu.system.logic.parser.exceptions.ParseException;
import seedu.system.model.Model;
import seedu.system.model.participation.Participation;

/**
 * Deletes participation from the address book.
 */
public class DeleteParticipationCommand extends Command {

    public static final String COMMAND_WORD = "deleteParticipation";
    public static final CommandType COMMAND_TYPE = CommandType.PARTICIPATION;
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the participation identified by the index number used in the displayed competition list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PARTICIPATION_SUCCESS = "Deleted Competition: %1$s";

    private final Index targetIndex;

    public DeleteParticipationCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        requireNonNull(model);
        List<Participation> lastShownList = model.getFilteredParticipationList();

        if (model.hasOngoingSession()) {
            throw new InSessionCommandException();
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PARTICIPATION_DISPLAYED_INDEX);
        }

        Participation partToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteParticipation(partToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PARTICIPATION_SUCCESS, partToDelete), COMMAND_TYPE);
    }
}