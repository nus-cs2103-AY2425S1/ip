package friday.command;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import friday.task.TaskList;
import friday.task.Todo;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

class CommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage();
    }

    @Test
    void markCommand_execute_marksTaskAsDone() throws IOException, FridayException {
        tasks.addTask(new Todo("read book"));
        String[] inputs = { "mark", "1" };
        Command command = new MarkUnmarkCommand(inputs);
        command.execute(tasks, ui, storage);

        Assertions.assertTrue(tasks.getTasks().get(0).isDone());
    }

    @Test
    void unmarkCommand_execute_marksTaskAsNotDone() throws IOException, FridayException {
        Todo task = new Todo("read book");
        task.markAsDone();
        tasks.addTask(task);
        String[] inputs = { "unmark", "1" };
        Command command = new MarkUnmarkCommand(inputs);
        command.execute(tasks, ui, storage);

        Assertions.assertFalse(tasks.getTasks().get(0).isDone());
    }

    @Test
    void byeCommand_execute_exits() throws IOException, FridayException {
        Command command = new ByeCommand();
        command.execute(tasks, ui, storage);

        Assertions.assertTrue(command.isExit());
    }
}
