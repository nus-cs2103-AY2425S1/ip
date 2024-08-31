package bob;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private TaskList setupTaskList() {
        TaskList tasks = new TaskList();
        tasks.todo("walk the dog");
        tasks.deadline("do math homework", "2014-01-11");
        tasks.event("attend lecture", "2024-08-30", "2024-08-31");
        tasks.mark(2);
        return tasks;
    }
    @Test
    public void saveFile_success_fileSavedCorrectly() {
        String filePath = "data/TestActual.txt";
        TaskList tasks = setupTaskList();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        Storage storage = new Storage(filePath);
        storage.saveFile(tasks);

        Scanner actual;
        Scanner expected;

        try {
            actual = new Scanner(file);
            expected = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            fail();
            return;
        }

        boolean isSame = true;
        while (actual.hasNextLine() && expected.hasNextLine() && isSame) {
            String act = actual.nextLine();
            String exp = expected.nextLine();
            isSame = act.equals(exp);
            System.out.println(act);
            System.out.println(exp);

            System.out.println(isSame);
        }
        assertTrue(actual.hasNextLine() == expected.hasNextLine() && isSame);
    }
}
