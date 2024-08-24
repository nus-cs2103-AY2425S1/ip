package sumode.task;

import org.junit.jupiter.api.Test;
import sumode.util.Command;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void eventTestDateLegit() {
        try {
            Task test = Task.of(Command.EVENT,"parktour with my hand /from 2024-08-06 /to 2024-08-07");
            assertEquals("E | 0 | parktour with my hand | 2024-08-06 | 2024-08-07", test.savedString());
            assertEquals("[E][ ]parktour with my hand (from: Aug 6 2024 to: Aug 7 2024)",test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void eventTestDateFlipped() {
        try {
            Task test = Task.of(Command.EVENT,"parktour with my hand /to 2024-08-07 /from 2024-08-06");
            assertEquals("E | 0 | parktour with my hand | 2024-08-06 | 2024-08-07", test.savedString());
            assertEquals("[E][ ]parktour with my hand (from: Aug 6 2024 to: Aug 7 2024)",test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void eventTestNonDate() {
        try {
            Task test = Task.of(Command.EVENT,"parktour with myself /to morrow /from now");
            assertEquals("E | 0 | parktour with myself | now | morrow", test.savedString());
            assertEquals("[E][ ]parktour with myself (from: now to: morrow)",test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void eventTestDateFromData() {
        try {
            Task test = Task.createFromData("E | 0 | parktour with my hand | 2024-08-06 | 2024-08-07");
            assertEquals("E | 0 | parktour with my hand | 2024-08-06 | 2024-08-07", test.savedString());
            assertEquals("[E][ ]parktour with my hand (from: Aug 6 2024 to: Aug 7 2024)",test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void eventTestNonDateFromData() {
        try {
            Task test = Task.createFromData("E | 0 | parktour with myself | now | morrow");
            assertEquals("E | 0 | parktour with myself | now | morrow", test.savedString());
            assertEquals("[E][ ]parktour with myself (from: now to: morrow)",test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void eventTestCorruptedFile() {
        assertThrows(IndexOutOfBoundsException.class, this::eventTestCorruptedFileHelper);
    }

    private void eventTestCorruptedFileHelper() {
        Task test = Task.createFromData("E | 0  parktour with myself | now | morrow");
    }


}
