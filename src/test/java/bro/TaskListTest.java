package bro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void addEventTest() {
        Ui ui = new Ui();
        try {
            new TaskList(ui, new Parser(ui)).addEvent("event name 2010-12-01 2011-12-01");
            fail();
        } catch (Exception e) {
            assertEquals(Ui.line + "   " + "Please include \"/from\" and \"/to\" in bro.Event!!!"
                         + "\n" + Ui.line, e.getMessage());
        }
    }

}
