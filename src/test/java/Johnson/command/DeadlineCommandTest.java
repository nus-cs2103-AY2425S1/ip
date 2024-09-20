// src/test/java/Johnson/command/DeadlineCommandTest.java
package Johnson.command;

import Johnson.task.Deadline;
import Johnson.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    void testConstructorWithTime() {
        String[] tags = new String[]{"urgent"};
        DeadlineCommand command = new DeadlineCommand("Finish project", "2023-10-10", "18:00", tags);
        Deadline deadline = command.getDeadline();
        assertEquals("Finish project", deadline.getTaskName());
        assertEquals("2023-10-10", deadline.getDate().toString());
        assertEquals("18:00", deadline.getTime().toString());
        assert tags[0].equals(deadline.getTags().get(0).getTag());
    }

    @Test
    void testConstructorWithoutTime() {
        String[] tags = new String[]{"important"};
        DeadlineCommand command = new DeadlineCommand("Submit report", "2023-10-11", tags);
        Deadline deadline = command.getDeadline();
        assertEquals("Submit report", deadline.getTaskName());
        assertEquals("2023-10-11", deadline.getDate().toString());
        assertNull(deadline.getTime());
        assert tags[0].equals(deadline.getTags().get(0).getTag());
    }

    @Test
    void testExecuteCommand() {
        DeadlineCommand command = new DeadlineCommand("Prepare presentation", "2023-10-12", "14:00", new String[] {"work"});
        String result = command.executeCommand();
        assertTrue(result.contains("Let's move, Chief! Got another deadline to hit:"));
        assertTrue(result.contains("Prepare presentation"));
        assertTrue(result.contains("2023-10-12"));
        assertTrue(result.contains("14:00"));
        assertTrue(result.contains("work"));
        assertEquals(1, taskList.taskCount());
        assertEquals(command.getDeadline(), taskList.getTask(0));
    }
}