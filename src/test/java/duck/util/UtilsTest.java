package duck.util;

import duck.data.exception.DuckException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {


    @Test
    public void convertToDateTime_correctStringFormat_success() throws DuckException {
        String dateTime = "2021-08-21 1800";
        String dataTime2 = "2021/08/21 1700";
        assertDoesNotThrow(() -> Utils.convertToDateTime(dateTime));
        assertEquals("2021-08-21T18:00", Utils.convertToDateTime(dateTime).toString());

        assertDoesNotThrow(() -> Utils.convertToDateTime(dataTime2));
        assertEquals("2021-08-21T17:00", Utils.convertToDateTime(dataTime2).toString());
    }

    @Test
    public void convertToDateTime_incorrectStringFormat_exceptionThrown() {
        String[] incorrectDateTimes = {
                "2021-08-21 18000",
                "2021-08-21 1860",
                "2021-08-21 18:00",
                "2021-08-21 18:00:00",
                "2021-08/21 1800",
                "20210821 1800",
                "2021-08-21",
                "20210821"
        };

        for (String dateTime : incorrectDateTimes) {
            assertThrows(Exception.class, () -> Utils.convertToDateTime(dateTime));
        }


    }
}
