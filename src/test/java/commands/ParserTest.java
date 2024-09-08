package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

public class ParserTest {

    @Test
    public void test_parse_create() {
        Task t1 = new Deadline("write essay", "2023/03/23 23:59");
        TaskList allTask = new TaskList();
        try {
            Parser.parse("deadline write essay /by 2023/03/23 23:59", allTask);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Task> list = new ArrayList<>();
        list.add(t1);
        assertEquals(list.toString(), allTask.toString());
    }

    @Test
    public void test_parse_delete() {
        Task t1 = new Deadline("write essay", "2023/03/23 23:59");
        Task t2 = new Event("read book", "2pm Sunday", "2pm Monday");
        TaskList allTask = new TaskList();
        try {
            Parser.parse("deadline write essay /by 2023/03/23 23:59", allTask);
            Parser.parse("event read book /from 2pm Sunday /to 2pm Monday", allTask);
            Parser.parse("delete 1", allTask);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Task> list = new ArrayList<>();
        list.add(t2);
        assertEquals(list.toString(), allTask.toString());
    }

}
