package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains methods to test the Undo Class.
 */
public class UndoTest {

    private static final String REPLY_NO_PREVIOUS_COMMAND = "There is no previous command to undo.";

    @Test
    public void undo_previousCommandExists_undoesCorrectCommand() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);

        Parser.parseCommand(taskList, "todo quiz");
        assertEquals(1, taskList.getNumberOfTasks());
        Parser.parseCommand(taskList, "undo");
        assertEquals(0, taskList.getNumberOfTasks());

        Parser.parseCommand(taskList, "todo homework");
        Parser.parseCommand(taskList, "deadline quiz /by 2022-02-02");
        Parser.parseCommand(taskList, "event lecture /from 2022-02-02 /to 2022-03-03");
        assertEquals(3, taskList.getNumberOfTasks());
        Parser.parseCommand(taskList, "undo");
        assertEquals(2, taskList.getNumberOfTasks());

        Parser.parseCommand(taskList, "deadline tutorial /by 2024-02-02");
        Parser.parseCommand(taskList, "delete 3");
        assertEquals(2, taskList.getNumberOfTasks());
        Parser.parseCommand(taskList, "undo");
        assertEquals(3, taskList.getNumberOfTasks());

        Parser.parseCommand(taskList, "mark 2");
        assertTrue(taskList.get(1).isDone);
        Parser.parseCommand(taskList, "undo");
        assertFalse(taskList.get(1).isDone);

        Parser.parseCommand(taskList, "mark 3");
        Parser.parseCommand(taskList, "unmark 3");
        assertFalse(taskList.get(2).isDone);
        Parser.parseCommand(taskList, "undo");
        assertTrue(taskList.get(2).isDone);
    }

    @Test
    public void undo_noPreviousCommand_noAction() throws DuckException {
        TaskList taskList = new TaskList(new Task[100], 0);

        assertEquals(REPLY_NO_PREVIOUS_COMMAND, Parser.parseCommand(taskList, "undo"));

        Parser.parseCommand(taskList, "todo quiz");
        Parser.parseCommand(taskList, "todo tutorial");
        assertEquals(2, taskList.getNumberOfTasks());

        Parser.parseCommand(taskList, "undo");
        assertEquals(1, taskList.getNumberOfTasks());
        assertEquals(REPLY_NO_PREVIOUS_COMMAND, Parser.parseCommand(taskList, "undo"));
    }
}
