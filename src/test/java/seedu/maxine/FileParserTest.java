package seedu.maxine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class FileParserTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private FileParser fileparser;
    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outputStream));
        fileparser = new FileParser();
    }
    @Test
    public void todoParse() {
        Task task = fileparser.parse("T / 0 / cs2103t ip");
        assertInstanceOf(Todo.class, task);
    }
    @Test
    public void deadlineParse() {
        Task task = fileparser.parse("D / 0 / discussion / today");
        assertInstanceOf(Deadline.class, task);
    }
    @Test
    public void eventParse() {
        Task task = fileparser.parse("E / 0 / discussion / today / tomorrow");
        assertInstanceOf(Event.class, task);
    }
    @Test
    public void nullParse() {
        Task task = fileparser.parse("");
        assertNull(task);
    }
    @Test
    public void nullParse2() {
        Task task = fileparser.parse("some random words!");
        assertNull(task);
    }
}
