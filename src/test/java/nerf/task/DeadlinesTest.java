package nerf.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlinesTest {
    @Test
    public void testNormalCreationOfTask(){
        LocalDate due = LocalDate.parse("2024-08-27");
        Task t = new Deadlines("Work", due);

        // test 1: the print string is in the right format
        assertEquals("[D][ ] Work (by: Aug 27 2024)", t.toString());

        // test 2: the save string is in the right format
        assertEquals("D | 0 | Work | 2024-08-27", t.getSaveFormat());
    }

    @Test
    public void testLoadedCreationOfTask(){
        LocalDate due = LocalDate.parse("2024-08-27");
        Task t = new Deadlines("Work", true, due, "1");
        
        // test 1: the print string is in the right format
        assertEquals("[D][X] Work (by: Aug 27 2024)", t.toString());

        // test 2: the save string is in the right format
        assertEquals("D | 1 | Work | 2024-08-27", t.getSaveFormat());
    }

    @Test
    public void testInvalidCreationOfTask(){
        try{
            Task t = new Deadlines("Work", null);
            t.toString();
            fail();
        } catch (Exception e){
            //test 1: no due date
            assertEquals("Due date cannot be null", e.getMessage());
        }
    }
}