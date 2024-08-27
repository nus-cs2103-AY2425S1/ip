package tohru.storage;

import tohru.task.TodoItem;

import java.util.ArrayList;

/**
 * A stub class that represent hte FileStore class simulating loading
 * and saving from local storage
 */
public class FileStoreStub extends FileStore {

    public FileStoreStub() {
        super("");
    }

    @Override
    public ArrayList<TodoItem> retrieveTodoList() {
        return new ArrayList<>();
    }

    @Override
    public void saveTodoList(ArrayList<TodoItem> todoList) {
    }
}
