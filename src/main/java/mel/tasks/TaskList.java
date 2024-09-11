package mel.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import mel.exceptions.MelException;
import mel.exceptions.TaskException;
import mel.main.Mel;
import mel.utils.Storage;

/**
 * TaskList class that handles a
 * list of tasks from user input.
 */
public class TaskList {
    private final Mel mel;
    private final Storage storage;
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a new TaskList to handle tasks.
     * @param mel Mel chatbot instance.
     * @param storage Storage instance handling save file.
     */
    public TaskList(Mel mel, Storage storage) {
        this.mel = mel;
        this.storage = storage;
    }

    /**
     * Handles task input to Mel chatbot.
     * @param input task input string.
     * @throws TaskException if input is of invalid format.
     * @throws TaskException if task input is invalid.
     */
    public void executeTask(String input) throws MelException, TaskException {
        String[] s = input.split(" ", 2);
        String cmd = s[0].trim();
        if (Objects.equals(input, "list")) {
            printAll();
        } else if (cmd.contains("mark")) {
            handleTaskMarking(input);
        } else if (cmd.equals("delete")) {
            deleteTask(input);
        } else if (cmd.equals("find")) {
            findTask(input);
        } else {
            mel.println("Mel remembers...");
            createTask(input);
        }
    }

    /**
     * Creates new tasks from input.
     * @param str task input string.
     * @throws TaskException if input is of invalid format.
     * @throws TaskException if task input is invalid.
     */
    private void createTask(String str) throws MelException, TaskException {
        Task task;
        switch (str.split(" ", 2)[0]) {
        case "todo":
            task = new ToDo(str);
            break;
        case "deadline":
            task = new Deadline(str);
            break;
        case "event":
            task = new Event(str);
            break;
        default:
            throw new MelException("Mel is confused...\n"
                    + "Mel doesn't understand you :((");
        }
        tasks.add(task);
        updateTasks();

        mel.println("  " + task);
        mel.println("Mel counts " + tasks.size()
                + " stuffs memorized XD");
    }

    /**
     * Deletes tasks based on input.
     * @param input user input string.
     */
    private void deleteTask(String... input) {
        /* Command detail string as array */
        String[] s = input[0].trim()
                             .split(" ", 2)[1]
                             .trim()
                             .split(" ");
        try {
            mel.println("Mel helps you forget...");
            for (String str : s) {
                int idx = Integer.parseInt(str) - 1;
                mel.println("  " + tasks.get(idx));
                tasks.remove(idx);
            }
            updateTasks();
            mel.println("Mel counts " + tasks.size()
                    + " stuffs memorized XD");
        } catch (IndexOutOfBoundsException e) {
            mel.println("Mel's brain explodes in anger?! "
                    + "Mel recalls only " + tasks.size() + " things");
        }
    }

    /**
     * Handles marking of tasks completion based on input.
     * @param input user input string.
     */
    private void handleTaskMarking(String... input) throws MelException {
        try {
            String[] str = input[0].trim()
                                   .split(" ", 2);
            String cmd = str[0].trim();
            String[] details = str[1].trim()
                                     .split(" ");
            if (Objects.equals(cmd, "mark")) {
                mel.println("Mel sees you completed your task!");
                markTasksAsDone(details);
            } else if (Objects.equals(cmd, "unmark")) {
                mel.println("Mel wonders how you undid your task...");
                markTasksAsNotDone(details);
            } else {
                throw new MelException("Mel is confused... \n"
                        + "Mel doesn't understand you :((");
            }
            updateTasks();
        } catch (IndexOutOfBoundsException e) {
            mel.println("Mel's brain explodes in anger?!\n"
                    + "Mel recalls only " + tasks.size() + " things");
        }
    }

    /**
     * Marks tasks as completed.
     * @param str indices of tasks.
     */
    private void markTasksAsDone(String... str) throws MelException {
        for (String s : str) {
            int idx = Integer.parseInt(s) - 1;
            Task task = tasks.get(idx);
            task.markTaskAsDone();
            mel.println("  " + task);
        }
    }

    /**
     * Marks tasks as incomplete.
     * @param str indices of tasks.
     */
    private void markTasksAsNotDone(String... str) throws MelException {
        for (String s : str) {
            int idx = Integer.parseInt(s) - 1;
            Task task = tasks.get(idx);
            task.markTaskAsNotDone();
            mel.println("  " + task);
        }
    }

    /**
     * Finds tasks that match user input from existing task list,
     * then outputs details of matching tasks
     * @param str input string.
     */
    private void findTask(String str) {
        try {
            mel.println("Mel brain rattles in recollection...");
            StringBuilder foundTasks = new StringBuilder();
            String s = str.split(" ", 2)[1].trim();
            for (Task t : tasks) {
                if (t.isMatch(s)) {
                    foundTasks.append(t).append("\n");
                } else {
                    //Fallthrough to continue iterating over all tasks.
                }
            }
            if (foundTasks.isEmpty()) {
                mel.println("Mel recalls nothing of the sort :)");
            } else {
                mel.println(foundTasks.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            mel.println("Mel's brain explodes in anger?!\n"
                    + "Mel recalls only " + tasks.size() + " things");
        }
    }

    /**
     * Updates save file of task list using Storage.
     * @see Storage
     */
    private void updateTasks() {
        int i = 0;
        String[] s = new String[tasks.size()];
        for (Task t : tasks) {
            s[i] = t.toString();
            i++;
        }
        try {
            storage.updateTasks(s);
        } catch (IOException e) {
            mel.println("Mel ran into an error"
                    + " creating save file :(");
        }
    }

    /**
     * Prints list of tasks.
     */
    private void printAll() {
        if (tasks.isEmpty()) {
            mel.println("Mel remembers... nothing?!");
        } else {
            mel.println("Mel remembers all your stuff~");
            int i = 0;
            for (Task t : tasks) {
                mel.println(++i + ". " + t);
            }
        }
    }
}
