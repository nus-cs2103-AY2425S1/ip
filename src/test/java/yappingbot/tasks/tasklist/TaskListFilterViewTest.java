package yappingbot.tasks.tasklist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.exceptions.YappingBotOobException;
import yappingbot.tasks.Deadline;
import yappingbot.tasks.Event;
import yappingbot.tasks.Task;
import yappingbot.tasks.Todo;

class TaskListFilterViewTest {

    /**
     * Dummy Task Stub for testing TaskList.
     */
    static class DummyTask extends Task {
        private static final String[] testNames = {
                "abc",
                "123",
                "doremi",
                "abc 123",
                "123 doremi"
        };
        private final int id;

        public DummyTask(int id) {
            super(testNames[id % testNames.length], false);
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String getTaskTypeSymbol() {
            return "DUMMY";
        }

        @Override
        public String getTaskDoneCheckmark() {
            return "X";
        }

        @Override
        public String toString() {
            return "DUMMY TASK";
        }

        @Override
        public String serialize() {
            return "DUMMY:VALUE:DUMMY:VALUE";
        }

        @Override
        public void deserialize(String[] stringDataSlices)
        throws YappingBotInvalidSaveFileException {
            if (stringDataSlices[0].equals("THROW")) {
                throw new YappingBotInvalidSaveFileException("THROW!!1!");
            }
            String[] expected = {
                    "DUMMY",
                    "VALUE",
                    "DUMMY",
                    "VALUE"
            };
            assertArrayEquals(expected, stringDataSlices);
        }
    }

    @Test
    void getParent() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));
        t.addTask(new DummyTask(3));
        t.addTask(new DummyTask(4));

        TaskListFilterView f1 = TaskListFilterView.createFilter(t, DummyTask.testNames[0]);
        TaskListFilterView f2 = TaskListFilterView.createFilter(f1, DummyTask.testNames[1]);

        assertEquals(f1, f2.getParent());
        assertEquals(t, ((TaskListFilterView) f2.getParent()).getParent());
        assertEquals(t, f1.getParent());
    }

    @Test
    void getFilterString() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));
        t.addTask(new DummyTask(3));
        t.addTask(new DummyTask(4));

        TaskListFilterView f1 = TaskListFilterView.createFilter(t, DummyTask.testNames[0]);
        TaskListFilterView f2 = TaskListFilterView.createFilter(f1, DummyTask.testNames[1]);

        assertEquals(DummyTask.testNames[0], f1.getFilterString());
        assertEquals(String.format("%s AND %s", DummyTask.testNames[0], DummyTask.testNames[1]),
                     f2.getFilterString());
    }

    @Test
    void createFilter() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));
        t.addTask(new DummyTask(3));
        t.addTask(new DummyTask(4));

        //       0 "abc",
        //       1 "123",
        //       2 "doremi",
        //       3 "abc 123",
        //       4 "123 doremi"


        TaskListFilterView f1 = TaskListFilterView.createFilter(t, DummyTask.testNames[0]);
        assertEquals(t.get(0).toString(), f1.get(0).toString());
        assertEquals(t.get(3).toString(), f1.get(0).toString());
        assertEquals(2, f1.size());

        TaskListFilterView f2 = TaskListFilterView.createFilter(f1, DummyTask.testNames[1]);
        assertEquals(1, f2.size());
        assertEquals(f1.get(1).toString(), f2.get(0).toString());
        assertEquals(t.get(3).toString(), f2.get(0).toString());
    }

    @Test
    void addTask() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));
        t.addTask(new DummyTask(3));
        t.addTask(new DummyTask(4));

        TaskListFilterView f1 = TaskListFilterView.createFilter(t, DummyTask.testNames[0]);
        f1.addTask(new DummyTask(5));
        assertEquals(t.get(5).toString(), f1.get(2).toString());
        assertEquals(3, f1.size());
        assertEquals(6, t.size());

        TaskListFilterView f2 = TaskListFilterView.createFilter(f1, DummyTask.testNames[1]);
        f2.addTask(new DummyTask(6));
        assertEquals(t.get(6).toString(), f2.get(1).toString());
        assertEquals(t.get(6).toString(), f1.get(3).toString());
        assertEquals(f1.get(3).toString(), f2.get(1).toString());
        assertEquals(2, f2.size());
        assertEquals(4, f1.size());
        assertEquals(7, t.size());
    }

    @Test
    void deleteTask() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));
        t.addTask(new DummyTask(3));
        t.addTask(new DummyTask(4));

        TaskListFilterView f1 = TaskListFilterView.createFilter(t, DummyTask.testNames[0]);
        TaskListFilterView f2 = TaskListFilterView.createFilter(f1, DummyTask.testNames[1]);
        f2.deleteTask(0);
        assertEquals(0, f2.size());
        assertEquals(1, f1.size());
        assertEquals(4, t.size());

        f1.deleteTask(0);
        assertEquals(0, f2.size());
        assertEquals(0, f1.size());
        assertEquals(3, t.size());
    }

}