package spongebob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import spongebob.exception.SpongebobException;

public class EventTest {

    @Test
    public void eventSave_correctInput_success() throws SpongebobException {
        assertEquals("EVENT|false|eat lunch|10/10/1010|10/10/1010",
                new Event("eat lunch", "10/10/1010", "10/10/1010").save());
    }

    @Test
    public void eventSave_wrongInput_failure() {
        try {
            assertEquals("", new Event("", "", "").save());
            fail();
        } catch (Exception e) {
            assertEquals("Barnacles! You missed out  Description, From, To!",
                        e.getMessage());
        }
    }

    @Test
    public void eventSave_wrongFromInput_failure() {
        try {
            assertEquals("",
                    new Event("eat lunch", "", "12/12/2939").save());
            fail();
        } catch (Exception e) {
            assertEquals("Barnacles! You missed out  From,!", e.getMessage());
        }
    }

    @Test
    public void eventSave_wrongToInput_failure() {
        try {
            assertEquals("",
                    new Event("eat lunch", "12/12/2030", "1").save());
            fail();
        } catch (Exception e) {
            assertEquals("Barnacles! Please enter date at dd/mm/yyyy!", e.getMessage());
        }
    }
}
