package task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toFileFormatString_normalInput_writtenCorrectly() throws Exception{
        assertEquals("D | 0 | test description | 2024-08-31 12:00"
                , new Deadline("test description",
                        LocalDateTime.parse("2024-08-31 12:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                        .toFileFormatString());
    }
}
