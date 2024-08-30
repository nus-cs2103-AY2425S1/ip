import Tasks.Deadline;
import Tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    public void toFileFormatTest() {
        Deadline deadline = new Deadline("1", "2019-03-30");
        String toFileFormat = deadline.toFileFormat();
        assertEquals("D | 0 | 1 | 2019-03-30", toFileFormat);
    }
}