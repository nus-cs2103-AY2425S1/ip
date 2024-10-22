package chatbot.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskStorageImplTest {
    @Test
    public void getTasksTest() {
        TaskStorageImpl storage = new TaskStorageImpl("data/atlas_test.txt");
        String[] action1 = {"todo", "return", "books"};
        storage.addTask(action1, Command.ToDo);
        String[] action2 = {"todo", "buy", "groceries"};
        storage.addTask(action2, Command.ToDo);
        String actual =
                "1.[T][ ] return books\n" +
                "2.[T][ ] buy groceries\n";
        TaskStorageResultImpl res = new TaskStorageResultImpl(actual);
        assertEquals(storage.getTasks(false), res);
    }

    @Test
    public void deleteTaskTest() {
        TaskStorageImpl storage = new TaskStorageImpl("data/atlas_test.txt");
        String[] action1 = {"todo", "return", "books"};
        storage.addTask(action1, Command.ToDo);
        String[] action2 = {"todo", "buy", "groceries"};
        storage.addTask(action2, Command.ToDo);
        String[] action3 = {"delete", "1"};
        String actual =
                "Noted. I've removed this task:  [T][ ] return books\n" +
                        "Now you have 1 task(s) in the list";
        TaskStorageResultImpl res = new TaskStorageResultImpl(actual);
        assertEquals(storage.deleteTask(action3), res);
    }
}
