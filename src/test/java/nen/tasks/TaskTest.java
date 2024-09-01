package nen.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void findArgumentOfTest(){
        try {
            assertTrue(Task.of("todo hello") instanceof Todo);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
