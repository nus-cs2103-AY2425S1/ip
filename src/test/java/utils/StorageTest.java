package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chatterboxerrors.ChatterBoxDataFileError;
import chatterboxerrors.ChatterBoxError;
import chatterboxerrors.ChatterBoxMarkError;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

public class StorageTest {
    private static final String TEST_FILENAME = "src/test/data/test_save.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final LocalDateTime TEST_DATE_1 = LocalDate.parse("01/12/2024", DATE_FORMATTER).atStartOfDay();
    private static final LocalDateTime TEST_DATE_2 = LocalDateTime.parse("02/12/2024 1600", DATE_TIME_FORMATTER);

    @BeforeEach
    public void setUp() throws Exception {
        Path testFilePath = Paths.get(System.getProperty("user.dir"), TEST_FILENAME);
        if (Files.notExists(testFilePath)) {
            Files.createFile(testFilePath);
        }
    }

    @Test
    public void testReadFromSaveTest() throws IOException, ChatterBoxMarkError {
        FileWriter testWriter = new FileWriter(TEST_FILENAME);
        testWriter.write("undone, todo assignment 1\n");
        testWriter.write("done, deadline assignment 2 /by 01/12/2024\n");
        testWriter.write("undone, event assignment 3 /from 01/12/2024 /to 02/12/2024 1600\n");
        testWriter.close();

        Storage testStorage = new Storage(TEST_FILENAME);
        StoredList testTaskList = new StoredList();
        testTaskList.addItem(new ToDo("assignment 1"));
        testTaskList.addItem(new Deadline("assignment 2", TEST_DATE_1));
        testTaskList.addItem(new Event("assignment 3", TEST_DATE_1, TEST_DATE_2));
        testTaskList.getItem(1).setCompleted(true);

        try {
            testStorage.readFromSave();
        } catch (ChatterBoxDataFileError e) {
            e.printStackTrace();
        }
        assertEquals(testTaskList.toString(), testStorage.getSaveList().toString());
    }

    @Test
    public void doCommandTest() throws ChatterBoxError {
        StoredList testTaskList = new StoredList();
        testTaskList.addItem(new ToDo("assignment 1"));
        testTaskList.addItem(new Deadline("assignment 2", TEST_DATE_1));
        testTaskList.addItem(new Event("assignment 3", TEST_DATE_1, TEST_DATE_2));
        testTaskList.getItem(1).setCompleted(true);

        Storage testStorage = new Storage("");
        testStorage.doCommand("todo assignment 1");
        testStorage.doCommand("deadline assignment 2 /by 01/12/2024");
        testStorage.doCommand("event assignment 3 /from 01/12/2024 /to 02/12/2024 1600");
        testStorage.doCommand("mark 2");

        assertEquals(testTaskList.toString(), testStorage.getSaveList().toString());
    }

}
