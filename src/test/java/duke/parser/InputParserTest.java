package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.FilterTaskCommand;
import duke.commands.ListTaskCommand;
import duke.commands.MarkTaskCommand;
import duke.exceptions.InvalidInputException;

public class InputParserTest {

    @Test
    public void parseUserInput_listCommand_returnsListTaskCommand() throws InvalidInputException {
        Command command = InputParser.parseUserInput("list");
        assertInstanceOf(ListTaskCommand.class, command, "Command should be of type ListTaskCommand");
    }

    @Test
    public void parseUserInput_markCommand_returnsMarkTaskCommand() throws InvalidInputException {
        Command command = InputParser.parseUserInput("mark 1");
        assertInstanceOf(MarkTaskCommand.class, command, "Command should be of type MarkTaskCommand");
    }

    @Test
    public void parseUserInput_deleteCommand_returnsDeleteTaskCommand() throws InvalidInputException {
        Command command = InputParser.parseUserInput("delete 2");
        assertInstanceOf(DeleteTaskCommand.class, command, "Command should be of type DeleteTaskCommand");
    }

    @Test
    public void parseUserInput_addTaskCommand_returnsAddTaskCommand() throws InvalidInputException {
        Command command = InputParser.parseUserInput("todo read a book");
        assertInstanceOf(AddTaskCommand.class, command, "Command should be of type AddTaskCommand");
    }

    @Test
    public void parseUserInput_filterCommand_returnsFilterTaskCommand() throws InvalidInputException {
        Command command = InputParser.parseUserInput("filter 2/12/2019 1800");
        assertInstanceOf(FilterTaskCommand.class, command, "Command should be of type FilterTaskCommand");
    }

    @Test
    public void parseTaskIndex_validIndex_returnsCorrectIndex() throws InvalidInputException {
        int index = InputParser.parseTaskIndex("delete 2");
        assertEquals(1, index, "Task index should be 1");
    }

    @Test
    public void parseTaskIndex_invalidIndex_throwsInvalidInputException() {
        // Test that the InvalidInputException is thrown when the task index is invalid
        assertThrows(InvalidInputException.class, () -> {
            InputParser.parseTaskIndex("delete invalid");
        }, "Expected InvalidInputException for invalid task index input");
    }

    @Test
    public void parseDateTime_validDateTime_returnsCorrectLocalDateTime() throws InvalidInputException {
        LocalDateTime expected = LocalDateTime.of(2019, 12, 2, 18, 0);
        LocalDateTime actual = DateTimeFormatEnum.parse("2/12/2019 1800")
                .orElseThrow(() -> new InvalidInputException("Invalid date format."));
        assertEquals(expected, actual, "Parsed date time should be 2/12/2019 1800");
    }

    @Test
    public void parseDateTime_invalidDateTime_returnsEmptyOptional() {
        // Ensure that the parser returns an empty Optional for invalid date input
        assertTrue(DateTimeFormatEnum.parse("invalid date").isEmpty(),
                "Parsed date time should be empty for invalid input");
    }

    @Test
    public void parseDateTime_validDate_returnsLocalDateTimeAtMidnight() throws InvalidInputException {
        LocalDateTime expected = LocalDateTime.of(2019, 12, 2, 0, 0);
        LocalDateTime actual = DateTimeFormatEnum.parse("2/12/2019")
                .orElseThrow(() -> new InvalidInputException("Invalid date format."));
        assertEquals(expected, actual, "Parsed date should be 2/12/2019 at start of day");
    }
}
