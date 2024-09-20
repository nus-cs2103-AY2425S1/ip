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

public class MarkCommandTest {
    private TasklistStub tasks;
    private StorageStub storage;

    private Task sampleTodoTask;
    private Task sampleDeadlineTask;
    private Task sampleEventTask;

    @BeforeEach
    void setUp() {
        tasks = new TasklistStub("");
        storage = new StorageStub("");

        sampleTodoTask = new Todo("sample todo task", false);
        sampleDeadlineTask = new Deadline("sample deadline task", LocalDate.parse("2021-12-12"), false);
        sampleEventTask = new Event("sample event task", LocalDate.parse("2020-10-02"),
                LocalDate.parse("2020-11-15"), false);

        tasks.addTask(sampleTodoTask);
        tasks.addTask(sampleDeadlineTask);
        tasks.addTask(sampleEventTask);
    }

    @Test
    void testValidMarkCommand() throws Exception {
        MarkCommand.run("mark 1", tasks, storage); // Marks "sample todo task" as complete
        assertEquals("[T][X] sample todo task ", tasks.getTask(0).toString(), "Task should have "
                + "an X in the completion bracket");

        MarkCommand.run("mark 2", tasks, storage); // Marks "sample deadline task" as complete
        assertEquals("[D][X] sample deadline task (Dec 12 2021)", tasks.getTask(1).toString(), "Task should have "
                + "an X in the completion bracket");

        MarkCommand.run("mark 3", tasks, storage); // Marks "sample event task" as complete
        assertEquals("[E][X] sample event task (Oct 2 2020 to Nov 15 2020)", tasks.getTask(2).toString(), "Task "
                + "should have an X in the completion bracket");
    }

    @Test
    void testInvalidMarkCommand() throws Exception {
        Exception noNumberException = assertThrows(InvalidCommandException.class, () ->
                MarkCommand.run("mark qwsqd", tasks, storage));
        String expectedMessage = "Watch out: You did format not your number properly... don't make me "
                + "warn you again (ʘ  ʘ)";
        assertTrue(noNumberException.getMessage().contains(expectedMessage));

        Exception numberNotInListException = assertThrows(InvalidCommandException.class, () ->
                MarkCommand.run("mark 4", tasks, storage));
        expectedMessage = "You can only mark a number your task list contains, mortal.";
        assertTrue(numberNotInListException.getMessage().contains(expectedMessage));
    }
}
