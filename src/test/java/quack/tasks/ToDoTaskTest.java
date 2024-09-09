package quack.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is to test the functionality of the ToDoTask class.
 */
public class ToDoTaskTest {

    /** Description of the event task */
    private String taskDescription;
    /** ToDo task object */
    private ToDoTask dummyToDoTask;

    /**
     * Initializes and sets all variables to its default value after each test.
     */
    @BeforeEach
    public void initializeVariables() {
        this.taskDescription = "Dummy todo task";
        this.dummyToDoTask = null;
    }

    /**
     * Tests if the class constructor can create a todo task with the correct information.
     * <p>
     * The todo task must have the same desciription given in the input,
     * and the task must be unmarked.
     */
    @Test
    public void testConstructor() {

        this.dummyToDoTask = new ToDoTask(this.taskDescription);

        assertEquals(this.taskDescription, this.dummyToDoTask.getDescription(),
            "The constructor did not create a todo task with the same description");

        assertEquals(false, this.dummyToDoTask.getIsChecked(),
            "The constructor created a new todo task that is originally marked");
    }

    /**
     * Tests if the mark function correctly marks a task.
     * <p>
     * If the task is already marked it should stay marked.
     */
    @Test
    public void testMark() {

        this.dummyToDoTask = new ToDoTask(this.taskDescription);

        this.dummyToDoTask.mark();

        assertEquals(true, this.dummyToDoTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyToDoTask.mark();

        assertEquals(true, this.dummyToDoTask.getIsChecked(),
            "The mark function unmarked task even through it was already marked");
    }

    /**
     * Tests if the unmark function correctly unmarks a task.
     * <p>
     * If the task is already unmarked it should stay unmarked.
     */
    @Test
    public void testUnmark() {

        this.dummyToDoTask = new ToDoTask(this.taskDescription);

        // We need to ensure that the mark function works before testing the unmark function
        this.dummyToDoTask.mark();

        assertEquals(true, this.dummyToDoTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyToDoTask.unmark();

        assertEquals(false, this.dummyToDoTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyToDoTask.unmark();

        assertEquals(false, this.dummyToDoTask.getIsChecked(),
            "The mark function unmarked task even through it was already marked");
    }

    /**
     * Tests if the toCsvFormat function converts a todo task into the proper csv format.
     * <p>
     * For this test the task will be unmarked.
     */
    @Test
    public void testToCsvFormatUnmarked() {

        this.dummyToDoTask = new ToDoTask(this.taskDescription);

        String expectedTaskCsvFormat = "TODO,Dummy todo task,false";
        String actualTaskCsvFormat = this.dummyToDoTask.toCsvFormat();

        assertEquals(expectedTaskCsvFormat, actualTaskCsvFormat,
            "The toCsvFormat function did not convert the task properly to its csv format");
    }

    /**
     * Tests if the toCsvFormat function converts a todo task into the proper csv format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToCsvFormatMarked() {

        this.dummyToDoTask = new ToDoTask(this.taskDescription);
        this.dummyToDoTask.mark();

        String expectedTaskCsvFormat = "TODO,Dummy todo task,true";
        String actualTaskCsvFormat = this.dummyToDoTask.toCsvFormat();

        assertEquals(expectedTaskCsvFormat, actualTaskCsvFormat,
            "The toCsvFormat function did not convert the task properly to its csv format");
    }

    /**
     * Tests if the toString function converts a todo task into the proper string format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToStringUnmarked() {

        this.dummyToDoTask = new ToDoTask(this.taskDescription);

        String expectedTaskStringFormat = "[T][ ] Dummy todo task";
        String actualTaskStringFormat = this.dummyToDoTask.toString();

        assertEquals(expectedTaskStringFormat, actualTaskStringFormat,
            "The toString function did not convert the task properly to its string format");
    }

    /**
     * Tests if the toString function converts a todo task into the proper string format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToStringMarked() {

        this.dummyToDoTask = new ToDoTask(this.taskDescription);
        this.dummyToDoTask.mark();

        String expectedTaskStringFormat = "[T][X] Dummy todo task";
        String actualTaskStringFormat = this.dummyToDoTask.toString();

        assertEquals(expectedTaskStringFormat, actualTaskStringFormat,
            "The toString function did not convert the task properly to its string format");
    }
}
