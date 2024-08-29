package joe.tasks;

import joe.Main.Parser;
import joe.exceptions.InvalidIndexException;
import joe.utils.Formatter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static final String line =
            "____________________________________________________________";
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
        System.out.println("New task list created.");
        System.out.println(line);
    }

    public TaskList(String[] lines) {
        taskList = new ArrayList<>();

        for (String line : lines) {
            if (line == null) {
                break;
            }
            taskList.add(Parser.parseTask(line));
        }

        System.out.println("Data loaded successfully");
        System.out.println(line);
    }

    /**
     * Adds a task to the user's list.
     *
     * @param t the Task to be added
     */
    public void addTask(Task t) {
        System.out.println(line);
        taskList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Deletes a task at a specific index.
     *
     * @param index the index of the Task the user wants to delete
     */
    public void deleteTask(int index) throws InvalidIndexException {
        System.out.println(line);
        if (index - 1 >= taskList.size() || index - 1 < 0) {
            throw new InvalidIndexException(index);
        }
        Task t = taskList.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Marks a task as done.
     *
     * @param index the index of the Task the user wants to mark
     */
    public void markDone(int index) throws InvalidIndexException {
        System.out.println(line);
        if (index - 1 >= taskList.size() || index - 1 < 0) {
            throw new InvalidIndexException(index);
        }
        Task t = taskList.get(index - 1);
        t.setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
        System.out.println(line);
    }

    /**
     * Unmarks a task.
     *
     * @param index the index of the Task the user wants to unmark
     */
    public void unmark(int index) throws InvalidIndexException {
        System.out.println(line);
        if (index - 1 >= taskList.size() || index - 1 < 0) {
            throw new InvalidIndexException(index - 1);
        }
        Task t = taskList.get(index - 1);
        t.setDone(false);
        System.out.println("OK, I've marked this task as undone:");
        System.out.println("  " + t);
        System.out.println(line);
    }

    /**
     * Lists all the tasks in the user's list.
     */
    public void listTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println(line);
    }

    /**
     * Saves the user's current list of Tasks and automatically loads the saved list during their next session
     */
    public void saveTasks() {
        int taskCount = taskList.size();
        System.out.println(line);
        System.out.println("Saving your tasks......");
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < taskCount; i++) {
                Task t = taskList.get(i);
                sb.append(t.saveRepr());
                System.out.println(t + " (saved)");
                sb.append(System.lineSeparator());
            }

            FileWriter fw = new FileWriter("src/main/data/joe.txt");
            fw.write(sb.toString());
            fw.close();
            System.out.println("Your tasks have been successfully saved.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            System.out.println(line);
        }
        System.out.println(line);
    }

    /**
     * Queries the tasks in the user's list by a specific date
     * and prints out the tasks that are due on that date for deadlines
     * and start on that date for events.
     *
     * @param date the date to query the tasks by
     */
    public void queryTasksByDate(String date) {
        System.out.println(line);

        // The arbitrary 1200 time here will not affect the output
        LocalDateTime targetDate = Formatter.createLocalDateTimeWithArbitraryTime(date);

        int tasksFound = 0;
        for (Task t : taskList) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                long days = d.daysTillDeadline(targetDate);
                if (days == 0L) {
                    System.out.printf("%d.%s\n", ++tasksFound, d);
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                long days = e.daysTillEvent(targetDate);
                if (days == 0L) {
                    System.out.printf("%d.%s\n", ++tasksFound, e);
                }
            }
        }

        if (tasksFound == 0) {
            System.out.printf("There are no tasks on %s\n", targetDate.format(DateTimeFormatter.ISO_DATE));
        }

        System.out.println(line);
    }

    /**
     * Returns the number of tasks in the user's list.
     *
     * @return the number of tasks in the user's list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at a specific index.
     *
     * @param index the index of the task
     * @return the task at the index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Prints the tasks that contains the search query string provided by the user
     *
     * @param queryString the query made by the user to find tasks with matching descriptions
     */
    public void find(String queryString) {
        System.out.println(line);
        System.out.println("Here are the matching tasks in the list:");

        int foundStrings = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String description = t.getDescription();
            if (description.contains(queryString)) {
                System.out.printf("%d. %s\n", ++foundStrings, t);
            }
        }
        System.out.println(line);
    }
}
