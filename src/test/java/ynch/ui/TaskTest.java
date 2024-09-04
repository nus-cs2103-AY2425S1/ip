import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TaskTest {
    
    @Test
    void testMarkAndUnmarkTask() {
        // Create a new task
        Task task = new Task("Buy groceries");
        assertFalse(task.isDone, "Task should not be done initially");

        // Mark the task as done
        task.mark();
        assertTrue(task.isDone, "Task should be marked as done");

        // Unmark the task
        task.unmark();
        assertFalse(task.isDone, "Task should be unmarked");
    }

    @Test
    void testStringToDate() {
        // Test the stringToDate method with valid date strings
        Task task = new Task("Buy groceries");
        LocalDate date = task.stringToDate("2023-04-25");
        assertNotNull(date, "Date should not be null");
        assertEquals(2023, date.getYear(), "Year should be 2023");
        assertEquals(4, date.getMonthValue(), "Month should be April");
        assertEquals(25, date.getDayOfMonth(), "Day should be 25");

        // Test the stringToDate method with an invalid date string
        date = task.stringToDate("invalid-date");
        assertNull(date, "Date should be null for an invalid string");
    }

}