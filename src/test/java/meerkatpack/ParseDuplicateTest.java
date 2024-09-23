package meerkatpack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import taskpack.TaskList;

public class ParseDuplicateTest {
    @Test
    public void testDetectDuplicates() throws IOException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        String output1 = Parser.parseTodoTask("eat", true);
        assertEquals(output1, ui.showTaskCreationMessage(taskList.getTaskList().get(0), taskList));
        assertEquals(taskList.getSize(), 1);
        String output2 = Parser.parseTodoTask("eat", true);
        assertEquals(output2, ui.showDuplicateDetectedMessage());
        assertEquals(taskList.getSize(), 1);
    }
}
