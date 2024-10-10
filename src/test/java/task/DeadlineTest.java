package task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ScoobyDoo.task.Deadline;

public class DeadlineTest {
    @Test
    public void toFileFormatString_normalInput_writtenCorrectly() {
        assertEquals("D | 0 | test description | 2024-08-31 12:00"
                , new Deadline("test description",
                        LocalDateTime.parse("2024-08-31 12:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                        .toFileFormatString());
    }
}
