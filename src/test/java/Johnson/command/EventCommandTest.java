// src/test/java/Johnson/command/DeadlineCommandTest.java
package Johnson.command;


import Johnson.task.Event;
import Johnson.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    void testConstructorWithTime() {
        String[] tags = new String[]{"urgent"};
        EventCommand command = new EventCommand("Finish project", "2023-10-10", "18:00", tags);
        Event deadline = command.getEvent();
        assertEquals("Finish project", deadline.getTaskName());
        assertEquals("2023-10-10", deadline.getDate().toString());
        assertEquals("18:00", deadline.getTime().toString());
        assert tags[0].equals(deadline.getTags().get(0).getTag());
    }

    @Test
    void testConstructorWithoutTime() {
        String[] tags = new String[]{"important"};
        EventCommand command = new EventCommand("Submit report", "2023-10-11", tags);
        Event deadline = command.getEvent();
        assertEquals("Submit report", deadline.getTaskName());
        assertEquals("2023-10-11", deadline.getDate().toString());
        assertNull(deadline.getTime());
        assert tags[0].equals(deadline.getTags().get(0).getTag());
    }

    @Test
    void testExecuteCommand() {
        EventCommand command = new EventCommand("Prepare presentation", "2023-10-12", "14:00", new String[] {"work"});
        String result = command.executeCommand();
        assertTrue(result.contains("Eyes peeled Chief! Got an event coming up:"));
        assertTrue(result.contains("Prepare presentation"));
        assertTrue(result.contains("2023-10-12"));
        assertTrue(result.contains("14:00"));
        assertTrue(result.contains("work"));
        assertEquals(1, taskList.taskCount());
        assertEquals(command.getEvent(), taskList.getTask(0));
    }
}