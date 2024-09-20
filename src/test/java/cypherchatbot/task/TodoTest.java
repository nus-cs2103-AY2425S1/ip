package cypherchatbot.task;
import cypherchatbot.CypherException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testDisplayString(){
        assertEquals("[T][ ] example", new ToDo("example").toString());
    }

    @Test
    public void testDisplayStringWithCompletion(){
        try {
            Task task = new ToDo("example");
            task.markAsComplete();
            assertEquals("[T][X] example", task.toString());
        } catch (CypherException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testFileString(){
        assertEquals("T|0|task description test", new ToDo("task description test").toStringInFile());
    }

    @Test
    public void testFileStringWithCompletion(){
        try {
            Task task = new ToDo("task description test");
            task.markAsComplete();
            assertEquals("T|1|task description test", task.toStringInFile());
        } catch (CypherException e) {
            System.out.println(e.getMessage());
        }
    }
}
