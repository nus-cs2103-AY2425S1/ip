package derek;

import static org.junit.jupiter.api.Assertions.*;

import derek.command.DeleteCommand;
import derek.task.ToDoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import derek.exception.IncorrectCommandException;
import derek.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommandTest {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    void setUp() throws IOException {
        taskList = new TaskList();
        storage = new Storage(taskList);
        ui = new Ui(storage, taskList);

        // Add tasks to task list for testing
        taskList.add(new ToDoTask("Task 1"));
        taskList.add(new ToDoTask("Task 2"));
        taskList.add(new ToDoTask("Task 3"));
    }

    @Test
    void testDeleteCommand_validTask() throws IncorrectCommandException {
        // Create DeleteCommand to delete Task 2
        DeleteCommand deleteCommand = new DeleteCommand("delete 2", storage, ui);

        // Execute the delete command and capture output
        String result = deleteCommand.execute();

        // Verify that Task 2 has been removed and the result message is correct
        assertEquals(2, taskList.size());
        assertTrue(result.contains("phew! that list was looooonngggg... i was getting tired of remembering it!"));
        assertFalse(taskList.get(0).getName().equals("Task 2"));
        assertFalse(taskList.get(1).getName().equals("Task 2"));
    }

    @Test
    void testDeleteCommand_invalidTaskNumber_throwsIncorrectCommandException() {
        // Attempt to delete a task with an invalid task number (out of bounds)
        DeleteCommand deleteCommand = new DeleteCommand("delete 5", storage, ui);

        // Expect IncorrectCommandException when task number is out of range
        IncorrectCommandException exception = assertThrows(IncorrectCommandException.class, deleteCommand::execute);
        assertEquals("do you not know how to count??", exception.getMessage());
    }

    @Test
    void testDeleteCommand_negativeTaskNumber_throwsIncorrectCommandException() {
        // Attempt to delete a task with a negative task number
        DeleteCommand deleteCommand = new DeleteCommand("delete -1", storage, ui);

        // Expect IncorrectCommandException when task number is negative
        IncorrectCommandException exception = assertThrows(IncorrectCommandException.class, deleteCommand::execute);
        assertEquals("do you not know how to count??", exception.getMessage());
    }
}
