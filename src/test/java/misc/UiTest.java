package misc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.PositionException;
import task.Task;
import task.Tasklist;

public class UiTest {
    @Test
    public void blankline_standard_printLine() {
        String expectedOutput = ("Bye. Hope to see you again soon!");

        Ui ui = new Ui();
        String actualOutput = ui.endGame();

        assertEquals(expectedOutput.trim(), actualOutput);
    }

    @Test
    public void markDone_standardTask_standardOutput() {
        String dummyTask = "[X] Task";
        Tasklist dummyTl = new Tasklist();
        String expectedOutput = ("Nice! I've marked this task as done:" + "\n"
                + dummyTask);
        dummyTl.add(new Task("Task"));

        Ui ui = new Ui();
        String actualOutput = "";
        try {
            actualOutput = ui.replyMarkDone(0, dummyTl);
        } catch (PositionException e) {
            return;
        }
        assertEquals(expectedOutput.trim(), actualOutput);
    }

    @Test
    public void markDone_missingTask_exceptionThrown() {
        Tasklist dummyTl = new Tasklist();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            new Ui().replyMarkDone(0, dummyTl); });
    }
}
