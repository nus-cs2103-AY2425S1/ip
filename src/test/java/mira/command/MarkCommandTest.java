package mira.command;

import mira.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {

    /**
     * Tests that MarkCommand throws IndexOutOfBoundsException for an invalid index (0).
     */
    @Test
    public void testMarkCommand_throwsIndexOutOfBoundsException() {
        TaskList taskList = new TaskList();
        MarkCommand command = new MarkCommand(0); // 0 is out of bounds for 1-based index
        command.setTaskList(taskList);

        assertThrows(IndexOutOfBoundsException.class, command::execute);
    }
}
