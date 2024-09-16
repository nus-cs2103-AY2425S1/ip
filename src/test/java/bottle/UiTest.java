package bottle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bottle.task.Task;
import bottle.task.TaskList;
import bottle.task.Todo;

/**
 * The type Ui test.
 */
public class UiTest {
    /**
     * The Ui.
     */
    private Ui ui;

    /**
     * Sets up.
     */
    @BeforeEach
    public void setUp() {
        ui = new Ui();
    }

    /**
     * Test print task list.
     */
    @Test
    public void testPrintTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Task 1"));
        taskList.addTask(new Todo("Task 2"));
        ui = new Ui();

        String expectedOutput =
                "\n____________________________________________________________\n"
                        + "1. [T][ ] Task 1\n" + "2. [T][ ] Task 2\n"
                        + "\n____________________________________________________________\n";
        assertEquals(expectedOutput, ui.printTaskList(taskList));
    }

    /**
     * Test print mark.
     */
    @Test
    public void testPrintMark() {
        Task task = new Todo("Sample Task");
        task.mark();

        String expectedOutput = "\n____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + "[T][X] Sample Task"
                + "\n____________________________________________________________\n";
        assertEquals(expectedOutput, ui.printMark(task));
    }

}
