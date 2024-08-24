import Task.Task;
import Task.TaskList;
import Utilities.Parser;
import Utilities.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    public void testTime(){
        Parser parser = new Parser();
        assertEquals("2000-12-12T18:00", parser.parseDateTime("12/12/2000 1800").toString());
    }

    @Test
    public void testInvalidTime(){
        Parser parser = new Parser();
        assertNull(parser.parseDateTime("12/13/2000 1800"));
    }

    @Test
    public void testExit(){
        Parser parser = new Parser();
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        Parser dateTimeParser = new Parser();
        Ui ui = new Ui();
        assertEquals(true, parser.parseCommand(tasks, "bye", taskList, ui, dateTimeParser));
    }

    @Test
    public void testDefaultCommand(){
        Parser parser = new Parser();
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList();
        Parser dateTimeParser = new Parser();
        Ui ui = new Ui();
        assertEquals(false, parser.parseCommand(tasks, "abc123", taskList, ui, dateTimeParser));
    }
}