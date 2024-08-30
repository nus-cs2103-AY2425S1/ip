package nah.tasklist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

import nah.data.Task;
import nah.exceptions.NahException;
import nah.storage.Decoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void addTest1() {
        TaskList tasks = new TaskList();
        assertEquals(" Got it. I've added this task:\n" +
                "   [T] [ ] go to bed\n" +
                " Now you have 1 tasks in the list.\n",
                tasks.add(new Task.ToDos("go to bed")));

    }

    @Test void deleteTest1() {
        TaskList tasks = new TaskList();
        for (int i = 1; i <= 5; i ++) {
            tasks.add(new Task.ToDos(i + "pull-ups"));
        }
        try {
            tasks.delete(4);
            assertEquals(" Here are the tasks in your list:\n" +
                            " 1. [T] [ ] 1pull-ups\n" +
                            " 2. [T] [ ] 2pull-ups\n" +
                            " 3. [T] [ ] 3pull-ups\n" +
                            " 4. [T] [ ] 5pull-ups\n",
                    tasks.readTask());
        } catch (NahException e) {
            fail("NahException Unexpected");
        }
    }

    @Test
    public void readTeskTest1() {
        TaskList tasks = new TaskList();
        tasks.add(new Task.ToDos("go to bed"));
        try {
            LocalDateTime time = LocalDateTime.
                    parse("2025-12-25 2400".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Task.Deadlines("Noel", time));
            tasks.mark(1);
        } catch (DateTimeParseException | NahException e) {
            fail("Unexpected exception");
        }
        assertEquals(" Here are the tasks in your list:\n" +
                " 1. [T] [X] go to bed\n" +
                " 2. [D] [ ] Noel (by: Dec 26 2025, 12:00 AM)\n", tasks.readTask());
    }

    @Test
    public void dueOnTest1() {
        TaskList tasks = new TaskList();
        tasks.add(new Task.ToDos("go to bed"));
        try {
            LocalDateTime time1 = LocalDateTime.
                    parse("2025-12-25 2400".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Task.Deadlines("Noel", time1));
            tasks.mark(1);
            LocalDateTime start = LocalDateTime.
                    parse("2025-10-10 1800".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime end = LocalDateTime.
                    parse("2025-10-10 2000".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Task.Events("dinner", start, end));
            LocalDateTime time2 = LocalDateTime.
                    parse("2026-01-01 0000".trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            tasks.add(new Task.Deadlines("new year", time2));
            tasks.mark(4);

        } catch (DateTimeParseException | NahException e) {
            fail("Unexpected exception");
        }
        assertEquals(" Here are the tasks in your list that ends before the due:\n" +
                " 2. [D] [ ] Noel (by: Dec 26 2025, 12:00 AM)\n" +
                " 3. [E] [ ] dinner (from: Oct 10 2025, 6:00 PM to: Oct 10 2025, 8:00 PM)\n",
                tasks.dueOn("2026-01-01 2000"));
    }

    @Test
    public void exceptionTest1() {
        TaskList tasks = new TaskList();
        for (int i = 1; i <= 5; i ++) {
            tasks.add(new Task.ToDos(i + "push-ups"));
        }
        try {
            tasks.mark(10);
            fail("NahException expected");
        } catch (NahException e) {
            assertEquals(" NAH!!! Nah.Data.Task 10 doesn't exist. Please give an number between 1 and 5\n",
                    e.getMessage());
        }
    }
}
