import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public void executeCommand(String command) {
        if (command.equals("list")) {
            this.list();
        } else {
            String[] splitwords = command.split(" ", 2);
            String firstCommand = splitwords[0];
            if (firstCommand.equals("mark")) {
                int index = Integer.valueOf(splitwords[1]);
                this.markTask(index);
            } else if (firstCommand.equals("unmark")) {
                int index = Integer.valueOf(splitwords[1]);
                this.unmarkTask(index);
            } else if (firstCommand.equals("deadline")) {
                String[] taskparts = splitwords[1].split(" /by ", 2);
                String name = taskparts[0];
                String deadline = taskparts[1];
                Task task = new Deadline(name, deadline);
                this.add(task);
            } else if (firstCommand.equals("event")) {
                String[] taskParts = splitwords[1].split(" /from ", 2);
                String[] deadlines = taskParts[1].split(" /to ", 2);
                String name = taskParts[0];
                String from = deadlines[0];
                String to = deadlines[1];
                Task task = new Event(name, from, to);
                this.add(task);
            } else if (firstCommand.equals("todo")) {
                String name = splitwords[1];
                Task task = new Todo(name);
                this.add(task);
            } else {
                System.out.println("Invalid command, please try again");
            }
        }
    }

    public void list() {
        System.out.println(this.toString());
    }

    private void add(Task task) {
        this.tasklist.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
    }

    private void markTask(int index) {
        if (index > this.tasklist.size()) {
            System.out.println("Invalid task");
        } else {
            Task task = this.tasklist.get(index-1);
            task.mark();
            System.out.println(" Nice! I've marked this task as done: \n" + task);
        }
    }

    private void unmarkTask(int index) {
        if (index > this.tasklist.size()) {
            System.out.println("Invalid task");
        } else {
            Task task = this.tasklist.get(index-1);
            task.unmark();
            System.out.println("OK, I've marked this task as not done yet: \n" + task);
        }
    }

    @Override
    public String toString() {
        String tasks = "Here are the tasks in your list: \n";
        for (int i=0; i < tasklist.size(); i++) {
            tasks += (i+1) + ". " + tasklist.get(i) + "\n";
        }
        return tasks;
    }

}
