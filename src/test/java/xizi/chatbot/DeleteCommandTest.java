package xizi.chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xizi.chatbot.command.DeleteCommand;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("testData.txt");
        tasks.addTask(new Todo("read book"));
    }

    @Test
    public void testDeleteCommand() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
        deleteCommand.execute(tasks, storage, ui);

        assertEquals(0, tasks.getSize());
    }

    @Test
    public void testDeleteInvalidTask() {
        Exception exception = assertThrows(Exception.class, () -> {
            DeleteCommand deleteCommand = new DeleteCommand("delete 2");
            deleteCommand.execute(tasks, storage, ui);
        });

        assertTrue(exception.getMessage().contains("The task number does not exist."));
    }
}

