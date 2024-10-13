package echobot.command;

import echobot.exception.EchoBotException;
import echobot.exception.InvalidDeadlineFormatException;
import echobot.exception.TaskNameEmptyException;
import echobot.exception.TaskNotFoundException;
import echobot.exception.UnknownCommandException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandParserTest {
    private static CommandParser commandParser;

    @BeforeAll
    public static void init() {
        CommandParserTest.commandParser = new CommandParser();
    }

    @Nested
    class ListAllCommandParserTest {
        @Test
        @DisplayName("Test 'list' command")
        public void testListAllCommandParser1() {
            try {
                Command command = commandParser.parse("list");
                assertInstanceOf(ListAllCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'list' command with trimming spaces")
        public void testListAllCommandParser2() {
            try {
                Command command = commandParser.parse("list  ");
                assertInstanceOf(ListAllCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }
    }

    @Nested
    class ListByDateCommandParserTest {
        @Test
        @DisplayName("Test 'list /on [date]' command parser")
        public void testListByDateCommandParser1() {
            try {
                Command command = commandParser.parse("list /on 13-11-2022");
                assertInstanceOf(ListByDateCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'list /on [date]' command parser with invalid date format")
        public void testListByDateCommandParser2() {
            try {
                commandParser.parse("list /on 2020-12-07");
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(InvalidDeadlineFormatException.class, e),
                        () -> assertEquals("Invalid deadline format! Please follow dd-MM-yyyy!", e.getMessage()));
            }
        }
    }


    @Nested
    class AddCommandParserTest {
        @Test
        @DisplayName("Test 'add todo [description]' command parser")
        public void testAddToDoCommandParser1() {
            try {
                Command command = commandParser.parse("add todo complete quiz 1");
                assertInstanceOf(AddCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'add todo [description]' command parser with missing description")
        public void testAddToDoCommandParser2() {
            try {
                commandParser.parse("add todo ");
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(TaskNameEmptyException.class, e), () -> assertEquals("OOPS!!! The task name could not be left empty!!!", e.getMessage()));
            }
        }

        @Test
        @DisplayName("Test 'add deadline [description] /by [datetime]' command parser")
        public void testAddDeadlineCommandParser1() {
            try {
                Command command = commandParser.parse("add deadline complete quiz 2 /by 03-07-2022 11:00");
                assertInstanceOf(AddCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'add deadline [description] /from [date]' command parser with invalid date format")
        public void testAddDeadlineCommandParser2() {
            try {
                commandParser.parse("add deadline complete quiz 2 /by 2022-07-03");
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(InvalidDeadlineFormatException.class, e), () -> assertEquals("Invalid deadline format! Please follow dd-MM-yyyy HH:ss!", e.getMessage()));
            }
        }

        @Test
        @DisplayName("Test 'add deadline [description] /from [date]' command parser with missing deadline")
        public void testAddDeadlineCommandParser3() {
            try {
                commandParser.parse("add deadline complete quiz 2");
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(InvalidDeadlineFormatException.class, e), () -> assertEquals("Invalid deadline format! Please follow dd-MM-yyyy HH:ss!", e.getMessage()));
            }
        }

        @Test
        @DisplayName("Test 'add event [description] /from [date] /to [date]' command parser")
        public void testAddEventCommandParser1() {
            try {
                Command command = commandParser.parse("add event complete quiz 2 /from 12-11-2020 11:00 /to 12-11-2020 12:00");
                assertInstanceOf(AddCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'add event [description] /from [date] /to [date]' command parser with invalid datetime format")
        public void testAddEventCommandParser2() {
            try {
                commandParser.parse("add event complete quiz 2 /from 12-11-2020 11:00 /to 12-11-2020 2:00");
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(InvalidDeadlineFormatException.class, e), () -> assertEquals("Invalid deadline format! Please follow dd-MM-yyyy HH:ss!", e.getMessage()));
            }
        }

        @Test
        @DisplayName("Test 'add event [description] /from [date] /to [date]' command parser with missing deadline")
        public void testAddEventCommandParser3() {
            try {
                commandParser.parse("add event complete quiz 2 /from 12-11-2020 11:00");
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(InvalidDeadlineFormatException.class, e), () -> assertEquals("Invalid deadline format! Please follow dd-MM-yyyy HH:ss!", e.getMessage()));
            }
        }
    }

    @Nested
    class DeleteCommandParserTest {
        @Test
        @DisplayName("Test 'delete [index] command parser")
        public void testDeleteCommandParser1() {
            try {
                Command command = commandParser.parse("delete 1");
                assertInstanceOf(DeleteCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'delete [index] command parser with non-integer index")
        public void testDeleteCommandParser2() {
            try {
                commandParser.parse("delete task1");
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(TaskNotFoundException.class, e),
                        () -> assertEquals("Cannot find this task in the list!", e.getMessage())
                );
            }
        }
    }

    @Nested
    class MarkCommandParserTest {
        @Test
        @DisplayName("Test 'mark [index] command parser")
        public void testMarkCommandParser1() {
            try {
                Command command = commandParser.parse("mark 1");
                assertInstanceOf(MarkCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'mark [index] command parser with non-integer index")
        public void testDeleteCommandParser2() {
            try {
                commandParser.parse("mark task1");
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(TaskNotFoundException.class, e),
                        () -> assertEquals("Cannot find this task in the list!", e.getMessage())
                );
            }
        }
    }

    @Nested
    class UnMarkCommandParserTest {
        @Test
        @DisplayName("Test 'unmark [index] command parser")
        public void testUnMarkCommandParser1() {
            try {
                Command command = commandParser.parse("unmark 1");
                assertInstanceOf(UnMarkCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'unmark [index] command parser with non-integer index")
        public void testUnMarkCommandParser2() {
            try {
                commandParser.parse("unmark task1");
                fail();
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(TaskNotFoundException.class, e),
                        () -> assertEquals("Cannot find this task in the list!", e.getMessage())
                );
            }
        }
    }

    @Nested
    class ExitCommandParserTest {
        @Test
        @DisplayName("Test 'bye' command parser")
        public void testExitParser1() {
            try {
                Command command = commandParser.parse("bye");
                assertInstanceOf(ExitCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }

        @Test
        @DisplayName("Test 'bye' command parser with trimming spaces")
        public void testExitParser2() {
            try {
                Command command = commandParser.parse("bye  ");
                assertInstanceOf(ExitCommand.class, command);
            } catch (EchoBotException ignored) {
                fail();
            }
        }
    }

    @Nested
    class UnknownCommandParserTest {
        @Test
        @DisplayName("Test unknown command parser")
        public void testUnknownCommandParser1() {
            try {
                commandParser.parse("display");
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(UnknownCommandException.class, e),
                        () -> assertEquals("OOPS!!! I don't know what \"display\" means...", e.getMessage())
                );
            }
        }

        @Test
        @DisplayName("Test unknown command parser with add command")
        public void testUnknownCommandParser2() {
            try {
                commandParser.parse("add reminder quiz2");
            } catch (EchoBotException e) {
                assertAll(() -> assertInstanceOf(UnknownCommandException.class, e),
                        () -> assertEquals("OOPS!!! I don't know what this command means...", e.getMessage())
                );
            }
        }
    }

}
