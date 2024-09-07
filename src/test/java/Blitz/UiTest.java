package blitz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Todo;

public class UiTest {
    @Test
    public void printAddedTask_taskTypeAndTaskListSizeAndTaskObject_returnString() {
        String type = "T";
        int size = 1;
        Todo task = new Todo("123", "T", false);

        String expected = "Got it. I've added this task:\n"
                + "  [" + type + "][ ] " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
        String response = new Ui().getStringForTaskAdded(size, task);
        assertEquals(expected, response);
    }
}
