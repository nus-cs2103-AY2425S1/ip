package pochat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo toDo = new ToDo("dummy task");
        assertEquals(toDo.toString(), "[T][ ] dummy task");
    }

    @Test
    public void formatYearMonthDayTest() {
        String expectedOutput = "2024-08-29 2200";
        String actualOutput = new ToDo("dummy task")
                .formatYearMonthDay(LocalDateTime.of(2024, 8, 29, 22, 0));

        assertEquals(expectedOutput, actualOutput);
    }
}
