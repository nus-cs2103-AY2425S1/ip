package knight2103.files;
import knight2103.tasks.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// NEED TO RELOOK AT THIS (THROWS FILENOTFOUNDEXCEPTION ISSUE)
public class StorageTest {
    @Test
    public void load_missingFile_emptyArray() throws FileNotFoundException {
        assertEquals(new ArrayList<>(), new Storage("./testing.txt").loadFileContents()); // expect empty List as FileNotFound exception
    }

    @Test
    public void load_fileInput_correctOutput() throws FileNotFoundException {
        String fileDirectory = "./testTaskList1.txt";
        ArrayList<Task> expectedOutput = new ArrayList<Task>();
        expectedOutput.add(new TodoTask("read book"));
        expectedOutput.add(new DeadlineTask("return book", "2020-12-20"));
        EventTask placeholder = new EventTask("book fair", "2020-12-21T09:00", "2020-12-22T22:00");
        placeholder.markDone();
        expectedOutput.add(placeholder);
        assertEquals(expectedOutput.toString(), new Storage(fileDirectory).loadFileContents().toString()); // toString to prevent reference comparison
    }

    @Test
    // ACTUALLY HOW TO WRITE ThiS TEST
    public void load_fileInputError_someException() throws FileNotFoundException {
        String fileDirectory = "./testTaskList2.txt";
        ArrayList<Task> expectedOutput = new ArrayList<Task>();
        expectedOutput.add(new TodoTask("read book"));
        assertEquals(expectedOutput.toString(), new Storage(fileDirectory).loadFileContents().toString());
    }

}
