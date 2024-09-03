package tars;  //same package as the class being tested

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;


/**
 * This class handles the testcases to test the parser class in Tars
 * Two different test methods to test 2 different possible bugs that Tars can encounter
 *
 * @author csk
 * @version 1
 */
public class ParserTest {
    private Parser parser;
    private TaskList taskList = new TaskList(new ArrayList<>());
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    static String line = "    _____________________________________________";
    @BeforeEach
    void initialise(){
        parser = new Parser();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Checks on the error of calling for an empty list by the user, possibly when starting the application
     */
    @Test
    public void taskList_EmptyList() {
        parser.listPrint(taskList);
        String expectedMessage = "No tasks added to list. Please add events/deadline/todos!\n";

        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    public void checkEntry_testPartialTask() {
        String[] entryParts = {"todo"};
        String entry = "todo";

        String expectedMessage = line + "\n" + "    OOPS! Describe the task/event/deadline/todo or list"
                                + "\n" + line+ "\n";
        parser.checkEntry(entryParts, entry, taskList);

        assertEquals(expectedMessage, outContent.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
