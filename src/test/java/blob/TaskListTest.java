package blob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private TaskList tl = new TaskList(new Storage("./src/test/java/blob/TaskListTest.csv"));

    @Test
    public void deleteTask_throwsException_fromEmptyCSVFile() {
        try {
            tl.deleteTask(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Exception thrown!", "Exception thrown!");
        }
    }

    @Test
    public void getTask_throwsException_fromEmptyCSVFile() {
        try {
            tl.getTask(10);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Exception thrown!", "Exception thrown!");
        }
    }
}
