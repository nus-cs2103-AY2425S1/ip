package bellroy;

import bellroy.GUI.Ui;
import bellroy.parser.Parser;
import bellroy.storage.Storage;
import bellroy.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testDeadlineError() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("Bellroy.txt");
        String output = parser.parse("deadline homework /by 3pm", tasklist, ui, storage);
        assertEquals(output, "Please input a valid date-time format");
    }
}
