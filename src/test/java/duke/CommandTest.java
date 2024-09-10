package duke;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandTest {
    // pre-commit hook of making sure test cases pass before code can be pushed to github
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        storage = mock(Storage.class);
        ui = mock(Ui.class);
    }

    @Test
    public void testExecuteListCommand() throws MentalHealthException {
        Command command = new Command("list", "");
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new ToDo("Task 2"));

        command.execute(taskList, ui, storage);

        // Verify that the tasks were listed correctly
        Assertions.assertEquals(2, taskList.getListTask().size());
    }

    @Test
    public void testExecuteMarkCommand() throws MentalHealthException {
        taskList.addTask(new ToDo("Task 1"));
        Command command = new Command("mark", "mark 1");

        command.execute(taskList, ui, storage);

        Assertions.assertTrue(taskList.getListTask().get(0).getIcon().equals("X"));
    }

    @Test
    public void testExecuteDeleteCommand() throws MentalHealthException {
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new ToDo("Task 2"));
        Command command = new Command("delete", "delete 1");

        command.execute(taskList, ui, storage);

        Assertions.assertEquals(1, taskList.getListTask().size());
        Assertions.assertEquals("Task 2", taskList.getListTask().get(0).getTaskDescription());
    }

    @Test
    public void testProcessMessageTodo() throws MentalHealthException {
        Command command = new Command("todo", "todo Finish homework");

        command.processMessage("todo Finish homework", taskList, storage);

        Assertions.assertEquals(1, taskList.getListTask().size());
        Assertions.assertEquals("Finish homework", taskList.getListTask().get(0).getTaskDescription());
    }

    @Test
    public void testProcessMessageDeadline() throws MentalHealthException {
        Command command = new Command("deadline", "deadline Submit report /by 2/12/2019 1800");

        command.processMessage("deadline Submit report /by 2/12/2019 1800", taskList, storage);

        Assertions.assertEquals(1, taskList.getListTask().size());
        Assertions.assertEquals("Submit report", taskList.getListTask().get(0).getTaskDescription());
    }

    @Test
    public void testProcessMessageEvent() throws MentalHealthException {
        Command command = new Command("event", "event Attend meeting /from 1800 /to 2000");

        command.processMessage("event Attend meeting /from 1800 /to 2000", taskList, storage);

        Assertions.assertEquals(1, taskList.getListTask().size());
        Assertions.assertEquals("Attend meeting", taskList.getListTask().get(0).getTaskDescription());
    }

    @Test
    public void testIsExitCommand() {
        Command command = new Command("bye", "");

        Assertions.assertTrue(command.isExit());
    }
}
