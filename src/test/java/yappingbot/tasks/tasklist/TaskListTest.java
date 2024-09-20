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

class TaskListTest {

    /**
     * Dummy Task Stub for testing TaskList.
     */
    static class DummyTask extends Task {
        private final int id;

        @Override
        public boolean equals(Object o) {
            if (o instanceof DummyTask) {
                return ((DummyTask) o).getId() == this.id;
            }
            return false;
        }

        public DummyTask(int id) {
            super();
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
            String[] expected = {"DUMMY", "VALUE", "DUMMY", "VALUE"};
            assertArrayEquals(expected, stringDataSlices);
        }

        @Override
        public boolean isStringFoundInTask(String searchString) {
            return  searchString.equals("DUMMY");
        }
    }

    @Test
    void generateFromRawTest() {
        TaskList t = new TaskList();
        Task t1 = new Todo("test1", true);
        Task t2 = new Event("test2", false, "2032-12-12", "2036-12-23");
        Task t3 = new Deadline("test3", true, "2024-12-23");
        ArrayList<String> rawInput = new ArrayList<>(Stream.of(t1, t2, t3)
                                                           .map(Task::serialize)
                                                           .toList());
        t.generateFromRaw(rawInput);
        assertEquals(t1.toString(), t.get(0).toString());
        assertEquals(t2.toString(), t.get(1).toString());
        assertEquals(t3.toString(), t.get(2).toString());
        assertEquals(rawInput.size(), t.size);
    }

    @Test
    void toRawFormatTest() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));
        ArrayList<String> result = t.toRawFormat();
        String[] expected = new String[3];
        Arrays.fill(expected, "DUMMY:VALUE:DUMMY:VALUE");
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    void addTaskTest() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(420));

        DummyTask expected = new DummyTask(420);
        assertEquals(expected, t.get(0));
        assertEquals(1, t.size);
    }

    @Test
    void deleteTaskTest() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));

        DummyTask expected1 = new DummyTask(1);
        assertEquals(expected1.getId(), ((DummyTask) t.deleteTask(1)).getId());

        Exception e1 = assertThrows(YappingBotOobException.class, () -> t.deleteTask(5));
        String expectedError1 = "I'm sorry, but task number 6 does not exist!";
        assertEquals(expectedError1, e1.getMessage());

        Exception e2 = assertThrows(YappingBotOobException.class, () -> t.deleteTask(-2));
        String expectedError2 = "I'm sorry, but task number -1 does not exist!";
        assertEquals(expectedError2, e2.getMessage());

        DummyTask expected2 = new DummyTask(2);
        assertEquals(expected2.getId(), ((DummyTask) t.deleteTask(1)).getId());

        t.deleteTask(0);
        assertTrue(t.isEmpty());

        Exception e3 = assertThrows(YappingBotOobException.class, () -> t.deleteTask(0));
        String expectedError3 = "I'm sorry, but task number 1 does not exist!";
        assertEquals(expectedError3, e3.getMessage());

    }

    @Test
    void isEmptyTest() {
        TaskList t = new TaskList();
        assertTrue(t.isEmpty());
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));
        assertFalse(t.isEmpty());
    }

    @Test
    void sizeTest() {
        TaskList t = new TaskList();
        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));

        assertEquals(3, t.size);
    }

    @Test
    void getTest() {
        TaskList t = new TaskList();

        Exception e1 = assertThrows(YappingBotOobException.class, () -> t.get(0));
        String expectedError1 = "I'm sorry, but task number 1 does not exist!";
        assertEquals(expectedError1, e1.getMessage());

        t.addTask(new DummyTask(0));
        t.addTask(new DummyTask(1));
        t.addTask(new DummyTask(2));

        DummyTask expected = new DummyTask(1);
        assertEquals(expected, t.get(1));

        Exception e2 = assertThrows(YappingBotOobException.class, () -> t.get(5));
        String expectedError2 = "I'm sorry, but task number 6 does not exist!";
        assertEquals(expectedError2, e2.getMessage());
    }
}