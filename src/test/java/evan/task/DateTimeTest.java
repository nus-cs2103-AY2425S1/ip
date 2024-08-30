package evan.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {
    @Test
    public void parseInput_variousString_success() {
        // Simple string → STRING
        assertEquals(DateTime.InputType.STRING, DateTime.parseInput("buy bread").type);

        // Invalid date → STRING
        assertEquals(DateTime.InputType.STRING, DateTime.parseInput("2024-13-02").type);

        // Valid date, invalid time → STRING
        assertEquals(DateTime.InputType.STRING, DateTime.parseInput("2024-11-02 2501").type);

        // Invalid date, invalid time → STRING
        assertEquals(DateTime.InputType.STRING, DateTime.parseInput("2024-12-32 1261").type);

        // Invalid date, valid time → STRING
        assertEquals(DateTime.InputType.STRING, DateTime.parseInput("2024-00-25 1200").type);

        // Valid date → DATE
        assertEquals(DateTime.InputType.DATE, DateTime.parseInput("2024-04-06").type);

        // Valid date and time → DATETIME
        assertEquals(DateTime.InputType.DATETIME, DateTime.parseInput("2024-09-10 2359").type);

    }
}
