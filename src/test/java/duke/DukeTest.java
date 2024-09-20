package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void getResponseTest() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("todo"), "The description of the task must contain some"
                + " substance; it cannot be void.");
        assertEquals(duke.getResponse("deadline"), "The description of the task must contain some"
                + " substance; it cannot be void.");
        assertEquals(duke.getResponse("event"), "The description of the task must contain some"
                + " substance; it cannot be void.");
        assertEquals(duke.getResponse("event"), "The description of the task must contain some"
                + " substance; it cannot be void.");
        assertEquals(duke.getResponse("deadline do work /by abc"), "Ah, esteemed inquirer, the date"
                + " format you have provided is not correct. It must be expressed as \"yyyy-mm-dd\".");
    }
}
