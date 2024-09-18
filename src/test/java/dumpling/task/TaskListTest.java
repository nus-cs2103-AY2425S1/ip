package dumpling.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void list_success() {
        TaskList taskList = new TaskList();
        Task[] tasksToBeAdded = {
            new ToDo("task 1", "task notes"),
            new Deadline("task 2", "2024-09-17"),
            new Event("task 3", "2024-09-17", "2024-09-18")
        };
        String[] expectedOutputs = {
            "     Hungry? Here are the tasks in your list:\n"
                    + "     1.[T][ ] task 1 (task notes)",
            "     Hungry? Here are the tasks in your list:\n"
                    + "     1.[T][ ] task 1 (task notes)\n"
                    + "     2.[D][ ] task 2 (by: Sep 17 2024)",
            "     Hungry? Here are the tasks in your list:\n"
                    + "     1.[T][ ] task 1 (task notes)\n"
                    + "     2.[D][ ] task 2 (by: Sep 17 2024)\n"
                    + "     3.[E][ ] task 3 (from: Sep 17 2024 to: Sep 18 2024)",
        };
        assertEquals(
                "      I'm hungry cause there are no tasks in your list...",
                taskList.list()
        );
        assert tasksToBeAdded.length == expectedOutputs.length
                : "Number of test cases to expected outputs are not equal";
        int testCases = tasksToBeAdded.length;
        for (int i = 0; i < testCases; i++) {
            taskList.add(tasksToBeAdded[i]);
            assertEquals(
                    expectedOutputs[i],
                    taskList.list()
            );
        }
    }

    @Test
    public void mark_success() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("task 1"));
        assertEquals(
                "     Slurp! I've marked this task as done:\n"
                        + "       [T][X] task 1",
                taskList.mark(1)
        );
    }

    @Test
    public void mark_indexOutOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("task 1"));

        // test for index 0
        try {
            assertEquals(
                    "     Slurp! I've marked this task as done:\n"
                            + "       [T][X] task 1",
                    taskList.mark(0)
            );
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(
                    null,
                    e.getMessage()
            );
        }

        // test for index outside length of tasklist
        try {
            assertEquals(
                    "     Slurp! I've marked this task as done:\n"
                            + "       [T][X] task 1",
                    taskList.mark(2)
            );
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(
                    null,
                    e.getMessage()
            );
        }
    }

    @Test
    public void updateTaskNotes_success() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("task 1"));
        taskList.add(new ToDo("task 2", "old task notes"));

        // test if task notes was empty
        assertEquals(
                "     Nom, nom, nom. I've updated the notes for this task:\n"
                        + "       [T][ ] task 1 (task notes)\n",
                taskList.updateTaskNotes(1, "task notes")
        );

        // test if new task notes overwrites old task notes
        assertEquals(
                "     Nom, nom, nom. I've updated the notes for this task:\n"
                        + "       [T][ ] task 2 (new task notes)\n",
                taskList.updateTaskNotes(2, "new task notes")
        );
    }

    @Test
    public void updateTaskNotes_indexOutOfBounds_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("task 1"));
        try {
            assertEquals(
                    "     Nom, nom, nom. I've updated the notes for this task:\n"
                            + "       [T][ ] task 1 (task notes)\n",
                    taskList.updateTaskNotes(0, "task notes")
            );
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(
                    null,
                    e.getMessage()
            );
        }

        try {
            assertEquals(
                    "     Nom, nom, nom. I've updated the notes for this task:\n"
                            + "       [T][ ] task 1 (task notes)\n",
                    taskList.updateTaskNotes(2, "task notes")
            );
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(
                    null,
                    e.getMessage()
            );
        }
    }
}
