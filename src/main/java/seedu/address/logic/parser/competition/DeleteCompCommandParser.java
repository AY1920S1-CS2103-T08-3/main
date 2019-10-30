package seedu.address.logic.parser.competition;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.competition.DeleteCompCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCompCommand object
 */
public class DeleteCompCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCompCommand
     * and returns a DeleteCompCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCompCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCompCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCompCommand.MESSAGE_USAGE), pe);
        }
    }

}
