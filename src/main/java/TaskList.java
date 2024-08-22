import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public void add(Task task) {
        listOfTasks.add(task);
    }

    public void mark(int i) throws NotInTaskListException {
        if (i > listOfTasks.size() || i < 1) {
            throw new NotInTaskListException();
        }
        listOfTasks.get(i - 1).done();
    }

    public void unMark(int i) throws NotInTaskListException {
        if (i > listOfTasks.size() || i < 1) {
            throw new NotInTaskListException();
        }
        listOfTasks.get(i - 1).undone();
    }

    public String getTask(int i) throws NotInTaskListException {
        if (i > listOfTasks.size() || i < 1) {
            throw new NotInTaskListException();
        }
        return listOfTasks.get(i - 1).toString();
    }

    public void deleteTask(int i) throws NotInTaskListException {
        if (i > listOfTasks.size() || i < 1) {
            throw new NotInTaskListException();
        }
        listOfTasks.remove(i + 1);
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
