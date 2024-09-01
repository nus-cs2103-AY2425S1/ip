package bob;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new ToDo("read book", false));
        taskList.addTask(new ToDo("buy pencil", false));
        taskList.addTask(new ToDo("do laundry", true));
        taskList.addTask(new Deadline("submit assignment",
                LocalDateTime.of(2024, 12, 12, 23, 59)));
        taskList.addTask(new Event("project meeting",
                LocalDateTime.of(2024, 12, 10, 14, 0),
                LocalDateTime.of(2024, 12, 10, 16, 0)));
        taskList.addTask(new Event("trip",
                LocalDateTime.of(2024, 12, 9, 0, 0),
                LocalDateTime.of(2024, 12, 16, 0, 0)));
    }

    @Test
    public void testDelTask_validTaskNum() {
        try {
            int initialSize = taskList.getNumTasks();
            taskList.delTask(1);
            assertEquals(initialSize - 1, taskList.getNumTasks());
            assertEquals("buy pencil", taskList.getTask(1).getDescription());
        } catch (BobException e) {
            fail("BobException should not be thrown when given a valid task number.");
        }
    }

    @Test
    public void delTask_invalidTaskNum_exceptionThrown() {
        BobException e1 = assertThrows(BobException.class, () -> taskList.delTask(10));
        assertEquals("The task number provided is invalid.", e1.getMessage());

        BobException e2 = assertThrows(BobException.class, () -> taskList.delTask(-1));
        assertEquals("The task number provided is invalid.", e2.getMessage());
    }

    @Test
    public void testPrintTasks_withTasks_printsCorrectly() {
        String expected = "1. [T][ ] read book\n"
                + "2. [T][ ] buy pencil\n"
                + "3. [T][X] do laundry\n"
                + "4. [D][ ] submit assignment (by: Dec 12 2024 11:59 pm)\n"
                + "5. [E][ ] project meeting (from: Dec 10 2024 02:00 pm to: Dec 10 2024 04:00 pm)\n"
                + "6. [E][ ] trip (from: Dec 09 2024 12:00 am to: Dec 16 2024 12:00 am)";
        assertEquals(expected, taskList.printTasks());
    }

    @Test
    public void testPrintTasks_noTasks_printsEmptyList() {
        TaskList emptyTaskList = new TaskList();
        assertEquals("", emptyTaskList.printTasks());
    }

    @Test
    void testGetRelevantTasks_validDate() throws BobException {
        String validDate1 = "2024-12-12";
        String expected1 = "1. [D][ ] submit assignment (by: Dec 12 2024 11:59 pm)\n"
                + "2. [E][ ] trip (from: Dec 09 2024 12:00 am to: Dec 16 2024 12:00 am)\n"
                + "Total number of relevant tasks for Dec 12 2024: 2";
        assertEquals(expected1, taskList.printRelevantTasksByDate(validDate1));

        String validDate2 = "2024-12-09";
        String expected2 = "1. [E][ ] trip (from: Dec 09 2024 12:00 am to: Dec 16 2024 12:00 am)\n"
                + "Total number of relevant tasks for Dec 09 2024: 1";
        assertEquals(expected2, taskList.printRelevantTasksByDate(validDate2));

        String validDate3 = "2024-12-10";
        String expected3 = "1. [E][ ] project meeting (from: Dec 10 2024 02:00 pm to: Dec 10 2024 04:00 pm)\n"
                + "2. [E][ ] trip (from: Dec 09 2024 12:00 am to: Dec 16 2024 12:00 am)\n"
                + "Total number of relevant tasks for Dec 10 2024: 2";
        assertEquals(expected3, taskList.printRelevantTasksByDate(validDate3));
    }

    @Test
    void testGetRelevantTasks_noRelevantTasks() throws BobException {
        String irrelevantDate = "2025-01-01";
        String expected = "Total number of relevant tasks for Jan 01 2025: 0";
        assertEquals(expected, taskList.printRelevantTasksByDate(irrelevantDate));
    }

    @Test
    void getRelevantTasks_invalidDateFormat_exceptionThrown() {
        String invalidDate = "01-01-2025";
        BobException e = assertThrows(BobException.class, () -> taskList.printRelevantTasksByDate(invalidDate));
        assertEquals("Invalid date format. Required format: relevant yyyy-MM-dd", e.getMessage());
    }

    @Test
    void getRelevantTasks_emptyDate_exceptionThrown() {
        String emptyDateStr = "";
        BobException e = assertThrows(BobException.class, () -> taskList.printRelevantTasksByDate(emptyDateStr));
        assertEquals("Invalid date format. Required format: relevant yyyy-MM-dd", e.getMessage());
    }
}
