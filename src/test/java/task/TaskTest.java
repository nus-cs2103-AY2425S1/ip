package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {

    @Test
    public void testConstructorDescriptionField() {
        assertEquals("eat lunch today", new Task("eat lunch today").description);
    }

    @Test
    public void testConstructorIsDoneField() {
        assertEquals(false, new Task("eat lunch today").isDone);
    }

    @Test
    public void testConstructorPriorityField() {
        assertEquals(Task.Priority.LOW, new Task("eat lunch today").getPriority());
    }

    @Test
    public void testSetPriority1() {
        Task task = new Task("test");
        task.setPriority(Task.Priority.MEDIUM);
        assertEquals(Task.Priority.MEDIUM, task.getPriority());
    }

    @Test
    public void testSetPriority2() {
        Task task = new Task("test");
        task.setPriority(Task.Priority.HIGH);
        assertEquals(Task.Priority.HIGH, task.getPriority());
    }

    @Test
    public void testGetShortPriority1() {
        Task task = new Task("test");
        task.setPriority(Task.Priority.LOW);
        assertEquals("L", task.getShortPriority());
    }

    @Test
    public void testGetShortPriority2() {
        Task task = new Task("test");
        task.setPriority(Task.Priority.MEDIUM);
        assertEquals("M", task.getShortPriority());
    }

    @Test
    public void testGetShortPriority3() {
        Task task = new Task("test");
        task.setPriority(Task.Priority.HIGH);
        assertEquals("H", task.getShortPriority());
    }

    @Test
    public void testGetStatusIcon1() {
        Task task = new Task("test");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void testGetStatusIcon2() {
        Task task = new Task("test");
        task.isDone = true;
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("test");
        task.markAsDone();
        assertEquals(true, task.isDone);
    }

    @Test
    public void testMarkAsUndone() {
        Task task = new Task("test");
        task.isDone = true;
        task.markAsUndone();
        assertEquals(false, task.isDone);
    }

    @Test
    public void testGetDescription() {
        Task task = new Task("test description");
        assertEquals("test description", task.getDescription());
    }

    @Test
    public void testToString1() {
        Task task = new Task("eat breakfast");
        task.isDone = true;
        task.setPriority(Task.Priority.HIGH);
        assertEquals("[X][H] eat breakfast", task.toString());
    }

    @Test
    public void testToString2() {
        Task task = new Task("eat lunch");
        task.isDone = false;
        task.setPriority(Task.Priority.HIGH);
        assertEquals("[ ][H] eat lunch", task.toString());
    }

    @Test
    public void testToString3() {
        Task task = new Task("play games");
        task.isDone = false;
        task.setPriority(Task.Priority.LOW);
        assertEquals("[ ][L] play games", task.toString());
    }

    @Test
    public void testToString4() {
        Task task = new Task("???::");
        task.isDone = true;
        task.setPriority(Task.Priority.LOW);
        assertEquals("[X][L] ???::", task.toString());
    }

    @Test
    public void testToString5() {
        Task task = new Task("coding later");
        task.isDone = false;
        task.setPriority(Task.Priority.MEDIUM);
        assertEquals("[ ][M] coding later", task.toString());
    }
}
