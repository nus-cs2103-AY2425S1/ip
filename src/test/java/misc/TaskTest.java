package misc;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;

public class TaskTest {
    @Test
    public void task_checkDone_notDone() {

        Task task = new Task("testing");
        String expected = " ";
        String result = task.getStatusIcon();

        assertEquals(expected, result);
    }

    @Test
    public void task_markDone_done() {

        Task task = new Task("testing");
        task.setDone();
        String expected = "X";
        String result = task.getStatusIcon();

        assertEquals(expected, result);
    }

    @Test
    public void task_getDescription_returnDescription() {

        Task task = new Task("testing");
        task.setDone();
        String expected = "testing";
        String result = task.getDescription();

        assertEquals(expected, result);
    }

    @Test
    public void task_getString_returnString() {

        Task task = new Task("testing");
        task.setDone();
        String expected = "[X] testing";
        String result = task.toString();

        assertEquals(expected, result);
    }
}
