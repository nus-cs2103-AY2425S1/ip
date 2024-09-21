package jackson.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CustomDateTimeTest {
    @Test
    public void compareTo_earlierDates_moreThan() {
        CustomDateTime a = new CustomDateTime("24-12-2024");
        CustomDateTime b = new CustomDateTime("23-12-2024 10:00");
        CustomDateTime c = new CustomDateTime("22-12-2024 23:59");
        CustomDateTime d = new CustomDateTime("21-12-2024");

        assertTrue(a.compareTo(b) > 0);
        assertTrue(a.compareTo(c) > 0);
        assertTrue(a.compareTo(d) > 0);

        assertTrue(b.compareTo(c) > 0);
        assertTrue(b.compareTo(d) > 0);

        assertTrue(c.compareTo(d) > 0);
    }
}
