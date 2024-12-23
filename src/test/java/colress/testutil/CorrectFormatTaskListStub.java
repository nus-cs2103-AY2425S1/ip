package colress.testutil;

import static colress.testutil.TestUtil.VALID_DATE_ONE;
import static colress.testutil.TestUtil.VALID_DATE_TWO;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_THREE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_TWO;
import static colress.testutil.TestUtil.VALID_FROM_TIME_ONE;
import static colress.testutil.TestUtil.VALID_TO_TIME_ONE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import colress.TaskList;
import colress.task.Deadline;
import colress.task.Event;
import colress.task.Task;
import colress.task.ToDo;

/**
 * A Stub class for testing purposes.
 */
public class CorrectFormatTaskListStub extends TaskList {
    /**
     * Constructs a TaskList object with valid tasks for testing.
     */
    public CorrectFormatTaskListStub() {
        super(new ArrayList<>(List.of(new ToDo(VALID_DESCRIPTION_ONE),
                new ToDo(VALID_DESCRIPTION_ONE, true),
                new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE),
                new Deadline(VALID_DESCRIPTION_TWO, VALID_DATE_ONE, true),
                new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE),
                new Event(VALID_DESCRIPTION_THREE, VALID_DATE_TWO, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE, true))));
    }

    @Override
    public String addTask(Task task) {
        return "This method should not be called";
    }

    @Override
    public String checkTask(int... taskNumbers) {
        return "This method should not be called";
    }

    @Override
    public String uncheckTask(int... taskNumbers) {
        return "This method should not be called";
    }

    @Override
    public void deleteTask(int... taskNumbers) {}

    @Override
    public String retrieveTasks() {
        return "This method should not be called";
    }

    @Override
    public String retrieveTasks(LocalDate date) {
        return "This method should not be called";
    }

    @Override
    public String retrieveTasks(String keyword) {
        return "This method should not be called";
    }

    @Override
    public Stream<Task> stream() {
        return null;
    }
}
