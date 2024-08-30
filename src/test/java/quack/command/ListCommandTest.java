package quack.command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import quack.Ui;
import quack.TaskListStub;

/** 
 * This class is to test the ListCommand functionality.
 */
public class ListCommandTest {
    
    /** Stub Tasklist object */
    private TaskListStub taskList;
    /** Ui object to handle UI interface tasks */
    private Ui ui;
    /** Command to be executed */
    private ListCommand listCommand;
    /** Set a output space to retrieve system.out messages */
    private ByteArrayOutputStream actualOutput;

    /** 
     * Initiates objectes needed to test the list command.
     */
    @BeforeEach
    public void initiate() {
        this.ui = new Ui();
        this.taskList = new TaskListStub();

        // Set the output to a container to be used for comparison
        actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
    }

    /** 
     * Tests if the list command prints a task list with tasks correctly.
     */
    @Test
    public void listFilledTasklist() {

        // Populate the list with predefined tasks
        this.taskList.populateTaskList();
        this.listCommand = new ListCommand(this.taskList, ui);

        String expected = "1. [T][ ] Dummy 1\n" +
            "2. [E][X] Dummy 2 (From: 01/01/2024 10:59:30 To: 10/01/2024 14:00:59)\n" +
            "3. [D][ ] Dummy 3 (Due by: 10/01/2024 14:00:59)\n" +
            "-".repeat(65) + "\n";
    
        // Execute the command  
        listCommand.execute();

        String actual = actualOutput.toString().replaceAll("\r", "");

        assertEquals(expected, actual, "List command did not display the task list correctly");
    }

    /** 
     * Tests if the list command prints an empty task list correctly.
     */
    @Test
    public void listEmptyTasklist() {

        // Populate the list with predefined tasks
        this.taskList.emptyTaskList();
        this.listCommand = new ListCommand(this.taskList, ui);

        String expected = "The list is empty, why not add something!\n" +
            "-".repeat(65) + "\n";
        
        // Execute the command  
        listCommand.execute();

        String actual = actualOutput.toString().replaceAll("\r", "");

        assertEquals(expected, actual, "List command did not display an empty task list correctly");
    }

}
