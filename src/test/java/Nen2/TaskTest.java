package Nen2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void findArgumentOfTest(){
        try {
            assertEquals(Task.of("todo hello") instanceof Todo, true);
        } catch (Exception e) {
            assertEquals(true, false);
        }
    }
}
