package ponderpika;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ponderpika.exception.DuplicateTaskException;
import ponderpika.exception.InvalidPriorityException;
import ponderpika.exception.InvalidTaskIndexException;
import ponderpika.exception.NoMatchingTasksFoundException;
import ponderpika.exception.TaskAlreadyMarkedException;
import ponderpika.exception.TaskAlreadyUnmarkedException;
import ponderpika.task.Deadline;
import ponderpika.task.Task;
import ponderpika.task.TaskList;
import ponderpika.task.Todo;

class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task1 = new Todo("Eat food");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("21/09/2024 18:00", formatter);
        task2 = new Deadline("Assignment", dateTime);
    }

    @Test
    void addTaskValidTask_taskAdded() throws DuplicateTaskException {
        taskList.addTask(task1);
        assertEquals(1, taskList.getTasks().size());
        assertEquals(task1, taskList.getTasks().get(0));
    }

    @Test
    void addTaskDuplicateTask_throwsDuplicateTaskException() throws DuplicateTaskException {
        taskList.addTask(task1);
        assertDoesNotThrow(() -> taskList.addTask(task2));
        assertThrows(DuplicateTaskException.class, () -> taskList.addTask(task1));
    }

    @Test
    void deleteTaskValidIndex_taskDeleted() throws InvalidTaskIndexException, DuplicateTaskException {
        taskList.addTask(task2);
        taskList.deleteTask(1);
        assertTrue(taskList.getTasks().isEmpty());
    }

    @Test
    void deleteTaskInvalidIndex_throwsInvalidTaskIndexException() {
        assertThrows(InvalidTaskIndexException.class, () -> taskList.deleteTask(10));
    }

    @Test
    void markTaskValidIndex_taskMarked() throws InvalidTaskIndexException, TaskAlreadyMarkedException,
            DuplicateTaskException {
        taskList.addTask(task1);
        taskList.markTask(1);
        assertTrue(taskList.getTasks().get(0).isDone());
    }

    @Test
    void markTaskInvalidIndex_throwsInvalidTaskIndexException() {
        assertThrows(InvalidTaskIndexException.class, () -> taskList.markTask(5));
    }

    @Test
    void markTaskTaskAlreadyMarked_throwsTaskAlreadyMarkedException() throws TaskAlreadyMarkedException,
            InvalidTaskIndexException, DuplicateTaskException {
        taskList.addTask(task2);
        taskList.markTask(1);
        assertThrows(TaskAlreadyMarkedException.class, () -> taskList.markTask(1));
    }

    @Test
    void unmarkTaskValidIndex_taskUnmarked() throws InvalidTaskIndexException, TaskAlreadyUnmarkedException,
            DuplicateTaskException {
        taskList.addTask(task2);
        taskList.getTasks().get(0).markDone();
        taskList.unmarkTask(1);
        assertFalse(taskList.getTasks().get(0).isDone());
    }

    @Test
    void unmarkTaskInvalidIndex_throwsInvalidTaskIndexException() {
        assertThrows(InvalidTaskIndexException.class, () -> taskList.unmarkTask(8));
    }

    @Test
    void unmarkTaskTaskAlreadyUnmarked_throwsTaskAlreadyUnmarkedException() throws DuplicateTaskException,
            InvalidTaskIndexException, TaskAlreadyUnmarkedException {
        taskList.addTask(task2);
        assertThrows(TaskAlreadyUnmarkedException.class, () -> taskList.unmarkTask(1));
    }

    @Test
    void setPriorityLevelValidIndex_prioritySet() throws InvalidTaskIndexException, InvalidPriorityException,
            DuplicateTaskException {
        taskList.addTask(task2);
        String response = taskList.setPriorityLevel(1, "H");
        assertEquals("Priority set to H for Assignment", response);
        assertEquals(" (HIGH)", taskList.getTasks().get(0).priorityStatus());
    }

    @Test
    void setPriorityLevelInvalidIndex_throwsInvalidTaskIndexException() {
        assertThrows(InvalidTaskIndexException.class, () -> taskList.setPriorityLevel(9, "M"));
    }

    @Test
    void setPriorityLevelInvalidPriority_throwsInvalidPriorityException() throws DuplicateTaskException {
        taskList.addTask(task2);
        taskList.addTask(task1);
        assertThrows(InvalidPriorityException.class, () -> taskList.setPriorityLevel(2, "X"));
    }

    @Test
    void printTasksEmptyList_returnsEmptyMessage() {
        String result = taskList.printTasks();
        assertEquals("Task list is Empty!", result);
    }

    @Test
    void printTasksNonEmptyList_returnsTaskList() throws DuplicateTaskException {
        taskList.addTask(task2);
        taskList.addTask(task1);
        String result = taskList.printTasks();
        assertTrue(result.contains("Here are the following tasks:"));
        assertTrue(result.contains("1. [D][ ] Assignment (by: 21 Sep 2024 18:00)"));
        assertTrue(result.contains("2. [T][ ] Eat food"));
    }

    @Test
    void findTasksExistingKeyword_returnsMatchingTasks() throws NoMatchingTasksFoundException, DuplicateTaskException {
        taskList.addTask(task1);
        String result = taskList.findTasks("food");
        assertTrue(result.contains("[T][ ] Eat food"));
    }

    @Test
    void findTasksNonExistingKeyword_throwsNoMatchingTasksFoundException() {
        assertThrows(NoMatchingTasksFoundException.class, () -> taskList.findTasks("party"));
    }
}
