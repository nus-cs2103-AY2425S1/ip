package sumode.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import sumode.util.Command;

public class DeadlineTest {

    @Test
    public void deadlineTestDate() {
        try {
            Task test = Task.of(Command.DEADLINE, "parktour with my hand /by 2024-08-06");
            assertEquals("D | 0 | parktour with my hand | Aug 6 2024", test.savedString());
            assertEquals("[D][ ]parktour with my hand (by: Aug 6 2024)", test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void deadlineTestNonDate() {
        try {
            Task test = Task.of(Command.DEADLINE, "parktour with my hand /by now");
            assertEquals("D | 0 | parktour with my hand | now", test.savedString());
            assertEquals("[D][ ]parktour with my hand (by: now)", test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void deadlineTestDateFromData() {
        try {
            Task test = Task.createFromData("D | 0 | parktour with my hand | 2024-08-06");
            assertEquals("D | 0 | parktour with my hand | Aug 6 2024", test.savedString());
            assertEquals("[D][ ]parktour with my hand (by: Aug 6 2024)", test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void deadlineTestNonDateFromData() {
        try {
            Task test = Task.createFromData("D | 1 | parktour with myself | now");
            assertEquals("D | 1 | parktour with myself | now", test.savedString());
            assertEquals("[D][X]parktour with myself (by: now)", test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void eventTestCorruptedFile() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Task test = Task.createFromData("D | 0  parktour with myself  now | morrow");
            System.out.println(test.toString());
        });
    }
}
