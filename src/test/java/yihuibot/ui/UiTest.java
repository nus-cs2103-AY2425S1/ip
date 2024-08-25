package yihuibot.ui;

import org.junit.jupiter.api.Test;

import yihuibot.exception.parse.ParseException;

import yihuibot.executable.Executable;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit Test for Ui.java
 * 
 * @author Toh Yi Hui A0259080A
 */
public class UiTest {
    private Ui ui;

    /**
     * Ensures that the Ui is not null.
     */
    @Test
    public void constructor_notNull() {
        ui = new Ui("yyyy-MM-dd HH:mm");
        assertNotNull(ui);
    }

    /**
     * Ensures that the parse method returns an instance of Executable.
     */
    @Test
    public void parse_returnsExecutable() {
        ui = new Ui("yyyy-MM-dd HH:mm");
        try {
            assertInstanceOf(Executable.class, ui.parse("list"));
        } catch (ParseException e) {
            fail();
        }
    }
}
