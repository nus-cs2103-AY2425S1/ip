package bmo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseValidListTest() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "list";
            assertArrayEquals(expected, parser.parse("list"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseValidMarkTest() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "mark";
            expected[1] = "1";
            assertArrayEquals(expected, parser.parse("mark 1"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseValidUnmarkTest() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "unmark";
            expected[1] = "1";
            assertArrayEquals(expected, parser.parse("unmark 1"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseValidTodoTest() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "todo";
            expected[1] = "Watch 2100 Lecture";
            assertArrayEquals(expected, parser.parse("todo Watch 2100 Lecture"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseValidDeadlineTest() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "deadline";
            expected[1] = "Submit Week 3 iP Tasks";
            expected[2] = "30/08/2024";
            assertArrayEquals(expected, parser.parse("deadline Submit Week 3 iP Tasks /by 30/08/2024"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseValidEventTest() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "event";
            expected[1] = "Public Garden";
            expected[2] = "28/09/2024";
            expected[3] = "29/09/2024";
            assertArrayEquals(expected, parser.parse("event Public Garden /from 28/09/2024 /to 29/09/2024"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseValidDelete() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "delete";
            expected[1] = "1";
            assertArrayEquals(expected, parser.parse("delete 1"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseValidByeTest() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "bye";
            assertArrayEquals(expected, parser.parse("bye"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseInvalidMarkTest() {
        Parser parser = new Parser();
        assertThrows(BmoException.class, () -> {
            parser.parse("mark");
        });
    }

    @Test
    public void parseInvalidUnmarkTest() {
        Parser parser = new Parser();
        assertThrows(BmoException.class, () -> {
            parser.parse("unmark");
        });

    }

    @Test
    public void parseInvalidTodoTest() {
        Parser parser = new Parser();
        assertThrows(BmoException.class, () -> {
            parser.parse("todo");
        });
    }

    @Test
    public void parseInvalidDeadlineTest() {
        Parser parser = new Parser();
        assertThrows(BmoException.class, () -> {
            parser.parse("deadline");
        });
    }

    @Test
    public void parseInvalidEventTest() {
        Parser parser = new Parser();
        assertThrows(BmoException.class, () -> {
            parser.parse("event");
        });
    }

    @Test
    public void parseInvalidDeleteTest() {
        Parser parser = new Parser();
        assertThrows(BmoException.class, () -> {
            parser.parse("delete");
        });
    }

    @Test
    public void parseInvalidInputTest() {
        try {
            Parser parser = new Parser();
            String[] expected = new String[5];
            expected[0] = "invalid";
            assertArrayEquals(expected, parser.parse("this is not a valid input"));
        } catch (BmoException e) {
            System.out.println(e.getMessage());
        }
    }
}
