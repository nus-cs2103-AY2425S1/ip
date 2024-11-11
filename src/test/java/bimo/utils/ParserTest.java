package bimo.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bimo.command.Command;
import bimo.command.CommandType;
import bimo.command.FindCommand;
import bimo.command.MarkCommand;
import bimo.exception.BimoException;
import bimo.exception.InvalidDateFormatException;
import bimo.exception.InvalidTaskNumberException;
import bimo.exception.MissingDateException;
import bimo.exception.MissingDescriptionException;
import bimo.tasks.Deadline;
import bimo.tasks.Event;
import bimo.tasks.Task;

public class ParserTest {
    @Test
    public void getCommandType_invalidUserCommand_unknownCommand() {
        String userInput = "abcdeg 123";
        CommandType commandType = Parser.getCommandType(userInput);
        boolean isUnknown = commandType == CommandType.UNKNOWN;
        assertEquals(true, isUnknown);
    }
    @Test
    public void parse_findCommand_success() {
        String userInput = "find 1";
        Parser parser = new Parser();
        try {
            Command command = parser.parse(userInput);
            boolean isFind = command instanceof FindCommand;
            assertEquals(true, isFind);
        } catch (BimoException e) {
            assertEquals(false, true);
        }
    }
    @Test
    public void parse_markCommand_success() {
        String userInput = "mark 1";
        Parser parser = new Parser();
        try {
            Command command = parser.parse(userInput);
            boolean isFind = command instanceof MarkCommand;
            assertEquals(true, isFind);
        } catch (BimoException e) {
            assertEquals(false, true);
        }
    }
    @Test
    public void convertDateToLocalDate_invalidDateInput_throwInvalidDateFormatException() {
        String date = "22222";
        try {
            Parser.convertDateToLocalDate(date);
            assertEquals(false, true);
        } catch (InvalidDateFormatException e) {
            assertEquals("Unable to get date, please use yyyy-mm-dd as date format ",
                    e.getMessage());
        }
    }

    @Test
    public void parseDate_emptyDateInput_throwMissingDateException() {
        String userInput = "deadline abcd";
        String[] array = userInput.split("/by");
        try {
            Parser.parseDate(true, false, array);
            assertEquals(false, true);
        } catch (MissingDateException e) {
            assertEquals("Please provide a date using /by yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void parseDescription_emptyDescriptionInput_throwMissingDescriptionException() {
        String userInput = "event";
        try {
            Parser.parseDescription(userInput);
            assertEquals(false, true);
        } catch (MissingDescriptionException e) {
            assertEquals("Please provide a description for you task.",
                    e.getMessage());
        }
    }

    @Test
    public void parseIndex_emptyNumberInput_throwsInvalidTaskNumberException() {
        String userInput = "mark";
        try {
            Parser.parseIndex(userInput);
            assertEquals(false, true);
        } catch (InvalidTaskNumberException e) {
            assertEquals("Input must include an integer that represents task number.",
                    e.getMessage());
        }
    }

    @Test
    public void parsePriority_invalidPriorityInput_throwsBimoException() {
        String userInput = "set 2 random";
        try {
            Parser.parsePriority(userInput);
            assertEquals(false, true);
        } catch (BimoException e) {
            assertEquals("Please choose either HIGH, MEDIUM or"
                    + " LOW priority only\n e.g set 2 high", e.getMessage());
        }
    }

    @Test
    public void parsePriority_emptyPriorityInput_throwsBimoException() {
        String userInput = "set 2";
        try {
            Parser.parsePriority(userInput);
            assertEquals(false, true);
        } catch (BimoException e) {
            assertEquals("Missing priority", e.getMessage());
        }
    }

    @Test
    public void parsePriority_emptyNumberInput_throwsBimoException() {
        String userInput = "set High";
        try {
            Parser.parsePriority(userInput);
            assertEquals(false, true);
        } catch (BimoException e) {
            assertEquals("Missing task number", e.getMessage());
        }
    }

    @Test
    public void testCreateEventTask() {
        String userInput = "event Go to work /from 2022-12-19 /to 2024-06-18";
        try {
            Task task = Parser.createEventTask(userInput);
            assertEquals(true, task instanceof Event);
        } catch (BimoException e) {
            assertEquals(false, true);
        }
    }
    @Test
    public void testCreateDeadlineTask() {
        String userInput = "deadline Go to work /by 2022-12-19";
        try {
            Task task = Parser.createDeadlineTask(userInput);
            assertEquals(true, task instanceof Deadline);
        } catch (BimoException e) {
            assertEquals(false, true);
        }
    }
}
