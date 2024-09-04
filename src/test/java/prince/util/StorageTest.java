package prince.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.exceptions.PrinceException;
import main.tasks.Deadline;
import main.tasks.Event;
import main.tasks.TaskList;
import main.tasks.Todo;
import main.util.Storage;

public class StorageTest {
    private static final Path FILE_PATH = (Paths.get("testFile", "temp.txt"));

    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        storage = new Storage(FILE_PATH);
        taskList = new TaskList();
    }

    @AfterEach
    public void cleanUp() throws IOException {
        if (Files.exists(FILE_PATH)) {
            Files.delete(FILE_PATH);
        }
        if (Files.exists(FILE_PATH.getParent())) {
            Files.delete(FILE_PATH.getParent());
        }
    }

    @Test
    public void saveToFile_newInput_taskSavedSuccess() throws IOException {
        Event eventTask = new Event("play football", "mon 5pm", "7pm");
        storage.saveToFile(eventTask, taskList);
        String inStorage = Files.readString(FILE_PATH);
        String expected = "E .. 0 .. play football .. mon 5pm .. 7pm";
        assertEquals(expected, inStorage.trim());
    }

    @Test
    public void saveToFile_secondInput_taskAppendedSuccess() throws IOException {
        Event eventTask = new Event("play football", "mon 5pm", "7pm");
        storage.saveToFile(eventTask, taskList);
        Todo todoTask = new Todo("read books");
        storage.saveToFile(todoTask, taskList);
        List<String> inStorageList = Files.readAllLines(FILE_PATH);
        assertEquals("E .. 0 .. play football .. mon 5pm .. 7pm", inStorageList.get(0).trim());
        assertEquals("T .. 0 .. read books", inStorageList.get(1).trim());
    }

    @Test
    public void updateFile_markAsDone_taskMarkedSuccess() throws IOException {
        Todo todoTask = new Todo("read books");
        taskList.add(todoTask);
        storage.saveToFile(todoTask, taskList);
        String inStorage = Files.readString(FILE_PATH);
        String expected = "T .. 0 .. read books";
        assertEquals(expected, inStorage.trim());
        todoTask.markAsDone();
        storage.updateFile("mark 1", taskList);
        String updated = Files.readString(FILE_PATH);
        String updatedExpected = "T .. 1 .. read books";
        assertEquals(updatedExpected, updated.trim());
    }

    @Test
    public void deleteFromFile_validInput_taskDeleteSuccess() throws IOException {
        Todo todoTask = new Todo("read books");
        taskList.add(todoTask);
        storage.saveToFile(todoTask, taskList);
        String inStorage = Files.readString(FILE_PATH);
        String expected = "T .. 0 .. read books";
        assertEquals(expected, inStorage.trim());
        storage.deleteFromFile("delete 1", taskList);
        String updated = Files.readString(FILE_PATH);
        String updatedExpected = "";
        assertEquals(updatedExpected, updated);
    }

    @Test
    public void loadFromFile_validTasks_success() throws IOException, PrinceException {
        Todo todoTask = new Todo("play tennis");
        Deadline deadlineTask = new Deadline("watch movie", "9 july");
        taskList.add(todoTask);
        taskList.add(deadlineTask);
        storage.saveToFile(todoTask, taskList);
        storage.saveToFile(deadlineTask, taskList);
        TaskList testTaskList = storage.loadFromFile();
        assertEquals(2, testTaskList.size());
        assertEquals("T .. 0 .. play tennis", testTaskList.get(0).toFileFormat());
        assertEquals("D .. 0 .. watch movie .. 9 july", testTaskList.get(1).toFileFormat());
    }
}
