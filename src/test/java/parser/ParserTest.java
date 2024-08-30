package parser;
import exception.IncompleteDescException;
import exception.UnknownWordException;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseConversation() throws UnknownWordException, IncompleteDescException {
        Parser parser = new Parser();
        if(TaskList.getList() == null) {
           ArrayList<Task> list = new ArrayList<>();
            TaskList taskList = new TaskList(list);
        }
        String result = parser.parseConversation("todo Buy milk");
        assertEquals("Got it. I've added this task:\n  [T][ ] Buy milk\nNow you have 1 tasks in the list",
                result);
    }

}
