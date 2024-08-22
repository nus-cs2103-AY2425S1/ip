import java.util.ArrayList;

public class ToDo {
    private ArrayList<Task> todoList = new ArrayList<>();

    public void add(Task task) {
        todoList.add(task);
    }

    public void mark(int taskNumber) {
        todoList.get(taskNumber - 1).check();
    }

    public void unmark(int taskNumber) {
        todoList.get(taskNumber - 1).uncheck();
    }

    public String getTask(int taskNumber) {
        return todoList.get(taskNumber - 1).toString();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < todoList.size(); i++) {
            str = str + (i + 1) + "." + todoList.get(i).toString() + "\n";
        }
        return str;
    }
}
