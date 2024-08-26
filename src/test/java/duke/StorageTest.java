package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private final DateTimeFormatter parse_format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
    @Test
    public void testCreateFile() throws DukeException {
        new Storage("testing/test.txt");

        assertTrue(Files.exists(Paths.get("testing/test.txt")), "File should be created");
    }

    @Test
    public void testWriteFile() throws DukeException {
        Storage storage = new Storage("testing/test.txt");
        TaskList tasks = new TaskList();
        ArrayList<Task> tasksFromFile = storage.loadFile();
        StringBuilder results = new StringBuilder();

        tasks.addTask(new Todo("read book"));
        tasks.addTask(new Deadline("finish assignment",
                LocalDateTime.parse("2024-08-24 04:00 pm", parse_format)));
        tasks.addTask(new Event("go sleep",
                LocalDateTime.parse("2024-08-24 10:00 pm", parse_format),
                LocalDateTime.parse("2024-08-24 07:00 am", parse_format)));

        storage.writeFile(tasks.getTasks());


        for(Task task : tasksFromFile) {
            results.append(task.toFileString()).append("\n");
        }

        String expect = "T | 0 | read book\n"
                + "D | 0 | finish assignment | Aug-24-2024 1600\n"
                + "E | 0 | go sleep | Aug-24-2024 2200 | Aug-24-2024 0700";

        assertEquals(expect, results.toString().trim(), "File should have such content");
    }
}
