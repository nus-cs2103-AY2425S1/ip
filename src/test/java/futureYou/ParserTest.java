package futureyou;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import futureyou.exception.FutureYouException;

/**
 * Tests for the {@link TaskList} class.
 */
public class ParserTest {
    private final Parser parser = new Parser();
    private TaskList taskList;

    @BeforeEach
    public void initializeVariables() throws IOException {
        String testFile = "./test.txt";
        File file = new File(testFile);
        file.delete();

        // Clear task list before each test
        TaskList.clearTaskList();

        this.taskList = new TaskList(testFile);
    }

    @Test
    void parseToDoTask_emptyInput_exceptionThrown() {
        //@@author {Nicholas-Cheng-De-Fei}-reused
        // Reused from https://github.com/Nicholas-Cheng-De-Fei/ip/blob/master/src/test/java/quack/util/ParserTest.java

        String invalidUserCmd = "todo ";
        FutureYouException exception = assertThrows(FutureYouException.class, () -> Parser.parseToDoTask(invalidUserCmd), "Function did not throw Futur You Exception");
        String expectedErrorMsg = "Please enter a non empty task name";
        assertEquals(expectedErrorMsg, exception.getMessage(),
                "The exception message is incorrect");
        //@@author
    }
}
