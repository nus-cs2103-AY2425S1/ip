package quack.command;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import quack.TaskListStub;
import quack.Ui;

/** 
 * This class is to test the AddTaskCommand functionality.
 */
public class AddTaskCommandTest {
    
     /** Stub Tasklist object */
    private TaskListStub taskList;
    /** Ui object to handle UI interface tasks */
    private Ui ui;
    /** Command to be executed */
    private AddTaskCommand addTaskCommand;
    /** Set a output space to retrieve system.out messages */
    private ByteArrayOutputStream actualOutput;

    /** 
     * Initiates objectes needed to test the add task command.
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
     * Tests if the add task command prints the correct output 
     * after adding an item.
     */
    @Test
    public void testAddTask() {

        // Populate task list with predefined tasks
        taskList.emptyTaskList();

        // Mimic user input to be 1
        String input = "todo\nTask1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ui = new Ui();
        addTaskCommand = new AddTaskCommand(taskList, ui);

        addTaskCommand.execute();
        String expected = "What is the type of task you would like to add: \n" +
            "-".repeat(65) + "\n" +
            "What is the description for the TODO task: \n" + 
            "-".repeat(65) + "\n" +
            "Success! I have added this task: [T][ ] Task1\n" + 
            "You now have 1 tasks in your list right now!\n" +
            "-".repeat(65) + "\n";
        
        assertEquals(expected, actualOutput.toString().replaceAll("\r", ""), "Test if the add task command delets and prints the correct message");
        assertEquals(taskList.getLength(), 1, "Task list length should be 1 since a task was added to an empty list");
    }

    /** 
     * Tests if the add task command prints the correct output 
     * after an invalid date input
     */
    @Test
    public void testInvalidDateInput() {

        // Populate task list with predefined tasks
        taskList.emptyTaskList();

        // Mimic user input to be 1
        String input = "event\nTask1\n1/1/1 1PM\n01/01/2024 10:10:10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ui = new Ui();
        addTaskCommand = new AddTaskCommand(taskList, ui);

        addTaskCommand.execute();
        String expected = "What is the type of task you would like to add: \n" +
        "-".repeat(65) + "\n" +
        "What is the description for the EVENT task: \n" +
        "-".repeat(65) + "\n" +
        "When is the start date for the EVENT task (Format: DD/MM/YYYY HH:MM:SS): \n"+
        "-".repeat(65) + "\n" +
        "When is the end date for the EVENT task (Format: DD/MM/YYYY HH:MM:SS): \n" +
        "-".repeat(65) + "\n" +
        "Im sorry but the date input is invalid ensure that it is in this format: DD/MM/YYYY HH:MM:SS\n" +
        "-".repeat(65) + "\n";
        
        assertEquals(expected, actualOutput.toString().replaceAll("\r", ""), "Test if the add task command delets and prints the correct message"); 
    }
}
