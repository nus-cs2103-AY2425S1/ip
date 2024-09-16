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

public class ListCommandTest {

    @Test
    public void guiExecute_emptyTaskList_correctString() {
        Command testCommand = new ListCommand();
        Tasklist emptyTasklistStub = new TasklistEmptyStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();
        assertDoesNotThrow(() -> {
            String actual = testCommand.guiExecute(emptyTasklistStub, ui, storage);
            String expected = "Here are the tasks in your list: \n";

            assertEquals(expected, actual);
        });
    }

    @Test
    public void guiExecute_populatedTaskList_correctString() {
        Command testCommand = new ListCommand();
        Tasklist fullTasklistStub = new TasklistFullStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();

        assertDoesNotThrow(() -> {
            String actual = testCommand.guiExecute(fullTasklistStub, ui, storage);
            String expected = "Here are the tasks in your list: \n" +
                              "1. [X] Return book\n";

            assertEquals(expected, actual);
        });
    }

    @Test
    public void isExit_whenCalled_returnsFalse() {
        Command testCommand = new ListCommand();
        assertFalse(testCommand.isExit());
    }
}
