package rex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rex.exception.InvalidInputException;
import rex.util.Storage;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() throws IOException {
        taskList = new TaskList(new Storage());
    }
    @AfterEach
    public void deleteFile() throws IOException {
        Files.deleteIfExists(Paths.get("./data/rex.txt"));
    }

    @Test
    public void testEmptyList() throws IOException {
        assertEquals("The list is empty! rawr\n", taskList.getListDisplay());
    }

    @Test
    public void testNonEmptyList() throws IOException {
        taskList.addToDo("do this");
        taskList.addToDo("do that");
        taskList.addToDo("do something");
        assertEquals("1.[T][ ] do this\n2.[T][ ] do that\n3.[T][ ] do something\n", taskList.getListDisplay());
        assertEquals(3, taskList.size());
    }

    @Test
    void testAddToDo() throws IOException {
        ToDo toDo = taskList.addToDo("Read a book");
        assertEquals(1, taskList.size());
        assertEquals(toDo, taskList.getTask(1));
    }

    @Test
    void testAddDeadline() throws IOException, InvalidInputException {
        Deadline deadline = taskList.addDeadline("submit assignment /by 01-09-24 1200");
        assertEquals(1, taskList.size(), "Task list size should be 1 after adding a Deadline");
        assertEquals(deadline, taskList.getTask(1), "The task added should be the one retrieved");
    }

    @Test
    void testAddEvent() throws IOException, InvalidInputException {
        Event event = taskList.addEvent("meeting /from 02-12-24 1200 /to 02-12-24 1400");
        assertEquals(1, taskList.size(), "Task list size should be 1 after adding an Event");
        assertEquals(event, taskList.getTask(1), "The task added should be the one retrieved");
    }
}
