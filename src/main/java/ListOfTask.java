import java.util.ArrayList;

public class ListOfTask {
    private ArrayList<Task> tasks;

    public ListOfTask() {
        this.tasks = new ArrayList<Task>();
    }

    public int getTotal() {
        return this.tasks.size();
    }

    public Task addToDo(String t) {
        Task task = new ToDoTask(t);
        this.tasks.add(task);
        return task;
    }

    public Task addDeadline(String t, String date) {
        Task task = new DeadlineTask(t, date);
        this.tasks.add(task);
        return task;
    }

    public Task addEvent(String t, String start, String end) {
        Task task = new EventTask(t, start, end);
        this.tasks.add(task);
        return task;
    }

    public Task deleteTask(int i) {
        return this.tasks.remove(i - 1);
    }

    public String markDone(int i) {
        return "     ____________________________________________________________ \n" +
                this.tasks.get(i - 1).markDone() + "\n" +
                "     ____________________________________________________________ \n";
    }

    public String markUndone(int i) {
        return "     ____________________________________________________________ \n" +
                this.tasks.get(i - 1).markUndone() + "\n" +
                "     ____________________________________________________________ \n";
    }

    public String printList() {
        String output = "     ____________________________________________________________ \n" +
                "     Here are the tasks in your list: \n";
        for (int i = 0; i < this.tasks.size(); i++) {
            int label = i + 1;
            output += "     " + label + "." + this.tasks.get(i).printTask() + "\n";
        }

        return output +
                "     ____________________________________________________________ \n";
    }
}
