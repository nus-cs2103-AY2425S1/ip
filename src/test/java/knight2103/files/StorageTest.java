package knight2103.files;
import knight2103.tasks.Task;
import knight2103.tasks.TodoTask;
import knight2103.tasks.DeadlineTask;
import knight2103.tasks.EventTask;
import knight2103.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void loadFileContents_missingFile_exceptionThrown() {
        final String DIRECTORY_NOT_FOUND = "./testing.txt";
        try {
            assertEquals(0, new Storage(DIRECTORY_NOT_FOUND).loadFileContents());
            fail(); // the test should not reach this line
        } catch (FileNotFoundException e) {
            assertEquals(
                    String.format("%s (The system cannot find the file specified)",
                            DIRECTORY_NOT_FOUND.replace("/", "\\")),
                    e.getMessage());
        }
    }

    @Test
    public void loadFileContents_fileInput_correctOutput() throws FileNotFoundException {
        final String TEST_DIRECTORY = "./testTaskList1.txt";
        ArrayList<Task> expectedList = new ArrayList<Task>();
        expectedList.add(new TodoTask("read book"));
        expectedList.add(new DeadlineTask("return book", "2020-12-20"));
        EventTask placeholder = new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00");
        placeholder.markDone();
        expectedList.add(placeholder);

        Pair<ArrayList<Task>, String> expectedOutput = new Pair<ArrayList<Task>, String>(expectedList, "");

        assertEquals(expectedOutput.toString(),
                new Storage(TEST_DIRECTORY).loadFileContents().toString()); // toString to prevent reference comparison
    }

    @Test
    public void loadFileContents_fileContentsError_someErrorShown() throws FileNotFoundException {
        final String TEST_DIRECTORY = "./testTaskList2.txt";
        ArrayList<Task> expectedList = new ArrayList<Task>();
        expectedList.add(new TodoTask("read book"));
        String expectedMessage =
                "\nFile line 2 - Errors found: Only T, E, D accepted but other characters found in first column"
                        + "\nFile line 3 - Errors found: the value of the 2nd column should only be 1 or 0"
                        + "\nFile line 4 - Errors found: Date format is wrong in the file contents."
                        + " For Deadline task, it should be yyyy-MM-dd format."
                        + " For Events task, it should be yyyy-MM-ddThh:mm format";

        Pair<ArrayList<Task>, String> expectedOutput =
                new Pair<ArrayList<Task>, String>(expectedList, expectedMessage);

        assertEquals(expectedOutput.toString(), new Storage(TEST_DIRECTORY).loadFileContents().toString());
    }

    @Test
    public void loadFileContents_fileContentsError_columnMistMatchAndTimeError() throws FileNotFoundException {
        final String TEST_DIRECTORY = "./testTaskList3.txt";
        ArrayList<Task> expectedList = new ArrayList<Task>();
        expectedList.add(new TodoTask("read book"));
        String expectedMessage =
                "\nFile line 1 - Errors found: Number of columns mismatch. 4 columns for DeadlineTask expected"
                        + "\nFile line 3 - Errors found: Number of columns mismatch. 3 columns for TodoTask expected"
                        + "\nFile line 4 - Errors found: Start date&time of Event Task must be BEFORE end date&time";
        Pair<ArrayList<Task>, String> expectedOutput =
                new Pair<ArrayList<Task>, String>(expectedList, expectedMessage);

        assertEquals(expectedOutput.toString(), new Storage(TEST_DIRECTORY).loadFileContents().toString());
    }
}
