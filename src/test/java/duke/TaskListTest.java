package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void getTest() throws TaskListOutOfBoundsException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("do work TODAY"));
        taskList.add(new Deadline("assignment 3 for General Relativity", LocalDate.parse("2012-12-12")));
        taskList.add(new Event("meteor shower", "Mon 4am", "Fri 6am"));
        assertEquals(taskList.size(), 3);
        assertEquals(taskList.get(2).toString(), "[E][ ] meteor shower (from: Mon 4am to: Fri 6am)");
        assertEquals(taskList.get(1).toString(), "[D][ ] assignment 3 for General Relativity (by: Dec 12 2012)");
    }
}
