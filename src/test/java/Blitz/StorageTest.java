package blitz;

/* My import */
import task.Todo;

/* System import */
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void storageReadFromFile_existingFilename_returnTaskListObject() {
        try {
            assertEquals(new TaskList(new ArrayList<>(List.of(new Todo("test", "T", false)))),
                    new Storage("./existent.txt").readFromFile(new Ui("", "")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void storageReadFromFile_nonExistingFilename_returnTaskListObject() {
        try {
            assertEquals(new TaskList(new ArrayList<>()),
                    new Storage("./nonexistent.txt").readFromFile(new Ui("", "")));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void storageWriteToFile_existingFilename_noError() {
        try {
            new Storage("./existent.txt").writeOneToFile(new Todo("test", "T", false));
            new Storage("./existent.txt").writeAllToFile(new TaskList(new ArrayList<>()));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void storageWriteToFile_nonExistingFilename_noError() {
        try {
            new Storage("./nonexistent2.txt").writeOneToFile(new Todo("", "", false));
            new Storage("./nonexistent3.txt").writeAllToFile(new TaskList(new ArrayList<>()));
        } catch (Exception e) {
            fail();
        }
    }
}
