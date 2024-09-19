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
    static String line = " _____________________________________________";
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
        String result = parser.listPrint(taskList);
        String expectedMessage = "No tasks added to list. Please add events/deadline/todos!";

        assertEquals(expectedMessage, result);
    }

    @Test
    public void checkEntry_testPartialTask() {
        String[] entryParts = {"t"};
        String entry = "t";

        String expectedMessage = line + "\n" + "    OOPS! Describe the t/e/d or list" + "\n" + line;
        String result = parser.checkEntry(entryParts, entry, taskList);

        assertEquals(expectedMessage, result);
    }

    @Test
    public void checkEntry_testInvalidInput() {
        String[] entryParts = {"finishing", "homework", "later"};
        String entry = "finishing homework later";

        String expectedMessage = "OOPS! Unable to add task. Please type 'help'!";
        String result = parser.addTask(entryParts, entry, taskList);

        assertEquals(expectedMessage, result);
    }

    @Test
    public void checkEntry_testInvalidFeatureInput() {
        String[] entryParts = {"delete", "read", "book"};
        String entry = "delete read book";

        String expectedMessage = "For input string: \"book\".Please state task index followed by m/um/delete command";
        String result = parser.addTask(entryParts, entry, taskList);

        assertEquals(expectedMessage, result);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
