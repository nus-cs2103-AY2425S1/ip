package mylo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mylo.data.DuplicatedTaskException;
import mylo.data.InsufficientInfoException;
import mylo.storage.StorageOperationException;
import mylo.utils.exceptions.IllegalValueException;

public class TaskListTest {

    private TaskList taskList;
    private final Todo todoTask;
    private final Event eventTask;
    private final Deadline deadlineTask;
    private final String todoTaskInfo = " Todo Task";
    private final String eventTaskInfo = " Event Task /from 01-01-1999 0000 /to 10-01-1999 2359";
    private final String deadlineTaskInfo = " Deadline Task /by 01-01-1999 2359";

    public TaskListTest() {
        try {
            todoTask = new Todo("Todo Task");
            eventTask = new Event("Event Task", "01-01-1999 0000", "10-01-1999 2359");
            deadlineTask = new Deadline("Deadline Task", "01-01-1999 2359");
        } catch (IllegalValueException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void addTask_todo_success() {
        ArrayList<Task> mockList = new ArrayList<>(List.of(todoTask));

        try {
            taskList.addTask(todoTaskInfo, TaskType.TODO);
        } catch (InsufficientInfoException | StorageOperationException | IllegalValueException
                 | DuplicatedTaskException e) {
            fail(e.getMessage());
        }

        assertEquals(new TaskList(mockList), taskList);
    }

    @Test
    public void addTask_event_success() {
        ArrayList<Task> mockList = new ArrayList<>(List.of(eventTask));

        try {
            taskList.addTask(eventTaskInfo, TaskType.EVENT);
        } catch (InsufficientInfoException | StorageOperationException | IllegalValueException
                 | DuplicatedTaskException e) {
            fail(e.getMessage());
        }

        assertEquals(new TaskList(mockList), taskList);
    }

    @Test
    public void addTask_deadline_success() {
        ArrayList<Task> mockList = new ArrayList<>(List.of(deadlineTask));

        try {
            taskList.addTask(deadlineTaskInfo, TaskType.DEADLINE);
        } catch (InsufficientInfoException | StorageOperationException | IllegalValueException
                 | DuplicatedTaskException e) {
            fail();
        }

        assertEquals(new TaskList(mockList), taskList);
    }

    @Test
    public void addTask_duplicateTask_throwsDuplicatedTaskException() {
        try {
            taskList.addTask(todoTaskInfo, TaskType.TODO);
            assertThrows(DuplicatedTaskException.class, () -> taskList.addTask(todoTaskInfo, TaskType.TODO));
        } catch (InsufficientInfoException | StorageOperationException | IllegalValueException
                 | DuplicatedTaskException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void addTask_emptyInfo_throwsInsufficientInfoException() {
        assertThrows(InsufficientInfoException.class, () -> taskList.addTask("", TaskType.TODO));
    }

    @Test
    public void deleteTask_validIndex_success() {
        try {
            taskList.addTask(todoTaskInfo, TaskType.TODO);
            assertEquals("Noted. I've removed this task:\n [T][ ] Todo Task\nNow you have 0 tasks in the list.",
                    taskList.deleteTask(1));
        } catch (InsufficientInfoException | StorageOperationException | IllegalValueException
                 | DuplicatedTaskException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteTask_invalidIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(1));
    }

    @Test
    public void markTaskAsDone_validIndex_success() {
        try {
            taskList.addTask(todoTaskInfo, TaskType.TODO);
            assertEquals("Nice! I've marked this task as done: \n[T][X] Todo Task", taskList.markTaskAsDone(1));
        } catch (InsufficientInfoException | StorageOperationException | IllegalValueException
                 | DuplicatedTaskException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void markTaskAsUndone_validIndex_success() {
        try {
            taskList.addTask(todoTaskInfo, TaskType.TODO);
            taskList.markTaskAsDone(1);
            assertEquals("OK, I've marked this task as not done yet: \n[T][ ] Todo Task",
                    taskList.markTaskAsUndone(1));
        } catch (InsufficientInfoException | StorageOperationException | IllegalValueException
                 | DuplicatedTaskException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void markTaskAsDone_invalidIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTaskAsUndone(1));
    }

    @Test
    public void markTaskAsUndone_invalidIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTaskAsUndone(1));
    }

    @Test
    public void tasksOnDate_success() {
        taskList = new TaskList(List.of(eventTask, deadlineTask));
        TaskList targetList = new TaskList(List.of(eventTask));

        TaskList filteredList = taskList.tasksOnDate(LocalDateTime.of(1999, 1, 10, 0, 0));
        assertEquals(targetList, filteredList, "Only 1 event should be found on that date");
    }

    @Test
    public void tasksWithKeyword_success() {
        taskList = new TaskList(List.of(todoTask, eventTask, deadlineTask));
        TaskList targetList = new TaskList(List.of(todoTask));

        TaskList filteredList = taskList.tasksWithKeyword("Todo");
        assertEquals(targetList, filteredList, "Only 1 todo should match the keyword");
    }

    @Test
    public void decodeTxt_validFileFormat_success() {
        List<String> encodedTasks = List.of(
                "TODO | False | Todo Task",
                "DEADLINE | True | Deadline Task | 25-09-2024 2359"
        );

        try {
            TaskList decodedTaskList = TaskList.decodeTxt(encodedTasks);
            assertEquals(2, decodedTaskList.getTaskNumber());
        } catch (IllegalValueException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void decodeTxt_invalidFileFormat_throwsIllegalValueException() {
        List<String> encodedTasks = List.of(
                "TODO False | Todo Task",
                "DEADLINE | True | Deadline Task | 25-09-2024 2359"
        );

        assertThrows(IllegalValueException.class, () -> TaskList.decodeTxt(encodedTasks),
                "Corrupted file format should throws IllegalValueException");
    }

    @Test
    public void getTaskNumber_emptyList_zeroTasks() {
        TaskList test = new TaskList();
        assertEquals(0, test.getTaskNumber(), "Task list should have zero tasks.");
    }

    @Test
    public void getTaskNumber_nonEmptyList_correctNumberOfTasks() {
        taskList = new TaskList(List.of(todoTask, eventTask, deadlineTask));
        assertEquals(3, taskList.getTaskNumber(), "Task list should have three tasks.");
    }

    @Test
    public void equals_sameTaskList_true() {

        try {
            Task todo1 = Task.of(todoTaskInfo, TaskType.TODO);
            Task todo2 = Task.of(todoTaskInfo, TaskType.TODO);
            Task event1 = Task.of(eventTaskInfo, TaskType.EVENT);
            Task event2 = Task.of(eventTaskInfo, TaskType.EVENT);
            Task deadline1 = Task.of(deadlineTaskInfo, TaskType.DEADLINE);
            Task deadline2 = Task.of(deadlineTaskInfo, TaskType.DEADLINE);

            ArrayList<Task> tasks1 = new ArrayList<>(List.of(todo1, event1, deadline1));
            ArrayList<Task> tasks2 = new ArrayList<>(List.of(todo2, event2, deadline2));

            TaskList test1 = new TaskList(tasks1);
            TaskList test2 = new TaskList(tasks2);

            assertEquals(test1, test2, "The two task lists should be equal.");
        } catch (InsufficientInfoException | IllegalValueException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void equals_differentTaskList_false() {
        try {
            Task todo1 = Task.of(todoTaskInfo + "1", TaskType.TODO);
            Task todo2 = Task.of(todoTaskInfo + "2", TaskType.TODO);

            ArrayList<Task> tasks1 = new ArrayList<>(List.of(todo1, eventTask, deadlineTask));
            ArrayList<Task> tasks2 = new ArrayList<>(List.of(todo2, eventTask, deadlineTask));

            TaskList test1 = new TaskList(tasks1);
            TaskList test2 = new TaskList(tasks2);

            assertNotEquals(test1, test2, "The two task lists should not be equal.");
        } catch (InsufficientInfoException | IllegalValueException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void equals_differentObject_false() {
        TaskList test = new TaskList();
        String notATaskList = "Not a TaskList";
        assertNotEquals(test, notATaskList, "A TaskList should not be equal to a different type of object.");
    }
}
