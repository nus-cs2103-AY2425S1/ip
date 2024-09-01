package mryapper.parser;

import mryapper.command.Command;
import mryapper.exception.IllegalTaskException;
import mryapper.exception.InvalidSyntaxException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testBye() {
        try {
            // successful parsing
            assertInstanceOf(Command.class, Parser.parse("bye"));
        } catch (Exception e) {
            fail("An exception is shown: " + e);
        }
    }

    @Test
    public void testList() {
        try {
            // successful parsing
            assertInstanceOf(Command.class, Parser.parse("list"));
        } catch (Exception e) {
            fail("An exception is shown: " + e);
        }
    }

    @Test
    public void testDelete() {
        // successful parsing
        try {
            assertInstanceOf(Command.class, Parser.parse("delete 2"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }

        // No argument provided
        try {
            assertInstanceOf(Command.class, Parser.parse("delete"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals(" Your syntax is incorrect!\n"
                    + " You have to give me a valid task number!\n e.g. delete 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Extra space but wrong syntax
        try {
            assertInstanceOf(Command.class, Parser.parse("delete "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals(" Your syntax is incorrect!\n"
                    + " You have to give me a valid task number!\n e.g. delete 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided has non-numerical characters
        try {
            assertInstanceOf(Command.class, Parser.parse("delete 1a"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(" You have to give me a valid task number!\n e.g. delete 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided is not an integer
        try {
            assertInstanceOf(Command.class, Parser.parse("delete 1.1"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(" You have to give me a valid task number!\n e.g. delete 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testMark() {
        // successful parsing
        try {
            assertInstanceOf(Command.class, Parser.parse("mark 2"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }

        // No argument provided
        try {
            assertInstanceOf(Command.class, Parser.parse("mark"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals(" Your syntax is incorrect!\n"
                    + " You have to give me a valid task number!\n e.g. mark 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Extra space but wrong syntax
        try {
            assertInstanceOf(Command.class, Parser.parse("mark "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals(" Your syntax is incorrect!\n"
                    + " You have to give me a valid task number!\n e.g. mark 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided has non-numerical characters
        try {
            assertInstanceOf(Command.class, Parser.parse("mark 2x"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(" You have to give me a valid task number!\n e.g. mark 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided is not an integer
        try {
            assertInstanceOf(Command.class, Parser.parse("mark 1.1"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(" You have to give me a valid task number!\n e.g. mark 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testUnmark() {
        // successful parsing
        try {
            assertInstanceOf(Command.class, Parser.parse("unmark 2"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }

        // no number provided
        try {
            assertInstanceOf(Command.class, Parser.parse("unmark"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals(" Your syntax is incorrect!\n"
                    + " You have to give me a valid task number!\n e.g. unmark 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Extra space but wrong syntax
        try {
            assertInstanceOf(Command.class, Parser.parse("unmark "));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals(" Your syntax is incorrect!\n"
                    + " You have to give me a valid task number!\n e.g. unmark 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided has non-numerical characters
        try {
            assertInstanceOf(Command.class, Parser.parse("unmark 2x"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(" You have to give me a valid task number!\n e.g. unmark 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // Argument provided is not an integer
        try {
            assertInstanceOf(Command.class, Parser.parse("unmark 1.1"));
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(" You have to give me a valid task number!\n e.g. unmark 2",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testTodo() {
        // successful parsing
        try {
            assertInstanceOf(Command.class, Parser.parse("todo some todo task"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }

        // no description provided
        try {
            assertInstanceOf(Command.class, Parser.parse("todo "));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" You need to provide the task details!\n"
                    + " To add a todo task, I need the task description\n"
                    + " e.g. todo read CS2103T notes",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testDeadline() {
        // successful parsing
        try {
            assertInstanceOf(Command.class, Parser.parse("deadline some task /by Sunday"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }

        // no details provided
        try {
            assertInstanceOf(Command.class, Parser.parse("deadline "));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" You need to provide the task details!\n"
                    + " I need one description and deadline using \"/by\"\n"
                    + " e.g. deadline CS2103T project /by Dec 31st",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // no parameters provided
        try {
            assertInstanceOf(Command.class, Parser.parse("deadline /by  "));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" I'll need you to format your details properly\n"
                    + " I need one description and deadline using \"/by\"\n"
                    + " e.g. deadline CS2103T project /by Dec 31st",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // no description provided
        try {
            assertInstanceOf(Command.class, Parser.parse("deadline /by june"));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" Your description cannot be empty!\n"
                    + " I need one description and deadline using \"/by\"\n"
                    + " e.g. deadline CS2103T project /by Dec 31st",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // no deadline provided
        try {
            assertInstanceOf(Command.class, Parser.parse("deadline some task /by "));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" I'll need you to format your details properly\n"
                    + " I need one description and deadline using \"/by\"\n"
                    + " e.g. deadline CS2103T project /by Dec 31st",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testEvent() {
        // successful parsing
        try {
            assertInstanceOf(Command.class,
                    Parser.parse("event something /from 1 pm /to 2 pm"));
        } catch (Exception e) {
            fail("An exception has been thrown instead");
        }

        // no details provided
        try {
            assertInstanceOf(Command.class,
                    Parser.parse("event "));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" You need to provide the task details!\n"
                    + " I need one description, start and end time using \"/from\" and \"/to\"\n"
                    + " e.g. event project meeting /from Mon 2pm /to 4pm",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // invalid syntax
        try {
            assertInstanceOf(Command.class,
                    Parser.parse("event something /from 3 pm"));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" I'll need you to format your details properly\n"
                            + " I need one description, start and end time using \"/from\" and \"/to\"\n"
                            + " e.g. event project meeting /from Mon 2pm /to 4pm",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // invalid syntax
        try {
            assertInstanceOf(Command.class,
                    Parser.parse("event something /to 5 pm"));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" I'll need you to format your details properly\n"
                            + " I need one description, start and end time using \"/from\" and \"/to\"\n"
                            + " e.g. event project meeting /from Mon 2pm /to 4pm",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // invalid syntax
        try {
            assertInstanceOf(Command.class,
                    Parser.parse("event something /to 5 pm /from 3 pm"));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" I'll need you to format your details properly\n"
                            + " I need one description, start and end time using \"/from\" and \"/to\"\n"
                            + " e.g. event project meeting /from Mon 2pm /to 4pm",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // no description provided
        try {
            assertInstanceOf(Command.class, Parser.parse("event /from 3 pm /to 5 pm"));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" Your description cannot be empty!\n"
                    + " I need one description, start and end time using \"/from\" and \"/to\"\n"
                    + " e.g. event project meeting /from Mon 2pm /to 4pm",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // no start time provided
        try {
            assertInstanceOf(Command.class, Parser.parse("event something /from /to 5 pm"));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" Your start time cannot be empty!\n"
                            + " I need one description, start and end time using \"/from\" and \"/to\"\n"
                            + " e.g. event project meeting /from Mon 2pm /to 4pm",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        // no start time provided
        try {
            assertInstanceOf(Command.class, Parser.parse("event something /from 3 pm /to  "));
            fail();
        } catch (IllegalTaskException e) {
            assertEquals(" I'll need you to format your details properly\n"
                            + " I need one description, start and end time using \"/from\" and \"/to\"\n"
                            + " e.g. event project meeting /from Mon 2pm /to 4pm",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }

    @Test
    public void testUnknownCommand() {
        try {
            assertInstanceOf(Command.class, Parser.parse("something"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals(" Your syntax is incorrect!\n"
                            + " Hmm... I'm not sure what you're trying to do :(",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }

        try {
            assertInstanceOf(Command.class, Parser.parse("LIST"));
            fail();
        } catch (InvalidSyntaxException e) {
            assertEquals(" Your syntax is incorrect!\n"
                            + " Hmm... I'm not sure what you're trying to do :(",
                    e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception has been thrown: " + e);
        }
    }
}
