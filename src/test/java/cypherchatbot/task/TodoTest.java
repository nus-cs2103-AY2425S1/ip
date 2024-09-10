package cypherchatbot.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testFileString(){
        assertEquals("T|0|task description test", new ToDo("task description test").toStringInFile());
    }

    @Test
    public void testFileStringWithCompletion(){
        Task task = new ToDo("task description test");
        task.completeTask();
        assertEquals("T|1|task description test", task.toStringInFile());
    }
}
