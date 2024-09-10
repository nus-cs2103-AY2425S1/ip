package nextgpt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import nextgpt.command.Command;


public class ParserTest {
    @Test
    public void invalidInput() {
        String invalid_input  = "add";

        try {
            Command c = Parser.parse(invalid_input);
            fail();
        }catch (Exception e){
            assertEquals("Sorry, I do not understand what that means.", e.getMessage());
        }
    }




    @Test
    public void wrongInputExceptionTest() {
        try {
            Command c = Parser.parse("Event read book /from 2024-08-01 /to");
            fail();
        } catch (NextGPTException e) {
            assertEquals("Please provide your Event task in the following format: \n"
                    + "Event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                    + "Example: event do homework /from 2024-08-01 /to 2024-08-03\n", e.getMessage());
        }

        try {
            Command c = Parser.parse("Deadline read book /by");
            fail();
        } catch (NextGPTException e) {
            assertEquals("Please provide your Deadline task in the following format: \n"
                    + "Deadline <description> /by <yyyy-mm-dd>\n"
                    + "Example: Deadline do homework /by 2024-09-01\n", e.getMessage());
        }
    }
}
