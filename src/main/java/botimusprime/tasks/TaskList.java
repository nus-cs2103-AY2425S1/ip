package botimusprime.tasks;

import botimusprime.parser.Parser;
import botimusprime.storage.Storage;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks, including operations for adding,
 * marking, deleting, and displaying tasks. It also interacts with the Storage class
 * to save changes to disk.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList with an empty list of tasks and associates it with a file for storage.
     *
     * @param fileName The name of the file where tasks are stored.
     */
    public TaskList(String fileName) {
        this.storage = new Storage(fileName);
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks and associates it with a file for storage.
     *
     * @param tasks    The initial list of tasks.
     * @param fileName The name of the file where tasks are stored.
     */
    public TaskList(ArrayList<Task> tasks, String fileName) {
        this.tasks = tasks;
        this.storage = new Storage(fileName);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Displays the list of tasks.
     * If the list is empty, it displays a message indicating that there are no tasks.
     */
    public void showList() {
        if (tasks.isEmpty()) {
            System.out.println("congrats bro u got nth to do in the todolist, respect 100");
            return;
        }
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Marks a task as done based on the index provided in the input.
     * If the input is invalid or the index is out of range, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public void markDone(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to mark");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        tasks.get(idx).markAsDone();
        storage.saveToDisk(this);
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n" +
                tasks.get(idx));
    }

    /**
     * Marks a task as not done based on the index provided in the input.
     * If the input is invalid or the index is out of range, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public void markUndone(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to unmark");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        tasks.get(idx).markAsUndone();
        storage.saveToDisk(this);
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n" +
                tasks.get(idx));
    }

    /**
     * Deletes a task based on the index provided in the input.
     * If the input is invalid or the index is out of range, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public void delete(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to delete");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        Task task = tasks.get(idx);
        tasks.remove(idx);
        storage.saveToDisk(this);
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n" + task + "\nNow you have "
                        + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a new To\Do task to the list based on the input.
     * If the input does not contain a valid description, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public void addToDo(String input) {
        if (input.length() <= 5) {
            System.out.println("eh bro udw to put ur description of ur task issit");
            return;
        }

        ToDo task = new ToDo(input.substring(5), false);

        tasks.add(task);
        storage.saveToDisk(this);

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n",
                                task, tasks.size())
                                        + "____________________________________________________________\n");
    }

    /**
     * Adds a new Deadline task to the list based on the input.
     * If the input does not contain a valid description or deadline, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public void addDeadline(String input) {
        if (input.length() <= 9 || !input.contains("/by")) {
            System.out.println("brother u forgot to type all the deadline task details plz.");
        }
        String[] parser = input.split("/by ");

        if (parser.length < 2 || parser[1].trim().isEmpty()) {
            System.out.println("wheres the deadline!!");
            return;
        }
        String description = parser[0].substring(9);
        String deadline = parser[1];

        if (deadline.isEmpty()) {
            System.out.println("eh bro u got due date anot");
            return;
        } else if (description.isEmpty()) {
            System.out.println("eh bro ur task no description leh wake up ur idea");
        }

        Deadline task = new Deadline(description,
                false,
                        Parser.stringToDateTime(deadline));

        tasks.add(task);
        storage.saveToDisk(this);

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n",
                                task, tasks.size())
                                        + "____________________________________________________________\n");
    }

    /**
     * Adds a new Event task to the list based on the input.
     * If the input does not contain valid times or description, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public void addEvent(String input) {
        if (input.length() <= 6 || input.substring(6)
                .trim()
                        .isEmpty()) {
            System.out.println("brother u forgot to type all the event task details");
            return;
        } else if (!input.contains("/from") || !input.contains("/to")) {
            System.out.println("eh bro ur event no time issit");
            return;
        }

        String[] parser = input.split("/from ");

        if (parser.length < 2 || parser[1].trim()
                .isEmpty()) {
            System.out.println("hi plz actually put a time");
            return;
        }

        String description = parser[0].substring(6);
        String times = parser[1];

        String[] fromAndTo = times.split("/to ");

        if (fromAndTo.length < 2 || fromAndTo[0].trim().
                isEmpty() || fromAndTo[1].trim()
                        .isEmpty()) {
            System.out.println("hi plz actually put times in ur EVENT");
            return;
        }

        String from = fromAndTo[0].trim();
        String to = fromAndTo[1].trim();

        Event task = new Event(description,
                false,
                        Parser.stringToDateTime(from),
                                Parser.stringToDateTime(to));

        tasks.add(task);
        storage.saveToDisk(this);

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n",
                                task, tasks.size())
                                        + "____________________________________________________________\n");
    }

    public void findTask(String input) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        if (input.length() <= 5) {
            System.out.println("brother u forgot to type the task to find");
            return;
        }

        String query = input.substring(5).trim();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(query.toLowerCase())) {
                foundTasks.add(task);
            }
        }

        System.out.println(
                "____________________________________________________________\n"
                        + "Here are the matching tasks in your list:\n"
        );
        if (foundTasks.isEmpty()) {
            System.out.println("lol gg no tasks match ur search bruh");
        } else {
            for (Task task : foundTasks) {
                System.out.println(task);
            }
        }
    }
}