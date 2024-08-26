package rob;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RobTest {
    @Test
    public void testFindTaskNum() throws DukeException {
        Rob rob = new Rob("./data/testFind.txt");
        assertEquals(1, rob.findTaskNum("mark 1"));
        assertEquals(3, rob.findTaskNum("unmark 3"));
        assertEquals(2, rob.findTaskNum("delete 2"));
    }

    @Test
    public void testInvalidTaskNum() {
        Rob rob = new Rob("./data/testFind.txt");
        assertThrows(DukeException.class, () -> rob.findTaskNum("mark 6"));

        assertThrows(DukeException.class, () -> rob.findTaskNum("mark 0"));
    }
}
