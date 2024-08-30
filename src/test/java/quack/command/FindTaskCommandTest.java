package quack.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import quack.TaskListStub;
import quack.Ui;

public class FindTaskCommandTest {
    
    /** List to store all tasks by Quack */
    private TaskListStub taskList;
    /** List to store all tasks by Quack */
    private Ui ui;
    /** Command to be executed */
    private FindTaskCommand findTaskCommand;
    /** Set a output space to retrieve system.out messages */
    private ByteArrayOutputStream actualOutput;

    @BeforeEach
    public void initiate() {

        taskList = new TaskListStub();
        taskList.populateTaskList();
        
        // Set the output to a container to be used for comparison
        actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
    }

    @Test
    public void findCorrectTask() {

        // Mimic user input to be DuMMy 1
        String input = "DuMMy 1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ui = new Ui();
        findTaskCommand = new FindTaskCommand(taskList, ui);

        findTaskCommand.execute();

        String expected = "What task would you like to find? : " + 
            "-".repeat(65) + "\n" +
            "Here are some tasks that I found that matches your description:\n" + 
            "1. [T][ ] Dummy 1\n"+
            "-".repeat(65) + "\n";
        
        assertEquals(expected, actualOutput.toString().replaceAll("\r", ""), "Find command did not find the correct tasks");
    }

    @Test
    public void cannotFindTask() {

        // Mimic user input to be DuMMy 1
        String input = "I love mugging everyday";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ui = new Ui();
        findTaskCommand = new FindTaskCommand(taskList, ui);

        findTaskCommand.execute();

        String expected = "What task would you like to find? : " + 
            "-".repeat(65) + "\n" +
            "Im sorry. Seems like no tasks in the task list fits the description!\n"+
            "-".repeat(65) + "\n";
        
        assertEquals(expected, actualOutput.toString().replaceAll("\r", ""), "Find command found something which they should not find");
    }
}
