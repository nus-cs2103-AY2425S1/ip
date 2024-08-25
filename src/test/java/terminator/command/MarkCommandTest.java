package terminator.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import terminator.task.Task;
import terminator.task.TodoTask;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {

    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private static final String ERR_MSG = """
            Index to mark cannot be empty.
            
            Usage: mark <index>""";

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5"})
    public void execute_validMarkIndices_success(String index) throws DukeException {
        MarkCommand mc = new MarkCommand(index);
        Task[] tasks = new Task[] {
                new TodoTask("task 1"),
                new TodoTask("task 2"),
                new TodoTask("task 3"),
                new TodoTask("task 4"),
                new TodoTask("task 5"),
        };
        ArrayList<Task> arr = new ArrayList<>(Arrays.asList(tasks));
        mc.execute(arr);
        String expectedOutput = "Objective marked as completed. Awaiting next directive:\n[T][X] task " + index + "\n";
        assertEquals(
                expectedOutput,
                outputStreamCaptor.toString());
    }
}
