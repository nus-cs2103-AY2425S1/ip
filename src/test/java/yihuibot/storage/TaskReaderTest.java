package yihuibot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;

import yihuibot.exception.taskformat.IncorrectTaskFormatException;
import yihuibot.exception.taskformat.StatusException;
import yihuibot.exception.taskformat.TypeException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit Test for TaskReader.java
 * 
 * @author Toh Yi Hui A0259080A
 */
public class TaskReaderTest {
    private TaskReader taskReader;
    private static File file;

    /**
     * Create new file before all tests.
     */
    @BeforeAll
    public static void setUp() {
        file = new File("task.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Remove the created file 'task.txt' after testing all tests.
     */
    @AfterAll
    public static void cleanUp() {
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Ensures that the TaskReader is not null.
     */
    @Test
    public void constructor_notNull() {
        try {
            taskReader = new TaskReader(file, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            assertNotNull(taskReader);
        } catch (FileNotFoundException e) {
            fail();
        }
    }

    /**
     * Ensures that read returns a TaskList of size 2.
     */
    @Test
    public void read_twoTasks_returnTaskListOfSizeTwo() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("T | 1 | read book\n");
            fileWriter.write("D | 0 | return book | 2024-04-08 14:30\n");
            fileWriter.close();
            taskReader = new TaskReader(file, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            assertEquals(2, taskReader.read().size());
        } catch (IOException | IncorrectTaskFormatException e) {
            fail();
        }
    }

    /**
     * Ensures that read throws a StatusException.
     */
    @Test
    public void read_invalidTaskStatus_throwsStatusException() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("T | 2 | read book");
            fileWriter.close();
            taskReader = new TaskReader(file, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            taskReader.read();
        } catch (IncorrectTaskFormatException e) {
            assertInstanceOf(StatusException.class, e);
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Ensures that read throws a TypeException.
     */
    @Test
    public void read_invalidTaskType_throwsTypeException() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Y | 0 | read book");
            fileWriter.close();
            taskReader = new TaskReader(file, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            taskReader.read();
        } catch (IncorrectTaskFormatException e) {
            assertInstanceOf(TypeException.class, e);
        } catch (IOException e) {
            fail();
        }
    }
}
