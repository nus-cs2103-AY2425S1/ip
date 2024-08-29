package misc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import task.Task;
import task.Tasklist;

public class UiTest {
    @Test
    public void blankline_standard_printLine() {
        String expectedOutput = ("____________________________________________________________ \s"
        + "\n" + "Bye. Hope to see you again soon!");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream));
            new Ui().endGame();
            String capturedOutput = outputStream.toString();

            assertEquals(expectedOutput.trim(), capturedOutput.trim());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void markDone_standardTask_standardOutput() {
        String dummyTask = "[X] Task";
        Tasklist dummyTl = new Tasklist();
        String expectedOutput = ("Nice! I've marked this task as done:" + "\n"
                + dummyTask + "\n" + "____________________________________________________________ \s");
        dummyTl.add(new Task("Task"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream));
            new Ui().replyMarkDone(0, dummyTl);
            String capturedOutput = outputStream.toString();
            assertEquals(expectedOutput.trim(), capturedOutput.trim());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void markDone_missingTask_exceptionThrown() {
        Tasklist dummyTl = new Tasklist();

        assertThrows(IndexOutOfBoundsException.class, () -> { new Ui().replyMarkDone(0, dummyTl); });
    }
}
