package chatkaki;

import chatkaki.tasks.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void saveTasksToFile_validTasks_savesSuccessfully() throws Exception {
        TaskList.addTask(new Todo(false, "read book"), true);
        TaskList.addTask(new Deadline(false, "return book", LocalDateTime.of(2019, 12, 2, 18, 0)), true);
        Storage.saveTasksToFile();

        File file = new File("data/tasks.txt");
        assertTrue(file.exists());

        Scanner scanner = new Scanner(file);
        assertEquals("TODO,false,read book", scanner.nextLine());
        assertEquals("DEADLINE,false,return book,2/12/2019 1800", scanner.nextLine());
    }

    @Test
    public void loadTasksFromFile_validFile_loadsSuccessfully() throws Exception {
        File file = new File("data/tasks.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("TODO,false,read book\n");
        fileWriter.write("DEADLINE,false,return book,2/12/2019 1800\n");
        fileWriter.close();

        Storage.loadTasksFromFile();

        Task task1 = TaskList.getTasks().get(0);
        assertTrue(task1 instanceof Todo);
        assertEquals("read book", task1.getDescription());

        Task task2 = TaskList.getTasks().get(1);
        assertTrue(task2 instanceof Deadline);
        assertEquals("return book", task2.getDescription());
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 0), ((Deadline) task2).getBy());
    }
}