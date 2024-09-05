package quack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import quack.Quack;
import quack.Storage;
import quack.TaskListStub;

/**
 * This class is to test the ExitCommand functionality.
 */
public class ExitCommandTest {

    /** Quack chatbot object */
    private Quack quack;
    /** Stub Tasklist object */
    private TaskListStub taskList;
    /** Ui object to handle UI interface tasks */
    private Storage storage;
    /** Command to be executed */
    private ExitCommand exitCommand;

    /**
     * Initiates objectes needed to test the delete command.
     */
    @BeforeEach
    public void initiate() {

        // Set the stub task list
        taskList = new TaskListStub();
        taskList.emptyTaskList();

        storage = new Storage(taskList);
    }

    /**
     * Tests if the exit command exits the program successfully.
     */
    @Test
    public void byeCommandOutput() {

        assertEquals(quack.getIsRunning(), false, "The command did not stop the Quack from running");
    }

}
