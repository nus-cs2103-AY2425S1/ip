package dumpling.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dumpling.DumplingException;

public class CommandEnumTest {
    @Test
    public void retrieveCommandEnum_correctCommandEnum_success() {
        String[] commmandEnumStrings = {
            "list",
            "todo",
            "deadline",
            "event",
            "delete",
            "mark",
            "unmark",
            "bye",
            "find",
            "note",
            "Find",
            "fInD"
        };
        CommandEnum[] commandEnumExpected = {
            CommandEnum.LIST,
            CommandEnum.TODO,
            CommandEnum.DEADLINE,
            CommandEnum.EVENT,
            CommandEnum.DELETE,
            CommandEnum.MARK,
            CommandEnum.UNMARK,
            CommandEnum.BYE,
            CommandEnum.FIND,
            CommandEnum.NOTE,
            CommandEnum.FIND,
            CommandEnum.FIND
        };
        int testExamples = commmandEnumStrings.length;
        assert commmandEnumStrings.length == commandEnumExpected.length : "Not the same number of test cases";
        for (int i = 0; i < testExamples; i++) {
            assertEquals(
                    commandEnumExpected[i],
                    CommandEnum.retrieveCommandEnum(commmandEnumStrings[i])
            );
        }
    }

    @Test
    public void retrieveCommandEnum_wrongCommandEnum_exceptionThrown() {
        String[] commmandEnumStrings = {
            "",
            "   find",
            "find   ",
            "finds"
        };
        int testExamples = commmandEnumStrings.length;
        for (int i = 0; i < testExamples; i++) {
            String expectedOutput = String.format(
                    "    Grrr, %s is not a valid command for Dumpling to eat!\n"
                            + "     To list items, use 'list'.\n"
                            + "     To mark or unmark an item as done, use '<mark/unmark> <item index>'.\n"
                            + "     To add a new item, use '<todo/deadline/event> <task name> <args>'.",
                    commmandEnumStrings[i]);
            try {
                CommandEnum.retrieveCommandEnum(commmandEnumStrings[i]);
                fail();
            } catch (DumplingException e) {
                assertEquals(
                        expectedOutput,
                        e.getMessage()
                );
            }
        }
    }
}
