package sinatra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ParserTest {
    private Parser parser;

    @Test
    public void checkIsTaskInTasksMemoryTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new sinatra.ToDo("sing", false));
        tasks.add(new sinatra.ToDo("dance", false));
        ToDo testTask = new sinatra.ToDo("sing", false);
        Boolean output = true;
        Parser parser = new sinatra.Parser();
        parser.setTasks(tasks);
        Boolean actual = parser.isTaskInTasksMemory(testTask);
        assertEquals(output, actual);
    }

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        parser.deleteAllTasks();
    }

    @Test
    public void listCommandTest() {
        parser.handleInputs("todo taskTest");
        List<String> response = parser.handleInputs("list");
        int index = parser.getTaskSize();
        System.out.println(index + ".[T][ ] taskTest");
        System.out.println(response);
        assertTrue(response.contains(index + ".[T][ ] taskTest"));
    }

    @Test
    public void unknownCommandTest() {
        List<String> response = parser.handleInputs("unknown command");
        assertTrue(response.contains("OOPS!!! I'm sorry, but I don't know what that means :-("));
    }

    @Test
    public void deadlineCommandTest() {
        List<String> response = parser.handleInputs("deadline return book /by 2/12/2019 1800");
        assertTrue(response.contains(" [D][ ] return book (by: 2/12/2019 1800)"));
    }

    @Test
    public void eventCommandTest() {
        List<String> response = parser.handleInputs("event project meeting /from Mon 2pm /to 4pm");
        System.out.println(response.get(1));
        assertTrue(response.contains(" [E][ ] project meeting (from: Mon 2pm to: 4pm)"));
    }


}
