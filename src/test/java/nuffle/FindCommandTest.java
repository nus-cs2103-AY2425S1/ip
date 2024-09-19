package nuffle;

import nuffle.command.FindCommand;
import nuffle.storage.Storage;
import nuffle.task.TaskList;
import nuffle.task.Todo;
import nuffle.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");
    }

    @Test
    void testFindCommandWithKeyword() {
        // Adding sample tasks
        tasks.addTask(new Todo("Buy milk"));
        tasks.addTask(new Todo("Submit assignment"));
        tasks.addTask(new Todo("Read book"));

        // Test FindCommand for keyword "Buy"
        FindCommand findCommand = new FindCommand("find Buy");
        String result = findCommand.execute(tasks, storage, ui);

        // Check if the correct task is returned
        assertTrue(result.contains("Buy milk"), "Should find 'Buy milk'");
        assertFalse(result.contains("Submit assignment"), "Should not find 'Submit assignment'");
    }

    @Test
    void testFindCommandNoMatchingTasks() {
        // Adding sample tasks
        tasks.addTask(new Todo("Buy milk"));
        tasks.addTask(new Todo("Submit assignment"));

        // Test FindCommand for keyword that does not exist
        FindCommand findCommand = new FindCommand("find Travel");
        String result = findCommand.execute(tasks, storage, ui);

        // Check that no tasks are found
        assertTrue(result.contains("Opps! Seems like there are no matching tasks."), "Should display 'Opps! Seems like there are no matching tasks.' message");
    }

    @Test
    void testFindCommandWithEmptyTaskList() {
        // Test FindCommand with an empty task list
        FindCommand findCommand = new FindCommand("find anything");
        String result = findCommand.execute(tasks, storage, ui);

        // Check that no tasks are found
        assertTrue(result.contains("Opps! Seems like there are no matching tasks."), "Should display 'No tasks found' when task list is empty");
    }
}
