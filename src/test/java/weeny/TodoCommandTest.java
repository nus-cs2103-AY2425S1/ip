package weeny;

import org.junit.jupiter.api.Test;
import weeny.task.Todo;
import weeny.ui.Ui;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void execute_todo_string() {
        Weeny weeny = new Weeny();
        Ui ui = new Ui();
        weeny.executeWeeny("todo Clean the room");
        Todo todo = new Todo("Clean the room");
        String expected = String.format("Gotcha, I have added:\n" + "[T][ ] Clean the room" +
                "\n" +"You have a total of " + 1 + " tasks in the list.\n");
        assertEquals(expected, ui.showTaskAddedMessage(todo, 1));
    }
}

