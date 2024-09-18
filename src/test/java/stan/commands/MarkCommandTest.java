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

class MarkCommandTest {

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
    void testMarkTaskSuccessfully() throws StanInvalidArgumentException, StanMissingArgumentException {
        taskList.add(new MockTask("Task to be marked"));
        MarkCommand markCommand = new MarkCommand(new String[]{"mark", "1"});
        String response = markCommand.execute(taskList, ui, storage);

        assertEquals("🎉 Awesome! I've marked this task as done! 🎯\n"
                + "   [X] Task to be marked\nYou're on a roll with 1 tasks left! Keep going! 🚀", response);
    }

    @Test
    void testMarkTaskAlreadyDone() throws StanInvalidArgumentException, StanMissingArgumentException {
        MockTask task = new MockTask("Task already marked");
        task.markAsDone();
        taskList.add(task);

        MarkCommand markCommand = new MarkCommand(new String[]{"mark", "1"});
        String response = markCommand.execute(taskList, ui, storage);

        assertEquals("OOPS!!!\nYou have already completed this task! 🎉\n"
                + "Anyways GOOD JOB (๑•̀ㅂ•́)و✧\nYou can enter \"delete 1\" to remove it, if you wish.", response);
    }

    @Test
    void testMarkTaskInvalidIndex() throws StanMissingArgumentException, StanInvalidArgumentException {
        taskList.add(new MockTask("Task 1"));
        MarkCommand markCommand = new MarkCommand(new String[]{"mark", "2"});
        Exception exception = assertThrows(StanInvalidArgumentException.class, () ->
                markCommand.execute(taskList, ui, storage));
        assertEquals("The task number is out of range.", exception.getMessage());
    }

    @Test
    void testMarkTaskMissingArgument() {
        Exception exception = assertThrows(StanMissingArgumentException.class, () -> {
            new MarkCommand(new String[]{"mark"}); // Missing task number
        });
        assertEquals("You need to specify the task number to mark.", exception.getMessage());
    }
}
