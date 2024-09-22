package nah.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import nah.data.Deadlines;
import nah.data.Events;
import nah.data.ToDos;
import nah.exceptions.NahException;
public class TaskListTest {
    /**
     * Tests method add.
     * The test pass if the String return by method add is correct
     */
    @Test
    public void addTest1() {
        TaskList tasks = new TaskList();
        assertEquals(" Got it. I've added this task:\n"
                + "   [T] [  ] go to bed\n"
                + " Now you have 1 tasks in the list.\n",
                tasks.add(new ToDos("go to bed")));

    }

    /**
     * Tests method delete.
     * The test pass if the String return by method delete is correct and no exception is thrown
     */
    @Test void deleteTest1() {
        TaskList tasks = new TaskList();
        for (int i = 1; i <= 5; i++) {
            tasks.add(new ToDos(i + "pull-ups"));
        }
        try {
            tasks.delete(4);
            assertEquals(" Here are the tasks in your list:\n"
                            + " 1. [T] [  ] 1pull-ups\n"
                            + " 2. [T] [  ] 2pull-ups\n"
                            + " 3. [T] [  ] 3pull-ups\n"
                            + " 4. [T] [  ] 5pull-ups\n",
                    tasks.readTask());
        } catch (NahException e) {
            fail("NahException Unexpected");
        }
    }

    /**
     * Tests method readTask.
     * Preparation needs to create a new TaskList, so some Tasks must be created and some exceptions
     * need to be handled.
     * The test pass if the String return by method readTask is correct.
     */
    @Test
    public void readTaskTest1() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDos("go to bed"));
        try {
            LocalDateTime time = LocalDateTime
                    .parse("2025-12-25 2400".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Deadlines("Noel", time));
            tasks.mark(1);
        } catch (DateTimeParseException | NahException e) {
            fail("Unexpected exception");
        }
        assertEquals(" Here are the tasks in your list:\n"
                + " 1. [T] [X] go to bed\n"
                + " 2. [D] [  ] Noel (by: Dec 26 2025, 12:00 AM)\n", tasks.readTask());
    }

    /**
     * Tests method dueOn.
     * Preparation needs to create a new TaskList, so some task must be created and some exceptions
     * need to be handled.
     * The test pass if the String return by method dueOn is correct.
     */
    @Test
    public void dueOnTest1() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDos("go to bed"));
        try {
            LocalDateTime time1 = LocalDateTime
                    .parse("2025-12-25 2400".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Deadlines("Noel", time1));
            tasks.mark(1);
            LocalDateTime start = LocalDateTime
                    .parse("2025-10-10 1800".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime end = LocalDateTime
                    .parse("2025-10-10 2000".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Events("dinner", start, end));
            LocalDateTime time2 = LocalDateTime
                    .parse("2026-01-01 0000".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Deadlines("new year", time2));
            tasks.mark(4);

        } catch (DateTimeParseException | NahException e) {
            fail("Unexpected exception");
        }
        assertEquals(" Here are the tasks in your list that end before the due date:\n"
                + " 2. [D] [  ] Noel (by: Dec 26 2025, 12:00 AM)\n"
                + " 3. [E] [  ] dinner (from: Oct 10 2025, 6:00 PM to: Oct 10 2025, 8:00 PM)\n",
                tasks.dueOn("2026-01-01 2000"));
    }

    /**
     * Tests method mark.
     * The test pass if the correct exception is thrown when trying to mark a non-exist Task.
     */
    @Test
    public void exceptionTest1() {
        TaskList tasks = new TaskList();
        for (int i = 1; i <= 5; i++) {
            tasks.add(new ToDos(i + "push-ups"));
        }
        try {
            tasks.mark(10);
            fail("NahException expected");
        } catch (NahException e) {
            assertEquals(" NAH!!! Nah.Data.Task 10 doesn't exist. Please give an number between 1 and 5\n",
                    e.getMessage());
        }
    }

    /**
     * Tests method find.
     * Preparation needs to create a new TaskList, so some task must be created and some exceptions
     * need to be handled.
     * The test pass if the String return by method find is correct.
     */
    @Test
    public void findTest1() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDos("go to school with Jonh"));
        try {
            LocalDateTime time1 = LocalDateTime
                    .parse("2025-12-25 2400".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Deadlines("celebrate Noel", time1));

            LocalDateTime start = LocalDateTime
                    .parse("2025-10-10 1800".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime end = LocalDateTime
                    .parse("2025-10-10 2000".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Events("dinner", start, end));
            LocalDateTime time2 = LocalDateTime
                    .parse("2026-01-01 0000".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Deadlines("celebrate new year", time2));


        } catch (DateTimeParseException e) {
            fail("Unexpected exception");
        }
        assertEquals(" Oke. Here are the task that match keywords:\n"
                        + " 1. [T] [  ] go to school with Jonh\n"
                        + " 2. [D] [  ] celebrate Noel (by: Dec 26 2025, 12:00 AM)\n"
                        + " 4. [D] [  ] celebrate new year (by: Jan 1 2026, 12:00 AM)\n",
                tasks.find("ToDo celebrate"));
    }
}
