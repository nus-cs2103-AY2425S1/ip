package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.FilterTaskCommand;
import duke.commands.ListTaskCommand;
import duke.commands.MarkTaskCommand;

public class ParserTest {

    @Test
    public void testParseUserInput_listCommand() {
        Command command = Parser.parseUserInput("list");
        assertInstanceOf(ListTaskCommand.class, command, "Command should be of type ListTaskCommand");
    }

    @Test
    public void testParseUserInput_markCommand() {
        Command command = Parser.parseUserInput("mark 1");
        assertInstanceOf(MarkTaskCommand.class, command, "Command should be of type MarkTaskCommand");
    }

    @Test
    public void testParseUserInput_deleteCommand() {
        Command command = Parser.parseUserInput("delete 2");
        assertInstanceOf(DeleteTaskCommand.class, command, "Command should be of type DeleteTaskCommand");
    }

    @Test
    public void testParseUserInput_addTaskCommand() {
        Command command = Parser.parseUserInput("todo read a book");
        assertInstanceOf(AddTaskCommand.class, command, "Command should be of type AddTaskCommand");
    }

    @Test
    public void testParseUserInput_filterTaskCommand() {
        Command command = Parser.parseUserInput("filter 2/12/2019 1800");
        assertInstanceOf(FilterTaskCommand.class, command, "Command should be of type FilterTaskCommand");
    }

    @Test
    public void testParseTaskIndex_validIndex() {
        int index = Parser.parseTaskIndex("delete 2");
        assertEquals(1, index, "Task index should be 1");
    }

    @Test
    public void testParseTaskIndex_invalidIndex() {
        int index = Parser.parseTaskIndex("delete invalid");
        assertEquals(-1, index, "Task index should be -1 for invalid input");
    }

    @Test
    public void testParseDateTime_validDateTime() {
        LocalDateTime expected = LocalDateTime.of(2019, 12, 2, 18, 0);
        LocalDateTime actual = Parser.parseDateTime("2/12/2019 1800");
        assertEquals(expected, actual, "Parsed date time should be 2/12/2019 1800");
    }

    @Test
    public void testParseDateTime_invalidDateTime() {
        LocalDateTime result = Parser.parseDateTime("invalid date");
        assertNull(result, "Parsed date time should be null for invalid input");
    }

    @Test
    public void testParseDateTime_validDate() {
        LocalDateTime expected = LocalDateTime.of(2019, 12, 2, 0, 0);
        LocalDateTime actual = Parser.parseDateTime("2/12/2019");
        assertEquals(expected, actual, "Parsed date should be 2/12/2019 at start of day");
    }
}
