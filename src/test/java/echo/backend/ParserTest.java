package echo.backend;

import echo.Echo;
import echo.Ui;
import echo.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void test_parse_invalid_date_time() {
        Parser parser = new Parser(
                new Ui(new TaskList(),
                new Echo("src/main/data/savedTasks.txt")));
        List<String> dateTimesToParse =
                new ArrayList<>(
                        Arrays.asList(
                            "Fifth of August",
                            "3/4",
                            "24-24-24",
                            "32/42",
                            "401-200-1024",
                            "invalid date",
                            "0-0-0"
                        ));
        for (String dateTimeToParse : dateTimesToParse) {
            assertThrows(DateTimeParseException.class,
                    () -> parser.parseDate(dateTimeToParse));
        }
    }
    @Test
    public void test_parse_valid_date_time() {
        Parser parser = new Parser(
                new Ui(new TaskList(),
                        new Echo("src/main/data/savedTasks.txt")));
        List<String> dateTimesToParse =
                new ArrayList<>(
                        Arrays.asList(
                                "2024-9-20",
                                "20/9/2024",
                                "20 Sep 2024",
                                "20-09-2024",
                                "September 20 2024"
                        ));
        LocalDate expectedDate = LocalDate.of(2024, 9, 20);
        for (String dateTimeToParse : dateTimesToParse) {
            assertEquals(expectedDate, parser.parseDate(dateTimeToParse));
        }
    }
}
