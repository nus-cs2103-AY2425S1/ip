import com.meow.com.tasks.Task;
import com.meow.com.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class TodoTest {


    @Test
    public void testTaskCreation() {
        Task test = new Todo("mewwing");
        assertEquals(test.toString(), "[T][ ] mewwing");
    }


    public void testTaskType() {
        Task test = new Todo("meow");
        //assertEquals(test.getType(), "Todo");
    }
}
