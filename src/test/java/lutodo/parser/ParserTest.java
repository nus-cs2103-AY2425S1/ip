package lutodo.parser;

import lutodo.commands.AddCommand;
import lutodo.commands.ExitCommand;
import lutodo.commands.ShowListCommand;
import lutodo.commands.UnknownCommand;
import lutodo.tasks.DeadlineTask;
import lutodo.tasks.Task;
import org.junit.jupiter.api.Test;

import static lutodo.parser.Parser.parse;
import static lutodo.parser.Parser.splitTaskInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {

    @Test
    public void splitTaskInfo_infoWithSpace_success() throws Exception {
        //
        String[] expected = {"taskType", "task information"};
        assertTrue(expected[0].equals(splitTaskInfo("taskType task information")[0]));
        assertTrue(expected[1].equals(splitTaskInfo("  taskType   task information  ")[1]));
    }

    @Test
    public void parse_infoWithSpace_success() throws Exception {
        assertEquals(new ShowListCommand(), parse(" list  "));
        assertEquals(new ExitCommand(), parse("  Bye  "));
    }

    @Test
    public void parse_unKnownCommand_success() {
        assertEquals(new UnknownCommand("what is this?"), parse("what is this?"));
    }

}
