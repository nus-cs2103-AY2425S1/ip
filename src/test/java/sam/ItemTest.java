package sam;  //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void toData_item_success() {
        Item item = new Item("task");
        assertEquals("0 | task", item.toData());
    }
}