import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(taskList);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void removeTask(int i) {
        this.tasks.remove(i);
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public void setTaskAsCompleted(int i) {
        this.tasks.get(i).markAsCompleted();
    }

    public void setTaskAsNotCompleted(int i) {
        this.tasks.get(i).markAsNotCompleted();
    }

     /**
     * Creates and returns an enumeration of the tasks added and stored by the user
     * 
     * @param tasks the list of tasks
     * @param numTasks the number of tasks
     * @return an enumeration of the tasks
     */
    public String taskListToString() {
        int numTasks = this.tasks.size();
        if (numTasks == 0) {
            return "\tempty";
        }

        try {
            String[] tempTasks = new String[numTasks];

            for (int i = 1; i <= numTasks; i++) {
                tempTasks[i - 1] = "\t" + i + ". " + this.tasks.get(i - 1).toString();
            }

            return String.join("\n", tempTasks);
        } catch (NullPointerException e) {
            return "\tOops! It seems like there's an invalid task in your list!\nI can't display the list yet.";
        }
    }

    public String taskListToFile() {
        return this.tasks.stream().map(t -> t.toEasyString()).reduce("----TASKS----", (x, y) -> x + "\n" + y);
    }

}
