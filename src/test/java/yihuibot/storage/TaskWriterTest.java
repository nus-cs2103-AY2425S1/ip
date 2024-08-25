package yihuibot.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import yihuibot.task.Deadline;
import yihuibot.task.TaskList;
import yihuibot.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit Test for TaskWriter.java
 * 
 * @author Toh Yi Hui A0259080A
 */
public class TaskWriterTest {
    private TaskWriter taskWriter;
    private TaskList taskList;
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
     * Ensures that the TaskWriter is not null.
     */
    @Test
    public void constructor_notNull() {
        taskList = new TaskList();
        try {
            taskWriter = new TaskWriter(file, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), taskList);
            assertNotNull(taskWriter);
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Ensures that the taskWriter write two files into file.
     */
    @Test
    public void write_twoTasks_writesTwoLinesInFile() {
        taskList = new TaskList();
        taskList.add(new Todo("read book"));
        taskList.add(new Deadline("return book", LocalDate.of(2020, 1, 18).atStartOfDay()));
        try {
            taskWriter = new TaskWriter(file, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), taskList);
            taskWriter.write();
            taskWriter.close();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
            assertEquals(2, lines);
        } catch (IOException e) {
            fail();
        }
    }
}
