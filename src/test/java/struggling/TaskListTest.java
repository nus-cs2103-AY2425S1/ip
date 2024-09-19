package struggling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import struggling.task.Task;
import struggling.task.ToDo;

public class TaskListTest {

    private final ArrayList<String> input = new ArrayList<>(
            Arrays.asList("T | 0 | 0 | borrow book", "D | 1 | 1 | return book | 2024-08-27",
                    "E | 0 | 0 | project meeting | Mon 2pm | 4pm"));

    @Test
    public void loadTask_corruptedSaveFile_throwException() {
        ArrayList<String> corruptedInput = new ArrayList<>(
                Arrays.asList("T | 0 | 0 | borrow book", "Corrupted | 1 | 0 | return book | 2024-08-27",
                        "E | 0 | 0 | project meeting | Mon 2pm | 4pm"));

        try {
            assertEquals(0, new TaskList(corruptedInput));
            fail();
        } catch (Exception e) {
            assertEquals(StrugglingException.class, e.getClass());
        }
    }

    @Test
    public void getTaskString_returnCorrectFormat() {
        ArrayList<String> stringOutput = new ArrayList<>(
                Arrays.asList("[T][ ][ ] borrow book", "[D][!][X] return book (by: Aug 27 2024)",
                        "[E][ ][ ] project meeting (from: Mon 2pm to: 4pm)"));

        assertEquals(stringOutput, new TaskList(input).getTasksString());
    }

    @Test
    public void getTasksState_returnCorrectFormat() {
        assertEquals(input, new TaskList(input).getTasksState());
    }

    @Test
    public void addTask_addTaskToList() {
        TaskList tasks = new TaskList(input);
        tasks.addTask(new ToDo("new task"));
        ArrayList<String> expectedOutput = new ArrayList<>(
                Arrays.asList("T | 0 | 0 | borrow book", "D | 1 | 1 | return book | 2024-08-27",
                        "E | 0 | 0 | project meeting | Mon 2pm | 4pm", "T | 0 | 0 | new task"));

        assertEquals(expectedOutput, tasks.getTasksState());
    }

    @Test
    public void addTask_returnAddedTask() {
        Task task = new ToDo("new task");
        assertEquals(task, new TaskList(input).addTask(task));
    }

    @Test
    public void deleteTask_removeTaskFromList() {
        TaskList tasks = new TaskList(input);
        tasks.deleteTask(1);
        ArrayList<String> expectedOutput = new ArrayList<>(
                Arrays.asList("T | 0 | 0 | borrow book", "E | 0 | 0 | project meeting | Mon 2pm | 4pm"));

        assertEquals(expectedOutput, tasks.getTasksState());
    }

    @Test
    public void addTask_returnDeletedTask() {
        assertEquals("D | 1 | 1 | return book | 2024-08-27", new TaskList(input)
                .deleteTask(1)
                .getState());
    }

    @Test
    public void markTask_markTaskInList() {
        TaskList tasks = new TaskList(input);
        tasks.markTask(0);
        ArrayList<String> expectedOutput = new ArrayList<>(
                Arrays.asList("T | 0 | 1 | borrow book", "D | 1 | 1 | return book | 2024-08-27",
                        "E | 0 | 0 | project meeting | Mon 2pm | 4pm"));

        assertEquals(expectedOutput, tasks.getTasksState());
    }

    @Test
    public void markTask_returnMarkedTask() {
        assertEquals("T | 0 | 1 | borrow book", new TaskList(input)
                .markTask(0)
                .getState());
    }

    @Test
    public void unmarkTask_unmarkTaskInList() {
        TaskList tasks = new TaskList(input);
        tasks.unmarkTask(1);
        ArrayList<String> expectedOutput = new ArrayList<>(
                Arrays.asList("T | 0 | 0 | borrow book", "D | 1 | 0 | return book | 2024-08-27",
                        "E | 0 | 0 | project meeting | Mon 2pm | 4pm"));

        assertEquals(expectedOutput, tasks.getTasksState());
    }

    @Test
    public void unmarkTask_returnUnmarkedTask() {
        assertEquals("D | 1 | 0 | return book | 2024-08-27", new TaskList(input)
                .unmarkTask(1)
                .getState());
    }

    @Test
    public void setTaskPriorityHigh_modifyTaskInList() {
        TaskList tasks = new TaskList(input);
        tasks.setTaskPriorityHigh(0);
        ArrayList<String> expectedOutput = new ArrayList<>(
                Arrays.asList("T | 1 | 0 | borrow book", "D | 1 | 1 | return book | 2024-08-27",
                        "E | 0 | 0 | project meeting | Mon 2pm | 4pm"));

        assertEquals(expectedOutput, tasks.getTasksState());
    }

    @Test
    public void setTaskPriorityHigh_returnModifiedTask() {
        assertEquals("T | 1 | 0 | borrow book", new TaskList(input)
                .setTaskPriorityHigh(0)
                .getState());
    }

    @Test
    public void setTaskPriorityLow_modifyTaskInList() {
        TaskList tasks = new TaskList(input);
        tasks.setTaskPriorityLow(1);
        ArrayList<String> expectedOutput = new ArrayList<>(
                Arrays.asList("T | 0 | 0 | borrow book", "D | 0 | 1 | return book | 2024-08-27",
                        "E | 0 | 0 | project meeting | Mon 2pm | 4pm"));

        assertEquals(expectedOutput, tasks.getTasksState());
    }

    @Test
    public void setTaskPriorityLow_returnModifiedTask() {
        assertEquals("D | 0 | 1 | return book | 2024-08-27", new TaskList(input)
                .setTaskPriorityLow(1)
                .getState());
    }
}
