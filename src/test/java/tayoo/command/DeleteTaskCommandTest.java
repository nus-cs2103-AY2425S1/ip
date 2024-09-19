package tayoo.command;

import org.junit.jupiter.api.Test;
import tayoo.Storage;
import tayoo.StorageStub;
import tayoo.TaskStub;
import tayoo.Tasklist;
import tayoo.TasklistEmptyStub;
import tayoo.TasklistFullStub;
import tayoo.Ui;
import tayoo.exception.TayooException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteTaskCommandTest {

    @Test
    public void guiExecute_validTaskNumber_returnsCorrectString() {
        List<Integer> testList = new ArrayList<>();
        testList.add(6);
        DeleteTaskCommand testCommand = new DeleteTaskCommand(testList);
        Tasklist fullTaskList = new TasklistFullStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();

        assertDoesNotThrow(() -> {
            String actual = testCommand.guiExecute(fullTaskList, ui, storage);
            String expected = "Deleted: Return book\n"
                    + "\n Now you have 100 tasks in your list";

            System.out.println(actual);
            assertEquals(actual, expected);
        });
    }

    @Test
    public void guiExecute_taskNumberLessThan0_exceptionThrown() {
        List<Integer> testList = new ArrayList<>();
        testList.add(0);
        DeleteTaskCommand testCommand = new DeleteTaskCommand(testList);
        Tasklist fullTaskList = new TasklistFullStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();

        assertThrows(TayooException.class, () -> testCommand.guiExecute(fullTaskList, ui, storage));
    }

    @Test
    public void guiExecute_taskNumberMoreThanMaximumCapacity_exceptionThrown() {
        List<Integer> testList = new ArrayList<>();
        testList.add(Tasklist.MAXIMUM_CAPACITY + 1);
        DeleteTaskCommand testCommand = new DeleteTaskCommand(testList);
        Tasklist fullTaskList = new TasklistFullStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();

        assertThrows(TayooException.class, () -> testCommand.guiExecute(fullTaskList, ui, storage));
    }

    @Test
    public void guiExecute_taskNumberMoreThanCurrentSize_correctString() {
        List<Integer> testList = new ArrayList<>();
        testList.add(1);
        DeleteTaskCommand testCommand = new DeleteTaskCommand(testList);
        Tasklist emptyTaskList = new TasklistEmptyStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();

        assertDoesNotThrow(() -> {
            String actual = testCommand.guiExecute(emptyTaskList, ui, storage);
            String expected = "Could not find task number 1\n"
                    + "\n Now you have 0 tasks in your list";

            System.out.println(actual);
            assertEquals(actual, expected);
        });
    }

    @Test
    public void isExit_whenCalled_returnsFalse() {
        List<Integer> testList = new ArrayList<>();
        testList.add(5);
        DeleteTaskCommand testCommand = new DeleteTaskCommand(testList);

        assertFalse(testCommand.isExit());
    }

}
