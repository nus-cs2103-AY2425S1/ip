package michael;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void Test1() {
        TaskList ls = new TaskList();
        ls.add(new ToDo("Test"));
        ls.add(new Deadline("Second Test", "2024-09-09"));
        assertEquals(2, ls.size());
    }

    @Test
    public void Test2() {
        TaskList ls = new TaskList();
        ls.add(new ToDo("Test"));
        ls.add(new Deadline("Second Test", "2024-09-09"));
        assertEquals("[D][ ] Second Test (by: 9 Sep 2024)", ls.get(1).toString());
    }

    @Test
    public void Test3() {
        TaskList ls = new TaskList();
        ls.add(new Deadline("Second Test", "2024-09-09"));
        ls.add(new ToDo("Test"));
        ls.delete(0);
        assertEquals("[T][ ] Test", ls.get(0).toString());
    }
}
