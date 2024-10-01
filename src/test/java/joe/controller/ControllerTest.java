package joe.controller;

import org.junit.jupiter.api.Test;

import joe.UiStub;
import joe.task.TaskDeadline;
import joe.task.TaskEvent;
// import joe.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;

public class ControllerTest {
    private final Controller controller = new Controller(new UiStub("Test"));

    @BeforeEach
    public void setUp() {
        controller.store.clear();
    }

    @Test
    public void testAddTodo() {
        String taskName = "task read book";
        controller.handleTodo(taskName);
        assertEquals(taskName, controller.store.get(0).getTask());
    }

    @Test
    public void testAddDeadline() {
        String taskName = "return book";
        String by = "2021-09-30";
        controller.handleDeadline(taskName, by);
        try {
            TaskDeadline task = (TaskDeadline) controller.store.get(0);
            assertEquals(by, task.getBy().toString());
            assertEquals(taskName, task.getTask());
        } catch (ClassCastException e) {
            fail("Task is not of type TaskDeadline");
        }
    }

    @Test
    public void testAddEvent() {
        String taskName = "project meeting";
        String from = "2021-09-30";
        String to = "2021-10-01";
        controller.handleEvent(taskName, from, to);
        try {
            TaskEvent task = (TaskEvent) controller.store.get(0);

            assertEquals(from, task.getFrom().toString());
            assertEquals(to, task.getTo().toString());
            assertEquals(taskName, task.getTask());
        } catch (ClassCastException e) {
            fail("Task is not of type TaskEvent");
        }
    }

    @Test
    public void testMarkDone() {
        String taskName = "read book";
        controller.handleTodo(taskName);

        // Mark as done
        controller.handleDone(0);
        assertEquals(true, controller.store.get(0).isDone());

        // Mark as undone
        controller.handleDone(0);
        assertEquals(false, controller.store.get(0).isDone());
    }

    @Test
    public void testDelete() {
        String taskName = "read book";
        controller.handleTodo(taskName);
        controller.handleDelete(0);
        assertEquals(0, controller.store.size());
    }
}
