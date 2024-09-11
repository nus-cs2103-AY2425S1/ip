package slave;

import java.time.LocalDate;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import slave.task.Deadline;
import slave.task.Event;
import slave.task.RecurringTypeTask;
import slave.task.Task;
import slave.task.Todo;

public class ParserTest {
    @Test
    public void indexOutOfRangeTest() {
        LinkedList<Task> list = new LinkedList<>();
        list.add(new Todo("fly", false));
        list.add(new Deadline("eat", RecurringTypeTask.RecurringType.NEVER, LocalDate.parse("2022-02-02")));
        try {
            list.add(new Event("sleep", RecurringTypeTask.RecurringType.NEVER, LocalDate.parse("2022-02-02"),
                    LocalDate.parse("2022-02-05")));
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
        Assertions.assertEquals(outOfBounds + "-1", parser.markAsDone("-1"));
        Assertions.assertEquals(outOfBounds + "4", parser.markAsDone("4"));
        Assertions.assertEquals(illegalArg, parser.markAsDone("a"));

        String incomplete = "Slacking off now, are you?\n";
        Assertions.assertEquals(incomplete + list.get(0).setAsIncomplete(), parser.markAsIncomplete("1"));
        Assertions.assertEquals(outOfBounds + "-1", parser.markAsIncomplete("-1"));
        Assertions.assertEquals(outOfBounds + "4", parser.markAsIncomplete("4"));
        Assertions.assertEquals(illegalArg, parser.markAsIncomplete("a"));

        String[] deleted = {"Good to know that I have less things to remember now...", "I'll forget about "
                + list.get(0)};
        String[] actual = parser.deleteTask("1");
        Assertions.assertEquals(deleted[0], actual[0]);
        Assertions.assertEquals(deleted[1], actual[1]);
        Assertions.assertEquals(outOfBounds + "-1", parser.deleteTask("-1")[0]);
        Assertions.assertEquals(outOfBounds + "4", parser.deleteTask("4")[0]);
        Assertions.assertEquals(illegalArg, parser.deleteTask("a")[0]);

    }

    @Test
    public void addWrongInputFormatToListTest() {
        LinkedList<Task> list = new LinkedList<>();
        list.add(new Todo("fly", false));
        list.add(new Deadline("eat", RecurringTypeTask.RecurringType.NEVER, LocalDate.parse("2022-02-02")));
        try {
            list.add(new Event("sleep", RecurringTypeTask.RecurringType.NEVER, LocalDate.parse("2022-02-02"), LocalDate.parse("2022-02-05")));
        } catch (InvalidChronologicalOrderException icoe) {
            // do nothing
            // event will not be added
        }
        Parser parser = new Parser(list);
        String missingDetails = "Can you not even tell me all the details for your event? Do you even want my help?";
        String wrongFormat = "Give me the date in yyyy-mm-dd or I won't remember it for you";
        String invalidChronologicalOrder = "How can your event end before it started?";
        String added = "added: ";


        // try to add Todos -0
        String[] expected = new String[2];
        expected[0] = "Hey maybe try using some of that memory of yours to remember these things...";
        expected[1] = added + new Todo("todo", false);
        String[] result = parser.addToList(Parser.TaskType.TODO, "todo");
        Assertions.assertEquals(expected[0], result[0]);
        Assertions.assertEquals(expected[1], result[1]);

        // should not accept empty string
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.TODO, "")[0]);
        // should not accept a string containing only spaces
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.TODO, "      ")[0]);

        // try to add Deadlines -1
        expected[1] = added + new Deadline("ff", RecurringTypeTask.RecurringType.NEVER, LocalDate.parse("2022-02-02"));
        result = parser.addToList(Parser.TaskType.DEADLINE, "ff /by 2022-02-02");
        Assertions.assertEquals(expected[0], result[0]);
        Assertions.assertEquals(expected[1], result[1]);
        // should not accept empty string as descriptor
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "")[0]);
        // should not accept a string containing only spaces
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "          ")[0]);
        // should not accept invalid date format that is not yyyy-mm-dd
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 2 Aug 2024")[0]);
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 2 August 2024")[0]);
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 02/02/2024")[0]);
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 2nd August 2024")[0]);
        Assertions.assertEquals(wrongFormat, parser.addToList(Parser.TaskType.DEADLINE, "ff /by 02-02-2024")[0]);
        // should not accept inputs with missing "/by" keyword
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "ff 2002-02-02")[0]);
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "ff /on 02-02-2024")[0]);
        // should not accept inputs with "/by" but without a space before and after it
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.DEADLINE, "ff/by02-02-2024")[0]);

        // try to add Events -2
        try {
            expected[1] = added + new Event("ff", RecurringTypeTask.RecurringType.NEVER, LocalDate.parse("2022-02-02"),
                    LocalDate.parse("2022-02-04"));
        } catch (InvalidChronologicalOrderException e) {
            // skip the test case in the event of an error;
        }
        result = parser.addToList(Parser.TaskType.EVENT, "ff /from 2022-02-02 /to 2022-02-04");
        Assertions.assertEquals(expected[0], result[0]);
        Assertions.assertEquals(expected[1], result[1]);
        // should not accept empty string as descriptor
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.EVENT, "")[0]);
        // should not accept a string containing only spaces
        Assertions.assertEquals(missingDetails, parser.addToList(Parser.TaskType.EVENT, "          ")[0]);
        // should not accept events that start after it has ended
        Assertions.assertEquals(invalidChronologicalOrder,
                parser.addToList(Parser.TaskType.EVENT, "ff /from 2022-02-02 /to 2022-01-01")[0]);
        // should not accept incomplete event descriptions
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff /from 2022-02-02")[0]);
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff /to 2022-02-02")[0]);
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff")[0]);
        // should not accept inputs with "/from" and "/to" but without a space before and after it
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff/from2022-02-02 /to 2022-02-04")[0]);
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff /from 2022-02-02/to2022-02-04")[0]);
        Assertions.assertEquals(missingDetails,
                parser.addToList(Parser.TaskType.EVENT, "ff/from2022-02-02/to2022-02-04")[0]);

    }
}
