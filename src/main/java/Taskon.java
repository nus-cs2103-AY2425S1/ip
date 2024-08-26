import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Taskon {

    /**
     * The main method runs the Taskon application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        ArrayList<Task> tasks = FileManager.loadTasks();

        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String description = scanner.nextLine();
            Command command = Command.fromString(description);

            switch (command) {
                case BYE:
                    exit();
                    return;

                case LIST:
                    listItems(tasks);
                    System.out.println("\n");
                    break;

                case MARK:
                    try {
                        int index = description.charAt(5) - '0';
                        tasks.get(index - 1).markAsDone();
                        completed(tasks.get(index - 1));
                        FileManager.saveTasks(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You only have " + tasks.size() + " tasks!\n");
                    } catch (TaskonException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case UNMARK:
                    try {
                        int index = Integer.parseInt(description.substring(7));
                        tasks.get(index - 1).markAsUndone();
                        uncompleted(tasks.get(index - 1));
                        FileManager.saveTasks(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You only have " + tasks.size() + " tasks!\n");
                    } catch (TaskonException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case DELETE:
                    try {
                        int index = Integer.parseInt(description.substring(7));
                        Task task = tasks.get(index-1);
                        tasks.remove(index-1);
                        System.out.println("Alright, I've removed this task:\n    " + task + "\nNow you have " + tasks.size() + " tasks.\n");
                        FileManager.saveTasks(tasks);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You only have " + tasks.size() + " tasks!\n");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Integer Input!\n");
                    }
                    break;

                case ADD:
                    try {
                        Task t = getTask(description);
                        tasks.add(t);
                        System.out.println("Got it! I've added this task:\n    " + t + "\nNow you have " +
                                tasks.size() + " tasks in your list.\n");
                        FileManager.saveTasks(tasks);
                    } catch (TaskonException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case ON:
                    try {
                        String date = description.substring(3);
                        getTasksOnDate(date, tasks);
                    } catch (DateTimeParseException e) {
                        System.out.println("Please input date in correct format.\n");
                    }

                    break;

                case UNKNOWN:
                default:
                    System.out.println("Oh no! I don't understand the command: " + description + "\nPlease try again.\n");
                    break;
            }
        }
    }

    /**
     * Enumeration for the different types of tasks that can be created.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN;

        /**
         * Converts a string command into a TaskType enum.
         *
         * @param description The command string.
         * @return The corresponding TaskType enum, or UNKNOWN if the command is unrecognized.
         */
        public static TaskType fromString(String description) {
            if (description.startsWith("todo ")) {
                return TODO;
            } else if (description.startsWith("deadline ")) {
                return DEADLINE;
            } else if (description.startsWith("event ")) {
                return EVENT;
            } else {
                return UNKNOWN;
            }
        }
    }

    /**
     * Parses a task creation command and returns the corresponding Task object.
     *
     * @param description The task creation command.
     * @return The created Task object.
     * @throws TaskonException If the command is invalid or incomplete.
     */
    private static Task getTask(String description) throws TaskonException {
        TaskType taskType = TaskType.fromString(description);
        Task t = null;

        switch (taskType) {
            case TODO:
                if (description.length() < 6) {
                    throw new TaskonException("It looks like you forgot to add a description for your todo. Please try again.\n");
                }
                t = new Todo(description.substring(5).trim());
                break;

            case DEADLINE:
                if (!description.contains("/by")) {
                    throw new TaskonException("It looks like you forgot to add a due date. Please try again.\n");
                }
                try {
                    String date = description.substring(description.indexOf("/by") + 4);
                    String substring = description.substring(9, description.indexOf("/by")).trim();
                    t = new Deadline(substring, date);
                    break;
                } catch (DateTimeParseException e) {
                    throw new TaskonException("Invalid date! Please use yyyy-MM-dd HHmm format!\n");
                }

            case EVENT:
                if (!description.contains("/from") || !description.contains("/to")) {
                    throw new TaskonException("It looks like you forgot to add a duration for your event. Please try again.\n");
                }
                if (description.indexOf("/from")-1 < 6) {
                    throw new TaskonException("It looks like you forgot to add a description for your event. Please try again.\n");
                }
                int start = description.indexOf("/from") + 6;
                int end = description.indexOf("/to") + 4;
                String from = description.substring(start, description.indexOf("/to")-1);
                String to = description.substring(end);
                String desc = description.substring(6, description.indexOf("/from")-1).trim();
                try {
                    t = new Event(desc, from, to);
                } catch (DateTimeParseException e) {
                    throw new TaskonException("Invalid date! Please use yyyy-MM-dd HHmm format!\n");
                }

                break;

            case UNKNOWN:
            default:
                throw new TaskonException("Oh no! I don't understand the command: " + description + "\nPlease try again.\n");
        }
        return t;
    }

    /**
     * Prints a greeting message when the application starts.
     */
    public static void greet() {
        String greeting = "Hello! I'm Taskon\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    /**
     * Prints an exit message when the application terminates.
     */
    public static void exit() {
        String exiting = "Bye. Hope to see you again soon!\n";
        System.out.println(exiting);
    }

    /**
     * Prints a message indicating that a task has been marked as completed.
     *
     * @param task The completed task.
     */
    public static void completed(Task task) {
        String complete = "Woohoo! Task complete! \nI've marked this as done:\n";
        System.out.println(complete + task.toString() + "\n");
    }

    /**
     * Prints a message indicating that a task has been marked as not completed.
     *
     * @param task The task marked as not done.
     */
    public static void uncompleted(Task task) {
        String uncompleted = "Got it! No rush, I've marked it as not done yet:\n";
        System.out.println(uncompleted + task.toString() + "\n");
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     */
    public static void listItems(ArrayList<Task> tasks) {
        System.out.println("Here's what we've got on your to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(i+1 + "." + t.toString());
        }
    }

    public static void getTasksOnDate(String date, ArrayList<Task> tasks) {
        LocalDate localDate = LocalDate.parse(date);
        boolean isFound = false;
        System.out.println("Tasks on " + localDate + " :");
        for (Task task : tasks) {
            if (task.occursOn(localDate)) {
                System.out.println(task);
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Hmm, it looks like you've got a free day!\n");
        }
    }
}
