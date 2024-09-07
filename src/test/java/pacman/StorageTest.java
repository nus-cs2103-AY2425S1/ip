package pacman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class StorageTest {
    @Test
    public void test() {
        Storage storage = new Storage("data/test/PacMan.txt");
        storage.write("T/1  /Test Task\nD/0/Test Deadline/2024-07-29");
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("Test Task"));
        list.get(0).setMarkDone(true);
        list.add(new Deadline("Test Deadline", "2024-07-29"));
        assertEquals(storage.load().toString(), list.toString());
    }
}
