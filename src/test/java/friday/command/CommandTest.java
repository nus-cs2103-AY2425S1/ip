package friday.command;

import friday.task.Deadline;
import friday.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import friday.task.TaskList;
import friday.util.Ui;
import friday.util.Storage;
import friday.util.FridayException;

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

        assertTrue(tasks.getTasks().get(0).isDone());
    }

    @Test
    void unmarkCommand_execute_marksTaskAsNotDone() throws IOException, FridayException {
        Todo task = new Todo("read book");
        task.markAsDone();
        tasks.addTask(task);
        String[] inputs = { "unmark", "1" };
        Command command = new MarkUnmarkCommand(inputs);
        command.execute(tasks, ui, storage);

        assertFalse(tasks.getTasks().get(0).isDone());
    }

    @Test
    void helpCommand_execute_displaysHelp() throws IOException, FridayException {
        Command command = new HelpCommand();
        command.execute(tasks, ui, storage);

        assertTrue(ui.showHelp());
    }

    @Test
    void listCommand_execute_displaysTaskList() throws IOException, FridayException {
        tasks.addTask(new Todo("read book"));
        tasks.addTask(new Deadline("return book", "2024-08-27 1800"));

        Command command = new ListCommand();
        command.execute(tasks, ui, storage);

        assertTrue(ui.showTaskList(tasks));
    }

    @Test
    void byeCommand_execute_exits() throws IOException, FridayException {
        Command command = new ByeCommand();
        command.execute(tasks, ui, storage);

        assertTrue(command.isExit());
    }
}
