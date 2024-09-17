package Johnson.command;

import Johnson.task.TaskList;
import Johnson.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindTagCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    public void testExecuteCommandWithMatchingTags() {
        ToDo task1 = new ToDo("read book");
        task1.addTag("leisure");
        taskList.addTask(task1);

        ToDo task2 = new ToDo("write report");
        task2.addTag("work");
        taskList.addTask(task2);

        ToDo task3 = new ToDo("read article");
        task3.addTag("leisure");
        taskList.addTask(task3);

        FindTagCommand findTagCommand = new FindTagCommand("leisure");
        String result = findTagCommand.executeCommand();

        assertTrue(result.contains("Chief, got some things on the radar with the tag:"));
        assertTrue(result.contains("1:[T][ ] read book"));
        assertTrue(result.contains("2:[T][ ] read article"));
    }

    @Test
    public void testExecuteCommandWithNoMatchingTags() {
        ToDo task1 = new ToDo("read book");
        task1.addTag("leisure");
        taskList.addTask(task1);

        ToDo task2 = new ToDo("write report");
        task2.addTag("work");
        taskList.addTask(task2);

        FindTagCommand findTagCommand = new FindTagCommand("exercise");
        String result = findTagCommand.executeCommand();

        assertTrue(result.contains("No tasks with this tag, Chief."));
    }

    @Test
    public void testExecuteCommandWithEmptyTaskList() {
        FindTagCommand findTagCommand = new FindTagCommand("leisure");
        String result = findTagCommand.executeCommand();

        assertTrue(result.contains("No tasks with this tag, Chief."));
    }
}