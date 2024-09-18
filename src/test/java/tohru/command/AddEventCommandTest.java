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

public class AddEventCommandTest {

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
        String args = "test event /from 01/01/2024 0101 /to 01/01/2024 1101";

        AddEventCommand cmd = new AddEventCommand(args);

        assertDoesNotThrow(() -> {
            cmd.execute(list, ui, store);
        });
        assertEquals(1, list.getTotal());
        assertEquals("[E] [ ] test event (from: Mon 01 Jan 2024 0101 to Mon 01 Jan 2024 1101)",
                list.getItemStatus(0));
    }

    @Test
    public void execute_missingArguments_exceptionThrown() {
        String args = null;

        AddEventCommand cmd = new AddEventCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Please specify description, from datetime and to datetime",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

    @Test
    public void execute_missingPartialArguments_exceptionThrown() {
        String args = "test event";

        AddEventCommand cmd = new AddEventCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Missing either description, from datetime or to datetime",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

    @Test
    public void execute_missingDescription_exceptionThrown() {
        String args = "/from 01/01/2024 0101 /to 01/01/2024 1101";

        AddEventCommand cmd = new AddEventCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Please specify description",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

    @Test
    public void execute_missingFromDateTime_exceptionThrown() {
        String args = "test event /from /to 01/01/2024 1101";

        AddEventCommand cmd = new AddEventCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Please specify from datetime",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

    @Test
    public void execute_missingToDateTime_exceptionThrown() {
        String args = "test event /from 01/01/2024 0101 /to";

        AddEventCommand cmd = new AddEventCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Missing argument: Please specify to datetime",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

    @Test
    public void execute_toDateTimeEarlierThanFromDateTime_exceptionThrown() {
        String args = "test event /from 01/01/2024 0101 /to 01/01/2024 0100";

        AddEventCommand cmd = new AddEventCommand(args);

        TohruException e = assertThrowsExactly(TohruException.class, () -> {
            cmd.execute(list, ui, store);
        });
        assertEquals("Invalid argument: To datetime cannot be earlier than From datetime",
                e.getMessage());
        assertEquals(0, list.getTotal());
    }

}
