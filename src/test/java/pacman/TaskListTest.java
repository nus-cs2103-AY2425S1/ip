package pacman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void test() {
        String opening = "Here are the tasks in your list:\n";
        String firstTask = "1. [T][ ] Test Todo\n";
        String secondTask = "2. [D][ ] Test Deadline (by: Jul 29 2024)\n";
        String thirdTask = "3. [E][ ] Test Event (from: Jul 29 2024 to: Sep 30 2024)\n";
        TaskList list = new TaskList(new ArrayList<>());
        list.addTask(new Todo("Test Todo"));
        list.addTask(new Deadline("Test Deadline ", "2024-07-29"));
        list.addTask(new Event("Test Event ", "2024-07-29", "2024-09-30"));
        assertEquals(list.getSize(), 3);
        assertEquals(list.toString(), opening + firstTask + secondTask + thirdTask);
        list.toggleTask(0, true);
        firstTask = "1. [T][X] Test Todo\n";
        assertEquals(list.toString(), opening + firstTask + secondTask + thirdTask);
        list.toggleTask(1, true);
        secondTask = "2. [D][X] Test Deadline (by: Jul 29 2024)\n";
        assertEquals(list.toString(), opening + firstTask + secondTask + thirdTask);
        list.toggleTask(2, true);
        thirdTask = "3. [E][X] Test Event (from: Jul 29 2024 to: Sep 30 2024)\n";
        assertEquals(list.toString(), opening + firstTask + secondTask + thirdTask);
        list.deleteTask(1);
        secondTask = "2. [E][X] Test Event (from: Jul 29 2024 to: Sep 30 2024)\n";
        assertEquals(list.toString(), opening + firstTask + secondTask);
    }
}
