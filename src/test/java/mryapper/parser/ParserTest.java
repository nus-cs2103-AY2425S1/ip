package mryapper.parser;

import mryapper.command.*;
import mryapper.exception.InvalidSyntaxException;

import mryapper.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Include test cases for parsing bye, list, delete, mark, unmark commands.
 * Also has test cases for making to do, deadline and event tasks.
 */
public class ParserTest {
    @Test
    public void testBye() {
        try {
            // successful parsing
            assertInstanceOf(SayGoodbye.class, Parser.parseInput("bye"));
        } catch (Exception e) {
            fail("An exception is shown: " + e);
        }
    }

    @Test
    public void testList() {
        try {
            // successful parsing
            assertInstanceOf(ListTasks.class, Parser.parseInput("list"));
        } catch (Exception e) {
            fail("An exception is shown: " + e);
        }
    }

    @Test
    public void testDelete_successfulParse() {
        // successful parsing
        try {
            assertInstanceOf(DeleteTask.class, Parser.parseInput("delete 2"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }
    }

    @Test
    public void testDelete_invalidSyntax() {
        // No argument provided
        try {
            assertInstanceOf(DeleteTask.class, Parser.parseInput("delete"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You have to give me a valid task number!\n" + DeleteTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Extra space but no argument provided
        try {
            assertInstanceOf(DeleteTask.class, Parser.parseInput("delete "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You have to give me a valid task number!\n" + DeleteTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testDelete_nonIntegerArgument() {
        // Argument provided has non-numerical characters
        try {
            assertInstanceOf(DeleteTask.class, Parser.parseInput("delete 1a"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You have to give me a valid task number!\n" + DeleteTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided is not an integer
        try {
            assertInstanceOf(DeleteTask.class, Parser.parseInput("delete 1.1"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You have to give me a valid task number!\n" + DeleteTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testDelete_integerArgumentNotPositive() {
        try {
            assertInstanceOf(DeleteTask.class, Parser.parseInput("delete 0"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Task number should be a positive integer",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        try {
            assertInstanceOf(DeleteTask.class, Parser.parseInput("delete -2"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Task number should be a positive integer",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testMark_successfulParse() {
        // successful parsing
        try {
            assertInstanceOf(MarkTask.class, Parser.parseInput("mark 2"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }
    }

    @Test
    public void testMark_invalidSyntax() {
        // No argument provided
        try {
            assertInstanceOf(MarkTask.class, Parser.parseInput("mark"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You have to give me a valid task number!\n" + MarkTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Extra space but no argument provided
        try {
            assertInstanceOf(MarkTask.class, Parser.parseInput("mark "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You have to give me a valid task number!\n" + MarkTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testMark_nonIntegerArgument() {
        // Argument provided has non-numerical characters
        try {
            assertInstanceOf(MarkTask.class, Parser.parseInput("mark 1a"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You have to give me a valid task number!\n" + MarkTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided is not an integer
        try {
            assertInstanceOf(MarkTask.class, Parser.parseInput("mark 1.1"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You have to give me a valid task number!\n" + MarkTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testMark_integerArgumentNotPositive() {
        try {
            assertInstanceOf(MarkTask.class, Parser.parseInput("mark 0"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Task number should be a positive integer",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        try {
            assertInstanceOf(MarkTask.class, Parser.parseInput("mark -2"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Task number should be a positive integer",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testUnmark_successfulParse() {
        // successful parsing
        try {
            assertInstanceOf(UnmarkTask.class, Parser.parseInput("unmark 2"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }
    }

    @Test
    public void testUnmark_invalidSyntax() {
        // No argument provided
        try {
            assertInstanceOf(UnmarkTask.class, Parser.parseInput("unmark"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You have to give me a valid task number!\n" + UnmarkTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Extra space but no argument provided
        try {
            assertInstanceOf(UnmarkTask.class, Parser.parseInput("unmark "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You have to give me a valid task number!\n" + UnmarkTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testUnmark_nonIntegerArgument() {
        // Argument provided has non-numerical characters
        try {
            assertInstanceOf(UnmarkTask.class, Parser.parseInput("unmark 1a"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You have to give me a valid task number!\n" + UnmarkTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided is not an integer
        try {
            assertInstanceOf(UnmarkTask.class, Parser.parseInput("unmark 1.1"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You have to give me a valid task number!\n" + UnmarkTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testUnmark_integerArgumentNotPositive() {
        try {
            assertInstanceOf(UnmarkTask.class, Parser.parseInput("unmark 0"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Task number should be a positive integer",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        try {
            assertInstanceOf(UnmarkTask.class, Parser.parseInput("unmark -2"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Task number should be a positive integer",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testTodo_successfulParse() {
        // successful parsing
        try {
            assertInstanceOf(AddTodoTask.class, Parser.parseInput("todo some todo task"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }
    }

    @Test
    public void testTodo_noArgument() {
        // no description provided
        try {
            assertInstanceOf(AddTodoTask.class, Parser.parseInput("todo "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You need to provide the task details!\n" + AddTodoTask.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testDeadline_successfulParse() {
        // successful parsing
        try {
            assertInstanceOf(AddDeadline.class, Parser.parseInput("deadline some task /by Sunday"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }

        try {
            assertInstanceOf(AddDeadline.class, Parser.parseInput(
                    "deadline  command with more whitespace    /by     1st Sep"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }
    }

    @Test
    public void testDeadline_noArguments() {
        // no details provided
        try {
            assertInstanceOf(AddDeadline.class, Parser.parseInput("deadline "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "You need to provide the task details!\n" + AddDeadline.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testDeadline_missingParameters() {
        // missing deadline
        try {
            assertInstanceOf(AddDeadline.class, Parser.parseInput("deadline aa"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You need to provide a deadline!\n" + AddDeadline.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testDeadline_emptyFields() {
        // empty description
        try {
            assertInstanceOf(AddDeadline.class, Parser.parseInput("deadline /by aa"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "Your description cannot be empty!\n" + AddDeadline.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // empty deadline
        try {
            assertInstanceOf(AddDeadline.class, Parser.parseInput("deadline aa /by  "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You need to provide a deadline!\n" + AddDeadline.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testEvent_successfulParse() {
        // successful parsing
        try {
            assertInstanceOf(AddEvent.class,
                    Parser.parseInput("event something /from 1 pm /to 2 pm"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }

        try {
            assertInstanceOf(AddEvent.class,
                    Parser.parseInput("event  more whitespace   /from   a   /to  b"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }
    }

    @Test
    public void testEvent_noArguments() {
        // no arguments
        try {
            assertInstanceOf(AddEvent.class, Parser.parseInput("event  "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You need to provide the task details!\n" + AddEvent.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testEvent_missingParameters() {
        // no /from or /to
        try {
            assertInstanceOf(AddEvent.class, Parser.parseInput("event some event "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You need to provide a start time!\n" + AddEvent.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // no /to
        try {
            assertInstanceOf(AddEvent.class, Parser.parseInput("event a /from b"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You need to provide an end time!\n" + AddEvent.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // no /from
        try {
            assertInstanceOf(AddEvent.class, Parser.parseInput("event a /to b"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You need to provide a start time!\n" + AddEvent.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testEvent_emptyFields() {
        // empty description
        try {
            assertInstanceOf(AddEvent.class, Parser.parseInput("event /from 1 pm /to 2 pm "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "Your description cannot be empty!\n" + AddEvent.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // empty start time
        try {
            assertInstanceOf(AddEvent.class, Parser.parseInput("event event  /from  /to 2 pm "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "Your start time cannot be empty!\n" + AddEvent.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // empty end time
        try {
            assertInstanceOf(AddEvent.class, Parser.parseInput("event event /from 1 pm /to  "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "You need to provide an end time!\n" + AddEvent.SYNTAX,
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testUnknownCommand() {
        try {
            assertInstanceOf(Command.class, Parser.parseInput("something"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                    + "Sorry, I'm not sure what you're trying to do :(\n"
                    + "Try reading up the user guide",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        try {
            assertInstanceOf(Command.class, Parser.parseInput("LIST"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals("Your syntax is incorrect!\n"
                            + "Sorry, I'm not sure what you're trying to do :(\n"
                            + "Try reading up the user guide",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }
}
