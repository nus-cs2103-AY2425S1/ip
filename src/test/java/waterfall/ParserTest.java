package waterfall;

import org.junit.jupiter.api.Test;
import waterfall.command.*;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void byeInput_returnExitCommand() throws WaterfallException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void listInput_returnShowTaskCommand() throws WaterfallException {
        assertInstanceOf(ShowTasksCommand.class, Parser.parse("list"));
    }

    @Test
    public void unknownInput_returnUnrecognisedCommand() throws WaterfallException {
        assertInstanceOf(UnrecognisedCommand.class, Parser.parse("abc"));
    }

    @Test
    public void markInput_returnMarkCommand() throws WaterfallException {
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
    }

    @Test
    public void unmarkInput_returnUnmarkCommand() throws WaterfallException {
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"));
    }

    @Test
    public void deleteInput_returnDeleteCommand() throws WaterfallException {
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 1"));
    }

    @Test
    public void todoInput_returnAddCommand() throws WaterfallException {
        assertInstanceOf(AddCommand.class, Parser.parse("todo test"));
    }

    @Test
    public void deadlineInput_returnAddCommand() throws WaterfallException {
        assertInstanceOf(AddCommand.class, Parser.parse("deadline test /by 2024-08-08 1600"));
    }

    @Test
    public void eventInput_returnAddCommand() throws WaterfallException {
        assertInstanceOf(AddCommand.class, Parser.parse("event test /from 2024-08-08 1600 /to 2024-08-09 0400"));
    }

    @Test
    void todoCommandEmptyTitle_throwsException() {
        assertThrows(WaterfallException.class, () -> Parser.parse("todo "));
    }

    @Test
    void eventCommandEmptyTitle_throwsException() {
        assertThrows(WaterfallException.class, () -> Parser.parse("event  /from 2024-08-08 1600 /to 2024-08-09 0400"));
    }

    @Test
    void deadlineCommandEmptyTitle_throwsException() {
        assertThrows(WaterfallException.class, () -> Parser.parse("deadline /by 2024-08-08 1600"));
    }
}
