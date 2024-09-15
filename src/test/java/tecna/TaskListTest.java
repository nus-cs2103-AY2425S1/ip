package tecna;

import java.util.ArrayList;

import tecna.collection.TaskList;

import tecna.exception.TaskDuplicateException;

import tecna.task.Deadline;
import tecna.task.Event;
import tecna.task.Task;

import tecna.util.DateTimeUtil;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void isDuplicate_allAttributesSame_true() {

        Deadline dl = new Deadline("go to bed", DateTimeUtil.parseDateTime("2024-12-12 1200"));

        Event e = new Event("attend meeting", DateTimeUtil.parseDateTime("2024-11-12 1400"), DateTimeUtil.parseDateTime("2024-11-12 1900"));

        ArrayList<Task> arr = new ArrayList<Task>();

        arr.add(dl);
        arr.add(e);

        TaskList taskListTest = new TaskList(arr);

        assertEquals(true, taskListTest.isDuplicate(e));

    }

    @Test
    public void isDuplicate_differentEndDate_false() {

        Deadline dl = new Deadline("go to bed", DateTimeUtil.parseDateTime("2024-12-12 1200"));

        Event e = new Event("attend meeting", DateTimeUtil.parseDateTime("2024-11-12 1400"), DateTimeUtil.parseDateTime("2024-11-12 1900"));

        Event e1 = new Event("attend meeting", DateTimeUtil.parseDateTime("2024-11-12 1400"), DateTimeUtil.parseDateTime("2024-11-12 2359"));

        ArrayList<Task> arr = new ArrayList<Task>();

        arr.add(dl);
        arr.add(e);

        TaskList taskListTest = new TaskList(arr);

        assertEquals(false, taskListTest.isDuplicate(e1));

    }


    @Test
    public void addItem_nonDuplicate_resultString() {
        Deadline dl = new Deadline("go to bed", DateTimeUtil.parseDateTime("2024-12-12 1200"));

        Event e = new Event("attend meeting", DateTimeUtil.parseDateTime("2024-11-12 1400"), DateTimeUtil.parseDateTime("2024-11-12 1900"));

        Event e1 = new Event("attend meeting", DateTimeUtil.parseDateTime("2024-11-12 1400"), DateTimeUtil.parseDateTime("2024-11-12 2359"));

        ArrayList<Task> arr = new ArrayList<>();

        arr.add(dl);
        arr.add(e);

        TaskList taskListTest = new TaskList(arr);

        ArrayList<Task> expectedArr = new ArrayList<>();
        expectedArr.add(dl);
        expectedArr.add(e);
        expectedArr.add(e1);

        try {
            taskListTest.addItem(e1);
            assertEquals(expectedArr, arr);
        } catch (TaskDuplicateException exception) {
            fail();
        }
    }
}
