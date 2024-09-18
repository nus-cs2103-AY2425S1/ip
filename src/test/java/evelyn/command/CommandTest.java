package evelyn.command;

import evelyn.task.Deadline;
import evelyn.task.Event;
import evelyn.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    private String home = System.getProperty("user.home");
    private final java.nio.file.Path DEFAULT_FILE_PATH = java.nio.file.Paths.get(home, "Documents", "EvelynTest.txt");
    private TaskList list = new TaskList(new Storage(DEFAULT_FILE_PATH));

    @Test
    public void checkedTodoTest() {
        Todo todo = new Todo("Test 2", true);
        assertEquals("[T][X] Test 2", todo.toString());
    }

    @Test
    public void checkedDeadlineTest() {
        Deadline deadline = new Deadline("Test 4", "2020-03-11", true);
        assertEquals("[D][X] Test 4 (by: 2020-03-11)", deadline.toString());
    }

    @Test
    public void checkedEventTest() {
        Event event = new Event("Test 6", "2020-04-04", "2020-04-04", true);
        assertEquals("[E][X] Test 6 (from: 2020-04-04 to: 2020-04-04)", event.toString());
    }

    @Test
    public void addTaskTest() {
        Todo todo = new Todo("Test", false);
        String str = "Got it. I've added this task: \n";
        str = str + "  " + todo.toString() + "\n";
        str = str + ((this.list.listSize() + 1) > 1 ? "Now you have "
                + (this.list.listSize() + 1) + " tasks in this list"
                : "Now you have " + (this.list.listSize() + 1)
                + " task in this list");
        assertEquals(str, this.list.addTask(todo));
        list.removeTask(1);
    }

    @Test
    public void deleteTaskTest() {
        Todo todo = new Todo("Test", false);
        this.list.addTask(todo);
        String str = "Noted. I've removed this task: \n";
        str = str + "   " + todo.toString() + "\n";
        str = str + ((this.list.listSize() - 1) > 1 ? "Now you have " + (this.list.listSize() - 1) + " tasks in this list"
                : "Now you have " + (this.list.listSize() - 1) + " task in this list");
        assertEquals(str, this.list.removeTask(1));
    }
}
