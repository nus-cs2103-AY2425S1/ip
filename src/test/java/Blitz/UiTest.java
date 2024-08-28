package blitz;

/* My import */
import exception.BlitzNoParameterException;

import task.Todo;

/* System import */
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void printTextBetweenDivider_arrayOfString_textPrinted() {
        String[] strings = {"abc", "123"};
        new Ui("    -----------------------------------------------\n", "    ").printInDivider(strings);

        String output = outputStreamCaptor.toString();
        String expected = "    -----------------------------------------------\n" +
                "    abc\n" + "    123\n" + "    -----------------------------------------------\n";
        assertEquals(expected, output);
    }

    @Test
    public void printTaskAddedBetweenDivider_currentTaskListSizeAndTaskTypeAndTaskObject_textPrinted() {
        String type = "T";
        int size = 1;
        Todo task = new Todo("123", "T", false);
        new Ui("    -----------------------------------------------\n", "    ").printTaskAddedWithDivider(type, size, task);

        String output = outputStreamCaptor.toString();
        String expected = "    -----------------------------------------------\n" +
                "    Got it. I've added this task:\n" + "      [T][ ] 123\n" + "    Now you have 1 tasks in the list.\n" +
                "    -----------------------------------------------\n";
        assertEquals(expected, output);
    }

    @Test
    public void printErrorMessageBetweenDivider_blitzExceptionError_textPrinted() {
        new Ui("    -----------------------------------------------\n", "    ").printError(new BlitzNoParameterException());

        String output = outputStreamCaptor.toString();
        String expected = "    -----------------------------------------------\n" +
                "    Missing parameter(s) for command!\n" + "    -----------------------------------------------\n";
        assertEquals(expected, output);
    }
}
