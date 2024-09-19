package tars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;


public class TaskListTest {
    private TaskList taskList;
    @BeforeEach
    void initialise(){
        this.taskList = new TaskList(new ArrayList<>());
    }
    @Test
    void testAddToDos() {
        String[] task = {"t", "Read", "book"};
        String entry = "t Read book";

        taskList.addToDos(task, entry);

        assertEquals(1, taskList.getList().size());
        assertEquals("[T] [ ] Read book", taskList.getList().get(0).toString());
    }

    @Test
    void addDeadline_testInvalidDateFormat() {
        String[] task = {"deadline", "Submit", "assignment", "/by", "1st", "Sept"};
        String entry = "deadline Submit assignment /by 1st Sept";

        String result = taskList.addDeadline(task, entry);

        assertEquals(0, taskList.getList().size());

        String expectedOutput = " Please state date and time of deadline\n" + "in YYYY-MM-dd HH:mm format";
        assertEquals(expectedOutput, result);
    }

    @Test
    void testAddDeadline() {
        String[] task = {"d", "Submit", "assignment", "/by", "2024-09-01", "23:59"};
        String entry = "d submit assignment /by 2024-09-01 23:59";

        taskList.addDeadline(task, entry);

        assertEquals(1, taskList.getList().size());

        String expectedOutput = "[D] [ ] Submit assignment (by: Sep 01 2024 23:59)";
        assertEquals(expectedOutput, (taskList.getList().get(0)).toString());
    }

    @Test
    void deleteTask_testInvalidIndex() {
        String[] task = {"deadline", "Submit", "assignment", "/by", "2024-09-01", "23:59"};
        String entry = "deadline Submit assignment /by 2024-09-01 23:59";

        taskList.addDeadline(task, entry);
        String result = taskList.deleteTask(2);

        assertEquals(1, taskList.getList().size());

        String expectedOutput = "Please state index of task within the list of Tasks";
        assertEquals(expectedOutput, result);
    }
}
