package colby;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseDeadlineTask_success() {
        Task task = new Deadline("quiz", "2024/09/09");
        assertEquals(task, Parser.parseDeadlineTask("deadline quiz /by 2024/09/09"));
    }
}
