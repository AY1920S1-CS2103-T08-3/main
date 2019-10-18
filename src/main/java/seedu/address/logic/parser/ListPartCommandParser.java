package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListPartCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses argument input and creates new ListPartCommand.
 */
public class ListPartCommandParser implements Parser<ListPartCommand> {
    /**
     * Parses the given String {@code userInput} into a ListPartCommand and returns it for execution.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ListPartCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListPartCommand.MESSAGE_USAGE));
        }

        Name compName = ParserUtil.parseName(trimmedArgs);

        return new ListPartCommand(compName);
    }
}
