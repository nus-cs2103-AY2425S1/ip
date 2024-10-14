package tasks;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateHandlerTest {

    @Test
    public void testParseLocalDateTimePass() {
        LocalDateTime[] testCases = new LocalDateTime[]{DateHandler.parseLocalDateTime("02/02/2000 1800"),
                                                        DateHandler.parseLocalDateTime("2000-12-12 1800")};

        for (LocalDateTime testCase : testCases) {
            assertNotNull(testCase);
        }
    }

    @Test
    public void testParseLocalDateTimeFail() {
        LocalDateTime[] testCases = new LocalDateTime[]{DateHandler.parseLocalDateTime("02/20/2000 1800"),
                                                        DateHandler.parseLocalDateTime("02/02/2000 2600"),
                                                        DateHandler.parseLocalDateTime("02/02/2000"),
                                                        DateHandler.parseLocalDateTime("2000-15-10 1800"),
                                                        DateHandler.parseLocalDateTime("2000-10-10 -1000"),
                                                        DateHandler.parseLocalDateTime("2000-15-10"),
                                                        DateHandler.parseLocalDateTime("2000 15 10 1800"),
                                                        DateHandler.parseLocalDateTime("")};

        for (LocalDateTime testCase : testCases) {
            assertNull(testCase);
        }
    }

    @Test
    public void testParseLocalDatePass() {
        LocalDate[] testCases = new LocalDate[]{DateHandler.parseLocalDate("02/02/2000"),
                                                DateHandler.parseLocalDate("2000-02-02"),
                                                DateHandler.parseLocalDate("02/02/2000 1800")};

        for (LocalDate testCase : testCases) {
            assertNotNull(testCase);
        }
    }

    @Test
    public void testParseLocalDateFail() {
        LocalDate[] testCases = new LocalDate[]{DateHandler.parseLocalDate("02/31/2000"),
                                                DateHandler.parseLocalDate("2000-15-10"),
                                                DateHandler.parseLocalDate("2000-02-33"),
                                                DateHandler.parseLocalDate("2000-1000-10"),
                                                DateHandler.parseLocalDate("2000 15 10 1800"),
                                                DateHandler.parseLocalDate("")};

        for (LocalDate testCase : testCases) {
            assertNull(testCase);
        }
    }
}
