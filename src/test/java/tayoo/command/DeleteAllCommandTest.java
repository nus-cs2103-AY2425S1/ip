package tayoo.command;

import org.junit.jupiter.api.Test;
import tayoo.Storage;
import tayoo.StorageStub;
import tayoo.Tasklist;
import tayoo.TasklistEmptyStub;
import tayoo.TasklistFullStub;
import tayoo.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteAllCommandTest {

    @Test
    public void guiExecute_emptyTasklist_returnsCorrectString() {
        Tasklist emptyTasklist = new TasklistEmptyStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();
        DeleteAllCommand testCommand = new DeleteAllCommand();

        assertDoesNotThrow( () -> {
            String actual = testCommand.guiExecute(emptyTasklist, ui, storage);
            String expected = "No tasks to delete";

            assertEquals(actual, expected);
        });
    }

    @Test
    public void guiExecute_fullTasklist_returnsCorrectString() {
        Tasklist fullTasklist = new TasklistFullStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();
        DeleteAllCommand testCommand = new DeleteAllCommand();

        assertDoesNotThrow( () -> {
            String actual = testCommand.guiExecute(fullTasklist, ui, storage);
            String expected = "Deleting all tasks";

            assertEquals(actual, expected);
        });
    }

    @Test
    public void isExit_whenCalled_returnsfalse() {
        DeleteAllCommand testCommand = new DeleteAllCommand();
        assertFalse(testCommand.isExit());
    }

}
