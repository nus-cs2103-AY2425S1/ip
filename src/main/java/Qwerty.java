import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class encapsulates a task helper chatbot.
 */
public class Qwerty {

    /** True if the bot is currently chatting and accepting input */
    private boolean isChatting;
    /** List of tasks entered by the user */
    private ArrayList<Task> tasks;

    public Qwerty() {
        this.isChatting = true;
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Parses user raw input into a map and returns it.
     * The map contains parameter-argument pairs, except the
     * preset keys "command" and "command_args" for the command and its main argument.
     *
     * @param rawInput The raw command line input from the user.
     * @return A hashmap containing parameters and their arguments
     */
    private HashMap<String, String> parse(String rawInput) {
        HashMap<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(rawInput).useDelimiter(" /");

        // extract main command and its argument, if any
        if (scanner.hasNext()) {
            String[] mainCommand = scanner.next().split(" ", 2);
            map.put("command", mainCommand[0]);

            // only create args entry if arguments are given
            if (mainCommand.length > 1) {
                map.put("command_args", mainCommand[1]);
            }
        }

        // extract additional parameters and their arguments
        while (scanner.hasNext()) {
            String[] params = scanner.next().split(" ", 2);

            // only create entry for a parameter if its arguments are given
            if (params.length > 1) {
                map.put(params[0], params[1]);
            }
        }

        return map;
    }

    /**
     * Prints a greeting message.
     */
    public void greet() {
        System.out.println("""

                Hello! I'm Qwerty.
                What can I do for you?""");
    }

    /**
     * Prints a goodbye message.
     */
    public void sayGoodbye() {
        System.out.println("""
                
                Bye. Hope to see you again soon!""");
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("\nGot it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ")
                + "in the list.");
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        try {
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println("\nNoted. I've removed this task:\n" + task
                    + "\nNow you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ")
                    + "in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThat index is invalid.");
        }
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index String describing the index of the task to be deleted.
     */
    public void deleteTask(String index) {
        try {
            deleteTask(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a number as the index.");
        }
    }

    /**
     * Prints the list of tasks.
     */
    public void listTasks() {
        System.out.println("\nHere are the tasks in your list:");
        int taskNumber = 1;
        for (Task task: tasks) {
            System.out.println(taskNumber + "." + task);
            taskNumber++;
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked, starting from 1.
     */
    public void markTaskAsDone(int index) {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            System.out.println("\nNice! I've marked this task as done:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThat index is invalid.");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index String representing the index of the task to be marked.
     */
    public void markTaskAsDone(String index) {
        try {
            markTaskAsDone(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a number as the index.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked, starting from 1.
     */
    public void markTaskAsNotDone(int index) {
        try {
            Task task = tasks.get(index - 1);
            task.markAsNotDone();
            System.out.println("\nOK, I've marked this task as not done yet:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThat index is invalid.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param index String representing the index of the task to be marked.
     */
    public void markTaskAsNotDone(String index) {
        try {
            markTaskAsNotDone(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a number as the index.");
        }
    }

    /**
     * Starts the chatbot and run the main chat loop.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        isChatting = true;
        greet();

        while (isChatting) {
            System.out.println(); // blank line before user input
            String rawInput = scanner.nextLine();
            HashMap<String, String> map = parse(rawInput);
            String command = map.get("command");
            String args = map.get("command_args");

            try {
                if (command == null) {
                    throw new QwertyException("""
                            Wow. You hit enter without saying anything.
                            Speak up or I can't help you.""");
                }

                switch (command) {

                    case "bye":
                        isChatting = false;
                        sayGoodbye();
                        break;

                    case "list":
                        listTasks();
                        break;

                    case "mark":
                        if (args == null) {
                            throw new QwertyException("""
                                    You forgot to give me a task number.""");
                        }
                        markTaskAsDone(args);
                        break;

                    case "unmark":
                        if (args == null) {
                            throw new QwertyException("""
                                    You forgot to give me a task number.""");
                        }
                        markTaskAsNotDone(args);
                        break;

                    case "todo":
                        if (args == null) {
                            throw new QwertyException("""
                                    The description of a Todo cannot be empty.""");
                        }
                        Task todoTask = new Todo(args);
                        addTask(todoTask);
                        break;

                    case "deadline":
                        String by = map.get("by");
                        if (args == null) {
                            throw new QwertyException("""
                                    The description of a deadline cannot be empty.""");
                        }
                        if (by == null) {
                            throw new QwertyException("""
                                    When is your deadline? You didn't say.""");
                        }
                        Task deadlineTask = new Deadline(args, by);
                        addTask(deadlineTask);
                        break;

                    case "event":
                        String from = map.get("from");
                        String to = map.get("to");
                        if (args == null) {
                            throw new QwertyException("""
                                    The description of an event cannot be empty.""");
                        }
                        if (from == null) {
                            throw new QwertyException("""
                                    Your event starts from...? You didn't say.""");
                        }
                        if (to == null) {
                            throw new QwertyException("""
                                    Your event ends at...? You didn't say.""");
                        }
                        Task eventTask = new Event(args, from, to);
                        addTask(eventTask);
                        break;

                    case "delete":
                        if (args == null) {
                            throw new QwertyException("""
                                    You forgot to give me a task number.""");
                        }
                        deleteTask(args);
                        break;

                    default:
                        System.out.println("\nThat word isn't in my dictionary. Try again.");
                }
            } catch (QwertyException e) {
                System.out.println("\n" + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Qwerty chatBot = new Qwerty();
        chatBot.start();
    }
}
