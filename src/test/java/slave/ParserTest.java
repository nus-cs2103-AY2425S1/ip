package slave;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;

public class ParserTest {
    @Test
    public void indexOutOfRangeTest() {
        LinkedList<Task> list = new LinkedList<>();
        list.add(new Todo("fly"));
        list.add(new Deadline("eat", LocalDate.parse("2022-02-02")));
        try {
            list.add(new Event("sleep", LocalDate.parse("2022-02-02"), LocalDate.parse("2022-02-05")));
        } catch (InvalidChronologicalOrderException icoe) {
            // do nothing
            // event will not be added
        }
        Parser parser = new Parser(list);

        //methods should not accept negative numbers, numbers out of range, and non-numbers
        Assertions.assertTrue(parser.markAsDone("1"));
        Assertions.assertFalse(parser.markAsDone("-1"));
        Assertions.assertFalse(parser.markAsDone("4"));
        Assertions.assertFalse(parser.markAsDone("a"));

        Assertions.assertTrue(parser.markAsIncomplete("1"));
        Assertions.assertFalse(parser.markAsIncomplete("-1"));
        Assertions.assertFalse(parser.markAsIncomplete("4"));
        Assertions.assertFalse(parser.markAsIncomplete("a"));

        Assertions.assertTrue(parser.deleteTask("1"));
        Assertions.assertFalse(parser.deleteTask("-1"));
        Assertions.assertFalse(parser.deleteTask("4"));
        Assertions.assertFalse(parser.deleteTask("a"));

    }

    @Test
    public void WrongInputFormatAddToListTest() {
        LinkedList<Task> list = new LinkedList<>();
        list.add(new Todo("fly"));
        list.add(new Deadline("eat", LocalDate.parse("2022-02-02")));
        try {
            list.add(new Event("sleep", LocalDate.parse("2022-02-02"), LocalDate.parse("2022-02-05")));
        } catch (InvalidChronologicalOrderException icoe) {
            // do nothing
            // event will not be added
        }
        Parser parser = new Parser(list);

        // try to add Todos -0
        Assertions.assertTrue(parser.addToList(0, "todo"));
        // should not accept empty string
        Assertions.assertFalse(parser.addToList(0, ""));
        // should not accept a string containing only spaces
        Assertions.assertFalse(parser.addToList(0, "      "));

        // try to add Deadlines -1
        Assertions.assertTrue(parser.addToList(1, "ff /by 2022-02-02"));
        // should not accept empty string as descriptor
        Assertions.assertFalse(parser.addToList(1, ""));
        // should not accept a string containing only spaces
        Assertions.assertFalse(parser.addToList(1, "          "));
        // should not accept invalid date format that is not yyyy-mm-dd
        Assertions.assertFalse(parser.addToList(1, "ff /by 2 Aug 2024"));
        Assertions.assertFalse(parser.addToList(1, "ff /by 2 August 2024"));
        Assertions.assertFalse(parser.addToList(1, "ff /by 02/02/2024"));
        Assertions.assertFalse(parser.addToList(1, "ff /by 2nd August 2024"));
        Assertions.assertFalse(parser.addToList(1, "ff /by 02-02-2024"));
        // should not accept inputs with missing "/by" keyword
        Assertions.assertFalse(parser.addToList(1, "ff 2002-02-02"));
        Assertions.assertFalse(parser.addToList(1, "ff /on 02-02-2024"));
        // should not accept inputs with "/by" but without a space before and after it
        Assertions.assertFalse(parser.addToList(1, "ff/by02-02-2024"));

        // try to add Events -2
        Assertions.assertTrue(parser.addToList(2, "ff /from 2022-02-02 /to 2022-02-04"));
        // should not accept empty string as descriptor
        Assertions.assertFalse(parser.addToList(2, ""));
        // should not accept a string containing only spaces
        Assertions.assertFalse(parser.addToList(2, "          "));
        // should not accept events that start after it has ended
        Assertions.assertFalse(parser.addToList(2, "ff /from 2022-02-02 /to 2022-01-01"));
        // should not accept incomplete event descriptions
        Assertions.assertFalse(parser.addToList(2, "ff /from 2022-02-02"));
        Assertions.assertFalse(parser.addToList(2, "ff /to 2022-02-02"));
        Assertions.assertFalse(parser.addToList(2, "ff"));
        // should not accept inputs with "/from" and "/to" but without a space before and after it
        Assertions.assertFalse(parser.addToList(2, "ff/from2022-02-02 /to 2022-02-04"));
        Assertions.assertFalse(parser.addToList(2, "ff /from 2022-02-02/to2022-02-04"));
        Assertions.assertFalse(parser.addToList(2, "ff/from2022-02-02/to2022-02-04"));

    }
}
