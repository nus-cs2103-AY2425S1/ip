package mel.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
            updateTasks();
        } else if (cmd.equals("delete")) {
            handleTaskDeletion(input);
            updateTasks();
        } else if (cmd.equals("find")) {
            findTask(input);
        } else {
            mel.println("Mel remembers...");
            createTask(input);
            updateTasks();
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

        mel.println("  " + task);
        mel.println("Mel counts " + tasks.size()
                + " stuffs memorized XD");
    }

    /**
     * Handles deletion of tasks based on input.
     * @param input user input string.
     * @throws MelException on unexpected user input.
     */
    private void handleTaskDeletion(String... input) throws MelException {
        try {
            String[] s = input[0].trim()
                                 .split(" ", 2)[1]
                                 .trim()
                                 .split(" ");
            mel.println("Mel helps you forget...");
            ArrayList<Integer> indices = new ArrayList<>();
            for (String str : s) {
                if (!str.isEmpty()) {
                    indices.add(Integer.parseInt(str) - 1);
                }
            }
            deleteTasks(indices);
            mel.println("Mel counts " + tasks.size()
                    + " stuffs memorized XD");
        } catch (IndexOutOfBoundsException e) {
            mel.println("Mel's brain explodes in anger?! "
                    + "Mel recalls only " + tasks.size() + " things");
            mel.setHasException();
        }
    }

    /**
     * Deletes tasks based on input.
     * @param indices user input string.
     * @throws MelException on unexpected user input.
     */
    private void deleteTasks(ArrayList<Integer> indices) throws MelException {
        if (indices.isEmpty()) {
            throw new MelException("Mel is confused... \n"
                    + "Mel doesn't understand you :((");
        }
        indices.sort(null);
        while (!indices.isEmpty()) {
            int idx = indices.get(indices.size() - 1);
            mel.println("  " + tasks.get(idx));
            tasks.remove(idx);
            indices.remove(idx);
        }
    }

    /**
     * Loads tasks from save file.
     */
    public void loadTasks() {
        try {
            ArrayList<String> taskList = storage.loadTasks();
            for (String s : taskList) {
                loadTask(s);
            }
        } catch (IOException e) {
            /* Fallthrough: No save file to load from,
            new task list and save file are created instead*/
        } catch (TaskException | MelException e) {
            mel.println(e.getMessage());
            mel.setHasException();
        }
    }

    /**
     * Loads individual tasks to task list.
     * @param str task string in save file format.
     */
    private void loadTask(String str) throws TaskException, MelException {
        String[] s = str.split("\\|");
        mel.println(Arrays.toString(s));
        String t;
        switch (s[0]) {
        case "T":
            t = "todo " + s[2];
            createTask(t);
            break;
        case "D":
            t = "deadline " + s[2] + " /by " + s[3];
            createTask(t);
            break;
        case "E":
            t = "event " + s[2] + " /from " + s[3] + " /to " + s[4];
            createTask(t);
            break;
        default:
            throw new TaskException("Mel is stunned!\n"
                    + "Mel couldn't understand your save file?!");
        }
        if (Objects.equals(s[1], "X")) {
            tasks.get(tasks.size() - 1).markTaskAsDone();
        } else if (Objects.equals(s[1], " ")) {
            //Fallthrough: task is marked incomplete by default.
        } else {
            throw new TaskException("Mel is stunned!\n"
                    + "Mel couldn't understand your save file?!");
        }
    }

    /**
     * Handles marking of tasks completion based on input.
     * @param input user input string.
     * @throws MelException on unexpected user input.
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
        } catch (IndexOutOfBoundsException e) {
            mel.println("Mel's brain explodes in anger?!\n"
                    + "Mel recalls only " + tasks.size() + " things");
            mel.setHasException();
        }
    }

    /**
     * Marks tasks as completed.
     * @param strings indices of tasks.
     */
    private void markTasksAsDone(String... strings) {
        for (String s : strings) {
            if (!s.isEmpty()) {
                int idx = Integer.parseInt(s) - 1;
                Task task = tasks.get(idx);
                task.markTaskAsDone();
                mel.println("  " + task);
            } else {
                //fallthrough for invalid string value.
            }
        }
    }

    /**
     * Marks tasks as incomplete.
     * @param strings indices of tasks.
     */
    private void markTasksAsNotDone(String... strings) {
        for (String s : strings) {
            if (!s.isEmpty()) {
                int idx = Integer.parseInt(s) - 1;
                Task task = tasks.get(idx);
                task.markTaskAsNotDone();
                mel.println("  " + task);
            } else {
                //fallthrough for invalid string value.
            }
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
            mel.setHasException();
        }
    }

    /**
     * Updates save file of task list using Storage.
     * @throws MelException if save file could not be executed on.
     * @see Storage
     */
    private void updateTasks() throws MelException {
        int i = 0;
        String[] s = new String[tasks.size()];
        for (Task t : tasks) {
            s[i] = t.toSaveString();
            i++;
        }
        try {
            storage.updateTasks(s);
        } catch (IOException e) {
            mel.setHasException();
            throw new MelException("Mel ran into an error"
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
