import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    protected int getSize() {
        return this.taskList.size();
    }

    private String getTaskString(int index) {
        return this.taskList.get(index).toString();
    }

    private Task getTask(int index) {
        return this.taskList.get(index);
    }

    protected void addTask(Task task) {
        this.taskList.add(task);
    }

    protected String deleteTask(int index) {
        Task task = getTask(index);
        this.taskList.remove(index);
        return task.toString();
    }

    protected String markTask(int index) {
        getTask(index).markDone();
        return getTaskString(index);
    }

    protected String unmarkTask(int index) {
        getTask(index).markUndone();
        return getTaskString(index);
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "\tbozo you got no tasks";
        }
        StringBuilder temp = new StringBuilder("\twhy am i here\n\t\t");
        for (int i = 0; i < this.taskList.size(); i++) {
            temp.append((i + 1)).append(". ").append(getTaskString(i)).append("\n\t\t");
        }
        temp.setLength(temp.length() - 3);
        return String.valueOf(temp);
    }

    public String toFileString() {
        StringBuilder fileString = new StringBuilder();
        for (Task task : taskList) {
            fileString.append(task.toFileString());
        }
        return String.valueOf(fileString);
    }

}
