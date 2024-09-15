package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.EchoException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

public class ParserTest {

    @Test
    public void test_parse_create() {
        TaskList allTask = new TaskList();
        ArrayList<Task> list = new ArrayList<>();
        try {
            Task t1 = new Deadline("write essay", "2023/03/23 23:59");
            Parser.parse("deadline write essay /by 2023/03/23 23:59", allTask);
            list.add(t1);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(list.toString(), allTask.toString());
    }

    @Test
    public void test_parse_delete() {
        TaskList allTask = new TaskList();
        ArrayList<Task> list = new ArrayList<>();
        try {

            Task t2 = new Event("read book", "2023/03/02 08:00", "2023/03/02 20:00");
            list.add(t2);
            Parser.parse("deadline write essay /by 2023/03/23 23:59", allTask);
            Parser.parse("event read book /from 2023/03/02 08:00 /to 2023/03/02 20:00", allTask);
            Parser.parse("delete 1", allTask);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(list.toString(), allTask.toString());
    }

    @Test
    public void test_parse_find() {
        TaskList allTask = new TaskList();
        ArrayList<Task> list = new ArrayList<>();
        try {
            Task t1 = new Deadline("write essay", "2023/03/23 23:59");
            Task t2 = new Event("read book", "2023/03/02 08:00", "2023/03/02 20:00");
            list.add(t1);
            list.add(t2);

            Parser.parse("deadline write essay /by 2023/03/23 23:59", allTask);
            Parser.parse("event read book /from 2023/03/02 08:00 /to 2023/03/02 20:00", allTask);

            String actualOutput = Parser.parse("find essay", allTask);
            String expectedOutput = "Here are the matching tasks in your list:\n" + "1. " + t1;

            assertEquals(actualOutput, expectedOutput);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_parse_tag() {
        TaskList allTask = new TaskList();
        ArrayList<Task> list = new ArrayList<>();
        try {
            Task t1 = new Deadline("write essay", "2023/03/23 23:59");
            t1.setTag("#fun");
            list.add(t1);

            Parser.parse("deadline write essay /by 2023/03/23 23:59", allTask);
            Parser.parse("tag 1 #fun", allTask);

            String actualOutput = allTask.toString();
            String expectedOutput = list.toString();

            assertEquals(actualOutput, expectedOutput);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }
    }
}
