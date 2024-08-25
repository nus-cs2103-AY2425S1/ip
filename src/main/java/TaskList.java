import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void addTask(Task task) throws PikappiException {
        if (task == null) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        if (task instanceof TodoTask) {
            tasks.add(task);
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        } else if (task instanceof DeadlineTask) {
            tasks.add(task);
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        } else if (task instanceof EventTask) {
            tasks.add(task);
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
        }
    }

    public void deleteTask(int taskNum) throws PikappiException {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new PikappiException("Pi-ka..?? Task does not exist..");
        }
        tasks.remove(taskNum - 1);
        System.out.println("Pipi-ka-pi! I've removed this task:\n " + tasks.get(tasks.size() - 1) +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Pika-ka! You have no tasks!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + "." + tasks.get(i));
            } else {
                break;
            }
        }
    }

    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskNumber - 1));
    }

    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).unmarkAsDone();
        System.out.println("Okie, I've unmarked this task as not done yet:\n" + tasks.get(taskNumber - 1));
    }
}
