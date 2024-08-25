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

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addTask(String command, Pikappi.TaskType tasktype) throws PikappiException {
        String[] substrings = command.split(" ");
        if (substrings.length == 1) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        String task = substrings[1];
        if (task.isEmpty()) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        switch (tasktype) {
        case TODO:
            tasks.add(new TodoTask(task));
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
            break;
        case DEADLINE:
            String by = "";
            try {
                for (int i = 3; i < substrings.length; i++) {
                    by += substrings[i] + " ";
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pi-ka..?? When is the deadline..?");
            }
            tasks.add(new DeadlineTask(task, by));
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
            break;
        case EVENT:
            String from = "";
            String to = "";
            try {
                from = substrings[3];
                if (!substrings[4].equals("/to")) {
                    from += " " + substrings[4];
                    for (int i = 6; i < substrings.length; i++) {
                        to += substrings[i] + " ";
                    }
                } else {
                    for (int i = 5; i < substrings.length; i++) {
                        to += substrings[i] + " ";
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pi-ka..?? When is the event starting and ending..?");
            }
            tasks.add(new EventTask(task, from, to));
            System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks.get(tasks.size() - 1) +
                    "\nNow you have " + tasks.size() + " tasks in the list.");
            break;
        }
    }

    public void deleteTask(String command) throws PikappiException {
        String[] substrings = command.split(" ");
        if (substrings.length == 1) {
            throw new PikappiException("Pi-ka..?? What do you want to delete..?");
        }
        int taskToDelete = Integer.parseInt(substrings[1]);
        if (taskToDelete > tasks.size() || taskToDelete < 1) {
            throw new PikappiException("Pi-ka..?? Task does not exist..");
        }
        tasks.remove(taskToDelete - 1);
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
