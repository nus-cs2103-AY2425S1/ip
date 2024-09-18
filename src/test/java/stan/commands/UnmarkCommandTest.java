package stan.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stan.MockTask;
import stan.Storage;
import stan.TaskList;
import stan.exceptions.StanInvalidArgumentException;
import stan.exceptions.StanMissingArgumentException;
import stan.ui.Ui;

class UnmarkCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("data/stan_test.txt"); // You can mock storage here if needed
    }

    @Test
    void testUnmarkTaskSuccessfully() throws StanInvalidArgumentException, StanMissingArgumentException {
        // Add a task, mark it as done, and then unmark it
        MockTask task = new MockTask("Task to be unmarked");
        task.markAsDone(); // Mark it as done so we can unmark it
        taskList.add(task);

        UnmarkCommand unmarkCommand = new UnmarkCommand(new String[]{"unmark", "1"});
        String response = unmarkCommand.execute(taskList, ui, storage);

        assertEquals("🔄 Oops! I've marked this task as not done yet. 🔄\n"
                + "   [ ] Task to be unmarked\nYou have 1 tasks in the list. Let's get it done! 💼", response);
    }

    @Test
    void testUnmarkTaskAlreadyNotDone() throws StanInvalidArgumentException, StanMissingArgumentException {
        // Add a task that is already not done (unmarked)
        MockTask task = new MockTask("Task already unmarked");
        taskList.add(task); // Leave it unmarked by default

        UnmarkCommand unmarkCommand = new UnmarkCommand(new String[]{"unmark", "1"});
        String response = unmarkCommand.execute(taskList, ui, storage);

        assertEquals("OOPS!!!\nYou have not completed this task yet, so it is already unmarked! 💼\n"
                + "You may want to start working on it now.\n"
                + "ALL THE BEST!!!", response);
    }

    @Test
    void testUnmarkTaskInvalidIndex() throws StanMissingArgumentException, StanInvalidArgumentException {
        // Add a task and try to unmark a task at an invalid index
        taskList.add(new MockTask("Task 1"));
        UnmarkCommand unmarkCommand = new UnmarkCommand(new String[]{"unmark", "2"});
        assertThrows(StanInvalidArgumentException.class, () -> {
            unmarkCommand.execute(taskList, ui, storage);
        });
    }
}
