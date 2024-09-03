package broski;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import broski.task.Todo;

public class TodoTest {
    @Test
    public void toString_descriptionInput_success() {
        Todo mathHomework = new Todo("math homework");
        assertEquals("[T][ ] math homework", mathHomework.toString());
    }
}
