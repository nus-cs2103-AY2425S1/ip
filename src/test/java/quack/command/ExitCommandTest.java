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

        quack = new Quack();
        storage = new Storage(taskList);
    }

    /** 
     * Tests if the exit command exits the program successfully.
     */
    @Test 
    public void byeCommandOutput() {
        exitCommand = new ExitCommand(quack, taskList, storage);
        exitCommand.execute();

        assertEquals(quack.getIsRunning(), false, "The command did not exit");

    }

}
