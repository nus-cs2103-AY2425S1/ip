package slave;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

        String outOfBounds = "You don't have a task number ";
        String success = "Finally doing something useful with your life eh...\n";
        String illegalArg = "That's not a task number";

        //methods should not accept negative numbers, numbers out of range, and non-numbers
        Assertions.assertEquals(success + list.get(0).setAsCompleted(), parser.markAsDone("1"));
        Assertions.assertEquals(outOfBounds, parser.markAsDone("-1"));
        Assertions.assertEquals(outOfBounds, parser.markAsDone("4"));
        Assertions.assertEquals(illegalArg, parser.markAsDone("a"));

        Assertions.assertEquals(success + list.get(1).setAsCompleted(), parser.markAsIncomplete("1"));
        Assertions.assertEquals(outOfBounds, parser.markAsIncomplete("-1"));
        Assertions.assertEquals(outOfBounds, parser.markAsIncomplete("4"));
        Assertions.assertEquals(illegalArg, parser.markAsIncomplete("a"));

        String deleted = "Good to know that I have less things to remember now...\nI'll forget about ";
        Assertions.assertEquals(deleted + list.get(1), parser.deleteTask("1"));
        Assertions.assertEquals(outOfBounds, parser.deleteTask("-1"));
        Assertions.assertEquals(outOfBounds, parser.deleteTask("4"));
        Assertions.assertEquals(illegalArg, parser.deleteTask("a"));

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
        String missingDetails = "Can you not even tell me all the details for your event? Do you even want my help?";
        String wrongFormat = "Give me the date in yyyy-mm-dd or I won't remember it for you";
        String invalidChronologicalOrder = "How can your event end before it started?";
        String success = "Hey maybe try using some of that memory of yours to remember these things...\nadded: ";


        // try to add Todos -0
        Assertions.assertEquals(success + new Todo("todo"), parser.addToList(Parser.TaskType.TODO, "todo"));
        // should not accept empty string
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.TODO, ""));
        // should not accept a string containing only spaces
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.TODO, "      "));

        // try to add Deadlines -1
        Assertions.assertEquals(success + new Deadline("ff", LocalDate.parse("2022-02-02")),
                parser.addToList(Parser.TaskType.DEADLINE, "ff /by 2022-02-02"));
        // should not accept empty string as descriptor
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, ""));
        // should not accept a string containing only spaces
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "          "));
        // should not accept invalid date format that is not yyyy-mm-dd
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 2 Aug 2024"));
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 2 August 2024"));
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 02/02/2024"));
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 2nd August 2024"));
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 02-02-2024"));
        // should not accept inputs with missing "/by" keyword
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "ff 2002-02-02"));
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "ff /on 02-02-2024"));
        // should not accept inputs with "/by" but without a space before and after it
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "ff/by02-02-2024"));

        // try to add Events -2
        try {
            Assertions.assertEquals(success +
                            new Event("ff", LocalDate.parse("2022-02-02"), LocalDate.parse("2022-02-04")),
                    parser.addToList(Parser.TaskType.EVENT, "ff /from 2022-02-02 /to 2022-02-04"));
        } catch (InvalidChronologicalOrderException e) {
            // do nothing
        }
        // should not accept empty string as descriptor
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.EVENT, ""));
        // should not accept a string containing only spaces
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.EVENT, "          "));
        // should not accept events that start after it has ended
        Assertions.assertEquals(invalidChronologicalOrder,
                parser.addToList(Parser.TaskType.EVENT, "ff /from 2022-02-02 /to 2022-01-01"));
        // should not accept incomplete event descriptions
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff /from 2022-02-02"));
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff /to 2022-02-02"));
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff"));
        // should not accept inputs with "/from" and "/to" but without a space before and after it
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff/from2022-02-02 /to 2022-02-04"));
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff /from 2022-02-02/to2022-02-04"));
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff/from2022-02-02/to2022-02-04"));

    }
}
