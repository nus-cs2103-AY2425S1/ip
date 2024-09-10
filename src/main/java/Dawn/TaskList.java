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
    public static String addTask(String command, String input) throws DawnException {
        Command cmd = Command.valueOf(command);
        if (input.isBlank()) {
            throw new DawnException("You might be missing the task description, please check again\n");
        }

        Task t = null;
        String[] s = input.split("/");

        if (s[0].isBlank()) {
            throw new DawnException("You might be missing the task description, please check again\n");
        }

        switch (cmd) {
            case TODO:
                t = new ToDo(s[0]);
                break;
            case DEADLINE:
                if (s.length < 2) {
                    throw new DawnException("Make sure you include both the task description and the deadline in this" +
                            " format:\n deadline [task name] /by [date yyyy-mm-dd] [time]\n" +
                            "For example: deadline submit assignment1 /by 2024-09-16 2pm");
                }
                t = new Deadline(s[0], s[1]);
                break;
            case EVENT:
                if (s.length < 3) {
                    throw new DawnException("Make sure you include the task description, start, and end times for " +
                            "your event in this format:\nevent [task name] /from [date yyyy-mm-dd] [time] / " +
                            "to [time]\nFor example: event party /from 2024-09-01 5pm /to 9pm");
                }
                t = new Event(s[0], s[1], s[2]);
                break;
        }
        tasks.add(t);
        return "Gotcha! I've added this task: \n" + tasks.size() + "." + t +
                "\nNow you have " + tasks.size() + " task(s) in the list \n";
    }

    /**
     * Deletes a task from the task list according to the specified task index
     *
     * @param index Task index
     * @throws DawnException
     */
    public static String delete(String index) throws DawnException {
        int ind;
        try {
            ind = Integer.parseInt(index);
            if (ind < 0 || ind >= numOfTasks()) {
                throw new DawnException("Task specified does not exist!\n");
            }
        } catch (NumberFormatException e){
            throw new DawnException("Please specify the index of the task to be deleted in this format: " +
                    "delete [index]\n For example: to delete the first task in the list, you can type <delete 1>");
        }
        StringBuilder s = new StringBuilder();
        Task t = tasks.get(ind - 1); // to account for index starting at 0
        s.append("OK! I have removed this task for you: \n" + t);
        tasks.remove(ind - 1);
        s.append(String.format("Now you have %d task(s) in the list \n", tasks.size()));
        return String.valueOf(s);
    }

    /**
     * Marks a task as done
     *
     * @param index Task index
     */
    public static String markAsDone(int index) {
        tasks.get(index - 1).markAsDone(); // account for index starting at 0
        return "Ok, I've marked this task as done! \n" + "  " + index + "." + tasks.get(index - 1);
    }

    /**
     * Marks a task as not done
     *
     * @param index Task index
     */
    public static String markAsNotDone(int index) {
        tasks.get(index - 1).markAsNotDone(); // account for index starting at 0
        return "Ok, I've marked this task as not done :( \n" + "  " + index + "." + tasks.get(index - 1);
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
    public static String doByToday() {
        StringBuilder s = new StringBuilder();
        s.append("Deadlines and events happening today:\n");
        boolean haveTasks = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline d = (Deadline) tasks.get(i);
                if (d.getDate().equals(LocalDate.now())) {
                    s.append(d);
                    haveTasks = true;
                }
            } else if (tasks.get(i) instanceof Event) {
                Event e = (Event) tasks.get(i);
                if (e.getDate().equals(LocalDate.now())) {
                    s.append(e);
                    haveTasks = true;
                }
            }
        }
        if (!haveTasks) {
            s.append("Yipee! There are no deadlines and events happening today!");
        }
        return String.valueOf(s);
    }

    /**
     * Prints all the tasks currently in the task list
     */
    public static String list() {
        StringBuilder s = new StringBuilder();
        s.append("listing all tasks...\n");
        if (tasks.isEmpty()) {
            s.append("There are no tasks in the list... \nPlease feed me some tasks!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                s.append(String.format("%d. %s \n", i + 1, tasks.get(i)));
            }
        }
        return String.valueOf(s);
    }
}
