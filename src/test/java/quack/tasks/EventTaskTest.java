package quack.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is to test the functionality of the EventTask class.
 */
public class EventTaskTest {

    /** Date time format for LocalDateTime objects */
    protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /** Description of the event task */
    private String taskDescription;
    /** Start date of the event task */
    private LocalDateTime taskStartDate;
    /** End date of the event task */
    private LocalDateTime taskEndDate;
    /** Event task object */
    private EventTask dummyEventTask;

    /**
     * Initializes and sets all variables to its default value after each test.
     */
    @BeforeEach
    public void initializeVariables() {
        this.taskDescription = "Dummy event task";
        this.taskStartDate = LocalDateTime.parse("10/05/2024 10:00:30", this.dateFormat);
        this.taskEndDate = LocalDateTime.parse("15/06/2024 12:30:00", this.dateFormat);
        this.dummyEventTask = null;
    }

    /**
     * Tests if the class constructor can create a event task with the correct information.
     * <p>
     * The event task must have the same description,
     * the same start and end date as the input and the task must be unmarked.
     */
    @Test
    public void testConstructor() {

        this.dummyEventTask = new EventTask(this.taskDescription, this.taskStartDate, this.taskEndDate);

        assertEquals(taskDescription, this.dummyEventTask.getDescription(),
            "The constructor did not create a deadline task with the same description");

        assertEquals(false, this.dummyEventTask.getIsChecked(),
            "The constructor created a new deadline task that is originally marked");

        assertEquals(this.taskStartDate, this.dummyEventTask.getStartDate(),
            "The constructor did not create a deadline task with the same start date");

        assertEquals(this.taskEndDate, this.dummyEventTask.getEndDate(),
            "The constructor did not create a deadline task with the same start date");
    }

    /**
     * Tests if the mark function correctly marks a task.
     * <p>
     * If the task is already marked it should stay marked.
     */
    @Test
    public void testMark() {

        this.dummyEventTask = new EventTask(this.taskDescription, this.taskStartDate, this.taskEndDate);

        this.dummyEventTask.mark();

        assertEquals(true, this.dummyEventTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyEventTask.mark();

        assertEquals(true, this.dummyEventTask.getIsChecked(),
            "The mark function unmarked task even through it was already marked");
    }

    /**
     * Tests if the unmark function correctly unmarks a task.
     * <p>
     * If the task is already unmarked it should stay unmarked.
     */
    @Test
    public void testUnmark() {

        this.dummyEventTask = new EventTask(this.taskDescription, this.taskStartDate, this.taskEndDate);

        // We need to ensure that the mark function works before testing the unmark function
        this.dummyEventTask.mark();

        assertEquals(true, this.dummyEventTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyEventTask.unmark();

        assertEquals(false, this.dummyEventTask.getIsChecked(),
            "The mark function did not mark the task as its instructed to");

        this.dummyEventTask.unmark();

        assertEquals(false, this.dummyEventTask.getIsChecked(),
            "The mark function unmarked task even through it was already marked");
    }

    /**
     * Tests if the toCsvFormat function converts a event task into the proper csv format.
     * <p>
     * For this test the task will be unmarked.
     */
    @Test
    public void testToCsvFormatUnmarked() {

        this.dummyEventTask = new EventTask(this.taskDescription, this.taskStartDate, this.taskEndDate);

        String expectedTaskCsvFormat = "EVENT,Dummy event task,10/05/2024 10:00:30,15/06/2024 12:30:00,false"
            + ",THERE-IS-NO-TAG";
        String actualTaskCsvFormat = this.dummyEventTask.toCsvFormat();

        assertEquals(expectedTaskCsvFormat, actualTaskCsvFormat,
            "The toCsvFormat function did not convert the task properly to its csv format");
    }

    /**
     * Tests if the toCsvFormat function converts a event task into the proper csv format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToCsvFormatMarked() {

        this.dummyEventTask = new EventTask(this.taskDescription, this.taskStartDate, this.taskEndDate);
        this.dummyEventTask.mark();

        String expectedTaskCsvFormat = "EVENT,Dummy event task,10/05/2024 10:00:30,15/06/2024 12:30:00,"
            + "true,THERE-IS-NO-TAG";
        String actualTaskCsvFormat = this.dummyEventTask.toCsvFormat();

        assertEquals(expectedTaskCsvFormat, actualTaskCsvFormat,
            "The toCsvFormat function did not convert the task properly to its csv format");
    }

    /**
     * Tests if the toString function converts a event task into the proper string format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToStringUnmarked() {

        this.dummyEventTask = new EventTask(this.taskDescription, this.taskStartDate, this.taskEndDate);

        String expectedTaskStringFormat = "[E][ ] Dummy event task "
            + "(From: 10/05/2024 10:00:30 To: 15/06/2024 12:30:00)";
        String actualTaskStringFormat = this.dummyEventTask.toString();

        assertEquals(expectedTaskStringFormat, actualTaskStringFormat,
            "The toString function did not convert the task properly to its string format");
    }

    /**
     * Tests if the toString function converts a event task into the proper string format.
     * <p>
     * For this test the task will be marked.
     */
    @Test
    public void testToStringMarked() {

        this.dummyEventTask = new EventTask(this.taskDescription, this.taskStartDate, this.taskEndDate);
        this.dummyEventTask.mark();

        String expectedTaskStringFormat = "[E][X] Dummy event task "
            + "(From: 10/05/2024 10:00:30 To: 15/06/2024 12:30:00)";
        String actualTaskStringFormat = this.dummyEventTask.toString();

        assertEquals(expectedTaskStringFormat, actualTaskStringFormat,
            "The toString function did not convert the task properly to its string format");
    }
}
