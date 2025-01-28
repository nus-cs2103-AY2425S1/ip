package command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.DynamikeException;
import storage.Storage;
import storage.TaskList;
import task.Todo;
import ui.Ui;

public class DeleteCommandTest {

    @Test
    public void testDelete() {
        TaskList tasks = new TaskList(new Todo("Test"));
        DeleteCommand deleter = new DeleteCommand(0);
        try {
            deleter.execute(tasks, new Ui(), new Storage("test.txt"));
        } catch (Exception e) {
            assert false : "Exception thrown";
        }
        assert (tasks.getSize() == 0);
    }

    @Test
    public void testDeleteInvalidIndex() throws DynamikeException {
        TaskList tasks = new TaskList(new Todo("Test"));
        DeleteCommand deleter = new DeleteCommand(1);
        assertThrows(DynamikeException.class, () -> deleter.execute(tasks, new Ui(), new Storage("test.txt")));
    }

    @Test
    public void testDeleteInvalidIndex2() throws DynamikeException {
        TaskList tasks = new TaskList(new Todo("Test"));
        DeleteCommand deleter = new DeleteCommand(-1);
        assertThrows(DynamikeException.class, () -> deleter.execute(tasks, new Ui(), new Storage("test.txt")));
    }
}
