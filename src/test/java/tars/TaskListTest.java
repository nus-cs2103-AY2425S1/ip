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
        String[] task = {"todo", "Read", "book"};
        String entry = "todo Read book";

        taskList.addToDos(task, entry);

        assertEquals(1, taskList.getList().size());
        assertEquals("[T] [ ] Read book", taskList.getList().get(0).toString());
    }

    @Test
    void testAddDeadline() {
        String[] task = {"deadline", "Submit", "assignment", "/by", "2024-09-01", "23:59"};
        String entry = "deadline Submit assignment /by 2024-09-01 23:59";

        taskList.addDeadline(task, entry);

        assertEquals(1, taskList.getList().size());

        String expectedOutput = "[D] [ ] Submit assignment (by: Sep 01 2024 23:59)";
        assertEquals(expectedOutput, (taskList.getList().get(0)).toString());
    }

    @Test
    void testAddEvent() {
        String[] task = {"deadline", "Submit", "assignment", "/by", "2024-09-01", "23:59"};
        String entry = "deadline Submit assignment /by 2024-09-01 23:59";

        taskList.addDeadline(task, entry);

        assertEquals(1, taskList.getList().size());

        String expectedOutput = "[D] [ ] Submit assignment (by: Sep 01 2024 23:59)";
        assertEquals(expectedOutput, (taskList.getList().get(0)).toString());
    }
}
