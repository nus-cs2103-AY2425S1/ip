package duke.parsers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import duke.exceptions.InvalidDateException;
import duke.exceptions.MissingDateException;
import duke.exceptions.MissingTaskNameException;
import duke.tasks.TaskListStub;
import duke.ui.Ui;

public class ParserTest {
    private Parser parser;
    private TaskListStub taskList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        taskList = new TaskListStub(ui);
        parser = new Parser(taskList, ui);
    }
    @Test
    void testHandleAddEvent_Success() throws Exception {
        String message = "event Meeting /from 01/09/2024 /to 10/09/2024";
        parser.parse(message);
        assertEquals("[E][ ] Meeting (from: 01/09/2024 to: 10/09/2024)", taskList.getTask(0));
    }

    @Test
    void testHandleAddEvent_InvalidDateException() {
        String message = "event Meeting /from 1 Sept /to 10 Sept";

        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> {
            parser.parse(message);
        });

        assertEquals("OOPS!!! Please ensure that date format are in dd/MM/yyyy HH:mm or dd/MM/yyyy",
                exception.toString());
    }

    @Test
    void testHandleAddEvent_MissingDateException() {
        String message = "event Meeting";

        MissingDateException exception = assertThrows(MissingDateException.class, () -> {
            parser.parse(message);
        });

        assertEquals("OOPS!!! Events needs to be followed by both a start and end date/time in the format: "
                        + "'team project meeting /from 2/10/2019 17:00 /to 2/10/2019 19:00'.",
                exception.toString());
    }

    @Test
    void testHandleAddEvent_MissingTaskNameException() {
        String message = "event /from 01/09/2024 /to 10/09/2024";

        MissingTaskNameException exception = assertThrows(MissingTaskNameException.class, () -> {
            parser.parse(message);
        });

        assertEquals("OOPS!!! The description of a event cannot be empty.",
                exception.toString());
    }

}
