package MeowMeow;

import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class SavingTest {

    public String printFirstTask(TaskList list) {
        return list.get(0).toString();
    }
    @Test
    public void load_success() throws IOException {
        Saving s = new Saving("./data/test.txt");
        s.load("./data/test.txt");
        assertEquals("[T][ ] borrow book", printFirstTask(s.getTaskList()));
    }

    @Test
    public void load_fail_FileNotFoundExceptionThrown() {
        try {
            Saving s = new Saving("./data/fake.txt");
            s.load("./data/fake.txt");
            assertEquals("", printFirstTask(s.getTaskList()));
            fail();
        } catch (Exception e) {
            assertEquals("./data/fake.txt (No such file or directory)", e.getMessage());
        }
    }

}
