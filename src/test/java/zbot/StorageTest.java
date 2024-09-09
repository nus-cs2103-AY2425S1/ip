package zbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import zbot.task.Deadline;
import zbot.task.Event;
import zbot.task.ToDo;

public class StorageTest {

    private String tmpFilePath = "./tmp/tasks.txt";

    @Test
    public void testCreateFileIfNotExists() {
        // delete file if it exists
        File file = new File(tmpFilePath);
        if (file.exists()) {
            file.delete();
        }

        // test if file is created
        Storage storage = new Storage(tmpFilePath);
        storage.createFileIfNotExists();
        assertTrue(file.exists());

        // check no error if it already exists
        storage.createFileIfNotExists();

        // delete file after test
        file.delete();
    }

    @Test
    public void testWriteToFile() {
        // delete file if it exists
        File file = new File(tmpFilePath);
        if (file.exists()) {
            file.delete();
        }

        // write to file
        Storage storage = new Storage(tmpFilePath);
        storage.writeToTextFile("test");
        assertTrue(file.exists());

        // check content
        try {
            Scanner sc = new Scanner(file);
            assertEquals("test", sc.nextLine());
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delete file after test
        file.delete();
    }

    @Test
    public void testSave() {

        // save tasks to file
        Storage storage = new Storage(tmpFilePath);
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.add(new Deadline("test", LocalDateTime.of(2021, 8, 23, 0, 0)));
        tasks.add(new Event("test", LocalDateTime.of(2021, 8, 23, 0, 0),
                LocalDateTime.of(2021, 8, 23, 0, 0)));
        storage.save(tasks);

        // check content
        try {
            File file = new File(tmpFilePath);
            Scanner sc = new Scanner(file);
            assertEquals("T,0,test", sc.nextLine());
            assertEquals("D,0,test,23/08/2021 0000", sc.nextLine());
            assertEquals("E,0,test,23/08/2021 0000,23/08/2021 0000", sc.nextLine());
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoad() {
        // write file
        File file = new File(tmpFilePath);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("T,0,test\n");
            fw.write("D,0,test,23/08/2021 0000\n");
            fw.write("E,0,test,23/08/2021 0000,23/08/2021 0000\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // load tasks from file
        Storage storage = new Storage(tmpFilePath);
        try {
            TaskList loadedTasks = new TaskList(storage.load());
            assertEquals(3, loadedTasks.size());
            assertEquals("[T][ ] test", loadedTasks.get(0).toString());
            assertEquals("[D][ ] test (by: 23 Aug 2021 00:00)", loadedTasks.get(1).toString());
            assertEquals("[E][ ] test (from: 23 Aug 2021 00:00 to: 23 Aug 2021 00:00)",
                    loadedTasks.get(2).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
