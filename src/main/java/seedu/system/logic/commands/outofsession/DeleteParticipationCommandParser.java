package seedu.system.logic.commands.outofsession;

import seedu.system.commons.core.index.Index;
import seedu.system.logic.parser.Parser;
import seedu.system.logic.parser.ParserUtil;
import seedu.system.logic.parser.exceptions.ParseException;

import static seedu.system.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input and returns a DeleteParticipationCommand
 */
public class DeleteParticipationCommandParser implements Parser<DeleteParticipationCommand> {
    @Override
    public DeleteParticipationCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new DeleteParticipationCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteParticipationCommand.MESSAGE_USAGE), pe);
    }
    }
}
