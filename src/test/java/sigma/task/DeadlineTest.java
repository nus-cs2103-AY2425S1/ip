package sigma.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import sigma.Parser;
import sigma.TaskList;

public class DeadlineTest {
    TaskList taskList;
    Parser parser;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        taskList = new TaskList();
        parser = new Parser();
    }

    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("homework", true, "2024-08-30 14:30:00");
        String actual = deadline.toString();
        String expected = "[D][X] homework (by: 2024-08-30 14:30:00)";
        assertEquals(expected, actual);
    }

    // comparison shows that the contents are literally identical so idk whats wrong with me
    @Test
    public void testHandleDeadline() {
        String userInput = "deadline implement unit test /by 2024-09-01 14:30:00";
        parser.handleDeadline(userInput);
        // Check if the task was added to the TaskList
        assertEquals(1, TaskList.getSize());
        Task task = TaskList.get(0);
        assertInstanceOf(Deadline.class, task);
        assertEquals("implement unit test", task.getName());
        assertEquals("Sep 01 2024 14:30:00", ((Deadline) task).getDeadline());

        // Check the output
        String expectedOutput = "added deadline task:\n  [D][ ] implement unit test (by: Sep 01 2024 14:30:00)\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}
