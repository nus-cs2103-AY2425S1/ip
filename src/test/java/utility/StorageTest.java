package utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import tasks.*;

class StorageTest {
    @Test
    public void load_emptyFile_emptyArrayList() throws IOException {
        Storage storage = new Storage("./src/test/java/utility/emptyFile.txt");
        assertEquals(new ArrayList<Task>(), storage.load());
    }

    @Test
    public void load_nonExistentFile_emptyArrayList() throws IOException {
        Storage storage = new Storage("some path");
        assertEquals(new ArrayList<Task>(), storage.load());
    }

    @Test
    public void load_nonEmptyFile_populatedArrayList() throws IOException {
        Storage storage = new Storage("./src/test/java/utility/nonEmptyFile.txt");
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Todo("some task"));
        expected.add(new DeadLine("some task", "Jan 01 2024", true));
        expected.add(new Event("some event", "Jan 01 2024", "Feb 01 2024"));
        expected.add(new Note("some note that i want to keep track of."));
        ArrayList<Task> actual = storage.load();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(true, expected.get(i).equals(actual.get(i)));
        }
    }

    @Test
    public void save_listOfTasks_void() throws IOException {
        Storage storage = new Storage("./src/test/java/utility/saveFile.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("some task"));
        tasks.add(new DeadLine("some task", "Jan 01 2024", true));
        tasks.add(new Event("some event", "Jan 01 2024", "Feb 01 2024"));
        tasks.add(new Note("some note that i want to keep track of."));
        storage.save(tasks);
        File saveFile = new File("./src/test/java/utility/saveFile.txt");
        File nonEmptyFile = new File("src/test/java/utility/nonEmptyFile.txt");
        Scanner s1 = new Scanner(saveFile);
        Scanner s2 = new Scanner(nonEmptyFile);
        while (s1.hasNextLine() || s2.hasNextLine()) {
            assertEquals(s1.nextLine(), s2.nextLine());
        }
    }
}
