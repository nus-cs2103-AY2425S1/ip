package tayoo.command;

import org.junit.jupiter.api.Test;
import tayoo.Storage;
import tayoo.StorageStub;
import tayoo.Tasklist;
import tayoo.TasklistEmptyStub;
import tayoo.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExitCommandTest {

    @Test
    public void isExit_whenCalled_false() {
        Command testCommand = new ExitCommand();
        assertTrue(testCommand.isExit());
    }

    @Test
    public void guiExecute_whenCalled_returnsCorrectString() {
        Command testCommand = new ExitCommand();
        Tasklist emptyTasklistStub = new TasklistEmptyStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();

        assertDoesNotThrow(() -> {
            String actual = testCommand.guiExecute(emptyTasklistStub, ui, storage);

            String expected = ui.getExitMessage();
            assertEquals(expected, actual);
        });

    }

}
