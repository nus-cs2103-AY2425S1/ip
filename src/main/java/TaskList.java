import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> userInputs;

    public TaskList(ArrayList<Task> userInputs) {
        setTaskList(userInputs);
    }

    public TaskList() {
        userInputs = new ArrayList<Task>();
    }

    private void setTaskList(ArrayList<Task> userInputs) {
        this.userInputs = userInputs;
    }

    public void set(int idx, boolean bool) {
        userInputs.get(idx).setDone(bool);
    }

    public Task get(int idx) {
        return userInputs.get(idx);
    }

    public void add(Task task) {
        userInputs.add(task);
    }

    public ArrayList<Task> getTaskList() {
        return userInputs;
    }

    public void delete(int idx) {
        userInputs.remove(idx);
    }

    public int size() {
        return userInputs.size();
    }
}
