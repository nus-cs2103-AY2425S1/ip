package miku;

import miku.task.Task;
import miku.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import miku.parser.CommandMikuParser;
import miku.command.AddCommand;


public class RunTest {

    @Test
    public void dummyTest1() {
        assertEquals(2, 2);
    }

    @Test
    public void parserAddCommandTest() {
        CommandMikuParser pr = new CommandMikuParser();
        assertEquals(pr.parse("todo asd") instanceof AddCommand, true);
    }

    @Test
    public void parserEventCommandTest() {
        CommandMikuParser pr = new CommandMikuParser();
        assertEquals(pr.parse("event test4 /from 2024-08-29T14:30:00 /to 2024-08-29T14:30:11") instanceof AddCommand, true);
    }

    @Test
    public void toDoClassTest() {
        Task todo = new Todo("read a book");
        todo.markDone();
        assertEquals("T | true | read a book | MEDIUM\n", todo.storeValue());
    }

}
