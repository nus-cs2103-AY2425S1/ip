package pochat.bot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parseTest() {
        Parser parser = Parser.of(new TaskList());
        try {
            assertEquals(parser.parse("bye"), "Bye. Hope to see you again soon!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
