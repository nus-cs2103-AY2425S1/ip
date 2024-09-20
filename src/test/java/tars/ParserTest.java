package tars;  //same package as the class being tested

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

/**
 * This class handles the testcases to test the parser class in Tars
 * Two different test methods to test 2 different possible bugs that Tars can encounter
 *
 * @author SKarthikeyan28
 */
public class ParserTest {
    private Parser parser;
    private TaskList taskList = new TaskList(new ArrayList<>());

    //Checked with Chat-GPT on ways to write tests to check if parser methods can handle a correct entry
    //and also return error messages when given wrong entries
    //mainly asked about the assertEquals method
    @BeforeEach
    void initialise(){
        parser = new Parser();
    }

    /**
     * Tests on the error of calling for an empty list by the user, possibly when starting the application
     */
    @Test
    public void taskList_EmptyList() {
        String result = parser.listPrint(taskList);
        String expectedMessage = "No tasks added to list. Please add events/deadline/todos!";

        assertEquals(expectedMessage, result);
    }

    /**
     * Tests on the error of partial input given by user in attempt to try adding a task
     */
    @Test
    public void checkEntry_testPartialTask() {
        String[] entryParts = {"t"};
        String entry = "t";

        String expectedMessage = " OOPS! Describe the t/e/d or list";
        String result = parser.checkEntry(entryParts, entry, taskList);

        assertEquals(expectedMessage, result);
    }

    /**
     * Tests on the error of an invalid input by the user that is not in line with expected format
     */
    @Test
    public void checkEntry_testInvalidInput() {
        String[] entryParts = {"finishing", "homework", "later"};
        String entry = "finishing homework later";

        String expectedMessage = "OOPS! Wrong input. Please type 'help'!";
        String result = parser.addTask(entryParts, entry, taskList);

        assertEquals(expectedMessage, result);
    }

    /**
     * Tests on the error of calling features with invalid input, like name of task instead of index
     */
    @Test
    public void checkEntry_testInvalidFeatureInput() {
        String[] entryParts = {"delete", "read", "book"};
        String entry = "delete read book";

        String expectedMessage = "For input string: \"book\".Please state task index followed by m/um/delete command";
        String result = parser.addTask(entryParts, entry, taskList);

        assertEquals(expectedMessage, result);
    }
}
