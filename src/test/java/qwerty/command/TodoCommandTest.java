package qwerty.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.stubs.UiStub;

public class TodoCommandTest {
    @Test
    public void execute_emptyMap_exceptionThrown() {
        HashMap<String, String> args = new HashMap<>();
        TodoCommand command = new TodoCommand(args);
        assertThrows(QwertyException.class, () -> command.execute(new TaskList(), new UiStub(), new Storage("")));
    }

    @Test
    public void execute_mapWithNull_exceptionThrown() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", null);
        TodoCommand command = new TodoCommand(args);
        assertThrows(QwertyException.class, () -> command.execute(new TaskList(), new UiStub(), new Storage("")));
    }

    @Test
    public void execute_taskWithOneWordDescription_success() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "cs2103t");
        TodoCommand command = new TodoCommand(args);
        assertDoesNotThrow(() -> command.execute(new TaskList(), new UiStub(), new Storage("")));
    }

    @Test
    public void execute_taskWithMultiWordDescription_success() {
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "cs2103t is a pain");
        TodoCommand command = new TodoCommand(args);
        assertDoesNotThrow(() -> command.execute(new TaskList(), new UiStub(), new Storage("")));
    }
}
