package ava.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ava.task.TaskManager;



class ParserTest {

    private TaskManager taskManager;
    @BeforeEach
    void setUp() {
        // sets a new TaskManager before each test to
        // ensure independence
        taskManager = new TaskManager();
    }

    @AfterEach
    void tearDown() {
        // resets the taskManager
        taskManager = null;
    }

    @Test
    void testParseCommand() {
        assertEquals(Command.LIST, Parser.parseCommand("list"));
        assertEquals(Command.MARK, Parser.parseCommand("mark 1"));
        assertEquals(Command.UNMARK, Parser.parseCommand("unmark 1"));
        assertEquals(Command.DELETE, Parser.parseCommand("delete 1"));
        assertEquals(Command.TODO, Parser.parseCommand("todo read book"));
        assertEquals(Command.DEADLINE, Parser.parseCommand("deadline submit report /by Sunday"));
        assertEquals(Command.EVENT, Parser.parseCommand("event team meeting /at 2pm"));
        assertEquals(Command.FIND, Parser.parseCommand("find book"));

        // Test for unsupported command
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseCommand("unsupported command");
        });
        assertEquals("Unsupported Command", exception.getMessage());
    }

    @Test
    void testParseToDo() {
        String command = "todo read book";
        String expected = "read book";
        String result = Parser.parseToDo(command, taskManager);
        assertEquals(expected, result);
    }

    @Test
    void testParseDeadline() {
        // checks for valid date
        String command = "deadline submit report /by 2023-10-05T14:30:00";
        String expected = "submit report by 2023-10-05T14:30:00";
        String result = Parser.parseDeadline(command, taskManager);
        assertEquals(expected, result);
    }

    @Test
    void testParseEvent() {
        // checks for valid date
        String command = "event team meeting /from 2023-10-05T14:30:00 /to 2023-10-05T15:30:00";
        String expected = "team meeting from 2023-10-05T14:30:00 to 2023-10-05T15:30:00";
        String result = Parser.parseEvent(command, taskManager);
        assertEquals(expected, result);
    }
}
