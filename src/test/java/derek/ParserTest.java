package derek;

import derek.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ParserTest {

    private Parser parser;
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        taskList = new TaskList();
        storage = new Storage(taskList);
        ui = new Ui(storage, taskList);
    }

    @Test
    void testGetCommand_withValidCommand() {
        parser = new Parser("list", storage, ui);
        assertDoesNotThrow(() -> parser.getCommand());
    }

    @Test
    void testGetCommand_withInvalidCommand() {
        parser = new Parser("invalidCommand", storage, ui);
        parser.getCommand();
        assertTrue(outContent.toString().contains("Please enter your commands correctly for Derek"));
    }

}
