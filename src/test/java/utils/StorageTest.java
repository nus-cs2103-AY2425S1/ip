package utils;

import chatterboxerrors.ChatterBoxError;
import chatterboxerrors.ChatterBoxMarkError;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    String TESTFILENAME = "test_save.txt";
    private final TextUi testTextUi = new TextUi();

    @BeforeEach
    public void setUp() throws Exception {
        Path testFilePath = Paths.get(System.getProperty("user.dir"), TESTFILENAME);
        if (Files.notExists(testFilePath)) {
            Files.createFile(testFilePath);
        }

    }

    @Test
    public void testReadFromSaveTest() throws IOException, ChatterBoxMarkError {
        FileWriter testWriter = new FileWriter(TESTFILENAME);
        testWriter.write("undone, todo assignment 1\n");
        testWriter.write("done, deadline assignment 2 /by 01/12/2024\n");
        testWriter.write("undone, event assignment 3 /from 01/12/2024 /to 02/12/2024 1600\n");
        testWriter.close();

        DateTimeFormatter time1Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time2Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        LocalDateTime time1 = LocalDate.parse("01/12/2024", time1Formatter).atStartOfDay();
        LocalDateTime time2 = LocalDateTime.parse("02/12/2024 1600", time2Formatter);

        Storage testStorage = new Storage(TESTFILENAME);
        StoredList testTaskList = new StoredList();
        testTaskList.addItem(new ToDo("assignment 1"));
        testTaskList.addItem(new Deadline("assignment 2", time1));
        testTaskList.addItem(new Event("assignment 3", time1, time2));
        testTaskList.getItem(1).setCompleted(true);

        testStorage.readFromSave();
        assertEquals(testTaskList.toString(), testStorage.getSaveList().toString());
    }

    @Test
    public void doCommandTest() throws ChatterBoxError {
        DateTimeFormatter time1Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time2Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        LocalDateTime time1 = LocalDate.parse("01/12/2024", time1Formatter).atStartOfDay();
        LocalDateTime time2 = LocalDateTime.parse("02/12/2024 1600", time2Formatter);

        StoredList testTaskList = new StoredList();
        testTaskList.addItem(new ToDo("assignment 1"));
        testTaskList.addItem(new Deadline("assignment 2", time1));
        testTaskList.addItem(new Event("assignment 3", time1, time2));
        testTaskList.getItem(1).setCompleted(true);

        Storage testStorage = new Storage("");
        testStorage.doCommand("todo assignment 1");
        testStorage.doCommand("deadline assignment 2 /by 01/12/2024");
        testStorage.doCommand("event assignment 3 /from 01/12/2024 /to 02/12/2024 1600");
        testStorage.doCommand("mark 2");

        assertEquals(testTaskList.toString(), testStorage.getSaveList().toString());
    }

}
