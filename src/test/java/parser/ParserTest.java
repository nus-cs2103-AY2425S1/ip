package parser;

import mollyexception.MollyException;
import storage.Storage;
import tasklist.TaskList;
import task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class ParserTest {

    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        storage = new Storage("./data/Molly.txt");
        taskList = new TaskList(new ArrayList<>());
        parser = new Parser(storage, taskList);
    }
    @Test
    public void testHandleUserInput_markTask() throws MollyException {

        Task task = new Task("sample task");
        taskList.addTask(task);

        parser.handleUserInput("mark 1");

        assertTrue(taskList.getBotMemory().get(0).taskIsDone);
    }

    @Test
    public void testHandleUserInput_unmarkTask() throws MollyException {

        Task task = new Task("sample task");
        task.markDone();
        taskList.addTask(task);


        parser.handleUserInput("unmark 1");


        assertFalse(taskList.getBotMemory().get(0).taskIsDone);
    }

    @Test
    public void testHandleUserInput_invalidMarkCommand() throws MollyException {
        MollyException exception = assertThrows(MollyException.class, () -> {
            parser.handleUserInput("mark invalid");
        });
        assertEquals("Invalid command. Please enter a valid task number to mark.", exception.getMessage());
    }

    @Test
    public void testHandleUserInput_addTodoTask() throws MollyException {

        parser.handleUserInput("todo write JUnit tests");

        assertEquals(1, taskList.getBotMemory().size());
        assertEquals("write JUnit tests", taskList.getBotMemory().get(0).description);
    }

    @Test
    public void testHandleUserInput_emptyTodoDescription() throws MollyException {
        MollyException exception = assertThrows(MollyException.class, () -> {
            parser.handleUserInput("todo");
        });
        assertEquals("Yikes! Sorry, the todo description cannot be empty.", exception.getMessage());
    }

    @Test
    public void testHandleUserInput_addDeadlineTask() throws MollyException {

        parser.handleUserInput("deadline submit assignment /by 30-08-2024 2359");

        assertEquals(1, taskList.getBotMemory().size());
        assertTrue(taskList.getBotMemory().get(0).toString().contains("submit assignment"));
    }

    @Test
    public void testHandleUserInput_invalidDeadlineFormat() throws MollyException {
        MollyException exception = assertThrows(MollyException.class, () -> {
            parser.handleUserInput("deadline invalid format");
        });
        assertEquals("Sorry, invalid format for deadline. Please follow the format: deadline (description) /by (end date). The end date can be in the format DD-MM-YYYY HHmm (24 hour format).", exception.getMessage());
    }

    @Test
    public void testHandleUserInput_addEventTask() throws MollyException {
        parser.handleUserInput("event project meeting /from 3pm /to 4pm");

        assertEquals(1, taskList.getBotMemory().size());
        assertTrue(taskList.getBotMemory().get(0).toString().contains("project meeting"));
    }

    @Test
    public void testHandleUserInput_invalidEventFormat() {
        MollyException exception = assertThrows(MollyException.class, () -> {
            parser.handleUserInput("event attend dinner");
        });
        assertEquals("Sorry, invalid format for event. Please follow the format: event (description) /from (start date or time) /to (end date or time). The start and end date/times can be in the format DD-MM-YYYY HHmm (24 hour format)", exception.getMessage());
    }

    @Test
    public void testHandleUserInput_listCommand() throws MollyException {
        Task task = new Task("sample task");
        taskList.addTask(task);

        String expectedOutput = "Here are the tasks in your list: \n1. [T][ ] sample task\n";
        String actualOutput = taskList.listToString();

        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
