package infinity.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import infinity.infinityexception.InfinityException;
import infinity.task.ToDos;

public class TaskListTest {

    @Test
    public void addTask_validTaskAdded_taskAdded() {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            assertEquals(taskList.isEmpty(), true);
            taskList.addTask(new ToDos("todo task A"));
            assertEquals(taskList.getTasks().size(), 1);
            assertEquals(taskList.isEmpty(), false);
            System.out.println("Test 1: Valid Todo Added - passed");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void addTask_overMaxTasksAdded_exceptionCaught() {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            for (int i = 0; i < (TaskList.MAX_SIZE + 1); i++) {
                taskList.addTask(new ToDos("todo task " + i));
            }
            fail();
        } catch (InfinityException e) {
            assertEquals(e.getMessage(), """
                    I'm sorry, but I can't remember more tasks.
                    """);
            System.out.println("Test 2: Too Many Todo Added - passed");
        }
    }

    @Test
    public void deleteTask_validTaskAddedThenDeleted_taskDeleted() {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            taskList.addTask(new ToDos("todo task A"));
            assertEquals(taskList.getTasks().size(), 1);
            taskList.deleteTask("1");
            assertEquals(taskList.getTasks().size(), 0);
            System.out.println("Test 3: Valid Todo Deleted - passed");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteTask_invalidTaskDeleted_exceptionCaught() {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            taskList.deleteTask("1");
            fail();
        } catch (InfinityException e) {
            assertEquals(e.getMessage(), """
                    Hmmm, you seem to have chose a task that doesn't exist. Nice try :)
                    """);
            System.out.println("Test 4: Invalid Todo Deleted - passed");
        }
    }

    @Test
    public void deleteTask_invalidStringParsed_exceptionCaught() {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            taskList.deleteTask("");
            fail();
        } catch (InfinityException e) {
            assertEquals(e.getMessage(), """
                    Hey! That's not a number
                    """);
            System.out.println("Test 5: Invalid String Parsed for Delete - passed");
        }
    }

    @Test
    public void markTask_validTaskMarked_taskMarked() {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            taskList.addTask(new ToDos("todo task A"));
            taskList.markTask("1");
            assertEquals(taskList.getTasks().get(0).toString(), "[T][X] todo task A");
            System.out.println("Test 6: Valid Marked Test - passed");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void markTask_invalidTaskMarked_exceptionCaught() {
        TaskList taskList = new TaskList(new ArrayList<>());
        try {
            taskList.markTask("mark 1");
            fail();
        } catch (InfinityException e) {
            assertEquals(e.getMessage(), """
                    Hey! That's not a number
                    """);
            System.out.println("Test 7: Invalid Marked Test - passed");
        }
    }

}
