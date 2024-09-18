package stobberi.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void testGreet() {
        // Expected greeting message
        String expectedGreeting = "Hello! I'm Stobberi.\n" +
                "I LOVEEEEE managing your tasks for you!\n" +
                "\n" +
                "What would you like to do with your task list today?\n" +
                "\n" +
                "If you dunno what you to do, just type '?'!!  :)   ";

        // Verify that greet() returns the correct message
        assertEquals(expectedGreeting, Ui.greet(), "The greet message should match the expected greeting.");
    }

    @Test
    public void testSayGoodbye() {
        // Expected goodbye message
        String expectedGoodbye = "Baiii baiiii!\n" +
                "\n" +
                "Seeee yaaaa! :)";

        // Verify that sayGoodbye() returns the correct message
        assertEquals(expectedGoodbye, Ui.sayGoodbye(), "The goodbye message should match the expected farewell.");
    }
}

