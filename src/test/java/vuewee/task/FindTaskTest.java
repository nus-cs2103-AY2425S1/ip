package vuewee.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import vuewee.parser.CommandParser;

public class FindTaskTest {

    private TaskList generateTaskList() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("task1"));
        taskList.add(new TodoTask("task2"));
        taskList.add(new TodoTask("task3"));
        return taskList;
    }

    @Test
    public void testSingleResult() {
        TaskList taskList = generateTaskList();

        CommandParser parser = new CommandParser("find 2");
        parser.parse(true);
        TaskList matchingTasks = taskList.findTasks(parser.getDescription());
        assertEquals(1, matchingTasks.size());
        assertEquals("T | 0 | task2\n", matchingTasks.serialize());
    }

    @Test
    public void testNoResult() {
        TaskList taskList = generateTaskList();

        CommandParser parser = new CommandParser("find asdf");
        parser.parse(true);
        TaskList matchingTasks = taskList.findTasks(parser.getDescription());
        assertEquals(0, matchingTasks.size());
    }

    @Test
    public void testAllResultCaseInsensitive() {
        TaskList taskList = generateTaskList();

        CommandParser parser = new CommandParser("find TASK");
        parser.parse(true);
        TaskList matchingTasks = taskList.findTasks(parser.getDescription());
        assertEquals(3, matchingTasks.size());
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 0 | task3\n", matchingTasks.serialize());
    }
}