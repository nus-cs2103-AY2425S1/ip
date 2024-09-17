package Johnson.command;

import Johnson.task.TaskList;
import Johnson.task.ToDo;
import Johnson.utils.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    public void testToDoCommand() {
        ToDoCommand toDoCommand = new ToDoCommand("read book");
        ToDo toDo = toDoCommand.getToDo();
        assertEquals("read book", toDo.getTaskName());
    }

    @Test
    public void testExecuteCommand() {
        ToDoCommand toDoCommand = new ToDoCommand("read book");
        String result = toDoCommand.executeCommand();
        assertTrue(result.contains("Get tactical, Chief! Got one more for the list:"));
        assertTrue(result.contains("read book"));
        assertEquals(1, taskList.taskCount());
        assertEquals(toDoCommand.getToDo(), taskList.getTask(0));
    }

    @Test
    public void testToDoCommandWithTags() {
        String[] tags = new String[]{"important", "urgent"};
        ToDoCommand toDoCommand = new ToDoCommand("finish assignment", tags);
        ToDo toDo = toDoCommand.getToDo();
        assertEquals("finish assignment", toDo.getTaskName());
        assertArrayEquals(tags, toDo.getTags().stream().map(Tag::getTag).toArray());
    }

    @Test
    public void testExecuteCommandWithTags() {
        String[] tags = new String[]{"important", "urgent"};
        ToDoCommand toDoCommand = new ToDoCommand("finish assignment", tags);
        String result = toDoCommand.executeCommand();
        assertTrue(result.contains("Get tactical, Chief! Got one more for the list:"));
        assertTrue(result.contains("finish assignment"));
        assertTrue(result.contains("important"));
        assertTrue(result.contains("urgent"));
        assertEquals(1, taskList.taskCount());
        assertEquals(toDoCommand.getToDo(), taskList.getTask(0));
    }
}