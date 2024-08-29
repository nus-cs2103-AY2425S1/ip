import boss.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BossTest {

    // let us try to write a test that checks whether the load function works
    // done this with an example where one task was written to a text file
    // after which the load function was called!

    // note that text file should be empty before test is run
    @Test
    public void testLoad() {
        ArrayList<Task> expectedArray = new ArrayList<>();
        Task task = new Todo("Write Essay", false);
        expectedArray.add(task);

        Storage storage = new Storage("src/main/data/boss.txt");

        try {
            storage.writeToFile(task.toString(), true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expectedArray.get(0).toString(), storage.load().get(0).toString());
    }

    // checks if tasks are correctly being marked
    @Test
    public void testMarkTask() {

        TaskList tasks = new TaskList(new ArrayList<>());

        Task task = new Event("Call Friend", "2pm", "3pm", false);

        tasks.addTask(task);

        String newTask = tasks.mark("mark 1");

        assertEquals(true, newTask.contains("[X]"));

    }



}
