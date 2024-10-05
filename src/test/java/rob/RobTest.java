package rob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RobTest {
    @Test
    public void testFindTaskNum() throws RobException {
        Rob rob = new Rob("./data/testFind.txt");
        assertEquals(1, rob.findTaskNum("mark 1"));
        assertEquals(3, rob.findTaskNum("unmark 3"));
        assertEquals(2, rob.findTaskNum("delete 2"));
    }

    @Test
    public void testInvalidTaskNum() {
        Rob rob = new Rob("./data/testFind.txt");
        assertThrows(RobException.class, () -> rob.findTaskNum("mark 6"));

        assertThrows(RobException.class, () -> rob.findTaskNum("mark 0"));
    }
}
