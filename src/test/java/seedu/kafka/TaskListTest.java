package seedu.kafka;

import kafka.Deadline;
import kafka.Event;
import kafka.TaskList;
import kafka.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private TaskList taskList;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    public void printList_emptyTaskList_displaysNothing() {
        outputStream.reset();
        taskList.printList();
        assertEquals("", outputStream.toString().trim());
    }

    //@Test
    /**public void printList_nonEmptyTaskList_displaysTasks() {
        outputStream.reset();

        taskList.addTask(new Todo("Todo sample", false));
        taskList.addTask(new Deadline("Deadline sample", LocalDateTime.of(2024, 1, 1, 12, 0), false));
        taskList.addTask(new Event("Event sample", LocalDateTime.of(2024, 1, 1, 12, 0), LocalDateTime.of(2024, 1, 1, 14, 0), false));

        taskList.printList();

        String expectedOutput = "1.[T][ ] Todo sample\n"
                + "  2.[D][ ] Deadline sample(by: January 01 2024 1200)\n"
                + "  3.[E][ ] Event sample(from: January 01 2024 1200 to: January 01 2024 1400)";

        assertEquals(expectedOutput, outputStream.toString().trim());
    }**/
}
