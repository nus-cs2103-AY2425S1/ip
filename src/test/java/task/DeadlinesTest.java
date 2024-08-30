package task;
import exception.CommandFoundButInvalidException;
import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlinesTest {
    @Test
    public void testSuccess() {
        String result = "[D][ ] meeting (by: 12 Dec 2024 10:00)";
        try {
            Deadlines d = new Deadlines("meeting /by 2024-12-12T10:00");
            assertEquals(result, d.toString());
        } catch (CommandFoundButInvalidException e) {
            System.out.println("Error when testing");
        }
    }

    @Test
    public void testException() {
        String exceptionMessage = "Uh Oh, wrong syntax for the command - deadline";
        String realMessage = "";
        try {
            Deadlines d = new Deadlines("aslkdhflwiu");
        } catch (CommandFoundButInvalidException e) {
            realMessage = e.getMessage();
        } finally {
            assertEquals(exceptionMessage, realMessage);
        }
    }
}
