import java.util.List;

public class TaskList {

    private List<Task> list;

    public TaskList(List<Task> loadedData) {
        this.list = loadedData;
    }

    public void add(Task task, boolean saved) {
        this.list.add(task);
        if (!saved) {
            System.out.println("I've added the task: ");
            System.out.println(task);
            System.out.println("Now you have " + this.list.size() + " tasks in the list");
        }
    }

    public void listOut() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + "." + this.list.get(i - 1));
        }
    }

    public void markTask(int index) {
        this.list.get(index).mark();
        System.out.println("You have marked the following task as done!");
        System.out.println(this.list.get(index));
    }

    public void unmarkTask(int index) {
        this.list.get(index).unmark();
        System.out.println("You have unmarked the following task!");
        System.out.println(this.list.get(index));
    }

    public void delete(int index) {
        Task deleted = this.list.get(index);
        this.list.remove(index);
        System.out.println("Let's go deleting!");
        System.out.println("Deleted task " + deleted);
    }

    public List<Task> getTaskList() {
        return this.list;
    }
}
