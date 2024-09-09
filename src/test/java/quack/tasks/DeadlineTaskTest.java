package quack.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is to test the functionality of the DeadlineTask class.
 */
public class DeadlineTaskTest {

    /** Date time format for LocalDateTime objects */
    protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /** Description of the deadline task */
    private String taskDescription;
    /** Due date of the deadline task */
    private LocalDateTime taskDueDate;
    /** Deadline task object */
    private DeadlineTask dummyDeadlineTask;

    /**
     * Initializes and sets all variables to its default value after each test.
     */
    @BeforeEach
    public void initializeVariables() {
        this.taskDescription = "Dummy deadline task";
        this.taskDueDate = LocalDateTime.parse("10/05/2024 10:00:30", this.dateFormat);
        this.dummyDeadlineTask = null;
    }

    /**
     * Tests if the class constructor can create a deadline task with the correct information.
     * <p>
     * The deadline task must have the same description,
     * the same due date as the input and the task must be unmarked.
     */
    @Test
    public void testConstructor() {

        this.dummyDeadlineTask = new DeadlineTask(this.taskDescription, this.taskDueDate);

        assertEquals(taskDescription, this.dummyDeadlineTask.getDescription(),
            "The constructor did not create a deadline task with the same description");

        assertEquals(false, this.dummyDeadlineTask.getIsChecked(),
            "The constructor created a new deadline task that is originally marked");

        assertEquals(this.taskDueDate, this.dummyDeadlineTask.getDueDate(),
            "The constructor did not create a deadline task with the same due date");
    }

    /**
     * Tests if the mark function correctly marks a task.
     * <p>
     * If the task is already marked it should stay marked.
     */
    @Test
    public void testMark() {

        this.dummyDeadlineTask = new DeadlineTask(this.taskDescription, this.taskDueDate);

        this.dummyDeadlineTask.mark();

        assertEquals(true, this.dummyDeadlineTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyDeadlineTask.mark();

        assertEquals(true, this.dummyDeadlineTask.getIsChecked(),
            "The mark function unmarked task even through it was already marked");
    }

    /**
     * Tests if the unmark function correctly unmarks a task.
     * <p>
     * If the task is already unmarked it should stay unmarked.
     */
    @Test
    public void testUnmark() {

        this.dummyDeadlineTask = new DeadlineTask(this.taskDescription, this.taskDueDate);

        // We need to ensure that the mark function works before testing the unmark function
        this.dummyDeadlineTask.mark();

        assertEquals(true, this.dummyDeadlineTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyDeadlineTask.unmark();

        assertEquals(false, this.dummyDeadlineTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyDeadlineTask.unmark();

        assertEquals(false, this.dummyDeadlineTask.getIsChecked(),
            "The mark function unmarked task even through it was already marked");
    }

    /**
     * Tests if the toCsvFormat function converts a deadline task into the proper csv format.
     * <p>
     * For this test the task will be unmarked.
     */
    @Test
    public void testToCsvFormatUnmarked() {

        this.dummyDeadlineTask = new DeadlineTask(this.taskDescription, this.taskDueDate);

        String expectedTaskCsvFormat = "DEADLINE,Dummy deadline task,10/05/2024 10:00:30,false";
        String actualTaskCsvFormat = this.dummyDeadlineTask.toCsvFormat();

        assertEquals(expectedTaskCsvFormat, actualTaskCsvFormat,
            "The toCsvFormat function did not convert the task properly to its csv format");
    }

    /**
     * Tests if the toCsvFormat function converts a deadline task into the proper csv format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToCsvFormatMarked() {

        this.dummyDeadlineTask = new DeadlineTask(this.taskDescription, this.taskDueDate);
        this.dummyDeadlineTask.mark();

        String expectedTaskCsvFormat = "DEADLINE,Dummy deadline task,10/05/2024 10:00:30,true";
        String actualTaskCsvFormat = this.dummyDeadlineTask.toCsvFormat();

        assertEquals(expectedTaskCsvFormat, actualTaskCsvFormat,
            "The toCsvFormat function did not convert the task properly to its csv format");
    }

    /**
     * Tests if the toString function converts a deadline task into the proper string format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToStringUnmarked() {

        this.dummyDeadlineTask = new DeadlineTask(this.taskDescription, this.taskDueDate);

        String expectedTaskStringFormat = "[D][ ] Dummy deadline task (Due by: 10/05/2024 10:00:30)";
        String actualTaskStringFormat = this.dummyDeadlineTask.toString();

        assertEquals(expectedTaskStringFormat, actualTaskStringFormat,
            "The toString function did not convert the task properly to its string format");
    }

    /**
     * Tests if the toString function converts a deadline task into the proper string format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToStringMarked() {

        this.dummyDeadlineTask = new DeadlineTask(this.taskDescription, this.taskDueDate);
        this.dummyDeadlineTask.mark();

        String expectedTaskStringFormat = "[D][X] Dummy deadline task (Due by: 10/05/2024 10:00:30)";
        String actualTaskStringFormat = this.dummyDeadlineTask.toString();

        assertEquals(expectedTaskStringFormat, actualTaskStringFormat,
            "The toString function did not convert the task properly to its string format");
    }
}
