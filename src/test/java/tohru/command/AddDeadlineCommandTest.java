package tohru.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.storage.FileStoreStub;
import tohru.task.TodoList;
import tohru.ui.Tui;
import tohru.ui.Ui;


public class AddDeadlineCommandTest {

    private static TodoList list;
    private static Ui ui;
    private static FileStore store;

    @BeforeAll
    static void initAll() {
        ui = new Tui();
        store = new FileStoreStub();
    }

    @BeforeEach
    void init() {
        list = new TodoList();
    }

    @Test
    public void execute_correctFormatArguments_success() {
        String args = "test deadline /by 01/01/2024 0101";

        AddDeadlineCommand cmd = new AddDeadlineCommand(args);

        assertDoesNotThrow(() -> {
            cmd.execute(list, ui, store);
        });
        assertEquals(1, list.getTotal());
        assertEquals("[D] [ ] test deadline (by: Mon 01 Jan 2024 0101)",
                list.getItemStatus(0));
    }

    @Test
    public void execute_missingArguments_exceptionThrown() {
        String args = null;

        AddDeadlineCommand cmd = new AddDeadlineCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Please specify description and deadline",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

    @Test
    public void execute_missingPartialArguments_exceptionThrown() {
        String args = "test deadline";

        AddDeadlineCommand cmd = new AddDeadlineCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Missing either description or deadline",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

    @Test
    public void execute_missingDescription_exceptionThrown() {
        String args = "/by 01/01/2024 0101";

        AddDeadlineCommand cmd = new AddDeadlineCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Please specify description",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

    @Test
    public void execute_missingDeadline_exceptionThrown() {
        String args = "test deadline /by";

        AddDeadlineCommand cmd = new AddDeadlineCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Please specify deadline",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

}
