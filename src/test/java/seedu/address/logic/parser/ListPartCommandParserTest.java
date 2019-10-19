package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListPartCommand;
import seedu.address.model.person.Name;

public class ListPartCommandParserTest {

    private ListPartCommandParser parser = new ListPartCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListPartCommand.MESSAGE_USAGE));
    }

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void parse_validArgs_returnsListPartCommand() {
        ListPartCommand expectedListPartCommand = new ListPartCommand(new Name("Test of Strength 2019"));
        String userInput = "Test of Strength 2019";
        assertParseSuccess(parser, userInput, expectedListPartCommand);
    }
}
