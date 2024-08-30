package julie.task;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadline_testStrings() {
        Task t = new Deadline("Test", LocalDate.parse("2000-12-09"));
        assertEquals("[D] [ ] Test (by: Dec 09 2000)", t.toString());
        assertEquals("D | Test | 2000-12-09", t.toStorageString());
    }
}
