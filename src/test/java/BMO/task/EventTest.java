package bmo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void saveFormatTest() {
        Task task = new Event("Public Garden", "28/09/2024", "29/09/2024");
        assertEquals(task.getSavedFormat(),
            "E | 0 | Public Garden | 28/09/2024 | 29/09/2024\n");
    }
}
