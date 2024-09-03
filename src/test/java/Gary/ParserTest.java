package Gary;

import Gary.command.AddCommand;
import Gary.command.Command;
import Gary.task.Event;
import Gary.task.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class ParserTest {
    @Test
    public void parseUnknownInput_ExceptionTest() {
        try {
            Command c = Parser.parse("Hello");
            fail();
        } catch (GaryException e) {
            assertEquals("Sorry! I do not understand what is this!!", e.getMessage());
        }
    }

    @Test
    public void parseWrongInput_ExceptionTest() {
        try {
            Command c = Parser.parse("Event competition /from 23-08-2024 /to");
            fail();
        } catch (GaryException e) {
            assertEquals("Please provide your Event task in the following format: \n" +
                            "Gary.task.Event <task name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>\n",
                    e.getMessage());
        }

        try {
            Command c = Parser.parse("Event competition class presentation /from /from 03-09-2024 /to 03-09-2024");
            fail();
        } catch (GaryException e) {
            assertEquals("Please provide your Event task in the following format: \n" +
                            "Gary.task.Event <task name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>\n",
                    e.getMessage());
        }

        try {
            Command c = Parser.parse("Deadline submit quiz /by");
            fail();
        } catch (GaryException e) {
            assertEquals("Please provide your Deadline task in the following format: \n" +
                    "Gary.task.Deadline <task name> /by <yyyy-mm-dd>\n", e.getMessage());
        }
    }

    @Test
    public void parseAddEventTest() throws GaryException{
        Command c1 = Parser.parse("Event overseas competition /from 2024-08-24 0800 /to 2024-08-24 1300");
        assertEquals(new AddCommand(new Event("overseas competition",
                "2024-08-24 0800", "2024-08-24 1300")), c1 );

        Command c2 = Parser.parse("Event class presentation/from2024-09-03 1500/to2024-09-03 1730");
        assertEquals(new AddCommand(new Event("class presentation",
                "2024-09-03 1500", "2024-09-03 1730")), c2 );
    }

    @Test
    public void parseAddDeadlineTest() throws GaryException{
        Command c1 = Parser.parse("Deadline submit quiz /by 2024-09-06");
        assertEquals(new AddCommand(new Deadline("submit quiz", "2024-09-06")), c1 );
    }
}