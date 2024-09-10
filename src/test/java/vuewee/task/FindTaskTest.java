package vuewee.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import vuewee.parser.CommandParser;
import vuewee.parser.description.StringDescriptionParser;

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
        TaskList matchingTasks = taskList.findTasks(parser.parse(new StringDescriptionParser()));
        assertEquals(1, matchingTasks.size());
        assertEquals("T | 0 | task2\n", matchingTasks.serialize());
    }

    @Test
    public void testNoResult() {
        TaskList taskList = generateTaskList();

        CommandParser parser = new CommandParser("find asdf");
        TaskList matchingTasks = taskList.findTasks(parser.parse(new StringDescriptionParser()));
        assertEquals(0, matchingTasks.size());
    }

    @Test
    public void testAllResultCaseInsensitive() {
        TaskList taskList = generateTaskList();

        CommandParser parser = new CommandParser("find TASK");
        TaskList matchingTasks = taskList.findTasks(parser.parse(new StringDescriptionParser()));
        assertEquals(3, matchingTasks.size());
        assertEquals("T | 0 | task1\nT | 0 | task2\nT | 0 | task3\n", matchingTasks.serialize());
    }
}
