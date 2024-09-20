package storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Manages a list of tasks, including adding, removing, and marking tasks as done or undone.
 * The TaskList class is responsible for handling all operations on the tasks stored in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MM uuuu");

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list the list of tasks to be managed
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Adds a task to the task list.
     * The user is prompted to choose the type of task (Todo, Deadline, or Event),
     * and additional input is gathered as needed.
     *
     * @param task the name of the task to be added
     */
    public void addTask(String task) {
        Scanner sc = new Scanner(System.in);
        if (isDuplicate(sc, task)) {
            return;
        }
        System.out.println("""
                Choose a task type (1, 2 or 3):
                1. Todo - No end date
                2. Deadline - Has end date
                3. Event - Has start and end date
                """);
        switch (getInputFromUser(sc, "(1, 2 or 3) > ")) {
        case "1":
            this.tasks.add(new Todo(task));
            System.out.println("Friday > Okay, I've added a todo: " + task);
            break;
        case "2":
            System.out.println("What is the deadline? In dd mm yyyy");
            this.tasks.add(new Deadline(task, getDate("d", sc)));
            System.out.println("Friday > Okay, I've added a deadline: " + task);
            break;
        case "3":
            System.out.println("What is the start date? In dd mm yyyy");
            LocalDate start = getDate("es", sc);
            System.out.println("What is the end date? In dd mm yyyy");
            LocalDate end = getDate("ee", sc);
            this.tasks.add(new Event(task, start, end));
            System.out.println("Friday > Okay, I've added an event: " + task);
            break;
        default:
            System.out.println("Invalid task type! Try adding again.");
        }
    }

    /**
     * Removes a task from the task list based on its index.
     * The task is removed from the list, and a confirmation message is displayed.
     *
     * @param task the index of the task to be removed
     */
    public String removeTask(int task) {
        Task temp = tasks.get(task);
        this.tasks.remove(task);

        String response = "Friday > Successfully removed: " + temp.getName();
        return response + "\nFriday > You now have " + getSize() + " total tasks left.";
    }

    /**
     * Returns a string representation of the task list, including the number of completed,
     * incomplete, and total tasks.
     *
     * @return a string representation of the task list
     */
    @Override
    public String toString() {
        String ans = "";
        ans += String.format("Completed: %d tasks | Incomplete: %d tasks | Total: %d tasks%n%n",
                countCompleted(true), countCompleted(false), tasks.size());
        assert countCompleted(true) + countCompleted(false) == tasks.size() : "Invalid completion breakdown";
        for (int i = 1; i <= tasks.size(); i++) {
            ans += String.format("%d: %s%n", i, tasks.get(i - 1).toString());
        }
        return ans;
    }

    /**
     * Returns the size of the task list.
     *
     * @return the number of tasks in the list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks managed by this TaskList.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task as done or undone based on the specified action.
     * The user can choose to mark a task as done or undone, and a confirmation message is displayed.
     *
     * @param action the action to perform ("mark" for done, any other value for undone)
     * @param task   the index of the task to be marked
     */
    public String setTaskCompletion(String action, int task) {
        Task temp = tasks.get(task);
        if (action.equals("mark") || action.equals("Mark")) {
            if (!temp.isDone()) {
                temp.setDone();
            }
            return "Friday > Good job! Marked as done :)";
        } else {
            if (temp.isDone()) {
                this.tasks.get(task).setUndone();
            }
            return "Friday > Oh man! Marked as undone :(";
        }
    }

    /**
     * Prompts the user for input with a given template and returns the user's response.
     * The method continues to prompt until valid input is provided.
     *
     * @param sc       the Scanner object used to read user input
     * @param template the prompt template to display to the user
     * @return the user's input as a trimmed string
     */
    public String getInputFromUser(Scanner sc, String template) {
        while (true) {
            System.out.print(template);
            String str = sc.nextLine();
            if (str.isEmpty()) {
                System.out.println("Friday > Invalid input! Did you make sure to type something?");
            } else {
                return str.trim();
            }
        }
    }

    /**
     * Prompts the user for a date and returns the date as a LocalDate object.
     * The date format is validated, and the method continues to prompt until a valid date is provided.
     *
     * @param type the type of date expected ("d" for deadline, "es" for event start, "ee" for event end)
     * @param sc   the Scanner object used to read user input
     * @return the user's input as a LocalDate object
     */
    public LocalDate getDate(String type, Scanner sc) {
        try {
            return switch (type) {
            case "d" -> LocalDate.parse(getInputFromUser(sc, "Deadline (in dd mm yyyy) > "), this.inputFormatter);
            case "es" ->
                    LocalDate.parse(getInputFromUser(sc, "Start Date (in dd mm yyyy) > "), this.inputFormatter);
            case "ee" -> LocalDate.parse(getInputFromUser(sc, "End Date (in dd mm yyyy) > "), this.inputFormatter);
            default -> getDate(type, sc);
            };
        } catch (DateTimeParseException e) {
            System.out.println("Friday > Invalid date format! Please follow dd mm yyyy format! e.g 26 06 2002");
        }
        return getDate(type, sc);
    }

    /**
     * Counts the number of tasks that are either completed or incomplete.
     *
     * @param isComplete true to count completed tasks, false to count incomplete tasks
     * @return the number of tasks that match the specified completion status
     */
    public int countCompleted(boolean isComplete) {
        int count = 0;
        if (isComplete) {
            for (Task task : this.tasks) {
                if (task.isDone()) {
                    count++;
                }
            }
        } else {
            for (Task task : this.tasks) {
                if (!task.isDone()) {
                    count++;
                }
            }
        }
        assert count >= 0 : "Invalid number of completed tasks";
        return count;
    }

    /**
     * Checks if a task with the specified name already exists in the task list.
     * If a duplicate task is found, the user is prompted to decide whether to proceed or not.
     * The method returns true if the user chooses not to proceed, and false if there are no duplicates or
     * if the user chooses to proceed despite the duplicate.
     *
     * @param sc     the Scanner object used to read user input
     * @param target the name of the task to check for duplicates
     * @return true if a duplicate is found and the user chooses not to proceed, false otherwise
     */
    public boolean isDuplicate(Scanner sc, String target) {
        for (Task task : this.tasks) {
            if (task.equals(target)) {
                System.out.println("Friday > There is already a task with the same name: " + target);
                System.out.println("Friday > Do you still want to proceed? (Y/N)");
                String ans = getInputFromUser(sc, "Y/N: ");
                return !ans.equalsIgnoreCase("y");
            }
        }
        return false;
    }
}
