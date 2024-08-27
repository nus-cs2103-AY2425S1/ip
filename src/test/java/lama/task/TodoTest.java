package lama.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void ConstructorTest() {
        String description = "Return Book";
        Task todo = new Todo(description);

        String output = "[T][ ] Return Book";

        assertEquals(output, todo.toString());
    }

    @Test
    public void toFileTest() {
        String description = "Return Book";
        Task todo = new Todo(description);

        String output = "T | 0 | Return Book";

        assertEquals(output, todo.toFile());
    }
}
