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
import tayoo.tasks.Task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddTaskCommandTest {

    @Test
    public void guiExecute_validTask_returnsCorrectString() {
        Task taskStub = new TaskStub();
        Tasklist tasklistStub = new TasklistEmptyStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();
        AddTaskCommand testCommand = new AddTaskCommand(taskStub);

        assertDoesNotThrow(() -> {
            String actual = testCommand.guiExecute(tasklistStub, ui, storage);

            String expected = "Got it. I've added this task: \n" +
                    taskStub.toString() +
                    tasklistStub.numberOfTasksLeft();
            assertEquals(expected, actual);
        });
    }

    @Test
    public void guiExecute_tooManyTasks_exceptionThrown() {
        Task taskStub = new TaskStub();
        Tasklist fullTasklistStub = new TasklistFullStub();
        Ui ui = new Ui("Tayoo");
        Storage storage = new StorageStub();
        AddTaskCommand testCommand = new AddTaskCommand(taskStub);

        assertThrows(TayooException.class, () -> {
            testCommand.guiExecute(fullTasklistStub, ui, storage);
        });
    }

    @Test
    public void toString_taskStub_returnsCorrectString() {
        Task taskStub = new TaskStub();
        AddTaskCommand testCommand = new AddTaskCommand(taskStub);

        assertEquals("[X] Return book", testCommand.toString());
    }

    @Test
    public void isExit_whenCalled_returnsFalse() {
        Task taskStub = new TaskStub();
        AddTaskCommand testCommand = new AddTaskCommand(taskStub);

        assertFalse(testCommand.isExit());
    }

}
