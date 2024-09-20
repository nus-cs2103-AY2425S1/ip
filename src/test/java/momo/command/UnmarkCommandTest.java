package momo.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import momo.StorageStub;
import momo.exception.InvalidCommandException;
import momo.task.Deadline;
import momo.task.Event;
import momo.task.Task;
import momo.task.TasklistStub;
import momo.task.Todo;

public class UnmarkCommandTest {
    private TasklistStub tasks;
    private StorageStub storage;

    private Task sampleTodoTask;
    private Task sampleDeadlineTask;
    private Task sampleEventTask;

    @BeforeEach
    void setUp() {
        tasks = new TasklistStub("");
        storage = new StorageStub("");

        sampleTodoTask = new Todo("sample todo task", true);
        sampleDeadlineTask = new Deadline("sample deadline task", LocalDate.parse("2021-12-12"), true);
        sampleEventTask = new Event("sample event task", LocalDate.parse("2020-10-02"),
                LocalDate.parse("2020-11-15"), true);

        tasks.addTask(sampleTodoTask);
        tasks.addTask(sampleDeadlineTask);
        tasks.addTask(sampleEventTask);
    }

    @Test
    void testValidUnmarkCommand() throws Exception {
        UnmarkCommand.run("unmark 1", tasks, storage); // Marks "sample todo task" as incomplete
        assertEquals("[T][ ] sample todo task ", tasks.getTask(0).toString(), "Task should not"
                + " have an X in the completion bracket");

        UnmarkCommand.run("unmark 2", tasks, storage); // Marks "sample deadline task" as incomplete
        assertEquals("[D][ ] sample deadline task (Dec 12 2021)", tasks.getTask(1).toString(),
                "Task should not have an X in the completion bracket");

        UnmarkCommand.run("unmark 3", tasks, storage); // Marks "sample event task" as incomplete
        assertEquals("[E][ ] sample event task (Oct 2 2020 to Nov 15 2020)", tasks.getTask(2).toString(),
                "Task should not have an X in the completion bracket");
    }

    @Test
    void testInvalidUnmarkCommand() throws Exception {
        Exception noNumberException = assertThrows(InvalidCommandException.class, () ->
                UnmarkCommand.run("unmark qwsqd", tasks, storage));
        String expectedMessage = "Watch out mortal: You did not format your number properly...";
        assertTrue(noNumberException.getMessage().contains(expectedMessage));

        Exception numberNotInListException = assertThrows(InvalidCommandException.class, () ->
                UnmarkCommand.run("unmark 5", tasks, storage));
        expectedMessage = "You can only unmark a number your task list contains, mortal.";
        assertTrue(numberNotInListException.getMessage().contains(expectedMessage));
    }
}
