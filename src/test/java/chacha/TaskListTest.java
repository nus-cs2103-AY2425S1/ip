package chacha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import chacha.exception.ChaChaException;
import org.junit.jupiter.api.Test;

import chacha.task.Task;
import chacha.task.TaskList;


public class TaskListTest {

    private TaskList tasks = new TaskList();
    private ChaCha chacha = new ChaCha();
    private Ui ui = chacha.ui;
    private Storage storage = chacha.storage;

    @Test
    public void addToDo_missingComponent_exceptionMessage() {
        try {
            Task task = tasks.addToDo("todo", ui, storage);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("What task are you intending to add as a \'todo\'?", actual);
        }
    }

    @Test
    public void deleteTask_missingIndex_exceptionMessage() {
        try {
            Task task = tasks.deleteTask("delete", storage);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("You are missing the index of task you want to delete. ", actual);
        }
    }

    @Test
    public void markDone_missingIndex_exceptionMessage() {
        try {
            Task task = tasks.markDone("mark", storage);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("You are missing the index of task you want to mark. ", actual);
        }
    }

    @Test
    public void markUndone_missingIndex_exceptionMessage() {
        try {
            Task task = tasks.markUndone("unmark", storage);
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("You are missing the index of task you want to unmark. ", actual);
        }
    }

    @Test
    public void find_missingText_exceptionMessage() {
        try {
            ArrayList<Task> list = tasks.find("find");
        } catch (ChaChaException e) {
            String actual = e.getMessage().split("\n")[1].substring(5);
            assertEquals("You are missing the text you want to find. ", actual);
        }
    }
}
