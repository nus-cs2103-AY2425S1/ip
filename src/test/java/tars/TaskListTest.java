package tars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

/**
 * Class represents testing class for methods that test taskList methods
 * Tests correct execution of methods and error handling when given wrong inputs
 *
 * @author SKarthikeyan28
 */
public class TaskListTest {
    private TaskList taskList;
    @BeforeEach
    void initialise(){
        this.taskList = new TaskList(new ArrayList<>());
    }

    //Checked with Chat-GPT on ways to write tests to check if taskList methods can handle a correct entry
    //and also return error messages when given wrong entries
    //mainly asked about the assertEquals method
    /**
     * Tests on adding todo task to the list when user gives input for the task
     */
    @Test
    void testAddToDos() {
        String[] task = {"t", "Read", "book"};
        taskList.addToDos(task);

        assertEquals(1, taskList.getList().size());
        assertEquals("[T] [ ] Read book", taskList.getList().get(0).toString());
    }

    /**
     * Tests on the error of giving invalid date entry for a deadline task addition call by user
     */
    @Test
    void addDeadline_testInvalidDateFormat() {
        String[] task = {"deadline", "Submit", "assignment", "/by", "1st", "Sept"};
        String result = taskList.addDeadline(task);

        assertEquals(0, taskList.getList().size());

        String expectedOutput = " Please state date and time of deadline\n" + "in YYYY-MM-dd HH:mm format";
        assertEquals(expectedOutput, result);
    }

    /**
     * Tests on adding deadline task to the list when user gives input for deadline
     */
    @Test
    void testAddDeadline() {
        String[] task = {"d", "Submit", "assignment", "/by", "2024-09-01", "23:59"};
        taskList.addDeadline(task);

        assertEquals(1, taskList.getList().size());

        String expectedOutput = "[D] [ ] Submit assignment (by: Sep 01 2024 23:59)";
        assertEquals(expectedOutput, (taskList.getList().get(0)).toString());
    }

    /**
     * Tests deleting task method giving error message when wrong input given instead of index
     */
    @Test
    void deleteTask_testInvalidIndex() {
        String[] task = {"deadline", "Submit", "assignment", "/by", "2024-09-01", "23:59"};
        taskList.addDeadline(task);
        String result = taskList.deleteTask(2);

        assertEquals(1, taskList.getList().size());

        String expectedOutput = "Please state index of task within the list of Tasks";
        assertEquals(expectedOutput, result);
    }
}
