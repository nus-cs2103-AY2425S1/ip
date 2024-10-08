package bob;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import bob.task.Task;
import bob.task.Todo;

/**
 * Tests the Parser class.
 */
public class ParserTest {

    /*
     * Checks if all tasks are correctly added to the task list.
     */
    public void executeListCommandTest() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("task1"));
        tasks.add(new Todo("task2"));

        assertEquals(Parser.executeListCommand(tasks), 
                "Here are the tasks in your list: \n1. [T][ ] task1\n2. [T][ ] task2\n");

        tasks.add(new Todo("task3"));

        assertEquals(Parser.executeListCommand(tasks), 
                "Here are the tasks in your list: \n1. [T][ ] task1\n2. [T][ ] task2\n3. [T][ ] task3\n");
    }

    /**
     * Checks if the method correctly parses a string into a LocalDateTime object.
     */
    public void parseDateTimeTest() {
        try {
            assertEquals(Parser.parseDateTime("02/12/2019 1800"), 
                    LocalDateTime.of(2019, 12, 2, 18, 0));
            assertEquals(Parser.parseDateTime("2/12/2019 1800"), 
                    LocalDateTime.of(2019, 12, 2, 18, 0));
            assertEquals(Parser.parseDateTime("02/2/2019 1800"), 
                    LocalDateTime.of(2019, 2, 2, 18, 0));
            assertEquals(Parser.parseDateTime("02/12/2019 1000"), 
                    LocalDateTime.of(2019, 12, 2, 10, 0));
        } catch (BobException e) {
            System.out.println(e.getMessage());
        }
    }
}
