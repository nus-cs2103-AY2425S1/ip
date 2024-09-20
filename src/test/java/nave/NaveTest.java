package nave;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NaveTest {
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static ByteArrayInputStream input;
    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(output));
    }
    @Test
    public void runAndExit() {
        input = new ByteArrayInputStream("bye".getBytes());
        System.setIn(input);
        Nave.main(null);
        assertEquals("""
                -----------------------------------------------------------------
                Hello! :) I'm Nave, your personal task management assistant.
                What can I do for you today?
                -----------------------------------------------------------------
                -----------------------------------------------------------------
                Goodbye :( Come visit me again
                -----------------------------------------------------------------
                """, output.toString());
        output.reset();
    }

    @Test
    public void helpMessage() {
        input = new ByteArrayInputStream("/help\nbye".getBytes());
        System.setIn(input);
        Nave.main(null);
        assertEquals("""
                -----------------------------------------------------------------
                Hello! :) I'm Nave, your personal task management assistant.
                What can I do for you today?
                -----------------------------------------------------------------
                -----------------------------------------------------------------
                /help: shows all available commands
                list: shows all tasks
                todo [name]: adds a todo with associated name
                deadline [name] /by [date]: adds a deadline with associated name and date
                event [name] /from [date] /to [date]: adds an event with associated name,
                    start date and end date
                bye: ends the Nave.Nave chatbot
                -----------------------------------------------------------------
                -----------------------------------------------------------------
                Goodbye :( Come visit me again
                -----------------------------------------------------------------
                """, output.toString());
        output.reset();
    }
}
