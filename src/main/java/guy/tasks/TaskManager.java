package guy.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import guy.exception.GuyException;
import guy.storage.Storage;

/**
 * Class responsible for managing a list of tasks in the ThatOneGuy application.
 * Only one instance of this class exists at a time.
 */
public class TaskManager {
    private static TaskManager tm;
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskManager object.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves the instance of this class.
     *
     * @return the instance of TaskManager
     */
    public static TaskManager getInstance() {
        if (tm == null) {
            tm = new TaskManager();
        }
        return tm;
    }

    /**
     * Loads a task from its string representation, adding it to the list.
     *
     * @param data string representation of the task
     */
    public void loadData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Arrays.stream(data.split("\n"))
                .map(line -> line.split("\\s*\\|\\s*"))
                .forEach(parts -> {
                    try {
                        String type = parts[0];
                        Task task = switch(type) {
                        case "T" -> new ToDo(parts[2]);
                        case "D" -> new Deadline(parts[2], LocalDateTime.parse(parts[3], formatter));
                        case "E" -> new Event(parts[2], LocalDateTime.parse(parts[3], formatter),
                                    LocalDateTime.parse(parts[4], formatter));
                        default -> throw new GuyException("Why did "
                                + "you give me a file with an invalid line, you dingus...");
                        };

                        if (parts[1].equals("1")) {
                            task.markComplete();
                        }
                        tasks.add(task);
                    } catch (GuyException e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    /**
     * Displays the list of tasks, or a rather rude message if no task exists.
     */
    public void list() {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("You really don't have anything better to do?");
        } else {
            System.out.println("Here are your damned tasks. Complete them or something.");
            for (int i = 0; i < len; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Retrieves a copy of the task list.
     *
     * @return an ArrayList with the current tasks
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Marks a task as completed, screaming at the user if unable to do so.
     *
     * @param input index of the task to mark as done
     * @throws GuyException if the input is missing/invalid/out of range, or the task list is empty
     */
    public void markTask(String input) throws GuyException {
        if (tasks.isEmpty()) {
            throw new GuyException("You have nothing to do. You lazy or what?");
        } else if (input.isEmpty()) {
            throw new GuyException("What do you want me to mark, you moron!?");
        }
        String rest = input.replaceAll("\\D+", "");
        if (rest.isEmpty()) {
            throw new GuyException("I can't work without a valid input. Screw you.");
        }
        int id = Integer.parseInt(rest) - 1;
        if (id < 0 || id >= tasks.size()) {
            throw new GuyException("Consider picking a number that's actually in range.");
        } else {
            if (tasks.get(id).isComplete()) {
                System.out.println("You dingus. This task was already done:");
            } else {
                tasks.get(id).markComplete();
                System.out.println("Eh. Consider this task done:");
            }
            Storage.saveData();
            System.out.println(tasks.get(id).toString());
        }
    }

    /**
     * Marks a task as incomplete, screaming at the user if unable to do so.
     *
     * @param input index of the task to mark as not done
     * @throws GuyException if the input is missing/invalid/out of range, or the task list is empty
     */
    public void unmarkTask(String input) throws GuyException {
        if (tasks.isEmpty()) {
            throw new GuyException("You have nothing to do. You lazy or what?");
        } else if (input.isEmpty()) {
            throw new GuyException("What do you want me to unmark, you moron!?");
        }
        String rest = input.replaceAll("\\D+", "");
        if (rest.isEmpty()) {
            throw new GuyException("I can't work without a valid input. Screw you.");
        }
        int id = Integer.parseInt(rest) - 1;
        if (id < 0 || id >= tasks.size()) {
            throw new GuyException("Consider picking a number that's actually in range.");
        } else {
            if (!tasks.get(id).isComplete()) {
                System.out.println("You dingus. This task still hasn't been done:");
            } else {
                tasks.get(id).markIncomplete();
                System.out.println("Sucks to be you. Looks like you haven't done this task:");
            }
            Storage.saveData();
            System.out.println(tasks.get(id).toString());
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param cmd   the type of task to add, can be a todo/deadline/event
     * @param input task description, including dates and times, where appropriate
     * @throws GuyException if the input is invalid
     */
    public void addTask(String cmd, String input) throws GuyException {
        try {
            if (input.isEmpty()) {
                throw new GuyException("You really think I can add an EMPTY TASK!?");
            }
            Task task;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            switch(cmd) {
            case "todo":
                task = new ToDo(input);
                break;
            case "deadline":
                if (!input.contains("/by") || input.indexOf("/by") == input.length() - 3) {
                    throw new GuyException("That isn't even a valid description!");
                }
                String[] splitted = input.split("/by", 2);
                task = new Deadline(splitted[0].trim(), LocalDateTime.parse(splitted[1].trim(), dtf));
                break;
            case "event":
                if (
                        !input.contains("/from")
                                || !input.contains("/to")
                                || input.indexOf("/from") == input.length() - 5
                                || input.indexOf("/to") == input.length() - 2
                ) {
                    throw new GuyException("That isn't even a valid description!");
                }
                String[] splitFrom = input.split("/from", 2);
                String[] splitTo = splitFrom[1].split("/to", 2);
                task = new Event(splitFrom[0].trim(),
                        LocalDateTime.parse(splitTo[0].trim(), dtf),
                        LocalDateTime.parse(splitTo[1].trim(), dtf));
                break;
            default:
                throw new GuyException("That's not even a task type!");
            }
            tasks.add(task);
            Storage.saveData();
            System.out.println("Fine. Added this lousy task:");
            System.out.println(task);
            System.out.println("That's " + tasks.size() + " tasks for your ass to handle.");
        } catch (GuyException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeException e) {
            System.out.println("I got a problem, and it sure as f*** ain't my fault! It says: \n" + e.getMessage());
        }
    }

    /**
     * Removes a task from the list.
     *
     * @param input index of the task to remove
     * @throws GuyException if the input is missing/invalid/out of range, or the task list is already empty
     */
    public void deleteTask(String input) throws GuyException {
        try {
            if (tasks.isEmpty()) {
                throw new GuyException("You have nothing to delete. You dumb or what?");
            } else if (input.isEmpty()) {
                throw new GuyException("What do you want me to delete, you moron!?");
            }
            String rest = input.replaceAll("\\D+", "");
            if (rest.isEmpty()) {
                throw new GuyException("I can't work without a valid input. Screw you.");
            }
            int id = Integer.parseInt(rest) - 1;
            if (id < 0 || id >= tasks.size()) {
                throw new GuyException("Consider picking a number that's actually in range.");
            } else {
                Task task = tasks.get(id);
                tasks.remove(id);
                Storage.saveData();
                System.out.println("There goes this dumb task:");
                System.out.println(task);
                System.out.println("Your ass still needs to handle " + tasks.size() + " more tasks.");
            }
        } catch (GuyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves a list of tasks matching the given keywords.
     *
     * @param input keywords used in the search
     * @throws GuyException if no task matches the input
     */
    public void findTask(String input) throws GuyException {
        List<Task> matchingTasks = tasks.stream()
                        .filter(t -> t.toString().contains(input))
                                .toList();

        System.out.println("These are your damned tasks, that actually match the keywords:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, matchingTasks.get(i).toString());
        }
    }

    /**
     * Deletes every task on the list.
     */
    public void clearTasks() {
        tasks.clear();
    }
}
