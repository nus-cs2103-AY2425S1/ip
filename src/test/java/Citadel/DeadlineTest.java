package Citadel;

import Citadel.Commands.handleDeadline;
import Citadel.Task.TaskList;
import Citadel.exception.CitadelException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
@Test
public void TestDeadline() {
    try {
        String input = "deadline sleep /by 11/01/2024 13:00";
        TaskList tasks = new TaskList();
        new handleDeadline(input, tasks).run();
        System.out.println(tasks.get(0).printTask());
        assertEquals("[D][ ] sleep (by: 11/01/2024 13:00)", tasks.get(0).toString());
        assertEquals("[D][ ] sleep (by: 11 01 2024 13:00)", tasks.get(0).printTask());
        tasks.get(0).markAsDone();
        assertEquals("[D][X] sleep (by: 11/01/2024 13:00)", tasks.get(0).toString());
        assertEquals("[D][X] sleep (by: 11 01 2024 13:00)", tasks.get(0).printTask());
    } catch (CitadelException e) {
        assertEquals(true, false);
    }
}
}
