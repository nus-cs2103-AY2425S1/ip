package taskon.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static taskon.common.Messages.MESSAGE_EMPTY_FIND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.task.Todo;
import taskon.ui.Ui;

public class FindCommandTest {

    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();

        // Adding test tasks
        taskList.addTask(new Todo("test todo 1"));
        taskList.addTask(new Todo("Another test todo"));
        taskList.addTask(new Todo("Yet another todo"));
    }

    @Test
    public void testFindCommand() {
        FindCommand command = new FindCommand("test");
        String result = command.execute(taskList, ui, new Storage("dummyPath"));

        String expected = "Here's what we've got on your to-do list:\n"
                + "1. [T][ ] test todo 1\n"
                + "2. [T][ ] Another test todo\n";

        assertEquals(expected, result);
    }

    @Test
    public void testFindCommandNoResults() {
        FindCommand command = new FindCommand("nonexistent");
        String result = command.execute(taskList, ui, new Storage("dummyPath"));

        assertEquals(MESSAGE_EMPTY_FIND, result);
    }
}
