package echo.backend;

import echo.Echo;
import echo.Ui;
import echo.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void test_parse_date_time() {
        Parser parser = new Parser(new Ui(new TaskList(), new Echo("src/main/data/savedTasks.txt")));
        assertThrows(DateTimeParseException.class,
                () -> parser.parseDate("Fifth of August"));
    }
}
