import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public void add(Task task) {
        listOfTasks.add(task);
    }

    public boolean mark(int i) {
        if (i > listOfTasks.size() || i < 1) {
            return false;
        }
        listOfTasks.get(i - 1).done();
        return true;
    }

    public boolean unMark(int i) {
        if (i > listOfTasks.size() || i < 1) {
            return false;
        }
        listOfTasks.get(i - 1).undone();
        return true;
    }

    public String getTask(int i) {
        if (i > listOfTasks.size() || i < 1) {
            return "";
        }
        return listOfTasks.get(i - 1).toString();
    }

    public int getSize() {
        return listOfTasks.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int length = listOfTasks.size();
        for (int i = 0; i < length; i++) {
            str.append((i + 1) + ". ");
            str.append(listOfTasks.get(i).toString());
            if (i != length - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }
}
