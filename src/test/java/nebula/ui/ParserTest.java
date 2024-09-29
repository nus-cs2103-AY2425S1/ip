package nebula.ui;

import nebula.command.AddTodoCommand;
import nebula.exception.NebulaException;
import org.junit.jupiter.api.Test;
import nebula.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parser_success() throws NebulaException {
        Ui ui = new Ui();

        assertEquals(new AddTodoCommand("todo do laundry").getDescription(),
                Parser.parse("todo do laundry").getDescription());
    }
    @Test
    public void unknownMessage_failure() {
        Ui ui = new Ui();

        NebulaException thrown = assertThrows(NebulaException.class, () -> {
            Parser.parse("todo");
        });
        assertEquals(ui.displayUnknownMessageException(), thrown.getMessage());
    }

    @Test
    public void missingTaskNumber_failure() {
        Ui ui = new Ui();

        NebulaException thrown = assertThrows(NebulaException.class, () -> {
            Parser.parse("mark");
        });
        assertEquals(ui.displayUnknownTaskNumberException(), thrown.getMessage());
    }
}
