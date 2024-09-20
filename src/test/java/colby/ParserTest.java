package colby;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseDeadlineTask_success() {
        Task task1 = new Deadline("quiz", "2024/09/09");
        assertEquals(task1, Parser.parseDeadlineTask("deadline quiz /by 2024/09/09"));
    }


    @Test
    public void parseToDoTask_success() {
        Task task2 = new ToDo("read book");
        assertEquals(task2, Parser.parseToDoTask("todo read book"));
    }
}
