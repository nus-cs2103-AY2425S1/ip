package alexer;

import alexer.command.Command;
import alexer.command.CommandHandler;
import alexer.task.Deadline;
import alexer.task.Event;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.task.Todo;
import alexer.ui.Response;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * lexer (ˈleksər) - A computer program that performs lexical analysis.
 *
 * @author sayomaki
 */
public class Alexer {
    private static final String BREAK = "____________________________________________________________";

    /** Wordart logo of the chatbot **/
    public static final String LOGO = """
                     .     .                           
                    /|     |     ___  _  .-   ___  .___
                   /  \\    |   .'   `  \\,'  .'   ` /   \\
                  /---'\\   |   |----'  /\\   |----' |   '
                ,'      \\ /\\__ `.___, /  \\  `.___, /   """;

    /** Name of the chatbot **/
    public static final String NAME = "Alexer";

    private static Alexer alexer;

    private final Scanner scanner;

    private final TaskManager tasks;

    private final Prompter prompter;

    private final CommandHandler commandHandler;

    /**
     * Returns an instance of the chatbot.
     *
     * @return the current bot instance
     */
    public static Alexer getInstance() {
        return alexer;
    }

    /**
     * Creates a new chatbot, and instantiate the relevant fields.
     * Also populates the instance of the chatbot.
     */
    public Alexer() {
        alexer = this;
        scanner = new Scanner(System.in);
        tasks = new TaskManager();
        prompter = new Prompter();
        commandHandler = new CommandHandler();
    }

    /**
     * Returns the prompter instance that handles all bot responses
     *
     * @return An instance of the prompter
     */
    public Prompter getPrompter() {
        return prompter;
    }

    /**
     * Returns the task manager instance for the bot, which
     * handles all task-related actions and operations.
     *
     * @return An instance of the task manager
     */
    public TaskManager getTaskManager() {
        return tasks;
    }

    public void addTodo(List<String> arguments) {
        String description = String.join(" ", arguments);
        if (description.isEmpty()) {
            System.out.println(BREAK);
            System.out.println("Oh-no! You forgot to include a description for your task!");
            System.out.println(BREAK);
            return;
        }

        Todo todo = new Todo(description);
        tasks.addTask(todo);
        tasks.saveTasks();

        System.out.println(BREAK);
        System.out.format("Sure! I’ve added the todo to your list:\n\n\t%s\n", todo);
        System.out.format("\nYou have %d tasks now.\n", tasks.getTaskCount());
        System.out.println(BREAK);
    }

    public void addDeadline(List<String> arguments) {
        int keywordIndex = 0;
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).equals("/by")) keywordIndex = i;
        }

        String description = arguments.stream().limit(keywordIndex)
                .collect(Collectors.joining(" "));
        String by = arguments.stream().skip(keywordIndex + 1).collect(Collectors.joining(" "));

        LocalDateTime dateTime;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            dateTime = LocalDateTime.parse(by, dateTimeFormat);
        } catch (DateTimeException e) {
            new Response("Uh-oh, I failed to understand what is the task deadline!").printToConsole();
            return;
        }

        Deadline deadline = new Deadline(description, dateTime);
        tasks.addTask(deadline);
        tasks.saveTasks();

        System.out.println(BREAK);
        System.out.format("No problems! I’ve added the task to your list:\n\n\t%s\n", deadline);
        System.out.format("\nYou have %d tasks now.\n", tasks.getTaskCount());
        System.out.println(BREAK);
    }

    public void addEvent(List<String> arguments) {
        int fromIndex = 0;
        int toIndex = 0;
        for (int i = 0; i < arguments.size(); i++) {
            if (arguments.get(i).equals("/from")) fromIndex = i;
            if (arguments.get(i).equals("/to")) toIndex = i;
        }

        String description = arguments.stream().limit(fromIndex)
                .collect(Collectors.joining(" "));
        String from = arguments.stream().limit(toIndex).skip(fromIndex + 1)
                .collect(Collectors.joining(" "));
        String to = arguments.stream().skip(toIndex + 1).collect(Collectors.joining(" "));

        Event event = new Event(description, from, to);
        tasks.addTask(event);
        tasks.saveTasks();

        System.out.println(BREAK);
        System.out.format("Noted! I’ve added a new event to your tasks:\n\n\t%s\n", event);
        System.out.format("\nYou have %d tasks now.\n", tasks.getTaskCount());
        System.out.println(BREAK);
    }

    public void markTaskDone(int index) {
        // assume input here is valid, we will handle exceptions later
        tasks.getTask(index - 1).markAsDone();
        tasks.saveTasks();

        System.out.println(BREAK);
        System.out.println("Great job completing the task! Keep up the great work!");
        System.out.format("\t%s\n", tasks.getTask(index - 1));
        System.out.println(BREAK);
    }

    public void unmarkTaskDone(int index) {
        // assume input here is valid, we will handle exceptions later
        tasks.getTask(index - 1).unmarkDone();
        tasks.saveTasks();

        System.out.println(BREAK);
        System.out.println("Alright, it seems you are not done with that yet.");
        System.out.println("I have unmarked it for you.");
        System.out.format("\t%s\n", tasks.getTask(index - 1));
        System.out.println(BREAK);
    }

    /**
     * Processes the user input provided, and executes the
     * relevant commands/operations
     * @param input the user input string
     */
    public void processInput(String input) {
        List<String> arguments = new ArrayList<>(List.of(input.split(" ")));
        String command = arguments.remove(0);

        Command cmd = commandHandler.getCommand(command.toLowerCase());
        if (cmd != null) {
            cmd.run(arguments.toArray(String[]::new));
        } else {
            switch (command) {
                case "bye":
                    prompter.buildGoodbye().printToConsole();
                    System.exit(0);
                    break;
                case "mark":
                    int index = Integer.parseInt(arguments.get(0));
                    markTaskDone(index);
                    break;
                case "unmark":
                    int taskNum = Integer.parseInt(arguments.get(0));
                    unmarkTaskDone(taskNum);
                    break;
                case "todo":
                    addTodo(arguments);
                    break;
                case "deadline":
                    addDeadline(arguments);
                    break;
                case "event":
                    addEvent(arguments);
                    break;
                default:
                    System.out.println(BREAK);
                    System.out.println("Uh-oh, I did not understand what you are trying to do.");
                    System.out.println(BREAK);
                    break;
            }
        }
    }

    /**
     * Prompts the user for input (in the form of commands)
     * This function will repeatedly call itself until a
     * terminating command (e.g. bye) is invoked.
     */
    public void promptLoop() {
        String input = scanner.nextLine();

        processInput(input);
        promptLoop();
    }

    /**
     * Starts the chatbot, load all the necessary
     * data and prepares the chatbot for operation.
     */
    public void start() {
        tasks.loadTasks();
        promptLoop();
    }

    /**
     * Starts the console chatbot routine for text-based usage
     */
    public void promptConsole() {
        prompter.buildLogo().printToConsole();
        prompter.buildGreeting().printToConsole();
        promptLoop();
    }

    public static void main(String[] args) {
        Alexer alexer = new Alexer();
        alexer.start();
        alexer.promptConsole();
    }
}
