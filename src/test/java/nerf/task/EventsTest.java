package nerf.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventsTest {
    @Test
    public void testNormalCreationOfTask(){
        LocalDate from = LocalDate.parse("2024-08-27");
        LocalDate to = LocalDate.parse("2024-08-28");
        Task t = new Events("Event", from, to);

        // test 1: the print string is in the right format
        assertEquals("[E][ ] Event (from: Aug 27 2024 to: Aug 28 2024)", t.toString());

        // test 2: the save string is in the right format
        assertEquals("E | 0 | Event | 2024-08-27 | 2024-08-28", t.getSaveFormat());
    }

    @Test
    public void testLoadedCreationOfTask(){
        LocalDate from = LocalDate.parse("2024-08-27");
        LocalDate to = LocalDate.parse("2024-08-28");
        Task t = new Events("Event", true, from, to, "1");
        
        // test 1: the print string is in the right format
        assertEquals("[E][X] Event (from: Aug 27 2024 to: Aug 28 2024)", t.toString());

        // test 2: the save string is in the right format
        assertEquals("E | 1 | Event | 2024-08-27 | 2024-08-28", t.getSaveFormat());
    }

    @Test
    public void testInvalidCreationOfTask(){
        try{
            Task t = new Events("Event", null,null);
            t.toString();
            fail();
        } catch (Exception e){
            // test 1: both are null
            assertEquals("Dates cannot be null", e.getMessage());
        }

        try{
            LocalDate from = LocalDate.parse("2024-08-27");
            Task t = new Events("Event", from,null);
            t.toString();
            fail();
        } catch (Exception e){
            // test 2: from are null
            assertEquals("Dates cannot be null", e.getMessage());
        }

        try{
            LocalDate to = LocalDate.parse("2024-08-28");
            Task t = new Events("Event", null,to);
            t.toString();
            fail();
        } catch (Exception e){
            // test 3: to are null
            assertEquals("Dates cannot be null", e.getMessage());
        }
    }
}