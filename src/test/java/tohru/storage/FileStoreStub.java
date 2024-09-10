package tohru.storage;

import java.util.ArrayList;

import tohru.task.TodoItem;

/**
 * A stub class that represent hte FileStore class simulating loading
 * and saving from local storage.
 */
public class FileStoreStub extends FileStore {

    public FileStoreStub() {
        super("stub");
    }

    @Override
    public ArrayList<TodoItem> retrieveTodoList() {
        return new ArrayList<>();
    }

    @Override
    public void saveTodoList(ArrayList<TodoItem> todoList) {
    }
}
