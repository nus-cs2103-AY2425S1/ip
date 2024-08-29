package Tasks;

import Exceptions.TestamentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void todoConstructorTest() throws TestamentException {
        assertEquals("spend 50 meter", Task.of("todo spend 50 meter").taskname);
    }

    @Test
    public void todoToStringTest() throws TestamentException {
        assertEquals("[T][ ] spend 50 meter", Task.of("todo spend 50 meter").toString());
    }

    @Test
    public void deadlineConstructorTest() throws TestamentException {
        assertEquals("spend 50 meter ", Task.of("deadline spend 50 meter /by 2024-08-30").taskname);
    }

    @Test
    public void deadlineToStringTest() throws TestamentException {
        assertEquals("[D][ ] spend 50 meter (by: 30 Aug 2024)",
                Task.of("deadline spend 50 meter /by 2024-08-30").toString());
    }

    @Test
    public void eventConstructorTest() throws TestamentException {
        assertEquals("spend 50 meter ",
                Task.of("event spend 50 meter /from 2024-08-30 /to 2024-08-31").taskname);
    }

    @Test
    public void eventToStringTest() throws TestamentException {
        assertEquals("[E][ ] spend 50 meter (from: 30 Aug 2024 to: 31 Aug 2024)",
                Task.of("event spend 50 meter /from 2024-08-30 /to 2024-08-31").toString());
    }

}
