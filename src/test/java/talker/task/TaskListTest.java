package talker.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import talker.TalkerException;
import talker.Ui;

public class TaskListTest {

    @Test
    public void listTasks_emptyList_exceptionThrown() {
        TaskList list = new TaskList();
        Ui ui = new Ui("Talker");
        try {
            list.listTasks(ui);
            fail();
        } catch (TalkerException e) {
            assertEquals("List is empty!", e.getMessage());
        }
    }

    @Test
    public void createToDo_missingDescription_exceptionThrown() {
        TaskList list = new TaskList();
        Ui ui = new Ui("Talker");
        try {
            list.createToDo("todo", ui);
            fail();
        } catch (TalkerException e) {
            assertEquals("ToDo format wrong. Try again with: todo <description>", e.getMessage());
        }
    }

    @Test
    public void createToDo_invalidCommand_exceptionThrown() {
        TaskList list = new TaskList();
        Ui ui = new Ui("Talker");
        try {
            list.createToDo("tode", ui);
            fail();
        } catch (TalkerException e) {
            assertEquals("ToDo format wrong. Try again with: todo <description>", e.getMessage());
        }
    }
}
