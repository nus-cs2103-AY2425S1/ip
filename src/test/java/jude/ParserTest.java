package jude;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jude.command.AddCommand;
import jude.command.DeleteCommand;
import jude.command.ListCommand;
import jude.command.MarkCommand;
import jude.command.UnmarkCommand;
import jude.task.Deadline;
import jude.task.Event;
import jude.task.Todo;

public class ParserTest {

    // list
    @Test
    public void parse_list_success() {
        try {
            assertEquals(new ListCommand().getType(), new Parser().parse("list").getType());
        } catch (JudeException je) {
            fail();
        }
    }

    @Test
    public void parse_listWithLeadingSpace_fail() {

        try {
            assertEquals(new ListCommand().getType(), new Parser().parse(" list").getType());
            fail();
        } catch (JudeException je) {
            assertEquals("No valid command was provided.", je.getMessage());
        }
    }

    @Test
    public void parse_listWithTrailingSpace_fail() {
        try {
            assertEquals(new ListCommand().getType(), new Parser().parse("list ").getType());
            fail();
        } catch (JudeException je) {
            assertEquals("Poyo, the description of a list must be empty.", je.getMessage());
        }
    }

    @Test
    public void parse_listWithTwoWords_fail() {
        try {
            assertEquals(new ListCommand(), new Parser().parse("list a"));
            fail();
        } catch (JudeException je) {
            assertEquals("Poyo, the description of a list must be empty.", je.getMessage());
        }
    }

    // mark
    @Test
    public void parse_mark_success() {
        try {
            assertEquals(new MarkCommand(1).getType(), new Parser().parse("mark 1").getType());
        } catch (JudeException je) {
            fail();
        }
    }

    @Test
    public void parse_markWithoutNumber_fail() {
        try {
            assertEquals(new MarkCommand(1).getType(), new Parser().parse("mark").getType());
            fail();
        } catch (JudeException je) {
            assertEquals(
                    "Poyo, the format of the command mark was not valid. The correct format: mark",
                    je.getMessage());
        }
    }

    @Test
    public void parse_ununmarkWithLetter_fail() {
        try {
            assertEquals(new UnmarkCommand(1).getType(), new Parser().parse("unmark AAA").getType());
            fail();
        } catch (JudeException je) {
            assertEquals("Invalid number format: expected an integer.", je.getMessage());
        }
    }

    // ununmark
    @Test
    public void parse_unmark_success() {
        try {
            assertEquals(new UnmarkCommand(1).getType(), new Parser().parse("unmark 1").getType());
        } catch (JudeException je) {
            fail();
        }
    }

    @Test
    public void parse_unmarkWithoutNumber_fail() {
        try {
            assertEquals(new UnmarkCommand(1).getType(), new Parser().parse("unmark").getType());
            fail();
        } catch (JudeException je) {
            assertEquals(
                    "Poyo, the format of the command unmark was not valid. The correct format: unmark",
                    je.getMessage());
        }
    }

    @Test
    public void parse_unmarkWithLetter_fail() {
        try {
            assertEquals(new UnmarkCommand(1).getType(), new Parser().parse("unmark AAA").getType());
            fail();
        } catch (JudeException je) {
            assertEquals("Invalid number format: expected an integer.", je.getMessage());
        }
    }

    // delete
    @Test
    public void parse_delete_success() {
        try {
            assertEquals(new DeleteCommand(1).getType(), new Parser().parse("delete 1").getType());
        } catch (JudeException je) {
            fail();
        }
    }

    @Test
    public void parse_deleteWithoutNumber_fail() {
        try {
            assertEquals(new DeleteCommand(1).getType(), new Parser().parse("delete").getType());
            fail();
        } catch (JudeException je) {
            assertEquals(
                    "Poyo, the format of the command delete was not valid. The correct format: delete",
                    je.getMessage());
        }
    }

    @Test
    public void parse_deleteWithLetter_fail() {
        try {
            assertEquals(new DeleteCommand(1).getType(), new Parser().parse("delete AAA").getType());
            fail();
        } catch (JudeException je) {
            assertEquals("Invalid number format: expected an integer.", je.getMessage());
        }
    }

    // to-do

    @Test
    public void parse_todo_success() {
        try {
            assertEquals(
                    new AddCommand(new Todo("AAA")).getType(),
                    new Parser().parse("to-do AAA").getType());
        } catch (JudeException je) {
            fail();
        }
    }

    @Test
    public void parse_todoWithoutDescription_fail() {
        try {
            assertEquals(
                    new AddCommand(new Todo("AAA")).getType(), new Parser().parse("to-do").getType());
            fail();
        } catch (JudeException je) {
            assertEquals("Poyo, the format of the command to-do was not valid."
                    + " The correct format: to-do", je.getMessage());
        }
    }

    @Test
    public void parse_todoWithoutDash_success() {
        try {
            assertEquals(
                    new AddCommand(new Todo("AAA")).getType(), new Parser().parse("todo AAA").getType());
            fail();
        } catch (JudeException je) {
            assertEquals("No valid command was provided.", je.getMessage());
        }
    }

    // deadline
    @Test
    public void parse_deadline_success() {
        try {
            assertEquals(
                    new AddCommand(new Deadline("AAA", "1/1/2000 0400")).getType(),
                    new Parser().parse("deadline AAA /by 1/1/2000 0400").getType()
            );
        } catch (JudeException je) {
            fail();
        }
    }

    @Test
    public void parse_deadlineNoTime_fail() {
        try {
            assertEquals(
                    new AddCommand(new Deadline("AAA", "1/1/2000 0000")).getType(),
                    new Parser().parse("deadline AAA /by 1/1/2000").getType()
            );
            fail();
        } catch (JudeException je) {
            assertEquals("wrong date or time format was provided."
                    + " Provide: day/month/year time (e.g. 1/1/2000 1800).", je.getMessage());
        }
    }

    @Test
    public void parse_deadlineWrongBy_fail() {
        try {
            assertEquals(
                    new AddCommand(new Deadline("AAA", "1/1/2000 0000")).getType(),
                    new Parser().parse("deadline AAA by 1/1/2000 0000").getType()
            );
            fail();
        } catch (JudeException je) {
            assertEquals("Poyo, the format of the command deadline was not valid. "
                            + "The correct format: deadline",
                    je.getMessage());
        }
    }

    // event
    @Test
    public void parse_event_success() {
        try {
            assertEquals(
                    new AddCommand(new Event("AAA", "1/1/2000 0000", "1/1/2000 1200")).getType(),
                    new Parser().parse("event AAA /from 1/1/2000 0000 /to 1/1/2000 1200").getType()
            );
        } catch (JudeException je) {
            System.out.println(je.getMessage());
            fail();
        }
    }

    @Test
    public void parse_eventNoTime_fail() {
        try {
            assertEquals(
                    new AddCommand(new Event("AAA", "1/1/2000 0000", "1/1/2000 1200")).getType(),
                    new Parser().parse("event AAA /from 1/1/2000 /to 1/1/2000").getType()
            );
            fail();
        } catch (JudeException je) {
            assertEquals("wrong date or time format was provided."
                    + " Provide: day/month/year time (e.g. 1/1/2000 1800).", je.getMessage());
        }
    }

    @Test
    public void parse_eventWrongTo_fail() {
        try {
            assertEquals(
                    new AddCommand(new Event("AAA", "1/1/2000 0000", "1/1/2000 1200")).getType(),
                    new Parser().parse("event AAA /from 1/1/2000 0000 to 1/1/2000 12000").getType()
            );
            fail();
        } catch (JudeException je) {
            assertEquals("Poyo, the format of the command event was not valid."
                    + " The correct format: event",
                    je.getMessage());
        }
    }

    @Test
    public void parse_eventWrongFrom_fail() {
        try {
            assertEquals(
                    new AddCommand(new Event("AAA", "1/1/2000 0000", "1/1/2000 1200")).getType(),
                    new Parser().parse("event AAA from 1/1/2000 0000 /to 1/1/2000 12000").getType()
            );
            fail();
        } catch (JudeException je) {
            assertEquals("Poyo, the format of the command event was not valid. "
                    + "The correct format: event", je.getMessage());
        }
    }
}
