package weeny;

import org.junit.jupiter.api.Test;
import weeny.task.Deadline;
import weeny.task.TaskList;
import weeny.ui.Ui;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void execute_delete_command() {
        Weeny weeny = new Weeny();
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Deadline deadline = new Deadline("Prepare Presentation", "05/09/2024 0930");
        tasks.addTask(deadline);

        weeny.executeWeeny("delete 1");
        tasks.deleteTask(0);
        String expected = String.format("Spooof! The task magically disappeared:\n" +
                deadline + "\n" + "You have a total of " + tasks.size() + " tasks in the list.\n");
        assertEquals(expected, ui.showTaskDeletedMessage(deadline, 0));
    }
}

