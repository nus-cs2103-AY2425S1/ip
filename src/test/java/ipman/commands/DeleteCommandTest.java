package ipman.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ipman.models.DummyFileManager;
import ipman.models.TaskList;
import ipman.models.ToDo;
import ipman.ui.SpyUi;

public class DeleteCommandTest {
    @Test
    public void execute_missingTask_throwsException() {
        TaskList tasks = new TaskList();
        Context context = new Context(tasks, new SpyUi(), new DummyFileManager());

        Command command = new DeleteCommand(0);
        assertThrows(InvalidIndexException.class, () -> {
            command.execute(context);
        });
    }

    @Test
    public void execute_delete_removesTask() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("a"));
        tasks.add(new ToDo("b"));
        tasks.add(new ToDo("c"));
        Context context = new Context(tasks, new SpyUi(), new DummyFileManager());

        Command command = new DeleteCommand(1);
        command.execute(context);

        assertEquals(tasks.get(0).getName(), "a");
        assertEquals(tasks.get(1).getName(), "c");
    }
}
