package nebula.task;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void createEvent_success(){
        assertEquals(new Event("birthday party", "2024-09-12 12:30", "2024-09-12 04:30").toString(),
                "[E] [ ] birthday party (from: September 12, 2024 12:30 to: September 12, 2024 04:30)");
    }
}
