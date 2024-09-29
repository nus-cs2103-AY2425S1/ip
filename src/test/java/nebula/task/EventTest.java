package nebula.task;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void createEvent_success() {
        assertEquals(new Event("birthday party", "2024-09-12 12:30", "2024-09-12 04:30").toString(),
                "[E] [ ] birthday party (from: September 12, 2024 12:30 to: September 12, 2024 04:30)");

        assertEquals(new Event("math lecture", "2024-10-12", "2024-10-12").toString(),
                "[E] [ ] math lecture (from: October 12, 2024 00:00 to: October 12, 2024 00:00)");

        assertEquals(new Event("bus tour", "2024-10-09", "2024-10-09 22:00").toString(),
                "[E] [ ] bus tour (from: October 9, 2024 00:00 to: October 9, 2024 22:00)");
    }

    @Test
    public void createEvent_failure(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Event("birthday party", "2024-09-12 12:30", "2024-09-12 4:30");
        });

        assertEquals("Invalid date format: 2024-09-12 4:30", thrown.getMessage());
    }
}
