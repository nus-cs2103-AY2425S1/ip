//package seedu.maxine;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seedu.maxine.task.Task;
//import seedu.maxine.task.Todo;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import static org.junit.jupiter.api.Assertions.assertInstanceOf;
//
//public class CommandTest {
//    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    private FileParser fileparser;
//    @BeforeEach
//    public void setUp() {
//        // Redirect System.out to capture the output
//        System.setOut(new PrintStream(outputStream));
//         = new FileParser();
//    }
//    @Test
//    public void todoParse() {
//        Task task = fileparser.parse("T / 0 / cs2103t ip");
//        assertInstanceOf(Todo.class, task);
//    }
//}
