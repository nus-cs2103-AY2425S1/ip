package Dawn;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Creates a new instance of an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of a TaskList which contains the previously saved tasks
     *
     * @param savedTasks Previously saved tasks
     */
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
    }

    private enum Command {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Adds the newly created tasks to the task list
     *
     * @param command Command that indicates the type of task to create
     * @param input Details of the tasks e.g. description, date, time etc
     * @throws DawnException
     */
    public static void addTask(String command, String input) throws DawnException {
        Command cmd = Command.valueOf(command);
        if (input.isBlank()) {
            throw new DawnException("You might be missing the task description, please check again\n");
        }

        Task t = null;
        String[] s = input.split("/");

        switch (cmd) {
            case TODO:
                t = new ToDo(s[0]);
                break;
            case DEADLINE:
                if (s.length < 2) {
                    throw new DawnException("Make sure you include both the task description and the deadline!\n");
                }
                t = new Deadline(s[0], s[1]);
                break;
            case EVENT:
                if (s.length < 3) {
                    throw new DawnException("Make sure you include the task description, start, and end times for " +
                            "your event!\n");
                }
                t = new Event(s[0], s[1], s[2]);
                break;
        }
        tasks.add(t);
        System.out.println("  Gotcha! I've added this task: \n" + tasks.size() + "." + t);
        System.out.printf("  Now you have %d task(s) in the list \n", tasks.size());
    }

    /**
     * Deletes a task from the task list according to the specified task index
     *
     * @param index Task index
     * @throws DawnException
     */
    public static void delete(String index) throws DawnException {
        int ind;
        try {
            ind = Integer.parseInt(index);
            if (ind < 0 || ind >= numOfTasks()) {
                throw new DawnException("Task specified does not exist!\n");
            }
        } catch (NumberFormatException e){
            throw new DawnException("Please specify the index of the task to be deleted!\n");
        }
        Task t = tasks.get(ind - 1); // to account for index starting at 0
        System.out.println("  OK! I have removed this task for you: \n" + t);
        tasks.remove(ind - 1);
        System.out.printf("  Now you have %d task(s) in the list \n", tasks.size());
    }

    /**
     * Marks a task as done
     *
     * @param index Task index
     */
    public static void markAsDone(int index) {
        tasks.get(index - 1).markAsDone(); // account for index starting at 0
        System.out.println("  i've marked this task as done!: ");
        System.out.println("  " + index + "." + tasks.get(index - 1));
    }

    /**
     * Marks a task as not done
     *
     * @param index Task index
     */
    public static void markAsNotDone(int index) {
        tasks.get(index - 1).markAsNotDone(); // account for index starting at 0
        System.out.println("  ok, I've unmarked this task :( ");
        System.out.println("  " + index + "." + tasks.get(index - 1));
    }

    /**
     * Returns the total number of tasks in the current task list
     */
    public static int numOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the task in the task list as specified by the task index
     *
     * @param index Task index
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Prints all the events and deadlines that are happening today
     */
    public static void doByToday() {
        System.out.println("Deadlines and events happening today: ");
        Boolean haveTasks = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline d = (Deadline) tasks.get(i);
                if (d.getDate().equals(LocalDate.now())) {
                    System.out.println(d);
                    haveTasks = true;
                }
            } else if (tasks.get(i) instanceof Event) {
                Event e = (Event) tasks.get(i);
                if (e.getDate().equals(LocalDate.now())) {
                    System.out.println(e);
                    haveTasks = true;
                }
            }
        }
        if (!haveTasks) {
            System.out.println("There are no deadlines and events happening today!");
        }
    }

    /**
     * Prints all the tasks currently in the task list
     */
    public static void list() {
        System.out.println("listing all tasks...");
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in the list... \nPlease feed me some tasks!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s  \n", i + 1, tasks.get(i));
            }
        }
    }
}
