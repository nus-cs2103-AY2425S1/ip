package trackie.storage;

import trackie.tasks.Deadline;
import trackie.tasks.Event;
import trackie.tasks.Task;
import trackie.tasks.Todo;
import trackie.ui.TrackieException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a task list designed for Trackie.
 * This class manages a collection of <code>Tasks</code> and provides methods to add, list, mark, unmark, and delete tasks.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list contains no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }


    /**
     * Adds a task of type Todo to the task list.
     *
     * @param arguments An array of strings containing the task description.
     * @throws TrackieException if the usage deviates from the required format or if the description is empty.
     */
    public void addTodoTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Correct usage: todo [desc]");
        }
        StringBuilder sb = new StringBuilder();
        int ptr = 1;
        while (ptr < arguments.length) {
            sb.append(arguments[ptr]).append(" ");
            ptr++;
        }
        String desc = sb.substring(0, sb.length() - 1);
        Task instance = new Todo(desc);
        tasks.add(instance);

        System.out.printf("Added: [T][%s] %s\n", instance.getStatusIcon(), instance.getTaskInfo());
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    /**
     * Adds a task of type Deadline to the task list.
     *
     * @param arguments An array of strings containing the task description and deadline.
     * @throws TrackieException if the usage deviates from the required format, or if the description or deadline is empty.
     */
    public void addDeadlineTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Correct usage: deadline [desc] /by [yyyy-mm-dd]");
        }

        String desc;
        String deadline;

        //retrieve the description
        int ptr = 1;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        while (!arguments[ptr].equals("/by")) {
            if (ptr == arguments.length - 1) {
                throw new TrackieException("Correct usage: deadline [desc] /by [yyyy-mm-dd]");
            }
            sb.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (sb.isEmpty()) {
            throw new TrackieException("Description cannot be empty!");
        } else {
            desc = sb.substring(0, sb.length() - 1);
        }

        //retrieve the deadline
        ptr++;
        while (ptr < arguments.length) {
            sb2.append(arguments[ptr]).append(' ');
            ptr++;
        }

        if (sb2.isEmpty()) {
            throw new TrackieException("Deadline cannot be empty!");
        } else {
            deadline = sb2.substring(0, sb2.length() - 1);
        }

        try {
            Task instance = new Deadline(desc, deadline);
            tasks.add(instance);

            System.out.printf("Added: [D][%s] %s\n", instance.getStatusIcon(), instance.getTaskInfo());
            System.out.printf("You now have %d task(s) in total.\n", tasks.size());
        } catch (DateTimeParseException e) {
            System.out.println("Please use the correct format: yyyy-mm-dd");
            System.out.println("(Also check that you are entering a valid date!)");
        }

    }

    /**
     * Adds a task of type Event to the task list.
     *
     * @param arguments An array of strings containing the task description, start time, and end time.
     * @throws TrackieException if the usage deviates from the required format, or if the description, start time, or end time is empty.
     */
    public void addEventTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
        }

        String desc = "";
        String start = "";
        String end = "";

        //retrieve the description
        int ptr = 1;
        StringBuilder sb = new StringBuilder();
        while (!arguments[ptr].equals("/from")) {
            if (ptr == arguments.length - 1) {
                throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
            }
            sb.append(arguments[ptr]).append(' ');
            ptr++;
        }
        if (sb.isEmpty()) {
            throw new TrackieException("Description cannot be empty!");
        } else {
            desc = sb.substring(0, sb.length() - 1);
        }

        ptr++;
        if (ptr >= arguments.length) {
            throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
        }
        //retrieve the start time
        StringBuilder sb2 = new StringBuilder();
        while (!arguments[ptr].equals("/to")) {
            if (ptr == arguments.length - 1) {
                throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
            }
            sb2.append(arguments[ptr]).append(" ");
            ptr++;
        }
        if (sb2.isEmpty()) {
            throw new TrackieException("Start timing cannot be empty!");
        } else {
            start = sb2.substring(0, sb2.length() - 1);
        }

        ptr++;
        //retrieve the end time
        StringBuilder sb3 = new StringBuilder();
        while (ptr < arguments.length) {
            sb3.append(arguments[ptr]).append(" ");
            ptr++;
        }
        if (sb3.isEmpty()) {
            throw new TrackieException("End timing cannot be empty!");
        } else {
            end = sb3.substring(0, sb3.length() - 1);
        }

        Task instance = new Event(desc, start, end);
        tasks.add(instance);

        System.out.printf("Added: [E][%s] %s\n", instance.getStatusIcon(), instance.getTaskInfo());
        System.out.printf("You now have %d task(s) in total.\n", tasks.size());
    }

    /**
     * Lists all tasks in the task list(if there are any).
     * Prints each task with its type, completion status, and information.
     * If there are no tasks, the user is notified that they have no tasks.
     */
    public void listTasks() {
        int counter = 1;
        if (tasks.isEmpty()) {
            System.out.println("You currently have no tasks.");
            return;
        }

        for (Task t : tasks) {
            System.out.println(String.format("%d. [%s][%s] %s",
                    counter, t.getTaskType(), t.getStatusIcon(), t.getTaskInfo()));
            counter++;
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param arguments An array of strings containing the index of the task to be marked.
     * @throws TrackieException if no index is specified or if the index is invalid.
     */
    public void markTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Please specify an index to mark!");
        }

        int index = Integer.parseInt(arguments[1]);

        if (index < 1 || index > tasks.size()) {
            throw new TrackieException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        t.markDone();
        System.out.println("Amazing! The specified task is now marked as complete:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    /**
     * Marks a task as not completed.
     *
     * @param arguments An array of strings containing the index of the task to be unmarked.
     * @throws TrackieException if no index is specified or if the index is invalid.
     */
    public void unmarkTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Please specify an index to unmark!");
        }

        int index = Integer.parseInt(arguments[1]);

        if (index < 1 || index > tasks.size()) {
            throw new TrackieException("Invalid index.");
        }
        Task t = tasks.get(index - 1);
        t.markUndone();
        System.out.println("Alright, the specified task has been marked undone:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }


    /**
     * Deletes a task from the task list.
     *
     * @param arguments An array of strings containing the index of the task to be deleted.
     * @throws TrackieException if no index is specified or if the index is invalid.
     */
    public void deleteTask(String[] arguments) throws TrackieException {
        if (arguments.length == 1) {
            throw new TrackieException("Please specify an index to unmark!");
        }

        int index = Integer.parseInt(arguments[1]);

        if (index < 1 || index > tasks.size()) {
            throw new TrackieException("Invalid index.");
        }

        Task t = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println("Yes boss, I have removed the following task:");
        System.out.println(String.format("[%s] %s", t.getStatusIcon(), t.getTaskInfo()));
    }

    public void findTasks(String[] arguments) throws TrackieException {
        StringBuilder sb = new StringBuilder();
        int ptr = 1;
        if (arguments.length == 1) {
            throw new TrackieException("Please provide a valid query to find!");
        }
        while (ptr < arguments.length) {
            sb.append(arguments[ptr]).append(" ");
            ptr++;
        }

        String query = sb.substring(0, sb.length() - 1);
        for (Task t : this.tasks) {
            if (t.getDescription().contains(query)) {
                System.out.println(t.getTaskInfo());
            }
        }
    }

}
