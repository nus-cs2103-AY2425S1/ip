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
 * This class is to test the UpdateTaskCommand functionality.
 */
public class UpdateTaskCommandTest {
    
      /** Stub Tasklist object */
      private TaskListStub taskList;
      /** Ui object to handle UI interface tasks */
      private Ui ui;
      /** Command to be executed */
      private UpdateTaskCommand updateTaskCommand;
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
     * after marking a task.
     */
    @Test
    public void testMarkTask() {

        // Populate task list with predefined tasks
        taskList.populateTaskList();

        // Mimic user input to be 10
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ui = new Ui();
        updateTaskCommand = new UpdateTaskCommand("mark", taskList, ui);

        updateTaskCommand.execute();

        String expected = "1. [T][ ] Dummy 1\n" +
        "2. [E][X] Dummy 2 (From: 01/01/2024 10:59:30 To: 10/01/2024 14:00:59)\n" +
        "3. [D][ ] Dummy 3 (Due by: 10/01/2024 14:00:59)\n" +
        "-".repeat(65) + "\n" +
        "Which task do you want to mark? (Input the index of the task): " +
        "-".repeat(65) + "\n" +
        "Success! I have marked this task: [T][X] Dummy 1\n" +
        "You now have 3 tasks in your list right now!\n" +
        "-".repeat(65) + "\n" +
        "1. [T][X] Dummy 1\n" +
        "2. [E][X] Dummy 2 (From: 01/01/2024 10:59:30 To: 10/01/2024 14:00:59)\n" +
        "3. [D][ ] Dummy 3 (Due by: 10/01/2024 14:00:59)\n" +
        "-".repeat(65) + "\n";
        
        ListCommand listCommand = new ListCommand(this.taskList, ui);
        listCommand.execute();

        assertEquals(expected, actualOutput.toString().replaceAll("\r", ""), "The command did not mark the task");
    }

    /** 
     * Tests if the add task command prints the correct output 
     * after marking a task.
     */
    @Test
    public void testUnmarkTask() {

        // Populate task list with predefined tasks
        taskList.populateTaskList();

        // Mimic user input to be 10
        String input = "2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ui = new Ui();
        updateTaskCommand = new UpdateTaskCommand("unmark", taskList, ui);

        updateTaskCommand.execute();
        
        String expected = "1. [T][ ] Dummy 1\n" +
        "2. [E][X] Dummy 2 (From: 01/01/2024 10:59:30 To: 10/01/2024 14:00:59)\n" +
        "3. [D][ ] Dummy 3 (Due by: 10/01/2024 14:00:59)\n" +
        "-".repeat(65) + "\n" +
        "Which task do you want to unmark? (Input the index of the task): " +
        "-".repeat(65) + "\n" +
        "Success! I have unmarked this task: [E][ ] Dummy 2 (From: 01/01/2024 10:59:30 To: 10/01/2024 14:00:59)\n" +
        "You now have 3 tasks in your list right now!\n" +
        "-".repeat(65) + "\n" +
        "1. [T][ ] Dummy 1\n" +
        "2. [E][ ] Dummy 2 (From: 01/01/2024 10:59:30 To: 10/01/2024 14:00:59)\n" +
        "3. [D][ ] Dummy 3 (Due by: 10/01/2024 14:00:59)\n" +
        "-".repeat(65) + "\n";
        
        ListCommand listCommand = new ListCommand(this.taskList, ui);
        listCommand.execute();

        assertEquals(expected, actualOutput.toString().replaceAll("\r", ""), "The command did not mark the task");
    }
}
