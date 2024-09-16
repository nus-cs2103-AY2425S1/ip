package wansbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static wansbot.WansBot.emptyInput;

import org.junit.jupiter.api.Test;

import wansbot.tasks.InputEmptyException;
import wansbot.tasks.TaskList;
import wansbot.tasks.Todos;
import wansbot.ui.UI;
import wansbot.WansBot;

public class WansBotTest {
    @Test
    public void emptyInput_throwsInputEmptyException() {
        TaskList taskList = new TaskList();
        try {
            emptyInput("todos ");
            fail();
        } catch (InputEmptyException e) {
            assertEquals("You need to input something after todos ", e.getMessage());
        }
    }

    @Test
    public void markTasks_marksTasks() {
        UI ui  = new UI();
        Todos read = new Todos("read");
        read.finish();
        TaskList taskList = new TaskList();
        taskList.add(read);
        WansBot wans = new WansBot();
        wans.addTodos("todos read");
        assertEquals(ui.handleSuccesfulMarking(taskList, 0), wans.markTasks("mark 1"));
    }

    @Test
    public void markTasks_throwsNumberFormatException() {
        UI ui  = new UI();
        Todos read = new Todos("read");
        read.finish();
        TaskList taskList = new TaskList();
        taskList.add(read);
        WansBot wans = new WansBot();
        wans.addTodos("todos read");
        assertEquals(ui.handleMarkingFormat(), wans.markTasks("mark a"));
    }

    @Test
    public void markTasks_throwsNotANumMarkingException() {
        UI ui  = new UI();
        Todos read = new Todos("read");
        read.finish();
        TaskList taskList = new TaskList();
        taskList.add(read);
        WansBot wans = new WansBot();
        wans.addTodos("todos read");
        assertEquals(ui.handleInvalidNum(), wans.markTasks("mark 2"));
    }
}
