package sigma.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import sigma.exception.SigmaException;
import sigma.task.TodoTask;
import sigma.utils.TaskList;


public class DeleteCommandTest {

    @Test
    public void deleteCommand_normalDeletion_deletesTaskSuccessfully() {
        TaskList tl = new TaskList(new ArrayList<>());
        tl.add(new TodoTask("buy groceries"));
        tl.add(new TodoTask("read a book"));

        DeleteCommand dc = new DeleteCommand(new String[]{"delete", "1"});
        try {
            dc.execute(tl, null, null);
        } catch (SigmaException e) {
            e.printStackTrace();
        }
        assertEquals(1, tl.size());
        assertEquals("[T][ ] read a book", tl.get(0).toString());
    }

    @Test
    public void deleteCommand_missingIndex_throwsException() {
        DeleteCommand dc = new DeleteCommand(new String[]{"delete"});
        TaskList tl = new TaskList(new ArrayList<>());
        SigmaException thrown = assertThrows(SigmaException.class, () -> dc.execute(tl, null, null));
        assertEquals("What the sigma? You're missing the task! Write \"delete <task>\"!", thrown.getMessage());
    }

    @Test
    public void deleteCommand_negativeIndex_throwsException() {
        TaskList tl = new TaskList(new ArrayList<>());
        tl.add(new TodoTask("buy groceries"));

        DeleteCommand dc = new DeleteCommand(new String[]{"delete", "-1"});
        SigmaException thrown = assertThrows(SigmaException.class, () -> dc.execute(tl, null, null));
        assertEquals("What the skibidi? Invalid task number!", thrown.getMessage());
    }

    @Test
    public void deleteCommand_indexGreaterThanSize_throwsException() {
        TaskList tl = new TaskList(new ArrayList<>());
        tl.add(new TodoTask("buy groceries"));

        DeleteCommand dc = new DeleteCommand(new String[]{"delete", "2"});
        SigmaException thrown = assertThrows(SigmaException.class, () -> dc.execute(tl, null, null));
        assertEquals("What the skibidi? Invalid task number!", thrown.getMessage());
    }

    @Test
    public void deleteCommand_deletingFromEmptyList_throwsException() {
        TaskList tl = new TaskList(new ArrayList<>());

        DeleteCommand dc = new DeleteCommand(new String[]{"delete", "1"});
        SigmaException thrown = assertThrows(SigmaException.class, () -> dc.execute(tl, null, null));
        assertEquals("What the skibidi? Invalid task number!", thrown.getMessage());
    }


}
