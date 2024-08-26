import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public Task getTask(int index) {
        try {
            ArrayList<Task> db = this.storage.getFileContents();
            return db.get(index);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        }
    }

    public int size() {
        try {
            ArrayList<Task> db = this.storage.getFileContents();
            return db.size();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        }
    }

    public void markTask(int index) {
        try {
            ArrayList<Task> db = this.storage.getFileContents();
            Task t = db.get(index);
            t.complete();
            this.storage.updateFileContents(db);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error updating database!");
        }
    }

    public void unmarkTask(int index) {
        try {
            ArrayList<Task> db = this.storage.getFileContents();
            Task t = db.get(index);
            t.undo();
            this.storage.updateFileContents(db);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error updating database!");
        }
    }

    public void addTask(Task task) {
        try {
            ArrayList<Task> db = this.storage.getFileContents();
            db.add(task);
            this.storage.updateFileContents(db);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error updating database!");
        }
    }

    public Task deleteTask(int index) {
        try {
            ArrayList<Task> db = this.storage.getFileContents();
            Task t = db.get(index);
            db.remove(t);
            this.storage.updateFileContents(db);
            return t;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found!");
        } catch (IOException e) {
            throw new RuntimeException("Error updating database!");
        }
    }
}
