package sunny;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskCreatorTest {

    @Test
    public void test1() {
        try {
            assertEquals(String.format("[T][] %s", "eat lunch"),
                    TaskCreator.create("todo eat lunch").toString());
        } catch (WrongMessageException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test2() {
        try {
            assertEquals(String.format("[D][] %s (by: %s)", "eat lunch", "2024-08-30"),
                    TaskCreator.create("deadline eat lunch/by 2024-08-30").toString());
        } catch (WrongMessageException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test3() {
        try{
            assertThrows(WrongMessageException.class,
                    () -> TaskCreator.create("hello"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
