import java.util.ArrayList;
import java.util.Scanner;

public class Botty {
    private static final String logo = " ________  ________  _________  _________    ___    ___                 \n" +
            "|\\   __  \\|\\   __  \\|\\___   ___\\\\___   ___\\ |\\  \\  /  /|                \n" +
            "\\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_\\|___ \\  \\_| \\ \\  \\/  / /                \n" +
            " \\ \\   __  \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\   \\ \\    / /                 \n" +
            "  \\ \\  \\|\\  \\ \\  \\\\\\  \\   \\ \\  \\     \\ \\  \\   \\/  /  /                  \n" +
            "   \\ \\_______\\ \\_______\\   \\ \\__\\     \\ \\__\\__/  / /                    \n" +
            "    \\|_______|\\|_______|    \\|__|      \\|__|\\___/ /                     \n" +
            "                                           \\|___|/                      \n" +
            "                                                                        \n" +
            " _________  ___  ___  _______           ________  ________  _________   \n" +
            "|\\___   ___\\\\  \\|\\  \\|\\  ___ \\         |\\   __  \\|\\   __  \\|\\___   ___\\ \n" +
            "\\|___ \\  \\_\\ \\  \\\\\\  \\ \\   __/|        \\ \\  \\|\\ /\\ \\  \\|\\  \\|___ \\  \\_| \n" +
            "     \\ \\  \\ \\ \\   __  \\ \\  \\_|/__       \\ \\   __  \\ \\  \\\\\\  \\   \\ \\  \\  \n" +
            "      \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_|\\ \\       \\ \\  \\|\\  \\ \\  \\\\\\  \\   \\ \\  \\ \n" +
            "       \\ \\__\\ \\ \\__\\ \\__\\ \\_______\\       \\ \\_______\\ \\_______\\   \\ \\__\\\n" +
            "        \\|__|  \\|__|\\|__|\\|_______|        \\|_______|\\|_______|    \\|__|";

    private static final String bottySymbol = "Botty: ";
    private static final String bottyIndentation = "       ";
    private static ArrayList<Task> taskList;

    public static void main(String[] args) {
        System.out.println(logo);
        System.out.println();

        reply("Hello, I am Botty the Bot, how may I be of service today?");

        Scanner inputScanner = new Scanner(System.in);

        taskList = new ArrayList<>(100);

        boolean exitFlag = false;

        while (!exitFlag) {
            try {
                System.out.println();

                String userInput = inputScanner.nextLine();

                String[] splitInput = userInput.trim().split(" ", 2);
                String command = splitInput[0].toLowerCase();
                String argument = splitInput.length > 1 ? splitInput[1] : null;

                switch (command) {
                    case "bye":
                        exitFlag = true;
                        break;
                    case "list":
                        handleList();
                        break;
                    case "mark":
                        handleMark(argument);
                        break;
                    case "unmark":
                        handleUnmark(argument);
                        break;
                    case "todo":
                        handleTodo(argument);
                        break;
                    case "deadline":
                        handleDeadline(argument);
                        break;
                    case "event":
                        handleEvent(argument);
                        break;
                    case "delete":
                        handleDelete(argument);
                        break;
                    default:
                        throw new BottyException("I'm sorry, that is not a command I am familiar with.");
                }
            } catch (BottyException exception) {
                reply(exception.getMessage());
            }
        }

        inputScanner.close();
        reply("Thank you for your continued patronage. Goodbye!");
    }
    private static void addToTaskList(Task task) {
        taskList.add(task);

        reply("I have added the following task to the list!",
                task.toString(),
                "You now have " + taskList.size() + " tasks.");

    }

    // Adapted from https://stackoverflow.com/a/39402538
    private static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    private static void setTaskCompletion(boolean completion, int taskIndex) throws BottyException {
        if (taskList.isEmpty()) {
            throw new BottyException("Your list is empty! Add a task with the todo, deadline or event command.");
        } else if (taskIndex < 0 || taskIndex > taskList.size() - 1) {
            throw new BottyException("I don't see a task with that number! Try a number from 1 to " +
                    taskList.size());
        }
        Task task = taskList.get(taskIndex);
        task.setCompleted(completion);
        reply(completion
                ? "Congrats on completing that! Let me just mark that as done for you."
                : "It's okay, we can get that done later. I'll mark that as undone for you.",
                task.toString());
    }
    private static void reply(String... strings) {
        System.out.println(bottySymbol + strings[0]);
        for (int i = 1; i < strings.length; i++) {
            System.out.println(bottyIndentation + strings[i]);
        }
    }

    private static void handleList() {
        if (taskList.isEmpty()) {
            reply("Your list is empty! Add a task with the todo, deadline or event command.");
        } else {
            String[] content = new String[taskList.size() + 1];
            content[0] = "Here you go!";

            for (int i = 1; i < taskList.size() + 1; i++) {
                content[i] = i + ". " + taskList.get(i - 1);
            }

            reply(content);
        }
    }
    private static void handleMark(String argument) throws BottyException {
        if (argument == null || !isNumber(argument)) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to mark with its number!");
        }
        setTaskCompletion(true, Integer.parseInt(argument) - 1);
    }
    private static void handleUnmark(String argument) throws BottyException {
        if (argument == null || !isNumber(argument)) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to unmark with its number!");
        }
        setTaskCompletion(false, Integer.parseInt(argument) - 1);
    }
    private static void handleTodo(String argument) throws BottyException {
        Todo todo = Todo.generateFromString(argument);
        if (todo == null) {
            throw new BottyException("I am unable to add that todo! Please ensure that the description is not blank");
        }
        addToTaskList(todo);
    }
    private static void handleDeadline(String argument) throws BottyException {
        Deadline deadline = Deadline.generateFromString(argument);
        if (deadline == null) {
            throw new BottyException("I am unable to add that deadline! Please provide details " +
                    "in the following format: [description] /by [deadline]");
        }
        addToTaskList(deadline);
    }
    private static void handleEvent(String argument) throws BottyException {
        Event event = Event.generateFromString(argument);
        if (event == null) {
            throw new BottyException("I am unable to add that event! Please provide details in " +
                    "the following format: [description] /from [start] /to [end]");
        }
        addToTaskList(event);
    }
    private static void handleDelete(String argument) throws BottyException {
        if (argument == null || !isNumber(argument)) {
            throw new BottyException("I don't quite know what you want me to do. " +
                    "Do indicate which task to mark with its number!");
        }
        int taskIndex = Integer.parseInt(argument) - 1;
        if (taskList.isEmpty()) {
            throw new BottyException("Your list is empty! Add a task with the todo, deadline or event command.");
        } else if (taskIndex < 0 || taskIndex > taskList.size() - 1) {
            throw new BottyException("I don't see a task with that number! Try a number from 1 to " +
                    taskList.size());
        }
        Task task = taskList.remove(taskIndex);
        reply("Got it! I have removed the following task:",
                task.toString(),
                "You have " + taskList.size() + " tasks left!");
    }
}
