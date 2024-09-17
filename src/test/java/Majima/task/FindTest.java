package Majima.command;

import Majima.MajimaException;
import Majima.storage.Storage;
import Majima.task.TaskList;
import Majima.task.Todo;
import Majima.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A closed simulation/testing environment of the FindTest class.
 * This only tests the data structure and inner handling of the FindCommand
 * and does not involve the GUI.
 */
public class FindTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = null;
    }

    @Test
    public void findTask_singleKeywordMatch() throws MajimaException {
        tasks.addTask(new Todo("Read book"));
        tasks.addTask(new Todo("Write book"));
        tasks.addTask(new Todo("Walk the dog"));

        FindCommand findCommand = new FindCommand("book");
        findCommand.execute(tasks, ui, storage);

        List<?> foundTasks = tasks.findTasks("book");

        assertEquals(2, foundTasks.size());
    }

    @Test
    public void findTask_noKeywordMatch() throws MajimaException {
        tasks.addTask(new Todo("Go jogging"));
        tasks.addTask(new Todo("Clean house"));

        FindCommand findCommand = new FindCommand("study");
        findCommand.execute(tasks, ui, storage);

        List<?> foundTasks = tasks.findTasks("study");

        assertEquals(0, foundTasks.size());
    }

    @Test
    public void findTask_multipleKeywordMatches() throws MajimaException {
        tasks.addTask(new Todo("Buy groceries"));
        tasks.addTask(new Todo("Buy books"));
        tasks.addTask(new Todo("Buy a gift"));

        FindCommand findCommand = new FindCommand("Buy");
        findCommand.execute(tasks, ui, storage);


        List<?> foundTasks = tasks.findTasks("Buy");

        assertEquals(3, foundTasks.size());
    }
}
