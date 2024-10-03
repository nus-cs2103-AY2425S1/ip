package screwllum.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import screwllum.exception.InvalidCommandException;
import screwllum.exception.InvalidDateFormatException;

public class ParserTest {
    @Test
    public void parseUserInput_bye_success() throws InvalidCommandException, InvalidDateFormatException {
        assertEquals(List.of("bye"), Parser.parseUserInput("bye"));
        assertEquals(List.of("bye"), Parser.parseUserInput("bYe _igN_ORED/stuff/that*follows'!"));
    }

    @Test
    public void parseUserInput_list_success() throws InvalidCommandException, InvalidDateFormatException {
        assertEquals(List.of("list"), Parser.parseUserInput("list"));
        assertEquals(List.of("list"), Parser.parseUserInput("liSt _ r a n dom/ignoredConteNt'!"));
    }

    @Test
    public void parseUserInput_delete_success() throws InvalidCommandException, InvalidDateFormatException {
        assertEquals(List.of("delete", "2"), Parser.parseUserInput("delete 2"));
        assertEquals(List.of("delete", "5"), Parser.parseUserInput("dEleTe 5 /IgnOred r a ndom stuff!"));
    }

    @Test
    public void parseUserInput_toggle_success() throws InvalidCommandException, InvalidDateFormatException {
        assertEquals(List.of("toggle", "7"), Parser.parseUserInput("toggle 7"));
        assertEquals(List.of("toggle", "1"), Parser.parseUserInput("toGglE 1/igN0r3DStuff@3"));
    }

    @Test
    public void parseUserInput_todo_success() throws InvalidCommandException, InvalidDateFormatException {
        assertEquals(List.of("todo", "someTask"), Parser.parseUserInput("todo someTask"));
        assertEquals(List.of("todo", "someTask"), Parser.parseUserInput("toDo   someTask  /this is ignored"));
    }

    @Test
    public void parseUserInput_find_success() throws InvalidCommandException, InvalidDateFormatException {
        assertEquals(List.of("find", "description"), Parser.parseUserInput("find description"));
        assertEquals(List.of("find", "desc"), Parser.parseUserInput("fINd  desc/ignoredinput?!4"));
    }

    @Test
    public void parseUserInput_archive_success() throws InvalidCommandException, InvalidDateFormatException {
        assertEquals(List.of("archive"), Parser.parseUserInput("archive"));
        assertEquals(List.of("archive"), Parser.parseUserInput("ArCHiVe  IGNORED/RANDOMST_UFF"));
    }

    @Test
    public void parseUserInput_deadline_success() throws InvalidCommandException, InvalidDateFormatException {
        String[] validDates = new String[]{"2022-01-05", "2011-06-6", "2000-7-13", "2003-4-3"};
        for (String validDate : validDates) {
            assertEquals(List.of("deadline", "name", validDate),
                    Parser.parseUserInput("deadline  name /by    " + validDate + " /Should be ignored"));
        }
    }

    @Test
    public void parseUserInput_event_success() throws InvalidCommandException, InvalidDateFormatException {
        String[] validDates = new String[]{"2022-01-05", "2011-06-6", "2000-7-13", "2003-4-3"};
        for (String validDate : validDates) {
            assertEquals(List.of("event", "name", validDate, validDate),
                    Parser.parseUserInput("event  name /from    " + validDate + " /to " + validDate));
        }
    }
}
