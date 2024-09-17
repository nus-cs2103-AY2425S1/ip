package Johnson.command;

import Johnson.task.TaskList;
import Johnson.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    public void testExecuteCommandWithMatchingTasks() {
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("write report"));
        taskList.addTask(new ToDo("read article"));

        FindCommand findCommand = new FindCommand("read");
        String result = findCommand.executeCommand();

        assertTrue(result.contains("Got a few tangos on the radar, Chief:"));
        assertTrue(result.contains("1:[T][ ] read book"));
        assertTrue(result.contains("2:[T][ ] read article"));
    }

    @Test
    public void testExecuteCommandWithNoMatchingTasks() {
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("write report"));

        FindCommand findCommand = new FindCommand("exercise");
        String result = findCommand.executeCommand();

        assertTrue(result.contains("Don't know anyone by that name, Chief."));
    }

    @Test
    public void testExecuteCommandWithPartialMatches() {
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("reading session"));
        taskList.addTask(new ToDo("write report"));

        FindCommand findCommand = new FindCommand("read");
        String result = findCommand.executeCommand();

        assertTrue(result.contains("Got a few tangos on the radar, Chief:"));
        assertTrue(result.contains("1:[T][ ] read book"));
        assertTrue(result.contains("2:[T][ ] reading session"));
    }

    @Test
    public void testExecuteCommandWithEmptyTaskList() {
        FindCommand findCommand = new FindCommand("read");
        String result = findCommand.executeCommand();

        assertTrue(result.contains("Don't know anyone by that name, Chief."));
    }
}