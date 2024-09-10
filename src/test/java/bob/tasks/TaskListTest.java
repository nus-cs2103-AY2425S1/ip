package bob.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import bob.exceptions.InvalidTaskNumberException;

public class TaskListTest {

    @Test
    public void sizeTest() throws InvalidTaskNumberException {
        TaskList myTasks = new TaskList();
        myTasks.addTask(new ToDo("Hello"));
        assertEquals(1, myTasks.size());
        myTasks.addTask(new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        assertEquals(2, myTasks.size());
        myTasks.addTask(new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        assertEquals(3, myTasks.size());
        ToDo todo = new ToDo("Hello");
        todo.mark();
        myTasks.addTask(todo);
        assertEquals(4, myTasks.size());
        Deadline deadline = new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        deadline.mark();
        myTasks.addTask(deadline);
        assertEquals(5, myTasks.size());
        EventTask eventTask = new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        eventTask.mark();
        myTasks.addTask(eventTask);
        assertEquals(6, myTasks.size());
        myTasks.removeTaskAtIndex(5);
        assertEquals(5, myTasks.size());
    }

    @Test
    public void addTaskTest() {
        TaskList myTasks = new TaskList();
        Task todo = new ToDo("TEST");
        assertEquals(0, myTasks.size());
        myTasks.addTask(todo);
        assertEquals(1, myTasks.size());
    }

    @Test
    public void removeTaskAtIndexTest() throws InvalidTaskNumberException {
        TaskList myTasks = new TaskList();
        Task todo = new ToDo("TEST");
        myTasks.addTask(todo);
        assertEquals(todo, myTasks.removeTaskAtIndex(0));
    }

    @Test
    public void removeTaskAtIndexWithInvalidIndexTest() {
        TaskList myTasks = new TaskList();
        assertThrows(InvalidTaskNumberException.class, () -> myTasks.removeTaskAtIndex(-1));
        assertThrows(InvalidTaskNumberException.class, () -> myTasks.removeTaskAtIndex(1));
    }

    @Test
    public void markTaskTest() throws InvalidTaskNumberException {
        TaskList myTasks = new TaskList();
        Task todo = new ToDo("TEST");
        myTasks.addTask(todo);
        myTasks.markTaskAtIndex(0);
        assertEquals("todo true TEST", todo.export());
    }

    @Test
    public void markTaskAtIndexWithInvalidIndexTest() {
        TaskList myTasks = new TaskList();
        assertThrows(InvalidTaskNumberException.class, () -> myTasks.markTaskAtIndex(-1));
        assertThrows(InvalidTaskNumberException.class, () -> myTasks.markTaskAtIndex(1));
    }

    @Test
    public void unmarkTaskTest() throws InvalidTaskNumberException {
        TaskList myTasks = new TaskList();
        Task todo = new ToDo("TEST");
        myTasks.addTask(todo);
        todo.mark();
        assertEquals("todo true TEST", todo.export());
        myTasks.unmarkTaskAtIndex(0);
        assertEquals("todo false TEST", todo.export());
    }

    @Test
    public void unmarkTaskAtIndexWithInvalidIndexTest() {
        TaskList myTasks = new TaskList();
        assertThrows(InvalidTaskNumberException.class, () -> myTasks.unmarkTaskAtIndex(-1));
        assertThrows(InvalidTaskNumberException.class, () -> myTasks.unmarkTaskAtIndex(1));
    }

    @Test
    public void exportTaskListTest() {
        TaskList myTasks = new TaskList();
        myTasks.addTask(new ToDo("Hello"));
        myTasks.addTask(new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        myTasks.addTask(new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        ToDo todo = new ToDo("Hello");
        todo.mark();
        myTasks.addTask(todo);
        Deadline deadline = new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        deadline.mark();
        myTasks.addTask(deadline);
        EventTask eventTask = new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        eventTask.mark();
        myTasks.addTask(eventTask);
        assertEquals("""
                todo false Hello
                deadline false Hello /by 18/12/2024
                event false Hello /from 18/12/2024 /to 18/12/2025
                todo true Hello
                deadline true Hello /by 18/12/2024
                event true Hello /from 18/12/2024 /to 18/12/2025""", myTasks.export());
    }

    @Test
    public void toStringTaskListTest() {
        TaskList myTasks = new TaskList();
        myTasks.addTask(new ToDo("Hello"));
        myTasks.addTask(new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        myTasks.addTask(new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu"))));
        ToDo todo = new ToDo("Hello");
        todo.mark();
        myTasks.addTask(todo);
        Deadline deadline = new Deadline("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        deadline.mark();
        myTasks.addTask(deadline);
        EventTask eventTask = new EventTask("Hello",
                LocalDate.parse("18/12/2024", DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                LocalDate.parse("18/12/2025", DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        eventTask.mark();
        myTasks.addTask(eventTask);
        assertEquals(" Here are the tasks in your list:\n"
                + " 1.[T][ ] Hello\n"
                + " 2.[D][ ] Hello (by: Dec 18 2024)\n"
                + " 3.[E][ ] Hello (from: Dec 18 2024 to: Dec 18 2025)\n"
                + " 4.[T][X] Hello\n"
                + " 5.[D][X] Hello (by: Dec 18 2024)\n"
                + " 6.[E][X] Hello (from: Dec 18 2024 to: Dec 18 2025)", myTasks.toString());
    }

}
