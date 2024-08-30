package luke;
import luke.task.NoDescriptionException;
import luke.task.TaskList;
import luke.task.UnknownCommandException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

public class StorageTest {
    @Test
    public void testLoadData()
            throws IOException, NoSaveDataFoundException, NoDescriptionException, UnknownCommandException {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        taskList.addToList("todo", "read book", true, false);
        storage.saveData(taskList);
        List<String> lines = storage.loadData();
        assertEquals(1, lines.size());
        assertEquals("X | todo | read book", lines.get(0).strip());
    }
}
