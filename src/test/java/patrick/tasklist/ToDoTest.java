package patrick.tasklist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patrick.parser.Parser;
import patrick.storage.Storage;
import patrick.ui.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @BeforeEach
    public void setUp() {
        clearFile("tasks.txt");
    }

    private void clearFile(String filename) {
        try {
            Path path = Paths.get(filename);
            if (Files.exists(path)) {
                Files.write(path, new byte[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToDoConstructor() {
        ToDo toDo = new ToDo("Test Task");
        assertEquals("Test Task", toDo.getDescription());
    }

    @Test
    public void testToString() {
        ToDo toDo = new ToDo("Test Task");
        assertEquals("T | O | Test Task", toDo.toString());
    }
}
