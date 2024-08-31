package orion.chatbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import orion.exceptions.OrionException;

import orion.tasks.Deadline;
import orion.tasks.Event;
import orion.tasks.Task;
import orion.tasks.Todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskListTest {

    @Test
    public void getTaskDescriptionsTest() {
        try {
            Todo todo = new Todo("sleep");
            Deadline deadline = new Deadline("sleep again", "2024-01-02");
            Event event = new Event("sleep and sleep again", "2024-01-03", "2024-01-04");

            List<Task> tasks = new ArrayList<>();
            Collections.addAll(tasks, todo, deadline, event);
            TaskList tl = new TaskList(tasks);

            List<String> strs = new ArrayList<>();
            Collections.addAll(strs, "1. [T][ ] sleep",
                    "2. [D][ ] sleep again (due by: 2/1/2024)",
                    "3. [E][ ] sleep and sleep again (from: 3/1/2024, to: 4/1/2024)");
            assertEquals(tl.getTaskDescriptions(), strs);
        } catch (OrionException e) {

        }
    }
}
