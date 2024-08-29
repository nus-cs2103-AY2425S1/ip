package greetbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void greetUser_Test() {

        Ui testUi = new Ui();
        String actualOutput = testUi.greetUser();

        assertEquals("こんにちわ！　私はグリーティングボットです\n" +
                "(Hello! I'm GreetBot)\n" +
                "どんな御用でしょうか\n" +
                "(What can I do for you?)", actualOutput);
    }

    @Test
    public void farewellUser_Test() {

        Ui testUi = new Ui();
        String actualOutput = testUi.farewellUser();

        assertEquals("また近いうちにお会いできるのを楽しみにしています！\n" +
                "(I'm looking forward to seeing you again soon!)", actualOutput);
    }
}
