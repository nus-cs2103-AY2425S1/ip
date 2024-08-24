package Spongebob.task;

import Spongebob.exception.SpongebobException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void todoSave_correctInput_success() throws SpongebobException {
        assertEquals("TODO|false|eat lunch",new Todo("eat lunch").save());
    }

    @Test
    public void todoSave_wrongInput_failure() {
        try {
            assertEquals("", new Todo("").save());
            fail();
        } catch (Exception e) {
            assertEquals("Barnacles! You can't enter an empty todo!", e.getMessage());
        }
    }
}
