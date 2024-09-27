package mgtow;

import mgtow.task.Deadline;
import mgtow.task.Event;
import mgtow.task.Task;
import mgtow.task.TaskList;
import mgtow.task.ToDo;
import mgtow.util.InvalidTaskException;
import mgtow.util.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MgtowTest {

    private Parser parser;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        parser = new Parser();
        taskList = new TaskList();
    }

    @Test
    void testParserCreateTask() throws InvalidTaskException {
        // Test creating a mgtow.task.ToDo task
        String[] todoCommand = {"todo", "Buy groceries"};
        Task todoTask = Parser.createTask(todoCommand);
        assertTrue(todoTask instanceof ToDo);
        assertEquals("Buy groceries", todoTask.getDesc());

        // Test creating a mgtow.task.Deadline task
        String[] deadlineCommand = {"deadline", "Submit report /by 2023-12-31 2359"};
        Task deadlineTask = Parser.createTask(deadlineCommand);
        assertTrue(deadlineTask instanceof Deadline);
        assertEquals("Submit report", deadlineTask.getDesc());
        assertEquals("2023-12-31 2359", ((Deadline) deadlineTask).getDeadline());

        // Test creating an mgtow.task.Event task
        String[] eventCommand = {"event", "Team meeting /from 2023-12-01 1400 /to 2023-12-01 1600"};
        Task eventTask = Parser.createTask(eventCommand);
        assertTrue(eventTask instanceof Event);
        assertEquals("Team meeting", eventTask.getDesc());
        assertEquals("2023-12-01 1400", ((Event) eventTask).getStart());
        assertEquals("2023-12-01 1600", ((Event) eventTask).getEnd());

        // Test invalid task type
        String[] invalidCommand = {"invalid", "This should fail"};
        assertThrows(InvalidTaskException.class, () -> Parser.createTask(invalidCommand));

        // Test invalid deadline format
        String[] invalidDeadlineCommand = {"deadline", "Invalid deadline /by not-a-date"};
        assertThrows(InvalidTaskException.class, () -> Parser.createTask(invalidDeadlineCommand));

        // Test invalid event format
        String[] invalidEventCommand = {"event", "Invalid event /from 2023-12-01 1400 /to not-a-date"};
        assertThrows(InvalidTaskException.class, () -> Parser.createTask(invalidEventCommand));
    }

    @Test
    void testTaskListGetTasksOnDate() throws InvalidTaskException {
        LocalDate testDate = LocalDate.of(2023, 12, 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        // Add tasks to the task list
        taskList.addTask(new ToDo("Buy groceries")); // Should not be included
        taskList.addTask(new Deadline("Submit report", "2023-12-01 2359"));
        taskList.addTask(new Event("Team meeting", "2023-12-01 1400", "2023-12-01 1600"));
        taskList.addTask(new Deadline("Another deadline", "2023-12-02 1200")); // Should not be included

        ArrayList<Task> tasksOnDate = taskList.getTasksOnDate(testDate);

        assertEquals(2, tasksOnDate.size());
        assertTrue(tasksOnDate.stream().anyMatch(task -> task instanceof Deadline && ((Deadline) task).getEnd().toLocalDate().equals(testDate)));
        assertTrue(tasksOnDate.stream().anyMatch(task -> task instanceof Event && ((Event) task).getStartDateTime().toLocalDate().equals(testDate)));
    }
}