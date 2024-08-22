import java.util.List;
import java.util.ArrayList;

public class TaskHandler {
    private List<Task> tasks;
    // private List<Boolean> markedDone;

    public TaskHandler() {
        this.tasks = new ArrayList<Task>();
        // this.markedDone = new ArrayList<Boolean>();
    }

    /*
    public void addToDo(String task) {
        this.tasks.add(task);
        // this.markedDone.add(false);
    }
     */

    public void handleCommand(String command, String desc) {
        if (command.equals("mark")) {
            Task t = this.tasks.get(Integer.parseInt(desc.stripLeading()) - 1).markAsDone();
            System.out.println("I've marked as done:\n" + t);
        } else if (command.equals("unmark")) {
            Task t = this.tasks.get(Integer.parseInt(desc.stripLeading()) - 1).markAsNotDone();
            System.out.println("I've marked as not done:\n" + t);
        } else if (command.equals("list")) {
            System.out.println(this.getTasksString());
        } else {
            String taskdesc = command + desc;
            Task t = new Task(taskdesc);
            this.tasks.add(t);
            System.out.println("added: " + taskdesc);
        }
    }

    public String getTasksString() {
        String s = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            s += String.format("%d.", i + 1) +  this.tasks.get(i) + "\n";
        }
        return s.stripTrailing();
    }

    public void markDone(int i) {
        if (i > this.tasks.size()) {
            System.out.println("Error: Task number out of bounds");
            return;
        }
        // this.markedDone.set(i - 1, true);
        System.out.println("Marked done: " + i + "\n");
    }
}
