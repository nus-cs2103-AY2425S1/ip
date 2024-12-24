package colress.testutil;

import static colress.testutil.TestUtil.VALID_DATE_ONE;
import static colress.testutil.TestUtil.VALID_DATE_TWO;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_THREE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_TWO;
import static colress.testutil.TestUtil.VALID_FROM_TIME_ONE;
import static colress.testutil.TestUtil.VALID_TO_TIME_ONE;

import java.util.ArrayList;
import java.util.List;

import colress.task.Deadline;
import colress.task.Event;
import colress.task.Task;
import colress.task.ToDo;
import colress.tasklist.TaskList;

/**
 * A Stub class for testing purposes.
 */
public class TaskListStub extends TaskList {
    public static final Task TASK_ONE = new ToDo(VALID_DESCRIPTION_ONE);
    public static final Task TASK_TWO = new ToDo(VALID_DESCRIPTION_ONE, true);
    public static final Task TASK_THREE = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE);
    public static final Task TASK_FOUR = new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true);
    public static final Task TASK_FIVE = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO,
            VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);
    public static final Task TASK_SIX = new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO,
            VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, true);

    private final boolean isEmpty;

    /**
     * Constructs a ColressTaskList object with valid tasks for testing.
     */
    public TaskListStub() {
        super(new ArrayList<>(List.of(TASK_ONE, TASK_TWO, TASK_THREE, TASK_FOUR, TASK_FIVE, TASK_SIX)));
        this.isEmpty = false;
    }

    /**
     * Constructs an empty ColressTaskList for testing.
     */
    public TaskListStub(boolean isEmpty) {
        super();
        this.isEmpty = isEmpty;
    }

    @Override
    public String addTask(Task task) {
        return "";
    }

    @Override
    public String checkTask(int... taskNumbers) {
        return "";
    }

    @Override
    public String uncheckTask(int... taskNumbers) {
        return "";
    }

    @Override
    public void deleteTask(int... taskNumbers) {}
}
