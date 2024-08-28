package xizi.chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xizi.chatbot.command.Command;
import xizi.chatbot.command.DeadlineCommand;
import xizi.chatbot.command.TodoCommand;
import xizi.chatbot.task.Deadline;
import xizi.chatbot.task.TaskList;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/xizi.txt");
    }

    @Test
    public void testAddTodoCommand() throws Exception {
        TodoCommand addCommand = new TodoCommand("todo read book");
        addCommand.execute(tasks, storage, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("read book", tasks.getItems().get(0).getName());
    }

    @Test
    public void testAddDeadlineCommand() throws Exception {
        DeadlineCommand addCommand = new DeadlineCommand("deadline submit report /by 1/12/2024 2359");
        addCommand.execute(tasks, storage, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("submit report", tasks.getItems().get(0).getName());
        assertInstanceOf(Deadline.class, tasks.getItems().get(0));
    }

    @Test
    public void testAddInvalidTask() {
        Exception exception = assertThrows(Exception.class, () -> {
            Parser parser = new Parser();
            Command addCommand = parser.parse("invalidCommand buy milk");
            addCommand.execute(tasks, storage, ui);
        });

        assertTrue(exception.getMessage().contains("Sorry, I didn't understand that command. Type help for all available commands and format."));
    }


}
