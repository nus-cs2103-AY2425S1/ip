package system;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeSystemTest {

    @Test
    public void testCreateDate(){
        DateTimeSystem dateTimeSystem = new DateTimeSystem();
        LocalDateTime ldt = LocalDateTime.of(2024,8,29,12,12);
        assertEquals(dateTimeSystem.createDateTime("2024","8","29","12","12"),ldt);
    }

    @Test
    public void testFormatDate(){
        DateTimeSystem dateTimeSystem = new DateTimeSystem();
        LocalDateTime ldt = LocalDateTime.of(2024,8,29,12,12);

        assertEquals("2024-08-29 12:12 pm",dateTimeSystem.formatLocalTimeDate(ldt));
    }

}
