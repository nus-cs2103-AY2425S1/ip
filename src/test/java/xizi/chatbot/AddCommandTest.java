package xizi.chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xizi.chatbot.command.DeadlineCommand;
import xizi.chatbot.command.EventCommand;
import xizi.chatbot.command.TodoCommand;
import xizi.chatbot.task.Deadline;
import xizi.chatbot.task.Event;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.task.Todo;




/**
 * Tests for various task addition commands in the application.
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
        storage = new Storage("./data/addCommandTest.txt");
    }

    /**
     * Tests the addition of a {@code Todo} task using the {@link TodoCommand}.
     * Verifies that the task is added correctly to the task list.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void todoCommandConstructor_validTask_success() throws Exception {
        TodoCommand addCommand = new TodoCommand("todo read book");
        addCommand.execute(tasks, storage, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("read book", tasks.getItems().get(0).getName());
        assertInstanceOf(Todo.class, tasks.getItems().get(0));
    }

    /**
     * Tests the addition of a {@code Deadline} task using the {@link DeadlineCommand}.
     * Verifies that the task is added correctly to the task list and that it is an instance of {@link Deadline}.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void deadlineCommandConstructor_validTask_success() throws Exception {
        DeadlineCommand addCommand = new DeadlineCommand("deadline submit report /by 1/12/2024 2359");
        addCommand.execute(tasks, storage, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("submit report", tasks.getItems().get(0).getName());
        assertInstanceOf(Deadline.class, tasks.getItems().get(0));
    }

    /**
     * Tests the addition of an {@code Event} task using the {@link EventCommand}.
     * Verifies that the task is added correctly to the task list and that it is an instance of {@link Event}.
     *
     * @throws Exception If there is an error executing the command.
     */
    @Test
    public void eventCommandConstructor_validTask_success() throws Exception {
        EventCommand addCommand = new EventCommand("event team meeting /from 5/12/2024 1400 /to 5/12/2024 1600");
        addCommand.execute(tasks, storage, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("team meeting", tasks.getItems().get(0).getName());
        assertInstanceOf(Event.class, tasks.getItems().get(0));
    }


    /**
     * Tests the addition of multiple tasks in succession to ensure the task list size is updated correctly.
     *
     * @throws Exception If there is an error executing the commands.
     */
    @Test
    public void commandConstructors_addMultipleTasks_success() throws Exception {
        TodoCommand todoCommand = new TodoCommand("todo finish homework");
        DeadlineCommand deadlineCommand = new DeadlineCommand("deadline project submission /by 15/12/2024 1800");
        EventCommand eventCommand = new EventCommand("event birthday party /from 10/12/2024 1900 /to 10/12/2024 2200");

        todoCommand.execute(tasks, storage, ui);
        deadlineCommand.execute(tasks, storage, ui);
        eventCommand.execute(tasks, storage, ui);

        assertEquals(3, tasks.getSize());
        assertInstanceOf(Todo.class, tasks.getItems().get(0));
        assertInstanceOf(Deadline.class, tasks.getItems().get(1));
        assertInstanceOf(Event.class, tasks.getItems().get(2));
    }

    /**
     * Tests the behavior when adding a {@code Todo} task with an empty description.
     * Verifies that an exception is thrown with an appropriate error message.
     */
    @Test
    public void execute_emptyTodoCommand_exceptionThrown() {
        Exception exception = assertThrows(Exception.class, () -> {
            TodoCommand addCommand = new TodoCommand("todo ");
            addCommand.execute(tasks, storage, ui);
        });

        assertEquals("The description of a todo cannot be empty.", exception.getMessage());
    }

    /**
     * Tests the behavior when adding a {@code Deadline} task with an empty description.
     * Verifies that an exception is thrown with an appropriate error message.
     */
    @Test
    public void execute_emptyDeadlineCommand_exceptionThrown() {
        Exception exception = assertThrows(Exception.class, () -> {
            DeadlineCommand addCommand = new DeadlineCommand("deadline /by 23/12/2020 2355");
            addCommand.execute(tasks, storage, ui);
        });

        assertEquals("The description or time of a deadline cannot be empty.", exception.getMessage());
    }

    /**
     * Tests the behavior when adding a {@code Deadline} task with an invalid datetime format.
     * Verifies that an exception is thrown with an appropriate error message.
     */
    @Test
    public void execute_invalidDeadlineCommandDate_exceptionThrown() {
        Exception exception = assertThrows(Exception.class, () -> {
            DeadlineCommand addCommand = new DeadlineCommand("deadline jdiwkdjx /by redjinwdnc");
            addCommand.execute(tasks, storage, ui);
        });

        assertEquals("Invalid date/time format. Use the format: d/M/yyyy HHmm", exception.getMessage());
    }

    /**
     * Tests the behavior when adding an {@code Event} task with an invalid description.
     * Verifies that an exception is thrown with an appropriate error message.
     */
    @Test
    public void execute_invalidEventCommand_exceptionThrown() {
        Exception exception = assertThrows(Exception.class, () -> {
            EventCommand addCommand = new EventCommand("event fr4jjdi4 /by djnejisje /to ewjhj");
            addCommand.execute(tasks, storage, ui);
        });

        assertEquals("Invalid event command format. Use: event <description> /from <start> /to <end>",
                exception.getMessage());
    }

}
