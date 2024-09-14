package bunbun.utils;

import bunbun.exceptions.BunbunException;
import bunbun.tasks.ToDo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TaskListTest {

    private static Storage s = new Storage("src/main/data/test");
    private static TaskList taskList = new TaskList(s, new Ui());
    private static ToDo toAdd = new ToDo("HAI");

    @BeforeAll
    public static void setUpTaskList() {
        s.clearAll();
        taskList.addTask(toAdd);
    }

    @Test
    public void markDoneTask_outOfBoundsTaskNum_exceptionThrown() {
        try {
            ArrayList<String> tokens = new ArrayList<>();
            tokens.add("mark");
            tokens.add("-1");
            taskList.markDoneTask(tokens);
            fail();
        } catch (BunbunException e) {
            assertEquals("I can't mark task -1 cause it doesn't exist!!! ;-;", e.getMessage());
        }
    }

    @Test
    public void markDoneTask_one_success() {
        try {
            ArrayList<String> tokens = new ArrayList<>();
            tokens.add("mark");
            tokens.add("1");
            taskList.markDoneTask(tokens);
            assertEquals("[T][X] HAI", toAdd.toString());
        } catch (BunbunException e) {
            fail();
        }
    }
}

