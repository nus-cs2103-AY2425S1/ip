package reminderebot;

import org.junit.jupiter.api.Test;
import reminderebot.task.Deadline;
import reminderebot.task.Event;
import reminderebot.task.Task;
import reminderebot.task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for TaskList class.
 */
public class TaskListTest {
    /**
     * Test if the delete method works as intended.
     * @throws Exception
     */
    @Test
    void testDelete() throws Exception {
        // Test if delete works with ToDo, Deadline and Event.
        LocalDateTime fromTime = LocalDateTime.of(24, 8, 22, 4, 0);
        LocalDateTime toTime = LocalDateTime.of(24, 8, 22, 4, 0);
        Event event = new Event("test 3", fromTime, toTime);
        Task[] tasks = {new ToDo("test 1"), new Deadline("test 2", toTime), event};
        TaskList taskList = new TaskList(new ArrayList<>(List.of(tasks)));
        taskList.deleteTask(1);
        assertEquals(event, taskList.getTask(1), "`delete()` should remove task from tasklist");
    }
}
