package skd.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class FindTest {

    private List<Task> tasks;

    @BeforeEach
    public void setUp() {
        tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new Deadline("return book", "2024-08-30 18:00"));
        tasks.add(new Event("project meeting", "2024-08-30 14:00", "2024-08-30 16:00"));
    }

    @Test
    public void testFindCommandSingleMatch() {
        String keyword = "read";
        long matchCount = tasks.stream().filter(task -> task.toString().contains(keyword)).count();

        assertEquals(1, matchCount);
        assertTrue(tasks.get(0).toString().contains(keyword));
    }

    @Test
    public void testFindCommandMultipleMatches() {
        String keyword = "book";
        long matchCount = tasks.stream().filter(task -> task.toString().contains(keyword)).count();

        assertEquals(2, matchCount);
        assertTrue(tasks.get(0).toString().contains(keyword));
        assertTrue(tasks.get(1).toString().contains(keyword));
    }

    @Test
    public void testFindCommandNoMatch() {
        String keyword = "exercise";
        long matchCount = tasks.stream().filter(task -> task.toString().contains(keyword)).count();

        assertEquals(0, matchCount);
    }
}
