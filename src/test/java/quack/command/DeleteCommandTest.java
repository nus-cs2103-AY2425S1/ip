package quack.command;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import quack.Ui;
import quack.TaskListStub;

/** 
 * This class is to test the DeleteCommand functionality.
 */
public class DeleteCommandTest {
    
    /** Stub Tasklist object */
    private TaskListStub taskList;
    /** Ui object to handle UI interface tasks */
    private Ui ui;
    /** Command to be executed */
    private DeleteTaskCommand deleteCommand;
    /** Set a output space to retrieve system.out messages */
    private ByteArrayOutputStream actualOutput;

    /** 
     * Initiates objectes needed to test the delete command.
     */
    @BeforeEach
    public void initiate() {

        // Set the stub task list
        taskList = new TaskListStub();
        
        // Set the output to a container to be used for comparison
        actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
    }

    /** 
     * Tests if the delete command prints the correct output after
     * deleting an item.
     */
    @Test
    public void testDeleteTask() {

        // Populate task list with predefined tasks
        taskList.populateTaskList();

        // Mimic user input to be 1
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ui = new Ui();
        deleteCommand = new DeleteTaskCommand(taskList, ui);

        deleteCommand.execute();
        String expected = "1. [T][ ] Dummy 1\n" + 
            "2. [E][X] Dummy 2 (From: 01/01/2024 10:59:30 To: 10/01/2024 14:00:59)\n" +
            "3. [D][ ] Dummy 3 (Due by: 10/01/2024 14:00:59)\n" + 
            "-".repeat(65) + "\n" +
            "Which task do you want to delete? (Input the index of the task): " + 
            "-".repeat(65) + "\n" +
            "Success! I have deleteed this task: [T][ ] Dummy 1\n" +
            "You now have 2 tasks in your list right now!\n" +
            "-".repeat(65) + "\n";
        
        String actual = actualOutput.toString().replaceAll("\r", "");
        
        assertEquals(expected, actual, "The delete command did not display the correct prompt");
        assertEquals(taskList.getLength(), 2, "The delete command did not delete a task");
    }

    /** 
     * Tests if the delete command prints the correct output 
     * after getting an out of bounds index.
     */
    @Test
    public void testDeleteWithWrongIndex() {

        // Populate task list with predefined tasks
        taskList.populateTaskList();

        // Mimic user input to be 10
        String input = "10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ui = new Ui();
        deleteCommand = new DeleteTaskCommand(taskList, ui);

        deleteCommand.execute();
        String expected = "1. [T][ ] Dummy 1\n" + 
            "2. [E][X] Dummy 2 (From: 01/01/2024 10:59:30 To: 10/01/2024 14:00:59)\n" +
            "3. [D][ ] Dummy 3 (Due by: 10/01/2024 14:00:59)\n" + 
            "-".repeat(65) + "\n" +
            "Which task do you want to delete? (Input the index of the task): " + 
            "-".repeat(65) + "\n" +
            "Oops looks like the index: 10 entered is out of bounds!\n" +
            "-".repeat(65) + "\n";

        String actual = actualOutput.toString().replaceAll("\r", "");

        assertEquals(expected, actual, "The delete command did not display the correct prompt");
        assertEquals(taskList.getLength(), 3, "The delete command deleted something when it is not supposed to");
    }

    /** 
     * Tests if the delete command prints the correct output 
     * after getting an empty task list.
     */
    @Test
    public void testDeleteEmpyTasklist() {

        this.taskList.emptyTaskList();

        ui = new Ui();
        deleteCommand = new DeleteTaskCommand(taskList, ui);

        deleteCommand.execute();
        String expected = "The list is empty, why not add something!\n"+
            "-".repeat(65) + "\n";
        
        String actual = actualOutput.toString().replaceAll("\r", "");

        assertEquals(expected, actual, "The delete command did not show the correct prompt when deleting from an empty list"); 
    }
}
