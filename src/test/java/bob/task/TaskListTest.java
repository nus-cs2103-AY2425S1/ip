package bob.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class TaskListTest {
    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;

    @BeforeEach
    public void initialiseTaskListTest() {
        System.setOut(new PrintStream(outputStreamCaptor));
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Code"));
        tasks.add(new Deadline("Submit quiz", LocalDate.parse("2024-09-10")));
        tasks.add(new Event("Birthday", "Monday", "5pm", "9pm"));
        taskList = new TaskList(tasks);
    }

    @Test
    public void testListRecords() {
        String expected = "\t____________________________________________________________\n"
                + "\t"
                + "Here are the tasks in your list:\n\t"
                + "1.[T][ ] Code\n\t"
                + "2.[D][ ] Submit quiz\n\t"
                + "3.[E][ ] Birthday\n\t"
                + "____________________________________________________________";
        taskList.listRecords();
        String actual = outputStreamCaptor.toString().replaceAll("\\s+$", "");
        assertEquals(expected, actual);
    }
}
