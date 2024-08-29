package derek;

import derek.task.Task;
import derek.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UiTest {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));

        taskList = new TaskList();
        storage = new Storage(taskList);
        ui = new Ui(storage, taskList);
    }

    @Test
    void testIntroduce_UserSaysNo() {
        String input = "N\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ui.introduce();
        assertTrue(outContent.toString().contains("Ok...\n"));
    }

    @Test
    void testAddTask() {
        Task task = Task.toDoTask("Finish testing");
        ui.addTask(task);
        assertTrue(outContent.toString().contains("Finish testing"));
    }

    @Test
    void testRemoveTask() {
        Task task = Task.toDoTask("Delete me");
        ui.removeTask(task);
        assertTrue(outContent.toString().contains("Delete me"));
    }

    @Test
    void testGenerateRandomCelebration() {
        String celebration = ui.generateRandomCelebration();
        assertTrue(celebration.matches("yay!|woohoo!|let's go!!!!|great job :\\)|you're on a roll!"));
    }
}
