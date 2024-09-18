import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import kotori.command.AddCommand;
import kotori.command.Command;
import kotori.command.DeleteCommand;
import kotori.command.ExitCommand;
import kotori.command.FindCommand;
import kotori.command.GreetCommand;
import kotori.command.MarkCommand;
import kotori.command.PrintListCommand;
import kotori.command.SearchCommand;
import kotori.command.SortCommand;
import kotori.command.UnmarkCommand;
import kotori.parser.Parser;
import kotori.storage.Storage;
import kotori.tasklist.Task;
import kotori.tasklist.TaskList;



public class KotoriTest {

    @Test
    public void greetTest() {
        assertEquals("Hello! I'm Kotori.\nWhat can I do for you?\n", new GreetCommand().execute());
    }

    @Test
    public void exitTest() {
        TaskList list = new TaskList();
        new ExitCommand(list).execute();
        assertEquals("Bye! Hope to see you again soon.", new ExitCommand(list).execute());
    }

    @Test
    public void printListTest() {
        TaskList list = new TaskList();
        try {
            list.add(Task.of("todo me"));
            list.mark(0);
        } catch (Exception e) { }
        assertEquals( "Now you have 1 tasks in list\n"
                + "1. [T][X] me\n", new PrintListCommand(list).execute());
    }

    @Test
    public void addTest() {
        Storage storage = new Storage("testData", "Add.txt");
        TaskList list = storage.load();

        assertEquals(String.format("Got it. I've added this task:\n" + "[T][ ] me\n"
                        + "Now you have %s tasks in the list\n", list.size() + 1),
                new AddCommand(storage, list, "todo me").execute());

    }

    @Test
    public void deleteTest() {
        Storage storage = new Storage("testData", "Delete.txt");
        TaskList list = new TaskList();
        try {
            list.add(Task.of("todo me") );
            list.mark(0);
        } catch (Exception e) { }
        assertEquals(String.format("OK~. I've deleted this task:\n"
                        +  "[T][X] me\n"
                        + "Now you have 0 tasks in the list\n", list.size()),
                new DeleteCommand(storage, list, 1).execute());


    }

    @Test
    public void markTest() {
        File file = new File("testData/Mark.txt");
        file.delete();
        Storage storage = new Storage("testData", "Mark.txt");
        TaskList list = new TaskList();
        try {
            list.add(Task.of("todo me") );
        } catch (Exception e) { }
        assertEquals("Nice Job, Job 1 has been marked as done!\n"
                + "    [T][X] me\n", new MarkCommand(storage, list, 1).execute());
    }

    @Test
    public void unmarkTest() {
        File file = new File("testData/Mark.txt");
        file.delete();
        Storage storage = new Storage("testData", "Unmark.txt");
        TaskList list = new TaskList();
        try {
            list.add(Task.of("todo me") );
            list.mark(0);
        } catch (Exception e) { }

        assertEquals("Alright, Job 1 has been marked as not done!\n"
               + "    [T][ ] me\n", new UnmarkCommand(storage, list, 1).execute());
    }

    @Test
    public void searchTest() {
        TaskList list = new TaskList();
        try {
            list.add(Task.of("deadline something /by 2020-10-11") );
            list.add(Task.of("event project meeting /from 2020-10-08/to 2020-10-26"));
        } catch (Exception e) { }

        assertEquals(
                "These are the tasks related to this date 2020-10-10\n"
                + "1. [D][ ] something  (by: 2020-10-11)\n"
                + "2. [E][ ] project meeting  (from: 2020-10-08 to: 2020-10-26)\n",
                new SearchCommand(list, "2020-10-10").execute());
    }
    @Test
    public void findTest() {
        TaskList list = new TaskList();
        try {
            list.add(Task.of("deadline something /by 2020-10-11") );
            list.add(Task.of("event project meeting for something/from 2020-10-08/to 2020-10-26"));
        } catch (Exception e) { }
        assertEquals("These are(is) the task(s) that match the description\n"
                + "1. [D][ ] something  (by: 2020-10-11)\n"
                + "2. [E][ ] project meeting for something (from: 2020-10-08 to: 2020-10-26)\n",
                new FindCommand(list, "something").execute());
    }

    @Test
    public void loadTest() {
        TaskList expected = new TaskList();
        try {
            expected.add(Task.of("todo me") );
            expected.mark(0);
        } catch (Exception e) { }
        Storage testStorage = new Storage("testData", "Read.txt");
        assertEquals(expected, testStorage.load());
    }

    @Test
    public void saveTest() {
        TaskList list = new TaskList();
        try {
            list.add(Task.of("todo me"));
            list.mark(0);
            Storage testStorage = new Storage("testData", "Load.txt");
            testStorage.updateFile(list);
            Scanner scanner = new Scanner(new File("testData/Load.txt"));
            assertEquals(list.get(0).getStorageMessage(), scanner.nextLine());
        } catch (Exception e) { }
    }

    @Test
    public void parseTest() {
        Parser parser = new Parser(new Storage("testData", "parse.txt"), new TaskList());
        Command command1 = parser.parse("bye");
        assertEquals(true, (command1 instanceof ExitCommand));
        Command command2 = parser.parse("list");
        assertEquals(true, (command2 instanceof PrintListCommand));
        Command command3 = parser.parse("todo something");
        assertEquals(true, (command3 instanceof AddCommand));
        Command command4 = parser.parse("mark 1");
        assertEquals(true,(command4 instanceof MarkCommand));
        Command command5 = parser.parse("unmark 1");
        assertEquals(true, (command5 instanceof UnmarkCommand));
        Command command6 = parser.parse("search 2020-11-11");
        assertEquals(true, (command6 instanceof SearchCommand));
        Command command7 = parser.parse("delete 1");
        assertEquals(true, (command7 instanceof DeleteCommand));
        Command command8 = parser.parse("find book");
        assertEquals(true, (command8 instanceof FindCommand));
        Command command9 = parser.parse("sort");
        assertEquals(true, (command9 instanceof SortCommand));
    }
}
