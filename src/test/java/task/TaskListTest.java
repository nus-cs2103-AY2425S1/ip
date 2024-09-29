package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTask_addAllTaskTypes_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("string"));
        taskList.addTask(new Deadline("string2", LocalDate.parse("2024-01-01")));
        taskList.addTask(new Event("string3", LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-01-02")));
        assertEquals(taskList.getTaskCount(), 3);
    }

    @Test
    public void deleteTask_deleteFirstTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("string"));
        taskList.addTask(new Deadline("string2", LocalDate.parse("2024-01-01")));
        taskList.addTask(new Event("string3", LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-01-02")));
        taskList.deleteTask(0);
        assertEquals(taskList.getTaskCount(), 2);
    }

    @Test
    public void getSummary_nonEmptyTaskList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("todo1"));
        taskList.addTask(new Todo("todo2"));
        assertEquals(taskList.getSummary(), "You have 2 tasks currently.");
    }

    @Test
    public void getSummary_emptyTaskList_success() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSummary(), "You have no tasks currently.");
    }

    @Test
    public void toString_nonEmptyTaskList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("string", true));
        taskList.addTask(new Deadline("string2", LocalDate.parse("2024-01-01")));
        taskList.addTask(new Event("string3", LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-01-02")));
        assertEquals(taskList.toString(),
                "You have 3 tasks currently.\n1. [T][X] string\n2. [D][ ] string2 (by: Jan 1 2024)\n"
                        + "3. [E][ ] string3 (from: Jan 1 2024, to: Jan 2 2024)\n");
    }

    @Test
    public void toData_nonEmptyTaskList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("string", true));
        taskList.addTask(new Deadline("string2", LocalDate.parse("2024-01-01")));
        taskList.addTask(new Event("string3", LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-01-02")));
        assertEquals(taskList.toData(),
                "T | string | true\nD | string2 | false | 2024-01-01\n"
                + "E | string3 | false | 2024-01-01 | 2024-01-02");
    }

    @Test
    public void markTask_markFirstTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("string1"));
        taskList.markTask(0);
        assertEquals(taskList.toString(), "You have 1 task currently.\n1. [T][X] string1\n");
    }

    @Test
    public void unmarkTask_unmarkFirstTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("string1", true));
        taskList.unmarkTask(0);
        assertEquals(taskList.toString(), "You have 1 task currently.\n1. [T][ ] string1\n");
    }

    @Test
    public void findMatches_nonEmptyTaskList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("string1"));
        taskList.addTask(new Todo("striing"));
        taskList.addTask(new Deadline("sTriNg", LocalDate.parse("2024-01-01")));
        assertEquals(taskList.findMatches("string"),
                "2 tasks found!\n1. [T][ ] string1\n3. [D][ ] sTriNg (by: Jan 1 2024)\n");
    }

    @Test
    public void genereateSchedule_nonEmptyTaskList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("string"));
        taskList.addTask(new Todo("string2"));
        taskList.addTask(new Deadline("string3", LocalDate.parse("2024-01-01")));
        taskList.addTask(new Deadline("string4", LocalDate.parse("2024-01-02")));
        taskList.addTask(new Event("string5",
                LocalDate.parse("2024-01-03"), LocalDate.parse("2024-01-04")));
        taskList.addTask(new Event("string6",
                LocalDate.parse("2024-01-02"), LocalDate.parse("2024-01-04")));
        assertEquals(taskList.generateSchedule(LocalDate.parse("2024-01-02")),
                "2 tasks on Jan 2 2024!\n4. [D][ ] string4 (by: Jan 2 2024)\n"
                        + "6. [E][ ] string6 (from: Jan 2 2024, to: Jan 4 2024)\n");
    }
}
