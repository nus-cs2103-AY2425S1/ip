package xizi.chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xizi.chatbot.command.DeadlineCommand;
import xizi.chatbot.command.TodoCommand;
import xizi.chatbot.task.Deadline;
import xizi.chatbot.task.TaskList;


/**
 * Unit tests for various task addition commands in the application.
 * This class tests the functionality of adding different types of tasks to the {@link TaskList}.
 */
public class AddCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the necessary objects before each test.
     * Initializes an empty {@link TaskList}, a {@link Ui}, and a {@link Storage}
     * object with the file path "./data/xizi.txt".
     */
    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/xizi.txt");
    }

    /**
     * Tests the addition of a {@code Todo} task using the {@link TodoCommand}.
     * Verifies that the task is added correctly to the task list.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void testAddTodoCommand() throws Exception {
        TodoCommand addCommand = new TodoCommand("todo read book");
        addCommand.execute(tasks, storage, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("read book", tasks.getItems().get(0).getName());
    }

    /**
     * Tests the addition of a {@code Deadline} task using the {@link DeadlineCommand}.
     * Verifies that the task is added correctly to the task list and that it is an instance of {@link Deadline}.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void testAddDeadlineCommand() throws Exception {
        DeadlineCommand addCommand = new DeadlineCommand("deadline submit report /by 1/12/2024 2359");
        addCommand.execute(tasks, storage, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("submit report", tasks.getItems().get(0).getName());
        assertInstanceOf(Deadline.class, tasks.getItems().get(0));
    }


}
