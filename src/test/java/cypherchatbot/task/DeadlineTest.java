package cypherchatbot.task;
import cypherchatbot.CypherException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDisplayString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime by = LocalDateTime.parse("2024-10-10 14:00", formatter);
        assertEquals("[D][ ] example (by: 10 Oct 2024 14:00)", new Deadline("example",by).toString());
    }

    @Test
    public void testDisplayStringWithCompletion(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime by = LocalDateTime.parse("2024-10-10 14:00", formatter);
            Task task = new Deadline("example", by);
            task.markAsComplete();
            assertEquals("[D][X] example (by: 10 Oct 2024 14:00)", task.toString());
        } catch (CypherException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFileString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime by = LocalDateTime.parse("2024-10-10 14:00", formatter);
        assertEquals("D|0|example|2024-10-10 14:00", new Deadline("example",by).toStringInFile());
    }

    @Test
    public void testFileStringWithCompletion(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime by = LocalDateTime.parse("2024-10-10 14:00", formatter);
            Task task = new Deadline("example",by);
            task.markAsComplete();
            assertEquals("D|1|example|2024-10-10 14:00", task.toStringInFile());
        } catch (CypherException e) {
            System.out.println(e.getMessage());
        }
    }
}
