package sigma.command;

import org.junit.jupiter.api.Test;
import sigma.utils.TaskList;
import sigma.exception.SigmaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ToDoCommandTest {

    @Test
    public void todoCommand_normalInput_addsTaskSuccessfully() {
        ToDoCommand tc = new ToDoCommand(new String[]{"todo", "buy groceries"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            tc.execute(tl, null, null);
        } catch (SigmaException e) {
            e.printStackTrace();
        }
        assertEquals("[T][ ] buy groceries", tl.get(0).toString());
    }

    @Test
    public void todoCommand_missingTaskDescription_throwsException() {
        ToDoCommand tc = new ToDoCommand(new String[]{"todo"});
        TaskList tl = new TaskList(new ArrayList<>());
        SigmaException thrown = assertThrows(SigmaException.class, () -> tc.execute(tl, null, null));
        assertEquals("???? You're missing the task! Write \"todo <task>\"!", thrown.getMessage());
    }

    @Test
    public void todoCommand_emptyCommandArray_throwsException() {
        ToDoCommand tc = new ToDoCommand(new String[]{});
        TaskList tl = new TaskList(new ArrayList<>());
        SigmaException thrown = assertThrows(SigmaException.class, () -> tc.execute(tl, null, null));
        assertEquals("???? You're missing the task! Write \"todo <task>\"!", thrown.getMessage());
    }

    @Test
    public void todoCommand_taskDescriptionWithWhitespace_addsTaskSuccessfully() {
        ToDoCommand tc = new ToDoCommand(new String[]{"todo", "   read a book   "});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            tc.execute(tl, null, null);
        } catch (SigmaException e) {
            e.printStackTrace();
        }
        assertEquals("[T][ ] read a book", tl.get(0).toString());
    }

    @Test
    public void todoCommand_specialCharactersInTaskDescription_addsTaskSuccessfully() {
        ToDoCommand tc = new ToDoCommand(new String[]{"todo", "clean @home!"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            tc.execute(tl, null, null);
        } catch (SigmaException e) {
            e.printStackTrace();
        }
        assertEquals("[T][ ] clean @home!", tl.get(0).toString());
    }

    @Test
    public void todoCommand_longTaskDescription_addsTaskSuccessfully() {
        String longDescription = "This is a very long task description to see how the system handles large inputs without any issues";
        ToDoCommand tc = new ToDoCommand(new String[]{"todo", longDescription});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            tc.execute(tl, null, null);
        } catch (SigmaException e) {
            e.printStackTrace();
        }
        assertEquals("[T][ ] " + longDescription, tl.get(0).toString());
    }

    @Test
    public void todoCommand_taskDescriptionWithMultipleWords_addsTaskSuccessfully() {
        ToDoCommand tc = new ToDoCommand(new String[]{"todo", "complete the assignment"});
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            tc.execute(tl, null, null);
        } catch (SigmaException e) {
            e.printStackTrace();
        }
        assertEquals("[T][ ] complete the assignment", tl.get(0).toString());
    }

    @Test
    public void todoCommand_taskDescriptionIsWhitespace_throwsException() {
        ToDoCommand tc = new ToDoCommand(new String[]{"todo", "   "});
        TaskList tl = new TaskList(new ArrayList<>());
        SigmaException thrown = assertThrows(SigmaException.class, () -> tc.execute(tl, null, null));
        assertEquals("???? You're missing the task! Write \"todo <task>\"!", thrown.getMessage());
    }

    @Test
    public void todoCommand_nullInput_throwsException() {
        ToDoCommand tc = new ToDoCommand(null);
        TaskList tl = new TaskList(new ArrayList<>());
        try {
            tc.execute(tl, null, null);
        } catch (SigmaException thrown) {
            assertEquals("Please don't run me on null!", thrown.getMessage());
        }
    }
}
