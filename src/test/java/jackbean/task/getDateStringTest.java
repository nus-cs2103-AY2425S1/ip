package jackbean.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class getDateStringTest {
    @Test
    public void getDateStringTest() {
        Deadline deadline = new Deadline("return book", "2021-08-24");
        assertEquals("Aug 24 2021", deadline.getDateString(deadline.getBy()));
    }

    @Test
    public void getDateStringTest2() {
        Deadline deadline = new Deadline("return book", "2024-02-27");
        assertEquals("Feb 27 2024", deadline.getDateString(deadline.getBy()));
    }

    @Test
    public void getDateStringTest3() {
        Deadline deadline = new Deadline("return book", "2022-12-31");
        assertEquals("Dec 31 2022", deadline.getDateString(deadline.getBy()));
    }
}
