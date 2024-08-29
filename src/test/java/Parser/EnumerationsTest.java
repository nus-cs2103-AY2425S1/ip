package Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumerationsTest {

    @Test
    public void byeTest() {
        assertEquals(Enumerations.BYE, Enumerations.convertString("bye"));
    }

    @Test
    public void scheduleTest() {
        assertEquals(Enumerations.SCHEDULE, Enumerations.convertString("schedule"));
    }

    @Test
    public void markTest() {
        assertEquals(Enumerations.MARK, Enumerations.convertString("mark"));
    }

    @Test
    public void unMarkTest() {
        assertEquals(Enumerations.UNMARK, Enumerations.convertString("unmark"));
    }

    @Test
    public void deleteTest() {
        assertEquals(Enumerations.DELETE, Enumerations.convertString("delete"));
    }

    @Test
    public void taskTest() {
        assertEquals(Enumerations.TASK, Enumerations.convertString("randomletters"));
    }

}
