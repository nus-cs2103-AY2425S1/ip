package sumode.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import sumode.exception.AlreadyMarkedException;
import sumode.exception.AlreadyUnmarkedException;
import sumode.util.Command;

public class TodoTest {

    @Test
    public void todoTest() {
        try {
            Task test = Task.of(Command.TODO, "parktour with my hand");
            test.mark();
            assertEquals("T | 1 | parktour with my hand", test.savedString());
            assertEquals("[T][X]parktour with my hand", test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void todoFromData() {
        try {
            Task test = Task.createFromData("T | 0 | parktour with my hand 2024-08-06");
            assertEquals("T | 0 | parktour with my hand 2024-08-06", test.savedString());
            assertEquals("[T][ ]parktour with my hand 2024-08-06", test.toString());
        } catch (Exception e) {
            fail("Exception is thrown when is it not supposed to be. Exception message below\n"
                    + e.getMessage());
        }
    }

    @Test
    public void todoTestCorruptedFile() {
        assertThrows(IndexOutOfBoundsException.class, this::todoTestCorruptedFileHelper);
    }

    private void todoTestCorruptedFileHelper() {
        Task test = Task.createFromData("T | 0  parktour morrow");
        System.out.println(test.toString());
    }

    @Test
    public void unknownTaskCorruptedFile() {
        assertThrows(IllegalArgumentException.class, this::unknownTaskCorruptedFileHelper);
    }

    private void unknownTaskCorruptedFileHelper() {
        Task test = Task.createFromData("F | 0 | parktour morrow");
        System.out.println(test.toString());
    }

    @Test
    public void testUnmarkError() {
        Exception exception = assertThrows(AlreadyUnmarkedException.class, this::testUnmarkErrorHelper);
        assertEquals("""
                        Sumo confused. This task is not completed in the first place!
                        But SUMO will mark it as NOT done again!
                        [T][ ]parktour with my hand 2024-08-06""",
                        exception.getMessage());
    }

    private void testUnmarkErrorHelper() throws AlreadyUnmarkedException {
        Task test = Task.createFromData("T | 0 | parktour with my hand 2024-08-06");
        test.unmark();
    }

    @Test
    public void testMarkError() {
        Exception exception = assertThrows(AlreadyMarkedException.class, this::testMarkErrorHelper);
        assertEquals("""
                        Sumo confused. This task is marked as done in the first place!
                        But SUMO will mark it as done again!
                        [T][X]parktour with my hand 2024-08-06""",
                exception.getMessage());
    }

    private void testMarkErrorHelper() throws AlreadyMarkedException {
        Task test = Task.createFromData("T | 1 | parktour with my hand 2024-08-06");
        test.mark();
    }


}
