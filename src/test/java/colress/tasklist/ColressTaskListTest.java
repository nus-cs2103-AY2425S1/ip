package colress.tasklist;

import static colress.tasklist.ColressTaskList.MESSAGE_TASK_NUMBER_OUT_OF_BOUND;
import static colress.tasklist.ColressTaskList.RESULT_PREAMBLE;
import static colress.testutil.TestUtil.EMPTY_STRING;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import colress.task.Deadline;
import colress.task.Event;
import colress.task.ToDo;

public class ColressTaskListTest {
    @Test
    public void isEmptyTest() {
        ColressTaskList colressTaskList = new ColressTaskList();

        // empty task list -> returns true
        assertTrue(colressTaskList.isEmpty());

        // 1 to-do -> returns false
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));
        assertFalse(colressTaskList.isEmpty());

        // 1 deadline -> returns false
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline)));
        assertFalse(colressTaskList.isEmpty());

        // 1 event -> returns false
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(event)));
        assertFalse(colressTaskList.isEmpty());

        // multiple tasks -> returns false
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo, deadline, event)));
        assertFalse(colressTaskList.isEmpty());
    }

    @Test
    public void isOutOfBoundsTest() {
        ColressTaskList colressTaskList = new ColressTaskList();

        // empty task list -> returns true
        assertTrue(colressTaskList.isOutOfBounds(1));

        // non-empty task list -> returns false
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        assertFalse(colressTaskList.isOutOfBounds(1));
    }

    @Test
    public void addTask_toDo_success() {
        ColressTaskList colressTaskList = new ColressTaskList();
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);

        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));
        assertEquals("\n1. " + todo, colressTaskList.addTask(todo));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void addTask_deadline_success() {
        ColressTaskList colressTaskList = new ColressTaskList();
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);

        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline)));
        assertEquals("\n1. " + deadline, colressTaskList.addTask(deadline));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void addTask_event_success() {
        ColressTaskList colressTaskList = new ColressTaskList();
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);

        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(event)));
        assertEquals("\n1. " + event, colressTaskList.addTask(event));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void checkTask_oneToDo_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        ToDo expectedTodo = new ToDo(VALID_DESCRIPTION_ONE, true);
        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(expectedTodo)));
        assertEquals("\n1. " + expectedTodo, colressTaskList.checkTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void checkTask_oneDeadline_success() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline)));

        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(expectedDeadline)));
        assertEquals("\n1. " + expectedDeadline, colressTaskList.checkTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void checkTask_oneEvent_success() {
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(event)));

        Event expectedEvent = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE,
                true);
        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(expectedEvent)));
        assertEquals("\n1. " + expectedEvent, colressTaskList.checkTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void checkTask_multipleTasks_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo, deadline, event)));

        ToDo expectedTodo = new ToDo(VALID_DESCRIPTION_ONE, true);
        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
        Event expectedEvent = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE,
                true);
        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(expectedTodo,
                expectedDeadline, expectedEvent)));
        assertEquals("\n1. " + expectedTodo + "\n2. " + expectedDeadline
                        + "\n3. " + expectedEvent,
                colressTaskList.checkTask(VALID_MULTIPLE_TASK_NUMBERS));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void checkTask_outOfBounds_exceptionThrown() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));
        assertEquals(MESSAGE_TASK_NUMBER_OUT_OF_BOUND, colressTaskList.checkTask(Integer.MAX_VALUE));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void uncheckTask_oneToDo_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        ToDo expectedTodo = new ToDo(VALID_DESCRIPTION_ONE);
        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(expectedTodo)));
        assertEquals("\n1. " + expectedTodo, colressTaskList.uncheckTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void uncheckTask_oneDeadline_success() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline)));

        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(expectedDeadline)));
        assertEquals("\n1. " + expectedDeadline, colressTaskList.uncheckTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void uncheckTask_oneEvent_success() {
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, true);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(event)));

        Event expectedEvent = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE,
                VALID_TO_TIME_ONE);
        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(expectedEvent)));
        assertEquals("\n1. " + expectedEvent, colressTaskList.uncheckTask(VALID_ONE_TASK_NUMBER));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void uncheckTask_multipleTasks_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, true);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo, deadline, event)));

        ToDo expectedTodo = new ToDo(VALID_DESCRIPTION_ONE);
        Deadline expectedDeadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        Event expectedEvent = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE,
                VALID_TO_TIME_ONE);
        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(expectedTodo,
                expectedDeadline, expectedEvent)));
        assertEquals("\n1. " + expectedTodo + "\n2. " + expectedDeadline
                        + "\n3. " + expectedEvent,
                colressTaskList.uncheckTask(VALID_MULTIPLE_TASK_NUMBERS));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void uncheckTask_outOfBounds_exceptionThrown() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));
        assertEquals(MESSAGE_TASK_NUMBER_OUT_OF_BOUND, colressTaskList.uncheckTask(Integer.MAX_VALUE));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void deleteTask_oneToDo_success() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        colressTaskList.deleteTask(VALID_ONE_TASK_NUMBER);
        ColressTaskList expectedColressTaskList = new ColressTaskList();
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void deleteTask_oneDeadline_success() {
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline)));

        colressTaskList.deleteTask(VALID_ONE_TASK_NUMBER);
        ColressTaskList expectedColressTaskList = new ColressTaskList();
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void deleteTask_oneEvent_success() {
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(event)));

        colressTaskList.deleteTask(VALID_ONE_TASK_NUMBER);
        ColressTaskList expectedColressTaskList = new ColressTaskList();
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void deleteTask_multipleTasks_success() {
        // delete all tasks
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo, deadline, event)));

        colressTaskList.deleteTask(VALID_MULTIPLE_TASK_NUMBERS);
        ColressTaskList expectedColressTaskList = new ColressTaskList();
        assertEquals(expectedColressTaskList, colressTaskList);

        // tasks remaining in list
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo, deadline, event, todo)));

        colressTaskList.deleteTask(VALID_MULTIPLE_TASK_NUMBERS);
        expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void deleteTask_outOfBounds_exceptionThrown() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        ColressTaskList colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        ColressTaskList expectedColressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));
        assertEquals(expectedColressTaskList, colressTaskList);
    }

    @Test
    public void retrieveTask_noParameter_success() {
        ColressTaskList colressTaskList = new ColressTaskList();

        // no tasks in list
        assertEquals(EMPTY_STRING, colressTaskList.retrieveTasks());

        // 1 To-do
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + todo, colressTaskList.retrieveTasks());

        // 1 Deadline
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline, colressTaskList.retrieveTasks());

        // 1 Event
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(event)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + event, colressTaskList.retrieveTasks());

        // multiple tasks
        event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_ONE, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline, event)));
        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline + "\n2. " + event,
                colressTaskList.retrieveTasks());
    }

    @Test
    public void retrieveTask_dateParameter_success() {
        ColressTaskList colressTaskList = new ColressTaskList();

        // no tasks in list
        assertEquals(EMPTY_STRING, colressTaskList.retrieveTasks(VALID_DATE_ONE));

        // 1 Deadline
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline, colressTaskList.retrieveTasks(VALID_DATE_ONE));

        // 1 Event
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(event)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + event, colressTaskList.retrieveTasks(VALID_DATE_TWO));

        // multiple tasks fall on date
        event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_ONE, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline, event)));
        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline + "\n2. " + event,
                colressTaskList.retrieveTasks(VALID_DATE_ONE));

        // no tasks fall on date
        assertEquals(EMPTY_STRING, colressTaskList.retrieveTasks(VALID_DATE_TWO));
    }

    @Test
    public void retrieveTask_keywordParameter_success() {
        ColressTaskList colressTaskList = new ColressTaskList();

        // no tasks
        assertEquals(EMPTY_STRING, colressTaskList.retrieveTasks(VALID_KEYWORD_ONE));

        // 1 To-do
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + todo, colressTaskList.retrieveTasks(VALID_KEYWORD_ONE));

        // 1 Deadline
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(deadline)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + deadline, colressTaskList.retrieveTasks(VALID_KEYWORD_TWO));

        // 1 Event
        Event event = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(event)));

        assertEquals(RESULT_PREAMBLE + "\n1. " + event, colressTaskList.retrieveTasks(VALID_KEYWORD_THREE));

        // multiple tasks contain keyword
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo, deadline, event)));
        assertEquals(RESULT_PREAMBLE + "\n1. " + todo + "\n2. " + deadline + "\n3. " + event,
                colressTaskList.retrieveTasks(VALID_KEYWORD_ALL));

        // no tasks contain keyword
        assertEquals(EMPTY_STRING, colressTaskList.retrieveTasks(VALID_KEYWORD_NONE));
    }

    @Test
    public void equalsTest() {
        ColressTaskList colressTaskList = new ColressTaskList();

        // empty list -> returns true
        assertTrue(colressTaskList.equals(new ColressTaskList()));

        colressTaskList = new ColressTaskList();

        // same tasks, same order -> returns true
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);
        Deadline deadline = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo, deadline)));
        assertTrue(colressTaskList.equals(new ColressTaskList(new ArrayList<>(List.of(todo, deadline)))));

        // same tasks, different order -> returns false
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo, deadline)));
        assertFalse(colressTaskList.equals(new ColressTaskList(new ArrayList<>(List.of(deadline, todo)))));

        // same object -> returns true
        assertTrue(colressTaskList.equals(colressTaskList));

        // null -> returns false
        assertFalse(colressTaskList.equals(null));

        // different types -> returns false
        assertFalse(colressTaskList.equals(17));

        // different tasks -> returns false
        colressTaskList = new ColressTaskList(new ArrayList<>(List.of(todo)));
        assertFalse(colressTaskList.equals(new ColressTaskList(new ArrayList<>(List.of(deadline)))));
    }
}
