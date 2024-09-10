package Dawn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
//    @Test
//    public void Parser_invalidCommand_ExceptionThrown() {
//        try {
//            new Parser("hello", "world");
//            fail();
//        } catch (DawnException e) {
//            assertEquals("Am I supposed to know what that means? Try something else\n", e.getMessage());
//        }
//    }

    @Test
    public void mark_missingIndex_ExceptionThrown() {
        try {
            ParserStub p = new ParserStub("mark");
            p.mark("mark", "");
            fail();
        } catch (DawnException e) {
            assertEquals("Please specify the index of the task to be marked!\n", e.getMessage());
        }
    }

    @Test
    public void mark_indexOutOfRange_exceptionThrown() {
        try {
            ParserStub p = new ParserStub("mark");
            p.mark("mark", "-1");
            fail();
        } catch (DawnException e) {
            assertEquals("Task specified does not exist!\n", e.getMessage());
        }
    }


}
