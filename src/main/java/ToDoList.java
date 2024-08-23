import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> todoList = new ArrayList<>();

    public void add(Task task) {
        todoList.add(task);
    }

    public void mark(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > todoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        todoList.get(taskNumber - 1).check();
    }

    public void unmark(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > todoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        todoList.get(taskNumber - 1).uncheck();
    }

    public String getTask(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > todoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        return todoList.get(taskNumber - 1).toString();
    }

    public int getNumberofTasks() {
        return this.todoList.size();
    }

    public void delete(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > todoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        todoList.remove(taskNumber - 1);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < todoList.size(); i++) {
            str = str + " " + (i + 1) + "." + todoList.get(i).toString() + "\n";
        }
        return str;
    }
}
