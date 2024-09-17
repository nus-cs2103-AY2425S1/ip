package blitz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Todo;

public class StorageTest {
    @Test
    public void storageWriteToFile_nonExistingFilename_noError() {
        try {
            new Storage("./nonexistent2.txt").writeAllToFile(new TaskList(new ArrayList<>()));
            new Storage("./nonexistent1.txt").writeOneToFile(new Todo("test", "T", false));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void storageWriteToFile_existingFilename_noError() {
        try {
            new Storage("./nonexistent2.txt").writeAllToFile(new TaskList(new ArrayList<>()));
            new Storage("./nonexistent2.txt").writeOneToFile(new Todo("test", "T", false));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void storageReadFromFile_nonExistingFilename_returnTaskListObject() {
        try {
            assertEquals(new TaskList(new ArrayList<>()),
                    new Storage("./nonexistent.txt").readFromFile());
        } catch (Exception e) {
            fail();
        }
    }
}
