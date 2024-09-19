package nen.tasks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void findArgumentOfTest() {
        try {
            assertTrue(Task.of("todo hello") instanceof Todo);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
