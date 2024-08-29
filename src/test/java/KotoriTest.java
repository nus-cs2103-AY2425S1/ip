package kotori;

import kotori.command.*;
import kotori.parser.Parser;
import kotori.storage.Storage;
import kotori.taskList.Task;
import kotori.taskList.TaskList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KotoriTest {

    @Test
    public void greetTest() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new GreetCommand().execute();
        System.setOut(originalOut);
        assertEquals("    ___________________________________________\n" +
                "    Hello! I'm Kotori.\n" +
                "    What can I do for you?\n" +
                "    ___________________________________________\n", outContent.toString());
    }

    @Test
    public void exitTest(){
        TaskList list = new TaskList();
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new ExitCommand(list).execute();
        System.setOut(originalOut);
        assertEquals("    ___________________________________________\n" +
                "    Bye! Hope to see you again soon.\n" +
                "    ___________________________________________\n", outContent.toString());
    }

    @Test
    public void printListTest(){
        TaskList list = new TaskList();
        try {
            list.add(Task.of("todo me"));
            list.mark(0);
        } catch (Exception e) {}
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new PrintListCommand(list).execute();
        System.setOut(originalOut);
        assertEquals( "    ___________________________________________\n" +
                "    1. [T][X] me\n" +
                "    ___________________________________________\n", outContent.toString());
    }

    @Test
    public void addTest() {
        Storage storage = new Storage("testData", "Add.txt");
        TaskList list = storage.load();
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new AddCommand(storage,list,"todo me").execute();
        System.setOut(originalOut);
        assertEquals(String.format("    ___________________________________________\n" +
                "    Got it. I've added this task:\n" +
                "    [T][ ] me\n" +
                "    Now you have %s tasks in the list\n" +
                "    ___________________________________________\n", list.size()), outContent.toString());

    }

    @Test
    public void deleteTest() {
        Storage storage = new Storage("testData", "Delete.txt");
        TaskList list = new TaskList();
        try {
            list.add(Task.of("todo me") );
            list.mark(0);
        } catch (Exception e) {}{}
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new DeleteCommand(storage,list,1).execute();
        System.setOut(originalOut);
        assertEquals(String.format("    ___________________________________________\n" +
                "    OK~. I've deleted this task:\n" +
                "    [T][X] me\n" +
                "    Now you have 0 tasks in the list\n" +
                "    ___________________________________________\n", list.size()), outContent.toString());


    }

    @Test
    public void markTest() {
        File file = new File("testData/Mark.txt");
        file.delete();
        Storage storage = new Storage("testData", "Mark.txt");
        TaskList list = new TaskList();
        try {
            list.add(Task.of("todo me") );
        } catch (Exception e) {}{}
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new MarkCommand(storage,list,1).execute();
        System.setOut(originalOut);
        assertEquals("    ___________________________________________\n" +
                "    Nice Job, Job 1 has been marked as done!\n" +
                "    [T][X] me\n" +
                "    ___________________________________________\n", outContent.toString());
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
        } catch (Exception e) {}{}
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new UnmarkCommand(storage,list,1).execute();
        System.setOut(originalOut);
        assertEquals("    ___________________________________________\n" +
                "    Alright, Job 1 has been marked as not done!\n" +
                "    [T][ ] me\n" +
                "    ___________________________________________\n", outContent.toString());
    }

    @Test
    public void searchTest() {
        TaskList list = new TaskList();
        try {
            list.add(Task.of("deadline something /by 2020-10-11") );
            list.add(Task.of("event project meeting /from 2020-10-08/to 2020-10-26"));
        } catch (Exception e) {}{}
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new SearchCommand(list, "2020-10-10").execute();
        System.setOut(originalOut);
        assertEquals("    ___________________________________________\n" +
                "    These are the tasks related to this date 2020-10-10\n" +
                "    [D][ ] something  (by: 2020-10-11)\n" +
                "    [E][ ] project meeting  (from: 2020-10-08 to: 2020-10-26)\n" +
                "    ___________________________________________\n", outContent.toString());
    }

    @Test
    public void findTest() {
        TaskList list = new TaskList();
        try {
            list.add(Task.of("deadline something /by 2020-10-11") );
            list.add(Task.of("event project meeting for something/from 2020-10-08/to 2020-10-26"));
        } catch (Exception e) {}{}
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new FindCommand(list, "something").execute();
        System.setOut(originalOut);
        assertEquals("    ___________________________________________\n" +
                "    These are(is) the task(s) that match the description\n" +
                "    1. [D][ ] something  (by: 2020-10-11)\n" +
                "    2. [E][ ] project meeting for something (from: 2020-10-08 to: 2020-10-26)\n" +
                "    ___________________________________________\n", outContent.toString());
    }

    @Test
    public void loadTest() {
        TaskList expected = new TaskList();
        try {
            expected.add(Task.of("todo me") );
            expected.mark(0);
        } catch (Exception e) {}{}
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
        } catch (Exception e) {}
    }

    @Test
    public void parseTest() {
        Parser parser = new Parser(new Storage("testData", "parse.txt"), new TaskList());
        Command command1 = parser.parse("bye");
        assertEquals((command1 instanceof ExitCommand),true);
        Command command2 = parser.parse("list");
        assertEquals((command2 instanceof PrintListCommand),true);
        Command command3 = parser.parse("todo something");
        assertEquals((command3 instanceof AddCommand),true);
        Command command4 = parser.parse("mark 1");
        assertEquals((command4 instanceof MarkCommand),true);
        Command command5 = parser.parse("unmark 1");
        assertEquals((command5 instanceof UnmarkCommand),true);
        Command command6 = parser.parse("search 2020-11-11");
        assertEquals((command6 instanceof SearchCommand),true);
        Command command7 = parser.parse("delete 1");
        assertEquals((command7 instanceof DeleteCommand),true);
        Command command8 = parser.parse("find book");
        assertEquals(true, (command8 instanceof FindCommand));
    }
}
