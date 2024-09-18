package pochat.bot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parseTest() {
        Parser parser = Parser.of(new TaskList());
        try {
            assertEquals(parser.parse("bye"), "Bye! Hope to see you again soon :)");
            assertEquals(parser.parse("invalidinput"),
                    "Please enter a valid input and try again! Some examples of valid inputs are:\n"
                            + "todo [description]\ndeadline [description] /by [dd/mm/yyyy HHMM]\n"
                            + "event [description] /from [dd/mm/yyyy HHMM] /to [dd/mm/yyyy HHMM]\n"
                            + "For more commands, refer to the project's README");
            assertEquals(parser.parse("list"),
                    "There are no tasks in the list! Add some tasks to get started :)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void sizeTest() {
        Parser parser = Parser.of(new TaskList());
        assertEquals(parser.getNumTasks(), 0);
    }

}
