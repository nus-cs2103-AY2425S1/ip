package matcha.parser;

import matcha.command.*;
import matcha.exception.MatchaException;
import matcha.task.Task;
import matcha.parser.Parser;
import matcha.task.Deadline;
import matcha.task.Event;
import matcha.task.Todo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ParserTest {

    @Test
    public void parseFileData_givenFileData_returnsCorrectTask() throws MatchaException {
        String data;
        Task actualTask;
        Task expectedTask;

        //for todo
        data = "T | 0 | read book";
        actualTask = Parser.parseFileData(data);
        expectedTask = new Todo("read book");
        assertEquals(expectedTask.toString(), actualTask.toString());

        //for deadline
        data = "D | 0 | return book | 2024-08-26T23:59";
        actualTask = Parser.parseFileData(data);
        expectedTask = new Deadline("return book", LocalDateTime.parse("2024-08-26 2359",
                Task.getInputFormat()));
        assertEquals(expectedTask.toString(), actualTask.toString());

        //for event
        data = "E | 0 | project meeting | 2024-08-26T23:59 | 2024-08-26T23:59";
        actualTask = Parser.parseFileData(data);
        expectedTask = new Event("project meeting", LocalDateTime.parse("2024-08-26 2359",
                Task.getInputFormat()), LocalDateTime.parse("2024-08-26 2359", Task.getInputFormat()));
        assertEquals(expectedTask.toString(), actualTask.toString());
    }

    @Test
    public void parseCommand_givenInput_returnsCorrectCommand() throws MatchaException {
        Command command;

        //for list command
        command = Parser.parseCommand("list");
        assertTrue(command instanceof ListTaskCommand);

        //for add todo command
        command = Parser.parseCommand("todo read book");
        assertTrue(command instanceof AddTaskCommand);

        //for add deadline command
        command = Parser.parseCommand("deadline return book /by 2024-08-26T23:59");
        assertTrue(command instanceof AddTaskCommand);

        //for add event command
        command = Parser.parseCommand("event project meeting /from 2024-08-26T23:59 /to 2024-08-26T23:59");
        assertTrue(command instanceof AddTaskCommand);

        //fro delete task command
        command = Parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteTaskCommand);

        //for marking task as done
        command = Parser.parseCommand("mark 1");
        assertTrue(command instanceof UpdateTaskCommand);

        //for unmarking task as done
        command = Parser.parseCommand("unmark 1");
        assertTrue(command instanceof UpdateTaskCommand);

        //for exiting the program
        command = Parser.parseCommand("bye");
        assertTrue(command instanceof ExitCommand);
    }
}
