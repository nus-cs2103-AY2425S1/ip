package espresso.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    private Ui ui;

    @BeforeEach
    void setUp() {
        ui = new Ui();
    }

    @Test
    void testPrintWelcome() {
        assertDoesNotThrow(() -> ui.printWelcome());
    }

    @Test
    void testPrintError() {
        assertDoesNotThrow(() -> ui.printError("Sample error message"));
    }

    @Test
    void testPrintTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new TodoTask("Sample Task"));
        assertDoesNotThrow(() -> ui.printTasks(taskList));
    }

    @Test
    void testReadCommand() {
        assertNotNull(ui.readCommand());
    }
}
