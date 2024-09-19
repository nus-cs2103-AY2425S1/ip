package tayoo.command;

import org.junit.jupiter.api.Test;
import tayoo.Storage;
import tayoo.StorageStub;
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

public class MarkTaskCommandTest {

    @Test
    public void guiExecute_emptyList_returnsCorrectString() {
        Tasklist emptyTasklistStub = new TasklistEmptyStub();
        Storage storage = new StorageStub();
        Ui ui = new Ui("Tayoo");
        List<Integer> testList= new ArrayList<>();
        testList.add(1);
        Command testCommand = new MarkTaskCommand(testList, true);

        assertDoesNotThrow( () -> {
            String actual = testCommand.guiExecute(emptyTasklistStub, ui, storage);
            String expected = "Okay! I've marked these tasks:\n"
                    + "Could not find task number 1\n";

            assertEquals(actual, expected);
        });
    }

    @Test
    public void guiExecute_fullListTaskNotMarkedYet_returnCorrectString() {
        Tasklist fullTasklistStub = new TasklistFullStub();
        Storage storage = new StorageStub();
        Ui ui = new Ui("Tayoo");
        List<Integer> testList= new ArrayList<>();
        testList.add(6);
        Command testCommand = new MarkTaskCommand(testList, true);

        assertDoesNotThrow( () -> {
            String actual = testCommand.guiExecute(fullTasklistStub, ui, storage);
            String expected = "Okay! I've marked these tasks:\n"
                    + "Marked: Return book\n";

            assertEquals(actual, expected);
        });
    }

    @Test
    public void guiExecute_fullListTaskAlreadyMarked_returnCorrectString() {
        Tasklist fullTasklistStub = new TasklistFullStub();
        Storage storage = new StorageStub();
        Ui ui = new Ui("Tayoo");
        List<Integer> testList= new ArrayList<>();
        testList.add(5);
        Command testCommand = new MarkTaskCommand(testList, true);

        assertDoesNotThrow( () -> {
            String actual = testCommand.guiExecute(fullTasklistStub, ui, storage);
            String expected = "Okay! I've marked these tasks:\n"
                    + "Already marked: Read book\n";

            assertEquals(actual, expected);
        });
    }

    @Test
    public void guiExecute_fullListTaskNotUnmarkedYet_returnCorrectString() {
        Tasklist fullTasklistStub = new TasklistFullStub();
        Storage storage = new StorageStub();
        Ui ui = new Ui("Tayoo");
        List<Integer> testList= new ArrayList<>();
        testList.add(6);
        Command testCommand = new MarkTaskCommand(testList, false);

        assertDoesNotThrow( () -> {
            String actual = testCommand.guiExecute(fullTasklistStub, ui, storage);
            String expected = "Okay! I've unmarked these tasks:\n"
                    + "Unmarked: Return book\n";

            assertEquals(actual, expected);
        });
    }

    @Test
    public void guiExecute_fullListTaskAlreadyUnmarked_returnCorrectString() {
        Tasklist fullTasklistStub = new TasklistFullStub();
        Storage storage = new StorageStub();
        Ui ui = new Ui("Tayoo");
        List<Integer> testList= new ArrayList<>();
        testList.add(5);
        Command testCommand = new MarkTaskCommand(testList, false);

        assertDoesNotThrow( () -> {
            String actual = testCommand.guiExecute(fullTasklistStub, ui, storage);
            String expected = "Okay! I've unmarked these tasks:\n"
                    + "Already unmarked: Read book\n";

            assertEquals(actual, expected);
        });
    }

    @Test
    public void guiExecute_inputTaskLessThanEqualsZero_exceptionThrown() {
        Tasklist fullTasklistStub = new TasklistFullStub();
        Storage storage = new StorageStub();
        Ui ui = new Ui("Tayoo");
        List<Integer> testList1= new ArrayList<>();
        testList1.add(0);
        List<Integer> testList2= new ArrayList<>();
        testList2.add(0);

        Command testCommand1 = new MarkTaskCommand(testList1, true);
        Command testCommand2 = new MarkTaskCommand(testList2, false);

        assertThrows(TayooException.class, () -> testCommand1.guiExecute(fullTasklistStub, ui, storage));
        assertThrows(TayooException.class, () -> testCommand2.guiExecute(fullTasklistStub, ui, storage));
    }

    @Test
    public void guiExecute_inputTaskMoreThanMaximumCapacity_exceptionThrown() {
        Tasklist fullTasklistStub = new TasklistFullStub();
        Storage storage = new StorageStub();
        Ui ui = new Ui("Tayoo");
        List<Integer> testList1= new ArrayList<>();
        testList1.add(Tasklist.MAXIMUM_CAPACITY + 1);
        List<Integer> testList2= new ArrayList<>();
        testList2.add(Tasklist.MAXIMUM_CAPACITY + 1);
        Command testCommand1 = new MarkTaskCommand(testList1, true);
        Command testCommand2 = new MarkTaskCommand(testList2, false);

        assertThrows(TayooException.class, () -> testCommand1.guiExecute(fullTasklistStub, ui, storage));
        assertThrows(TayooException.class, () -> testCommand2.guiExecute(fullTasklistStub, ui, storage));
    }

    @Test
    public void isExit_whenCalled_returnsFalse() {
        List<Integer> testList = new ArrayList<>();
        Command testCommand = new MarkTaskCommand(testList, true);
        assertFalse(testCommand.isExit());
    }

}
