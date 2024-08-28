import static org.junit.jupiter.api.Assertions.*;

import fishman.command.Command;
import fishman.exception.FishmanException;
import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.ToDo;
import fishman.task.Task;
import fishman.task.TaskList;
import fishman.utils.Parser;
import fishman.utils.Ui;
import fishman.utils.Storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class StorageTest {

    @TempDir
    Path tempDir;

    private Storage storage;
    private Path filePath;
    private TaskList tasks;
    private Ui ui;

    private Path tempFile;
    @BeforeEach
    void setUp() {
        filePath = tempDir.resolve("test.txt");
        storage = new Storage(filePath.toString());
        tasks = new TaskList();
        ui = new Ui();

    }

    @AfterEach
    void tearDown() throws IOException {
        if (tempFile != null && Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
    }

    @Test
    void save_and_load_tasks() throws FishmanException {
        Command commandTodo = Parser.parse("todo Sample Todo Task", tasks);
        commandTodo.execute(tasks, ui);
        Command commandDeadline = Parser.parse("deadline Sample Deadline Task /by 2024-09-01 2359", tasks);
        commandDeadline.execute(tasks, ui);
        Command command = Parser.parse("event Sample Event Task /from 2024-09-01 1400 /to 2024-09-01 1600", tasks);
        command.execute(tasks, ui);
        storage.save(tasks);
        TaskList loadedTasks = storage.load();
        TaskList originalTasks = tasks;
        assertEquals(originalTasks.size(), loadedTasks.size());
        for (int i = 0; i < originalTasks.size(); i++) {
            Task originalTask = originalTasks.getTask(i);
            Task loadedTask = loadedTasks.getTask(i);

            assertEquals(originalTask.getDescription(), loadedTask.getDescription());
            assertEquals(originalTask.getStatus(), loadedTask.getStatus());

            if (originalTask instanceof Deadline originalDeadline) {
                Deadline loadedDeadline = (Deadline) loadedTask;
                assertEquals(originalDeadline.getBy(), loadedDeadline.getBy());
            } else if (originalTask instanceof Event originalEvent) {
                Event loadedEvent = (Event) loadedTask;
                assertEquals(originalEvent.getFrom(), loadedEvent.getFrom());
                assertEquals(originalEvent.getTo(), loadedEvent.getTo());
            }
        }
    }

    @Test
    void load_missingArguments_throwsInvalidArgumentsException() throws IOException {
        Path tempFile = Files.createTempFile("test-", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            writer.write("T|1"); // Missing description
            writer.newLine();
            writer.write("D|0|Finish report"); // Missing deadline
            writer.newLine();
            writer.write("E|1|Team meeting|2024-08-29 10:00"); // Missing end date
            writer.newLine();
        }

        Storage storage = new Storage(tempFile.toString());
        assertThrows(FishmanException.InvalidArgumentsException.class, storage::load);

        Files.deleteIfExists(tempFile);
    }


}
