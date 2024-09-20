package bellroy;

import bellroy.GUI.Ui;
import bellroy.parser.Parser;
import bellroy.storage.Storage;
import bellroy.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser();
    TaskList tasklist = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("Bellroy.txt");
    @Test
    public void testDeadlineDueDateError() {
        String output = parser.parse("deadline homework /by 3pm", tasklist, ui, storage);
        assertEquals(output, "Please input a valid date-time format");
    }

    @Test
    public void testDeadlineFormatError() {
        String output = parser.parse("deadline homework", tasklist, ui, storage);
        assertEquals(output, "Hoot! Please ensure your format is correct!\n" + "e.g deadline (description) /by (dueDate)\n");
    }

    @Test
    public void testEventFormatError() {
        String output = parser.parse("event laundry /from 6pm", tasklist, ui, storage);
        assertEquals(output, "Hoot! Please ensure your format is correct!\n" + "e.g. event (description) /from (start) /to (end)\n");
    }
}
