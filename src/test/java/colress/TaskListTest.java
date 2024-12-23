package colress;

import static colress.TaskList.MESSAGE_TASK_NUMBER_OUT_OF_BOUND;
import static colress.TaskList.RESULT_PREAMBLE;
import static colress.testutil.TestUtil.EMPTY_STRING;
import static colress.testutil.TestUtil.INVALID_COMMAND;
import static colress.testutil.TestUtil.VALID_DATE_ONE;
import static colress.testutil.TestUtil.VALID_DATE_TWO;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_THREE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_TWO;
import static colress.testutil.TestUtil.VALID_FROM_TIME_ONE;
import static colress.testutil.TestUtil.VALID_KEYWORD_ALL;
import static colress.testutil.TestUtil.VALID_KEYWORD_NONE;
import static colress.testutil.TestUtil.VALID_KEYWORD_ONE;
import static colress.testutil.TestUtil.VALID_KEYWORD_THREE;
import static colress.testutil.TestUtil.VALID_KEYWORD_TWO;
import static colress.testutil.TestUtil.VALID_MULTIPLE_TASK_NUMBERS;
import static colress.testutil.TestUtil.VALID_ONE_TASK_NUMBER;
import static colress.testutil.TestUtil.VALID_TO_TIME_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import colress.exception.UnknownCommandException;
import org.junit.jupiter.api.Test;

import colress.task.Deadline;
import colress.task.Event;
import colress.task.Task;
import colress.task.ToDo;

public class TaskListTest {
    @Test
    public void isEmptyTest() {
        TaskList taskList = new TaskList();

        // empty task list -> returns true
        assertTrue(taskList.isEmpty());

        // 1 to-do -> returns false
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(todo)));
        assertFalse(taskList.isEmpty());

        // 1 deadline -> returns false
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(deadline)));
        assertFalse(taskList.isEmpty());

        // 1 event -> returns false
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(event)));
        assertFalse(taskList.isEmpty());

        // multiple tasks -> returns false
        taskList = new TaskList(new ArrayList<>(List.of(todo, deadline, event)));
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void isOutOfBoundsTest() {
        TaskList taskList = new TaskList();

        // empty task list -> returns true
        assertTrue(taskList.isOutOfBounds(1));

        // non-empty task list -> returns false
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(todo)));

        assertFalse(taskList.isOutOfBounds(1));
    }

    @Test
    public void addTask_toDo_success() {
        TaskList taskList = new TaskList();
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);

        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(todo)));
        assertEquals("\n1. " + todo, taskList.addTask(todo));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void addTask_deadline_success() {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);

        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(deadline)));
        assertEquals("\n1. " + deadline, taskList.addTask(deadline));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void addTask_event_success() {
        TaskList taskList = new TaskList();
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);

        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(event)));
        assertEquals("\n1. " + event, taskList.addTask(event));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void checkTask_oneToDo_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo)));

        ToDo expectedTodo = new ToDo(VALID_DESCRIPTION_ONE, true);
        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(expectedTodo)));
        assertEquals("\n1. " + expectedTodo, taskList.checkTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void checkTask_oneDeadline_success() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(deadline)));

        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(expectedDeadline)));
        assertEquals("\n1. " + expectedDeadline, taskList.checkTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void checkTask_oneEvent_success() {
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(event)));

        Event expectedEvent = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE,
                true);
        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(expectedEvent)));
        assertEquals("\n1. " + expectedEvent, taskList.checkTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void checkTask_multipleTasks_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo, deadline, event)));

        ToDo expectedTodo = new ToDo(VALID_DESCRIPTION_ONE, true);
        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
        Event expectedEvent = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE,
                true);
        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(expectedTodo,
                expectedDeadline, expectedEvent)));
        assertEquals("\n1. " + expectedTodo + "\n2. " + expectedDeadline
                        + "\n3. " + expectedEvent,
                taskList.checkTask(VALID_MULTIPLE_TASK_NUMBERS));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void checkTask_outOfBounds_exceptionThrown() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo)));

        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(todo)));
        assertEquals(MESSAGE_TASK_NUMBER_OUT_OF_BOUND, taskList.checkTask(Integer.MAX_VALUE));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void uncheckTask_oneToDo_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo)));

        ToDo expectedTodo = new ToDo(VALID_DESCRIPTION_ONE);
        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(expectedTodo)));
        assertEquals("\n1. " + expectedTodo, taskList.uncheckTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void uncheckTask_oneDeadline_success() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(deadline)));

        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(expectedDeadline)));
        assertEquals("\n1. " + expectedDeadline, taskList.uncheckTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void uncheckTask_oneEvent_success() {
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, true);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(event)));

        Event expectedEvent = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE,
                VALID_TO_TIME_ONE);
        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(expectedEvent)));
        assertEquals("\n1. " + expectedEvent, taskList.uncheckTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void uncheckTask_multipleTasks_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, true);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo, deadline, event)));

        ToDo expectedTodo = new ToDo(VALID_DESCRIPTION_ONE);
        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        Event expectedEvent = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE,
                VALID_TO_TIME_ONE);
        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(expectedTodo,
                expectedDeadline, expectedEvent)));
        assertEquals("\n1. " + expectedTodo + "\n2. " + expectedDeadline
                        + "\n3. " + expectedEvent,
                taskList.uncheckTask(VALID_MULTIPLE_TASK_NUMBERS));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void uncheckTask_outOfBounds_exceptionThrown() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo)));

        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(todo)));
        assertEquals(MESSAGE_TASK_NUMBER_OUT_OF_BOUND, taskList.uncheckTask(Integer.MAX_VALUE));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void deleteTask_oneToDo_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo)));

        taskList.deleteTask(VALID_ONE_TASK_NUMBER);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void deleteTask_oneDeadline_success() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(deadline)));

        taskList.deleteTask(VALID_ONE_TASK_NUMBER);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void deleteTask_oneEvent_success() {
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(event)));

        taskList.deleteTask(VALID_ONE_TASK_NUMBER);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void deleteTask_multipleTasks_success() {
        // delete all tasks
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo, deadline, event)));

        taskList.deleteTask(VALID_MULTIPLE_TASK_NUMBERS);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);

        // tasks remaining in list
        taskList = new TaskList(new ArrayList<>(List.of(todo, deadline, event, todo)));

        taskList.deleteTask(VALID_MULTIPLE_TASK_NUMBERS);
        expectedTaskList = new TaskList(new ArrayList<>(List.of(todo)));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void deleteTask_outOfBounds_exceptionThrown() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        TaskList taskList = new TaskList(new ArrayList<>(List.of(todo)));

        TaskList expectedTaskList = new TaskList(new ArrayList<>(List.of(todo)));
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void retrieveTask_noParameter_success() {
        TaskList taskList = new TaskList();

        // no tasks in list
        assertEquals(EMPTY_STRING, taskList.retrieveTasks());

        // 1 To-do
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(todo)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + todo, taskList.retrieveTasks());

        // 1 Deadline
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(deadline)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline, taskList.retrieveTasks());

        // 1 Event
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(event)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + event, taskList.retrieveTasks());

        // multiple tasks
        event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_ONE, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(deadline, event)));
        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline + "\n2. " + event,
                taskList.retrieveTasks());
    }

    @Test
    public void retrieveTask_dateParameter_success() {
        TaskList taskList = new TaskList();

        // no tasks in list
        assertEquals(EMPTY_STRING, taskList.retrieveTasks(VALID_DATE_ONE));

        // 1 Deadline
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(deadline)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline, taskList.retrieveTasks(VALID_DATE_ONE));

        // 1 Event
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(event)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + event, taskList.retrieveTasks(VALID_DATE_TWO));

        // multiple tasks fall on date
        event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_ONE, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(deadline, event)));
        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline + "\n2. " + event,
                taskList.retrieveTasks(VALID_DATE_ONE));

        // no tasks fall on date
        assertEquals(EMPTY_STRING, taskList.retrieveTasks(VALID_DATE_TWO));
    }

    @Test
    public void retrieveTask_keywordParameter_success() {
        TaskList taskList = new TaskList();

        // no tasks
        assertEquals(EMPTY_STRING, taskList.retrieveTasks(VALID_KEYWORD_ONE));

        // 1 To-do
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(todo)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + todo, taskList.retrieveTasks(VALID_KEYWORD_ONE));

        // 1 Deadline
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(deadline)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline, taskList.retrieveTasks(VALID_KEYWORD_TWO));

        // 1 Event
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(event)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + event, taskList.retrieveTasks(VALID_KEYWORD_THREE));

        // multiple tasks contain keyword
        taskList = new TaskList(new ArrayList<>(List.of(todo, deadline, event)));
        assertEquals(RESULT_PREAMBLE + "\n1. " + todo + "\n2. " + deadline + "\n3. " + event,
                taskList.retrieveTasks(VALID_KEYWORD_ALL));

        // no tasks contain keyword
        assertEquals(EMPTY_STRING, taskList.retrieveTasks(VALID_KEYWORD_NONE));
    }

    @Test
    public void equalsTest() {
        TaskList taskList = new TaskList();

        // empty list -> returns true
        assertTrue(taskList.equals(new TaskList()));

        taskList = new TaskList();

        // same tasks, same order -> returns true
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        taskList = new TaskList(new ArrayList<>(List.of(todo, deadline)));
        assertTrue(taskList.equals(new TaskList(new ArrayList<>(List.of(todo, deadline)))));

        // same tasks, different order -> returns false
        taskList = new TaskList(new ArrayList<>(List.of(todo, deadline)));
        assertFalse(taskList.equals(new TaskList(new ArrayList<>(List.of(deadline, todo)))));

        // same object -> returns true
        assertTrue(taskList.equals(taskList));

        // null -> returns false
        assertFalse(taskList.equals(null));

        // different types -> returns false
        assertFalse(taskList.equals(17));

        // different tasks -> returns false
        taskList = new TaskList(new ArrayList<>(List.of(todo)));
        assertFalse(taskList.equals(new TaskList(new ArrayList<>(List.of(deadline)))));
    }
}
