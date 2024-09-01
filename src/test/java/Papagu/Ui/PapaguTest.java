package Papagu.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PapaguTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private ByteArrayInputStream inputStream;
    private InputStream originalIn;


    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        inputStream = new ByteArrayInputStream("bye\n".getBytes());
        originalIn = System.in;
        System.setIn(inputStream);
    }

    @Test
    public void testWelcomeAndByeMessage() {
        String simulatedInput = "bye\n";
        inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Papagu papagu = new Papagu("src/main/java/Data/tasks.txt");
        papagu.run();
        String expectedOutput = "____________________________________________________________\n" +
                "Hello! I'm Papagu\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n" +
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void TestList() {
        String simulatedInput = "list\nbye\n";
        inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        Papagu papagu = new Papagu("src/main/java/Data/tasks.txt");
        papagu.run();
        String expectedOutput = "____________________________________________________________\n" +
                "Hello! I'm Papagu\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n" +
                "____________________________________________________________\n" +
                "Here are the tasks in your list:\n" +
                "1.[T][X] read book\n" +
                "2.[D][ ] return book (by: Jun-07-2014 18:00)\n" +
                "3.[E][ ] project meeting (from: Aug-10-2024 14:00 to: 16:00)\n" +
                "4.[T][X] join sports club\n" +
                "5.[D][ ] return book (by: Dec-02-2019 18:00)\n" +
                "6.[D][ ] test code (by: Feb-07-2019 12:30)\n" +
                "7.[E][ ] lck finals (from: Feb-02-2024 16:00 to: 21:00)\n" +
                "8.[T][ ] test\n" +
                "9.[D][X] return book (by: Dec-02-2019 18:00)\n" +
                "\n" +
                "____________________________________________________________\n" +
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void TestEvent() {
        String simulatedInput = "event Holiday Prep /from 5/10/2010 0930 /to 2130\nbye\n";
        inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        Papagu papagu = new Papagu("src/main/java/Data/tasks.txt");
        papagu.run();

        String expectedOutput = "____________________________________________________________\n" +
                "Hello! I'm Papagu\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n" +
                "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "[E][ ] Holiday Prep (from: Oct-05-2010 09:30 to: 21:30)\n" +
                "____________________________________________________________\n"+
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
