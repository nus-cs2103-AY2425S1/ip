package weeny;

import org.junit.jupiter.api.Test;
import weeny.task.Deadline;
import weeny.ui.Ui;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void execute_deadline_string() {
        Weeny weeny = new Weeny();
        Ui ui = new Ui();
        weeny.executeWeeny("deadline Prepare Presentation /by 05/09/2024 0930");
        Deadline deadline = new Deadline("Prepare Presentation", "05/09/2024 0930");
        String expected = String.format("Gotcha, I have added:\n" + "[D][ ] Prepare Presentation (by: Sep 5 2024 9:30 am)" +
                "\n" +"You have a total of " + "1" + " tasks in the list.\n");
        assertEquals(expected, ui.showTaskAddedMessage(deadline, 1));
    }
}

